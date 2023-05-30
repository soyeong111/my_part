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
import com.study.bookspace.util.PageVO;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/club")
public class ClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	//북클럽 이용안내
	@GetMapping("/clubInfo")
	public String clubInfo() {
		return "content/club/club_guide";
	}
	
	//북클럽 정보***
	@GetMapping("/club")
	public String club(Model model, SubMenuVO subMenuVO) {
		//북클럽 목록 조회
		model.addAttribute("clubList", clubService.getClubList());
		
		return "content/club/club_info";
	}
	
	//북클럽 가지고 있는지 확인
	@ResponseBody
	@PostMapping("/hasClubAjax")
	public boolean hasClubAjax(String memId) {
		return clubService.hasClub(memId);
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
		//clubService.joinClub(bookClubVO.getBookClubMemberVO());
		
		return "redirect:/club/club";
	}
	
	//북클럽 상세페이지
	@GetMapping("/clubDetail")
	public String clubDetail(Model model, String clubCode) {
		//클럽 상세 조회
		model.addAttribute("club", clubService.getClubDetail(clubCode));
		
		return "content/club/club_detail";
	}
	
	//해당 클럽 가입 이력
	@ResponseBody
	@PostMapping("/alreadyApplyAjax")
	public boolean alreadyApplyAjax(BookClubMemberVO bookClubMemberVO) {
		return clubService.alreadyApply(bookClubMemberVO);
	}
	
	//회원 북클립 가입
	@PostMapping("/joinClubAjax")
	public String joinClubAjax(BookClubMemberVO bookClubMemberVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		bookClubMemberVO.setMemId(memId);
		
		clubService.joinClub(bookClubMemberVO);
		
		return "redirect:/club/club";
	}
	
	//북클럽 수정 페이지
	@GetMapping("/updateClub")
	public String updateClubForm(Model model, String clubCode) {
		model.addAttribute("club", clubService.getClubDetail(clubCode));
		
		return "content/club/update_club_form";
	}
	
	//북클럽 수정
	@PostMapping("/updateClub")
	public String updateClub(BookClubVO bookClubVO) {
		
		clubService.updateClub(bookClubVO);
		
		return "redirect:/club/clubDetail?clubCode=" + bookClubVO.getClubCode();
	}
	
	//북클럽 삭제
	@GetMapping("/deleteClub")
	public String deleteClub(String clubCode) {
		
		clubService.deleteClub(clubCode);
		
		return "redirect:/club/club";
	}
	
	//북클럽 관리 페이지
	@GetMapping("/clubManage")
	public String clubManage(String clubCode, Model model) {
		
		//북클럽 회원 목록 조회
		model.addAttribute("memberList", clubService.getClubMemberList(clubCode));
		//북클럽 가입 신청 회원 목록 조회 (승인 전)
		model.addAttribute("applyList", clubService.getApplyMemberList(clubCode));
		
		
		return "content/club/club_manage";
	}
	
	//북클럽 회원 승인
	@ResponseBody
	@PostMapping("/acceptMemberAjax")
	public void acceptMemberAjax(String acceptCode) {
		clubService.acceptMember(acceptCode);
	}
	
	//북클럽 회원 거절/강퇴
	@ResponseBody
	@PostMapping("/refuseMemberAjax")
	public void refuseMemberAjax(String acceptCode) {
		clubService.refuseMember(acceptCode);
	}
	
	//커뮤니티 이동 (클럽 멤버만)
	@ResponseBody
	@PostMapping("/isClubMemberAjax")
	public boolean isClubMemberAjax(BookClubMemberVO bookClubMemberVO) {
		return clubService.isClubMember(bookClubMemberVO);
	}
	
	// ------------------------- 커뮤니티 -------------------------- //
	
	//RequestMapping 사용
	//북클럽 커뮤니티 페이지
	@RequestMapping("/community")
	public String community(SubMenuVO subMenuVO, Model model, CommunityVO communityVO) {
		System.out.println("@@@@@@@@@@@@@2" + communityVO);
		
		//전체 게시글 수 조회
		int totalDataCnt = clubService.getBoardCnt(communityVO.getClubCode());
		
		//전체 데이터 수 세팅
		communityVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		communityVO.setPageInfo();
		
		model.addAttribute("clubCode", communityVO.getClubCode());
		model.addAttribute("boardList", clubService.getBoardList(communityVO));
		
		System.out.println("@@@@@@@@@@" + communityVO);
		
		return "content/club/community";
	}
	
	//글 작성 페이지 이동
	@GetMapping("/regBoard")
	public String regBoardForm(String clubCode, Model model) {
		
		model.addAttribute("clubCode", clubCode);
		
		return "content/club/board_write";
	}
	
	//글 작성
	@PostMapping("/regBoard")
	public String regBoard(CommunityVO communityVO, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		communityVO.setBoardWriter(memId);
		
		clubService.regBoard(communityVO);
		
		return "redirect:/club/community?clubCode=" + communityVO.getClubCode();
	}
	
	//게시글 상세페이지 이동
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, String boardNum, CommunityVO communityVO, CommunityReplyVO communityReplyVO) {
		clubService.updateReadCnt(communityVO);
		
		model.addAttribute("board", clubService.getBoardDetail(boardNum));
		model.addAttribute("replyList", clubService.getReplyList(communityReplyVO));
		
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

		System.out.println(communityVO);
		clubService.updateBoard(communityVO);
		
		return "redirect:/club/boardDetail?clubCode=" 
				+ communityVO.getClubCode() + "&boardNum=" + communityVO.getBoardNum();
	}	
	
	//게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(String boardNum, String clubCode) {
		
		clubService.deleteBoard(boardNum);
		
		return "redirect:/club/community?clubCode=" + clubCode;
	}
	
	//게시글 댓글 작성
	@PostMapping("/regReply")
	public String regReply(CommunityReplyVO communityReplyVO, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		communityReplyVO.setReplyWriter(memId);
		System.out.println("@@@@@@@@" + communityReplyVO);
		clubService.regReply(communityReplyVO);
		
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode();
	}
	
	//게시글 댓글 수정
	@PostMapping("/updateReply")
	public String updateReply(CommunityReplyVO communityReplyVO) {
		
		clubService.updateReply(communityReplyVO);
		
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode();
	}
	
	//게시글 댓글 삭제
	@GetMapping("/deleteReply")
	public String deleteReply(CommunityReplyVO communityReplyVO) {
		
		clubService.deleteReply(communityReplyVO.getReplyNum());
		System.out.println("@@@@@@@@@@@@" + communityReplyVO);
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
