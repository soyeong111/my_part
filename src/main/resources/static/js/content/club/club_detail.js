
//클럽 가입 신청 버튼 클릭 시
function joinClub(){
	
	//ajax start
	$.ajax({
	   url: '/club/joinClubAjax', //요청경로
	   type: 'post',
	   async : true, 
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {}, //필요한 데이터
	   success: function(result) {
	      alert('ajax 통신 성공');
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
	if(memId == ''){
		alert('로그인 후 가입신청이 가능합니다.')		
	}
	
	alert('가입신청이 완료되었습니다.\n클럽장의 승인 후 가입이 완료됩니다.');
	
}