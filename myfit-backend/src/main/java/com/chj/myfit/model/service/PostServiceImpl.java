package com.chj.myfit.model.service;

import com.chj.myfit.model.dao.PostDao;
import com.chj.myfit.model.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

	private final PostDao postDao;
	private final ResourceLoader resourceLoader;

	@Autowired
	public PostServiceImpl(PostDao postDao, ResourceLoader resourceLoader) {
		this.postDao = postDao;
		this.resourceLoader = resourceLoader;
	}

	@Override
	public List<Post> selectAll() {
		return postDao.selectAll();
	}

	@Override
	public List<Post> searchByMemberId(int memberId) {
		return postDao.searchByMemberId(memberId);
	}

	@Override
	public List<Post> searchByVideoId(int videoId) {
		return postDao.searchByVideoId(videoId);
	}

	@Override
	public int createPost(List<MultipartFile> multipartFiles, Post post) {
		int result = 0;
		try {
			if (multipartFiles != null && !multipartFiles.isEmpty()) {
				for (int i = 0; i < multipartFiles.size(); i++) {
					MultipartFile multipartFile = multipartFiles.get(i);
					if (multipartFile != null && multipartFile.getSize() > 0) {
						// 파일 확장자 명 추가하기
						StringTokenizer st = new StringTokenizer(multipartFile.getOriginalFilename(), ".");
						String str = null;
						while(st.hasMoreTokens()) {
							str = st.nextToken();
						}
						String fileId = UUID.randomUUID().toString() + "." + str;

						Resource resource = resourceLoader.getResource("classpath:/static/img");
						System.out.println(resource.getFile().getAbsolutePath());
						multipartFile.transferTo(new File(resource.getFile(), fileId));
						// System.out.println("이미지 주입 테스트");

						if (i == 0) {
							post.setImgSrc1(fileId);
						} else if (i == 1) {
							post.setImgSrc2(fileId);
						} else if (i == 2) {
							post.setImgSrc3(fileId);
						}
						
					}
				}
				result = postDao.insertFile(post);
			}else {
				result = postDao.insertPost(post);
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatePost(List<MultipartFile> multipartFiles, Post post) {
		// 이미지 자리에 남은 자리 확인
		List<Integer> imgList = new ArrayList<>();
		if(post.getImgSrc1()==null) {
			imgList.add(0);
		}
		if(post.getImgSrc2()==null) {
			imgList.add(1);
		}
		if(post.getImgSrc3()==null) {
			imgList.add(2);
		}
		
		try {
			if (multipartFiles != null && !multipartFiles.isEmpty() && imgList.size()!=0) {
				int less = Math.min(imgList.size(), multipartFiles.size());
				for (int i = 0; i < less; i++) {
					MultipartFile multipartFile = multipartFiles.get(i);
					if (multipartFile != null && multipartFile.getSize() > 0) {
						// 파일 확장자 명 추가하기
						StringTokenizer st = new StringTokenizer(multipartFile.getOriginalFilename(), ".");
						String str = null;
						while(st.hasMoreTokens()) {
							str = st.nextToken();
						}
						String fileId = UUID.randomUUID().toString() + "." + str;

						Resource resource = resourceLoader.getResource("classpath:/static/img");
						// System.out.println(resource.getFile().getAbsolutePath());
						multipartFile.transferTo(new File(resource.getFile(), fileId));
						// System.out.println("이미지 주입 테스트");

						if (imgList.get(i)==0) {
							post.setImgSrc1(fileId);
						} else if (imgList.get(i) == 1) {
							post.setImgSrc2(fileId);
						} else if (imgList.get(i) == 2) {
							post.setImgSrc3(fileId);
						}
						
					}
				}
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return postDao.updatePost(post);
	}

	@Override
	public int deletePost(int id) {
		return postDao.deletePost(id);
	}

	@Override
	public Post searchById(int id) {
		// 상세조회 시 좋아요 정보를 함께 담아 보내주자!
		return postDao.searchById(id);
	}

}
