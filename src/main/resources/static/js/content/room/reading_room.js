
function getSeat(memId, seatCode, mainMenuCode, subMenuCode){
	
	if(memId == 'anonymousUser'){
		const result = confirm('회원만 입실가능합니다.\n로그인 하시겠습니까?');
		
		if(result){
			location.href = `/member/loginForm`;
		}
		return ;
	}
	
	
	//ajax start
	$.ajax({
	   url: '/room/isUsingSeatAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'memId':memId, 'seatCode':seatCode}, //필요한 데이터
	   success: function(result) {
	      const seatMsg = confirm('입실하시겠습니까?');
		  if(seatMsg){
	      
	          if(result){
			      alert('이미 사용중인 좌석이 있습니다.');
			      return ;
		  	  }
		  	  else{
				  
				//ajax start
				$.ajax({
				   url: '/room/getSeatAjax', //요청경로
				   type: 'post',
				   async : true,
				   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				   data: {'seatCode':seatCode}, //필요한 데이터
				   success: function(result) {
					  
				      location.href=`/room/readingRoom?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
				   },
				   error: function() {
				      alert('실패');
				   }
				});
				//ajax end
				  
			  }
		  }
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}




// 퇴실 버튼 클릭 시
function checkOutSeat(seatCode,seatUseCode, mainMenuCode, subMenuCode){
	const checkOutMsg = confirm('퇴실하시겠습니까?');
	if(checkOutMsg){
		location.href=`/room/checkOutSeat?seatCode=${seatCode}&seatUseCode=${seatUseCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
	}
}








