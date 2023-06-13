

// 취소 버튼 클릭 시
function cancelApply(acceptCode, clubCode){
	const cancelMsg = confirm('신청을 취소하시겠습니까?');
	
	if(cancelMsg){
		//ajax start
		$.ajax({
		   url: '/mClub/cancelApplyAjax', //요청경로
		   type: 'post',
		   async : true,
		   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		   data: {'acceptCode':acceptCode}, //필요한 데이터
		   success: function(result) {
		      alert('신청이 취소되었습니다.');
		      location.href=`/mClub/myBookClub?clubCode=${clubCode}`;
		   },
		   error: function() {
		      alert('실패');
		   }
		});
		//ajax end
		}
}


