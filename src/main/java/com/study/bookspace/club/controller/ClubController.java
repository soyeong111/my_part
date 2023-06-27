package com.study.bookspace.club.controller;

import java.io.File;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.alram.service.AlramService;
import com.study.bookspace.alram.vo.AlramVO;
import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.club.vo.BookClubImageVO;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.club.vo.BookClubVO;
import com.study.bookspace.club.vo.CommunityImageVO;
import com.study.bookspace.club.vo.CommunityReplyVO;
import com.study.bookspace.club.vo.CommunityVO;
import com.study.bookspace.info.service.AnswerService;
import com.study.bookspace.info.vo.NoticeVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.util.ConstVariable;
import com.study.bookspace.util.DateUtil;
import com.study.bookspace.util.PageVO;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/club")
public class ClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	@Resource(name = "alramService")
	private AlramService alramService;
	
	@Resource(name = "bookService")
	private BookService bookService;
	
	//메인 테스트
	@GetMapping("/maintest")
	public String maintest(SubMenuVO subMenuVO, Model model) {
		
		
		return "content/club/main_test";
	}
	
	
	//북클럽 이용안내
	@GetMapping("/clubInfo")
	public String clubInfo(SubMenuVO subMenuVO) {
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
	public String regClub(BookClubVO bookClubVO, BookClubImageVO bookClubImageVO, BookClubMemberVO bookClubMemberVO, MultipartFile clubImg, Authentication authentication, SubMenuVO subMenuVO) {
		
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
		
		// -- 클럽 멤버로 + 매니저로 -- //
		bookClubMemberVO.setClubCode(clubCode);
		bookClubMemberVO.setMemId(memId);
		bookClubMemberVO.setClubRole("MANAGER");
		bookClubMemberVO.setClubMemStatus(2);
		
		clubService.regClub(bookClubVO, bookClubImageVO, bookClubMemberVO);
		
		System.out.println(bookClubVO.getClubMemCnt());
		
		return "redirect:/club/club?mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//북클럽 상세페이지
	@GetMapping("/clubDetail")
	public String clubDetail(Model model, String clubCode, SubMenuVO subMenuVO) {
		//클럽 상세 조회
		BookClubVO bookClubVO = clubService.getClubDetail(clubCode);
		model.addAttribute("club", bookClubVO);
		if(bookClubVO.getThisBookCode() != null) {
			model.addAttribute("thisBook", clubService.getThisBookDetail(bookClubVO.getThisBookCode()));
		}
		//클럽 활동 중인 회원수
		model.addAttribute("clubMemCnt", clubService.countMemCnt(clubCode));
		//클럽 멤버 리스트 조회
		model.addAttribute("memList", clubService.getMemListByClub(clubCode));
		//이번달
		model.addAttribute("thisMonth", DateUtil.getMonth());
		//독서 랭킹
		model.addAttribute("rankingList", clubService.getRankingByClub(clubCode));
		//커뮤니티 활동 랭킹
		model.addAttribute("comRankgingList", clubService.getCommunityRankByClub(clubCode));
		
		return "content/club/club_detail";
	}
	
	//이달의 책 선택 클릭 시
	@ResponseBody
	@PostMapping("/bookChoiceAjax")
	public List<BookVO> bookChoiceAjax(BookVO bookVO, Model model) {
		
		return bookService.getBookListForUser(bookVO);
	}
	
	//이달의 책 업데이트
	@ResponseBody
	@PostMapping("/updateClubBookAjax")
	public void updateClubBookAjax(BookClubVO bookClubVO) {
		
		System.out.println("@@@" + bookClubVO);
		
		clubService.updateClubBookCode(bookClubVO);
	}
	
	
	//해당 클럽 가입 이력
	@ResponseBody
	@PostMapping("/alreadyApplyAjax")
	public boolean alreadyApplyAjax(BookClubMemberVO bookClubMemberVO) {
		return clubService.alreadyApply(bookClubMemberVO);
	}
	
	//회원 북클립 가입
	@PostMapping("/joinClubAjax")
	public String joinClubAjax(BookClubMemberVO bookClubMemberVO, Authentication authentication, String clubCode, AlramVO alramVO) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		bookClubMemberVO.setMemId(memId);
		
		clubService.joinClub(bookClubMemberVO);
		
		String bossId = clubService.getClubBossId(clubCode);
		
		alramVO.setMemId(bossId);
		alramVO.setAContent("소유한 북클럽에 가입을 신청한 회원이 있습니다.");
		alramVO.setSection(2);
		
		alramService.insertAlram(alramVO);
		
		return "redirect:/club/club";
	}
	
	//북클럽 수정 페이지
	@GetMapping("/updateClubForm")
	public String updateClubForm(Model model, String clubCode, SubMenuVO subMenuVO) {
		model.addAttribute("club", clubService.getClubDetail(clubCode));
		
		return "content/club/update_club_form";
	}
	
	//북클럽 수정
	@PostMapping("/updateClub")
	public String updateClub(BookClubVO bookClubVO, SubMenuVO subMenuVO, BookClubImageVO bookClubImageVO, MultipartFile clubImg) {
		
		// -- 파일 첨부 -- //
		BookClubImageVO attachedClubImageVO = UploadUtil.uploadFileClub(clubImg);
		String oldFile = bookClubImageVO.getClubImgCode();
		String clubImgName = clubService.getClubImageName(bookClubImageVO.getClubCode());
		
		if(attachedClubImageVO != null) {
			clubService.deleteClubImg(oldFile);
			
			bookClubImageVO.setClubCode(bookClubImageVO.getClubCode());
			bookClubImageVO.setBcOriginFileName(attachedClubImageVO.getBcOriginFileName());
			bookClubImageVO.setBcAttachedFileName(attachedClubImageVO.getBcAttachedFileName());
			clubService.insertImg(bookClubImageVO);
			
			File file = new File(ConstVariable.CLUB_UPLOAD_PATH + clubImgName);
			file.delete();
		}
		
		clubService.updateClub(bookClubVO);
		
		return "redirect:/club/clubDetail?clubCode=" 
			+ bookClubVO.getClubCode() + "&mainMenuCode=" + subMenuVO.getMainMenuCode() 
			+ "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//북클럽 삭제
	@GetMapping("/deleteClub")
	public String deleteClub(String clubCode, SubMenuVO subMenuVO, AlramVO alramVO) {
		String clubImgName = clubService.getClubImageName(clubCode);
		
		File file = new File(ConstVariable.CLUB_UPLOAD_PATH + clubImgName);
		file.delete();		
		
		clubService.deleteClub(clubCode);
		
		return "redirect:/club/club?mainMenuCode=" 
			+ subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//북클럽 관리 페이지
	@GetMapping("/clubManage")
	public String clubManage(String clubCode, Model model, BookClubMemberVO bookClubMemberVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		bookClubMemberVO.setMemId(memId);
		//System.out.println(bookClubMemberVO);
		
		//북클럽 회원 목록 조회
		model.addAttribute("memberList", clubService.getClubMemberList(clubCode));
		//북클럽 가입 신청 회원 목록 조회 (승인 전)
		model.addAttribute("applyList", clubService.getApplyMemberList(clubCode));
		//내 북클럽 상태 조회
		model.addAttribute("myClubDetail", clubService.getMyClubDetail(memId));
		
		//클럽장 아이디 조회
		model.addAttribute("bossId", clubService.getClubBossId(clubCode));
		
		model.addAttribute("clubCode", clubCode);
		
		return "content/club/club_manage";
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
		int totalDataCnt = clubService.getBoardCnt(communityVO);
		
		//전체 데이터 수 세팅
		communityVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		communityVO.setPageInfo();
		
		model.addAttribute("clubCode", communityVO.getClubCode());
		model.addAttribute("boardList", clubService.getBoardList(communityVO));
		model.addAttribute("noticeList", clubService.getNoticeList(communityVO.getClubCode()));
		
		return "content/club/community";
	}
	
	//글 작성 페이지 이동
	@GetMapping("/regBoardForm")
	public String regBoardForm(String clubCode, Model model, SubMenuVO subMenuVO) {
		
		model.addAttribute("clubCode", clubCode);
		model.addAttribute("bossId", clubService.getClubBossId(clubCode));
		return "content/club/board_write";
	}
	
	//글 작성
	@PostMapping("/regBoard")
	public String regBoard(CommunityVO communityVO, Authentication authentication, SubMenuVO subMenuVO, MultipartFile communityImg ,CommunityImageVO communityImageVO) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		communityVO.setBoardWriter(memId);
		
		//등록될 게시글번호 조회
		String boardNum = clubService.getNextBoardNum();
		communityVO.setBoardNum(boardNum);
		
		// -- 게시글 등록 -- //
		clubService.regBoard(communityVO);
		
		// -- 파일 첨부 -- //
		CommunityImageVO attachedCommunityImageVO = UploadUtil.communityUploadFile(communityImg);
		if(attachedCommunityImageVO != null) {
			communityImageVO.setBoardNum(boardNum);
			communityImageVO.setBcOriginFileName(attachedCommunityImageVO.getBcOriginFileName());
			communityImageVO.setBcAttachedFileName(attachedCommunityImageVO.getBcAttachedFileName());
			clubService.insertCommunityImg(communityImageVO);
		}
		
		
		
		return "redirect:/club/community?clubCode=" + communityVO.getClubCode() 
		+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	
	//게시글 상세페이지 이동
	@GetMapping("/boardDetail")
	public String boardDetail(Model model, CommunityVO communityVO, CommunityReplyVO communityReplyVO, SubMenuVO subMenuVO) {
		clubService.updateReadCnt(communityVO.getBoardNum());
		
		model.addAttribute("board", clubService.getBoardDetail(communityVO.getBoardNum()));
		model.addAttribute("replyList", clubService.getReplyList(communityReplyVO.getBoardNum()));
		
		return "content/club/board_detail";
	}
	
	//게시글 수정페이지 이동
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(Model model, String boardNum, SubMenuVO subMenuVO) {
		
		model.addAttribute("board", clubService.getBoardDetail(boardNum));
		
		return "content/club/update_form";
	}
	
	//게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(CommunityVO communityVO, SubMenuVO subMenuVO, MultipartFile communityImg, CommunityImageVO communityImageVO) {

		
		String oldFile = communityImageVO.getComImgCode();
		CommunityImageVO attachedCommunityImageVO = UploadUtil.communityUploadFile(communityImg);
		String communityImgName = clubService.getCommunityImageName(communityImageVO.getBoardNum());
		if(attachedCommunityImageVO != null) {
			clubService.deleteComImg(oldFile);
			
			communityImageVO.setBoardNum(communityImageVO.getBoardNum());
			communityImageVO.setBcOriginFileName(attachedCommunityImageVO.getBcOriginFileName());
			communityImageVO.setBcAttachedFileName(attachedCommunityImageVO.getBcAttachedFileName());
			clubService.insertCommunityImg(communityImageVO);
			
			File file = new File(ConstVariable.COMMUNITY_UPLOAD_PATH + communityImgName);
			file.delete();
		}
		
		clubService.updateBoard(communityVO);
		
		return "redirect:/club/boardDetail?clubCode=" 
				+ communityVO.getClubCode() + "&boardNum=" + communityVO.getBoardNum()
				+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}	
	
	//게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(String boardNum, String clubCode, SubMenuVO subMenuVO) {
		
		String communityImgName = clubService.getCommunityImageName(boardNum);
		
		System.out.println(ConstVariable.COMMUNITY_UPLOAD_PATH + communityImgName);
		File file = new File(ConstVariable.COMMUNITY_UPLOAD_PATH + communityImgName);
		file.delete();	
		
		
		clubService.deleteBoard(boardNum);
		
		return "redirect:/club/community?clubCode=" + clubCode 
				+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//게시글 댓글 작성
	@PostMapping("/regReply")
	public String regReply(CommunityReplyVO communityReplyVO, Authentication authentication, SubMenuVO subMenuVO) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		communityReplyVO.setReplyWriter(memId);
		System.out.println("@@@@@@@@" + communityReplyVO);
		clubService.regReply(communityReplyVO);
		
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode()
				+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//게시글 댓글 수정
	@PostMapping("/updateReply")
	public String updateReply(CommunityReplyVO communityReplyVO, SubMenuVO subMenuVO) {
		
		clubService.updateReply(communityReplyVO);
		
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode()
				+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	//게시글 댓글 삭제
	@GetMapping("/deleteReply")
	public String deleteReply(CommunityReplyVO communityReplyVO, SubMenuVO subMenuVO) {
		
		clubService.deleteReply(communityReplyVO.getReplyNum());
		return "redirect:/club/boardDetail?boardNum=" 
				+ communityReplyVO.getBoardNum() + "&clubCode=" + communityReplyVO.getClubCode()
				+ "&mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	
	//이달의 책 등록 페이지
	@GetMapping("/bookOfThisMonth")
	public String bookOfThisMonth(SubMenuVO seMenuVO) {
		return "content/club/book_of_this_month";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
