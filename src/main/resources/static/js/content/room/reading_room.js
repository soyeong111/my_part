
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
	      str += ``;
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