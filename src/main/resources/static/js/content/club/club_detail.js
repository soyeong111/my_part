
//클럽 가입 신청 버튼 클릭 시
function joinClub(memId, clubCode){
	
	if(memId == 'anonymousUser'){
		const result = confirm('로그인 후 가입신청이 가능합니다.\n로그인 하시겠습니까?');
		
		if(result){
			location.href = `/member/loginForm`;
		}
		return ;
	}
	
	//북클럽 가입 신청
	joinClubAjax(clubCode);
	
}

//클럽 가입 Ajax
function joinClubAjax(clubCode){
	//ajax start
	$.ajax({
	   url: '/club/joinClubAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'clubCode':clubCode}, //필요한 데이터
	   success: function(result) {
	      alert('가입 신청이 완료되었습니다.\n클럽장의 승인을 기다려주세요.');
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
}


//클럽 삭제버튼 클릭시
function deleteClub(clubCode){
	const result = confirm('이 북클럽을 삭제하시겠습니까?');
	
	if(result){
		location.href=`/club/deleteClub?clubCode=${clubCode}`;
		
	}
}

//커뮤니티 버튼 클릭 시 - 클럽 회원만 조회 가능
function memberOnly(clubCode, memId){
	
	//ajax start
	$.ajax({
	   url: '/club/isClubMemberAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'clubCode':clubCode, 'memId':memId}, //필요한 데이터
	   success: function(result) {
	      alert('ajax 통신 성공');
	      alert(result);
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}





