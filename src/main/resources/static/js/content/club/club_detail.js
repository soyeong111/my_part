
//클럽 가입 신청 버튼 클릭 시
function joinClub(memId, clubCode){
	
	if(memId == 'anonymousUser'){
		const result = confirm('로그인 후 가입신청이 가능합니다.\n로그인 하시겠습니까?');
		
		if(result){
			location.href = `/member/loginForm`;
		}
		return ;
	}
	
	//ajax start
	$.ajax({
	   url: '/club/alreadyApplyAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'memId':memId, 'clubCode':clubCode}, //필요한 데이터
	   success: function(result) {
	      if(result){
			  alert('가입이력이 있습니다.');
		  }
		  else{
			  const applyMsg = confirm('가입하시겠습니까?');
			  
			  if(applyMsg){
				  //북클럽 가입 신청
			 	   joinClubAjax(clubCode);				
			  }
		  }
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
	
	
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


//클럽 삭제버튼 클릭시
function deleteClub(clubCode, mainMenuCode, subMenuCode){
	const result = confirm('이 북클럽을 삭제하시겠습니까?');
	
	if(result){
		location.href=`/club/deleteClub?clubCode=${clubCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
		
	}
}

//커뮤니티 버튼 클릭 시 - 클럽 회원만 조회 가능
function memberOnly(memId, clubCode, mainMenuCode, subMenuCode){
	
	//ajax start
	$.ajax({
	   url: '/club/isClubMemberAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'memId':memId, 'clubCode':clubCode}, //필요한 데이터
	   success: function(result) {
		  if(result){
		      location.href=`/club/community?clubCode=${clubCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
		  }
		  else{
			  alert('클럽 회원만 입장할 수 있습니다.');
		  }
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}

getWeather();

function getWeather(){
	document.addEventListener("DOMContentLoaded", getWeather);

		function getWeather() {
			fetch('https://goweather.herokuapp.com/weather/Ulsan')
			.then((response) => response.json())
			.then((data) => {
				document.getElementById("temperature").innerHTML = data['temperature'];
				document.getElementById("wind").innerHTML = data['wind'];
				document.getElementById("description").innerHTML = data['description'];

				if (data['description'] === 'Sunny') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/weather/sunny.png")';
				}
				else if (data['description'] === 'Cloudy') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/weather/cloudy.png")';
				}
				else if (data['description'] === 'Rainy') {
					document.querySelector('.weather-img').style.backgroundImage = 'url("/image/weather/rainy.png")';
				}
			});
		}
}



