package com.chj.myfit.model.service;

import com.chj.myfit.model.dao.MemberDao;
import com.chj.myfit.model.dto.Diet;
import com.chj.myfit.model.dto.Member;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MemberServiceImpl implements MemberService {
	private final MemberDao memberDao;

	private static String API_KEY = "";
	private static String GPT_URL = "";

    @Autowired
    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public List<Member> getMemberList() {
        return memberDao.selectAll();
    }

    @Override
    public void signup(Member member) {
        memberDao.insertMember(member);
    }

    @Override
    public Member login(String email, String password) {
        Map<String, String> info = new HashMap<>();
        info.put("email", email);
        info.put("password", password);
        return memberDao.selectOne(info);
    }

    @Override
    public Member readMember(int id) {
        return memberDao.searchById(id);
    }

    @Override
    public void resign(int id) {
        memberDao.deleteMember(id);
    }

    @Override
    public Member readMemberByEmail(String email) {
        return memberDao.selectByEmail(email);
    }

	@Override
	public int saveDiet(Diet diet) {
		try {
			URL url = new URL(GPT_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        
	        String subject = diet.getPurpose();
	        String allergie = diet.getAllergie();
	        String pastDiet = diet.getPastMeal();
	        
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", "application/json");
	        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
	        connection.setDoInput(true);
	        connection.setDoOutput(true);

	        JSONObject data = new JSONObject();
	        data.put("model", "gpt-3.5-turbo");
	        data.put("temperature", 0.7);
	        
	        String msg = "아래의 정보에 맞추어 식단을 추천해줘:\r\n"
	        		+ "1. 목표: {"+subject+"}\r\n"
	        		+ "2. 알레르기 유발 성분: {"+allergie+"}\r\n"
	        		+ "3. 직전 식사: {"+pastDiet+"}\r\n"
	        		+ "\r\n"
	        		+ "이때, 식단은 알레르기 유발 성분이 없도록 하고, 아침, 점심, 저녁 각각 추천하되,"
	        		+ "아래 양식을 수정하지 않고 '' 안에 있는 문자만 채워 대괄호 안에 메뉴를 작성하고, 열량도 '' 안에 내용을 작성해서 대답주세요."
	        		+ "아침/['메뉴 1', '메뉴 2', '메뉴 3']/'열량'"
	        		+ "/점심/['메뉴 1', '메뉴 2', '메뉴 3']/'열량'"
	        		+ "/저녁/['메뉴 1', '메뉴 2', '메뉴 3']/'열량'";
	        // System.out.println(msg);
	        JSONObject message = new JSONObject();
	        message.put("role", "user");
	        message.put("content", msg);

	        JSONArray messages = new JSONArray();
	        messages.put(message);

	        data.put("messages", messages);

	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
	        bw.write(data.toString());
	        bw.flush();
	        bw.close();
	        
	        Thread.sleep(1000);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line = null;

	        while ((line = br.readLine()) != null) {
	            sb.append(line);
	        }
	        
	        //System.out.println(sb);
	        JSONObject jsonObject = new JSONObject(sb.toString());
	        JSONArray choicesArray = jsonObject.getJSONArray("choices");
	        JSONObject firstChoice = choicesArray.getJSONObject(0);
	        JSONObject messageObject = firstChoice.getJSONObject("message");
	        String messageContent = messageObject.getString("content");
	        System.out.println(messageContent);
	        String[] arr = messageContent.split("/");
	        diet.setMealTime("Breakfast");
	        diet.setFoodList(arr[1]);
	        diet.setCalories(Integer.parseInt(arr[2], 1, 4, 10));
	        int result = memberDao.insertDiet(diet);
	        if(result == 1) {
	        	diet.setMealTime("Lunch");
		        diet.setFoodList(arr[4]);
		        diet.setCalories(Integer.parseInt(arr[5], 1, 4, 10));
		        result = memberDao.insertDiet(diet);
		        if(result == 1) {
		        	diet.setMealTime("Dinner");
			        diet.setFoodList(arr[7]);
			        diet.setCalories(Integer.parseInt(arr[8], 1, 4, 10));
			        result = memberDao.insertDiet(diet);
			        return result;
		        }else {
		        	return 0;
		        }
	        }else {
	        	return 0;
	        }
		} catch (Exception e) {
			String str = "아침/['오트밀', '과일 샐러드', '녹차']/'400kcal'/점심/['닭가슴살 샐러드', '채소 스프', '요거트']/'450kcal'/저녁/['삶은 달걀', '야채 샐러드', '식빵']/'350kcal'";
			if(diet.getPurpose() == "Bulkup") {
				str = "아침/['오트밀', '과일', '달걀']/'400kcal'/점심/['닭가슴살 샐러드', '채소 스프', '견과류']/'450kcal'/저녁/['생선구이', '채소볶음', '쌀밥']/'350kcal'";				
			}else if(diet.getPurpose() == "Healthy") {
				str = "아침/['오트밀', '과일', '샐러드']/'400kcal'/점심/['닭가슴살', '콩국수', '시금치 샐러드']/'450kcal'/저녁/['참치 샐러드', '콩나물국', '쌀밥']/'350kcal'";
			}
			String[] arr = str.split("/");
	        diet.setMealTime("Breakfast");
	        diet.setFoodList(arr[1]);
	        diet.setCalories(Integer.parseInt(arr[2], 1, 4, 10));
	        int result = memberDao.insertDiet(diet);
	        if(result == 1) {
	        	diet.setMealTime("Lunch");
		        diet.setFoodList(arr[4]);
		        diet.setCalories(Integer.parseInt(arr[5], 1, 4, 10));
		        result = memberDao.insertDiet(diet);
		        if(result == 1) {
		        	diet.setMealTime("Dinner");
			        diet.setFoodList(arr[7]);
			        diet.setCalories(Integer.parseInt(arr[8], 1, 4, 10));
			        result = memberDao.insertDiet(diet);
			        return result;
		        }else {
		        	return 0;
		        }
	        }else {
	        	return 0;
	        }
		}        
	}

	@Override
	public int deleteDiet(int dietId) {
		return memberDao.deleteDiet(dietId);
	}

	@Override
	public List<Diet> getDiet() {
		return memberDao.selectAllDiet();
	}

	@Override
	public int updateMember(Member member) {
		return memberDao.updateMember(member);
	}
}
