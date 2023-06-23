package com.study.bookspace.util;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.CommunityImageVO;
import com.study.bookspace.goods.vo.GoodsImgVO;
import com.study.bookspace.goods.vo.GoodsVO;


public class UploadUtil {
	public static ImgVO uploadFile(MultipartFile img) {
        ImgVO imgVO = new ImgVO();

        if (!img.isEmpty()) {
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
				File file = new File(ConstVariable.HCLUB_UPLOAD_PATH + attachedFileName);
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
	
	
	
	
	
	
//	단일 파일 업로드- 굿즈
	public static GoodsImgVO goodsUploadFile(MultipartFile goodsImg) {
		
		GoodsImgVO goodsImgVO = null;
		
		if(!goodsImg.isEmpty()) {
			
			goodsImgVO = new GoodsImgVO();
			
			String originFileName = goodsImg.getOriginalFilename();
			String uuid = UUID.randomUUID().toString();
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String attachedFileName = uuid + extension;

			try {
				File file = new File(ConstVariable.GOODS_UPLOAD_PATH + attachedFileName);
				goodsImg.transferTo(file);
				
				goodsImgVO.setOriginFileName(originFileName);
				goodsImgVO.setAttachedFileName(attachedFileName);
				goodsImgVO.setIsMain("Y");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return goodsImgVO;
	}
	
//	다중 파일 업로드 메소드
	public static List<GoodsImgVO> goodsMultiFileUpload(MultipartFile[] imges) {
	
//		첨부된 파일정보를 다 담을 수 있는 통
		List<GoodsImgVO> result = new ArrayList<>();
		
		
		for(MultipartFile img : imges) {
			GoodsImgVO vo = goodsUploadFile(img);
			vo.setIsMain("N");
			result.add(vo);
			
		
		}
		return result;
	}
	
	
	
	
	
	//클럽 커뮤니티 게시글 이미지 단일 파일 업로드 메소드
	public static CommunityImageVO communityUploadFile(MultipartFile communityImg) {
		
		CommunityImageVO communityImageVO = null;
		
		if(!communityImg.isEmpty()) {
			
			communityImageVO = new CommunityImageVO(); //첨부가 되면 bookClubImgVo를 만든다.
			
			String originFileName = communityImg.getOriginalFilename();
			String uuid = UUID.randomUUID().toString(); // UUID.randomUUID : static이당
			String extension = originFileName.substring(originFileName.lastIndexOf("."));
			String attachedFileName = uuid + extension;
			
			try {
				File file = new File(ConstVariable.HCOMMUNITY_UPLOAD_PATH + attachedFileName);
				communityImg.transferTo(file);
				
				//첨부가 제대로 되야 필요하므로 try문 안에 작성했다.
				communityImageVO.setBcOriginFileName(originFileName);
				communityImageVO.setBcAttachedFileName(attachedFileName);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return communityImageVO;
	}


	
	
}
