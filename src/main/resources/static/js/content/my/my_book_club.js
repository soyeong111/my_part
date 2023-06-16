

// 취소 버튼 클릭 시
function deleteBtn(acceptCode, clubCode, clubMemStatus){
	if(clubMemStatus == 1){
		const cancelMsg = confirm('신청을 취소하시겠습니까?');
		if(cancelMsg){
			deleteAjax(acceptCode, clubCode, clubMemStatus);
		}
	}
	
	if(clubMemStatus == 2){
		const leaveMsg = confirm('탈퇴 하시겠습니까?');
		if(leaveMsg){
			deleteAjax(acceptCode, clubCode, clubMemStatus);
		}
	}
}

function deleteAjax(acceptCode, clubCode, clubMemStatus){
	//ajax start
		$.ajax({
		   url: '/mClub/deleteBtnAjax', //요청경로
		   type: 'post',
		   async : true,
		   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		   data: {'acceptCode':acceptCode}, //필요한 데이터
		   success: function(result) {
		      if(clubMemStatus == 1){
				  alert('신청이 취소되었습니다.');
				  location.href=`/mClub/myBookClub?clubCode=${clubCode}`;
			  }
			  else if(clubMemStatus == 2){
				  alert('탈퇴가 완료되었습니다.');
				  location.href=`/mClub/myBookClub?clubCode=${clubCode}`;
			  }
		   },
		   error: function() {
		      alert('실패');
		   }
		});
		//ajax end
}