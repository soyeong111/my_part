
//북클럽 만들기 버튼 클릭 시 실행
function regClub(memId, mainMenuCode, subMenuCode){
	
	if(memId == 'anonymousUser'){
		const result = confirm('로그인 후 이용가능합니다.\n로그인 하시겠습니까?');
		
		if(result){
			location.href = `/member/loginForm`;
		}
		return ;
	}
	
	
	//ajax start
	$.ajax({
	   url: '/club/hasClubAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'memId':memId}, //필요한 데이터
	   success: function(result) {
	      if(result){
			  alert('이미 만든 북클럽이 있습니다.\n마이페이지에서 확인해주세요.');
			  return ;
	      }
	      else{
			  location.href = `/club/regClubForm?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
		  }
	      
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
	
	
}


