package com.study.bookspace.club.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/club")
public class ClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	//북클럽 정보
	@GetMapping("/clubInfo")
	public String clubInfo(Model model, SubMenuVO subMenuVO) {
		//북클럽 목록 조회
		model.addAttribute("clubList", clubService.getClubList());
		
		return "content/club/club_info";
	}
	
	//북 클럽 생성화면
	@GetMapping("/regClubForm")
	public String regClubForm(SubMenuVO subMenuVO) {
		return "content/club/reg_club_form";
	}
	
	//북클럽명 중복체크
	@ResponseBody
	@PostMapping("/isDuplicateClubNameAjax")
	public boolean isDuplicateClubNameAjax(String clubName) {
		System.out.println(clubName);
		return clubService.isDuplicateClubName(clubName);
	}
	
	//북 클럽 생성
	@PostMapping("/regClub")
	public String regClub(BookClubVO bookClubVO, BookClubImageVO bookClubImageVO, MultipartFile clubImg, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		bookClubVO.setMemId(memId);
		
		// -- 파일 첨부 -- //
		BookClubImageVO attachedClubImageVO = UploadUtil.uploadFileClub(clubImg);
		
		// -- 클럽 등록 -- //
		//등록될 클럽코드 조회
		String clubCode = clubService.getNextClubCode();
		bookClubVO.setClubCode(clubCode);
		
		// -- 클럽 이미지 등록 -- //
		bookClubImageVO.setClubCode(clubCode);
		bookClubImageVO.setBcOriginFileName(attachedClubImageVO.getBcOriginFileName());
		bookClubImageVO.setBcAttachedFileName(attachedClubImageVO.getBcAttachedFileName());
		//bookClubVO.setBookClubImageVO(attachedClubImageVO);
		
		clubService.regClub(bookClubVO);
		clubService.insertImg(bookClubImageVO);
		
		return "redirect:/club/clubInfo";
	}
	
	//북클럽 상세페이지
	@GetMapping("/clubDetail")
	public String clubDetail(Model model, String clubCode) {
		//클럽 상세 조회
		model.addAttribute("club", clubService.getClubDetail(clubCode));
		
		return "content/club/club_detail";
	}
	
	//회원 북클립 가입
	@PostMapping("/joinClubAjax")
	public String joinClubAjax(BookClubMemberVO bookClubMemberVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		bookClubMemberVO.setMemId(memId);
		
		clubService.joinClub(bookClubMemberVO);
		
		return "redirect:/club/clubInfo";
	}
	
	//북클럽 커뮤니티 페이지
	@GetMapping("/community")
	public String community(SubMenuVO subMenuVO, Model model, String clubCode) {
		
		model.addAttribute("boardList", clubService.getBoardList(clubCode));
		
		return "content/club/community";
	}
	
	//글 작성 페이지 이동
	@GetMapping("/regBoard")
	public String regBoardForm(CommunityVO communityVO) {
		
		
		return "content/club/board_write";
	}
	
	//글 작성
	@PostMapping("/regBoard")
	public String regBoard(CommunityVO communityVO, Authentication authentication, String clubCode) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		communityVO.setBoardWriter(memId);
		
		clubService.regBoard(communityVO);
		
		return "redirect:/club/community";
	}
	
	//게시글 상세페이지 이동
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, String boardNum) {
		
		model.addAttribute("board", clubService.getBoardDetail(boardNum));
		model.addAttribute("replyList", clubService.getReplyList(boardNum));
		
		return "content/club/board_detail";
	}
	
	//게시글 수정페이지 이동
	@GetMapping("/updateBoard")
	public String updateBoardForm(Model model, String boardNum) {
		
		model.addAttribute("board", clubService.getBoardDetail(boardNum));
		
		return "content/club/update_form";
	}
	
	//게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(CommunityVO communityVO) {
		
		clubService.updateBoard(communityVO);
		
		return "redirect:/club/boardDetail";
	}	
	
	//게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(String boardNum) {
		
		clubService.deleteBoard(boardNum);
		
		return "redirect:/club/community";
	}
	
	//게시글 댓글 작성
	@PostMapping("/regReply")
	public String regReply(CommunityReplyVO communityReplyVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		communityReplyVO.setReplyWriter(memId);
		
		clubService.regReply(communityReplyVO);
		
		return "redirect:/club/boardDetail";
	}
	
	//북클럽 관리 페이지
	@GetMapping("/clubManage")
	public String clubManage() {
		return "content/club/club_manage";
	}
	
	@ResponseBody
	@PostMapping("/acceptMemberAjax")
	public void acceptMemberAjax(String memId) {
		clubService.acceptMember(memId);
	
	}
	
}
