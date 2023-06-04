
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
	      str += `<td><input type="button" class="btn custom-btn" value="입실" onclick="getIn();"></td>`;
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

function getIn(seatCode){
	const getInMsg = confirm('입실하시겠습니까?');
	if(getInMsg){
		alert(1);
	}
}
