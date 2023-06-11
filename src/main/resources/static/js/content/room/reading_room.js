
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
					  
					  
					  //document.querySelector(`#getSeatBtn_${result.seatCode}`).disabled = true;
					  
					  
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


function seatPick(seatCode){
	//ajax start
	$.ajax({
	   url: '/room/readingRoomAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'seatCode':seatCode}, //필요한 데이터
	   success: function(result) {
	      const room_seat_div = document.querySelector('#room-seat-div');
	      room_seat_div.replaceChildren();
	      
	      let str = '';
	      
	      str += `<div class="row">`;
	      str += `<div class="col">`;
	      str += `<table class="table text-center">`;
	      str += `<thead>`;
	      str += `<tr>`;
	      str += `<td>좌석번호</td>`;
	      str += `<td>상태</td>`;
	      str += `<td>입실/퇴실</td>`;
	      str += `</tr>`;
	      str += `</thead>`;
	      str += `<tbody>`;
	      str += `<tr>`;
	      str += `<td>${result.seatCode}</td>`;
	      str += `<td>${result.seatIsUsed}</td>`;
	      str += `<td><input type="button" class="btn custom-btn" value="입실" onclick="getIn('${result.seatCode}');"></td>`;
	      str += `</tr>`;
	      str += `</div>`;
	      str += `</div>`;
	      str += ``;
	      str += ``;
	      
	      
	      
	      
	      room_seat_div.insertAdjacentHTML('afterbegin', str);
	      
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


//전부 퇴실 버튼 클릭 시
function allCheckOut(mainMenuCode, subMenuCode){
	const allOutMsg = confirm('전부 퇴실시키겠습니까?');
	if(allOutMsg){
		location.href=`/room/allCheckOut?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
	}
}








// ------------------ 아코디언 ---------------------//
/*
document.addEventListener("DOMContentLoaded", function() {
    // 모든 아코디언 버튼 요소를 선택합니다.
    const accordionButtons = document.querySelectorAll(".accordion-button");

    // 각 아코디언 버튼에 클릭 이벤트를 추가합니다.
    accordionButtons.forEach(function(button) {
      button.addEventListener("click", function() {
        // 현재 클릭된 아코디언 버튼을 제외한 모든 아코디언 요소를 닫습니다.
        accordionButtons.forEach(function(otherButton) {
          if (otherButton !== button) {
            otherButton.setAttribute("aria-expanded", "false");
            otherButton.classList.add("collapsed");
            const target = document.querySelector(otherButton.getAttribute("data-bs-target"));
            target.classList.remove("show");
          }
        });
      });
    });
  });
*/