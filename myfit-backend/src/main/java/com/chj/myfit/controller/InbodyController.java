package com.chj.myfit.controller;

import com.chj.myfit.model.dto.Inbody;
import com.chj.myfit.model.service.InbodyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "인바디 관리", description = "인바디 정보를 관리하는 컨트롤러")
@RestController
@RequestMapping("/api-inbody")
public class InbodyController {

	private final InbodyService inbodyService;

	@Autowired
	public InbodyController(InbodyService inbodyService) {
		this.inbodyService = inbodyService;
	}

	@Operation(summary = "이미지 파일 읽기 메서드")
	@PostMapping("/read")
	public ResponseEntity<?> read(@RequestParam("file") MultipartFile multipartFile) {
		Inbody result = inbodyService.read(multipartFile);
		return new ResponseEntity<Inbody>(result, HttpStatus.OK);
	}

	@Operation(summary = "인바디 등록 메서드")
	@PostMapping("/inbody")
	public ResponseEntity<?> createInbody(@RequestBody Inbody inbody) {
		int result = inbodyService.registInbody(inbody);
		return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "인바디 상세 메서드")
	@GetMapping("/inbody/{id}")
	public ResponseEntity<?> searchById(@PathVariable("id") int id) {
		Inbody result = inbodyService.searchById(id);
		if (result == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Inbody>(result, HttpStatus.OK);
	}

	@Operation(summary = "특정 회원 인바디 목록 조회 메서드")
	@GetMapping("/inbody/{id}/list")
	public ResponseEntity<?> searchByMemberId(@PathVariable("id") int id) {
		List<Inbody> list = inbodyService.searchByMemberId(id);
		if (list == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		list.sort(new Comparator<Inbody>() {
			@Override
			public int compare(Inbody o1, Inbody o2) {
				int daysBetween = (int) ChronoUnit.DAYS.between(o1.getMeasurementDate(), o2.getMeasurementDate());
				if (daysBetween == 0)
					return o1.getInbodyId() - o2.getInbodyId();
				return daysBetween;
			}
		});
		return new ResponseEntity<List<Inbody>>(list, HttpStatus.OK);
	}

	@Operation(summary = "인바디 전체 조회 메서드")
	@GetMapping("inbody/list")
	public ResponseEntity<?> list() {
		List<Inbody> list = inbodyService.selectAll();
		if (list == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Inbody>>(list, HttpStatus.OK);
	}

	@Operation(summary = "인바디 솔루션 메서드")
	@GetMapping("inbody/solution/{memberId}")
	public ResponseEntity<?> inbodySolution(@PathVariable("memberId") int memberId) {
		inbodyService.deletePreferredExerciseAreaByMemberId(memberId);
		Inbody inbody = inbodyService.searchByMemberId(memberId).get(0);
		if (inbody == null)	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);

		Map<String, String> sol = inbodyService.inbodySolution(inbody);

		for (String key : sol.keySet()) {
			String exerciseArea = sol.get(key);
			if(exerciseArea == null) continue;
			map.put("exerciseArea", exerciseArea);
			int result = inbodyService.insertPreferredExerciseArea(map);
			if (result != 1)
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Operation(summary = "인바디 수정 메서드")
	@PutMapping("/inbody/{id}")
	public ResponseEntity<?> editInbody(@PathVariable("id") int id, @RequestBody Inbody inbody) {
		inbody.setInbodyId(id);
		int result = inbodyService.editInbody(inbody);
		return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@Operation(summary = "인바디 삭제 메서드")
	@DeleteMapping("/inbody/{id}")
	public ResponseEntity<?> removeInbody(@PathVariable("id") int id) {
		int result = inbodyService.removeInbody(id);
		return new ResponseEntity<Integer>(result, result == 1 ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST);
	}
	
	@Operation(summary = "특정 멤버 선호 운동 부위 조회 메서드")
	@GetMapping("/pref-ex-area/{memberId}")
	public ResponseEntity<?> findPreExArea(@PathVariable("memberId") int memberId){
		List<String> result = inbodyService.findPreferredExerciseAreaByMemberId(memberId);
		if(result == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	}
}
