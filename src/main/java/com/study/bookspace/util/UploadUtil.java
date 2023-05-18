package com.study.bookspace.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.club.vo.BookClubImageVO;


public class UploadUtil {
//	단일 파일 업로드 메소드
	public static ImgVO uploadFile(MultipartFile img) {
		
		ImgVO imgVO = null;
		
		if(!img.isEmpty()) {
			
			imgVO = new ImgVO(); 
			
			String originFileName = img.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String attachedFileName = uuid + extension;

			try {
				File file = new File(ConstVariable.BOOK_UPLOAD_PATH + attachedFileName);
				img.transferTo(file);
				
				imgVO.setOriginFileName(originFileName);
				imgVO.setAttachedFileName(attachedFileName);
				imgVO.setIsMainImg("Y");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return imgVO;
	}
	
//	다중 파일 업로드 메소드
	public static List<ImgVO> multiFileUpload(MultipartFile[] imges) {
	
//		첨부된 파일정보를 다 담을 수 있는 통
		List<ImgVO> result = new ArrayList<>();
		
		
		for(MultipartFile img : imges) {
			ImgVO vo = uploadFile(img);
			vo.setIsMainImg("N");
			result.add(vo);
			
		
		}
		return result;
	}
	
	//클럽 이미지 단일 파일 업로드 메소드
	public static BookClubImageVO uploadFileClub(MultipartFile clubImg) {
		
		BookClubImageVO bookClubImageVO = null;
		
		if(!clubImg.isEmpty()) {
			
			bookClubImageVO = new BookClubImageVO(); //첨부가 되면 bookClubImgVo를 만든다.
			
			String originFileName = clubImg.getOriginalFilename();
			String uuid = UUID.randomUUID().toString(); // UUID.randomUUID : static이당
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String attachedFileName = uuid + extension;
			
			try {
				File file = new File(ConstVariable.CLUB_UPLOAD_PATH + attachedFileName);
				clubImg.transferTo(file);
				
				//첨부가 제대로 되야 필요하므로 try문 안에 작성했다.
				bookClubImageVO.setBcOriginFileName(originFileName);
				bookClubImageVO.setBcAttachedFileName(attachedFileName);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookClubImageVO;
	}
	
	
	
}
