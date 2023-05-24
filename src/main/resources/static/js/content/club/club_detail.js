
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










