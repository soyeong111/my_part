
//승인 버튼 클릭 시
function acceptMember(acceptCode){
	
	//ajax start
	$.ajax({
	   url: '/club/acceptMemberAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'acceptCode':acceptCode}, //필요한 데이터
	   success: function(result) {
	      alert('ajax 통신 성공');
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}

//거절 버튼 클릭 시
function refuse(acceptCode){
	
	//ajax start
	$.ajax({
	   url: '/club/refuseMemberAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'acceptCode':acceptCode}, //필요한 데이터
	   success: function(result) {
	      alert('ajax 통신 성공');
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}