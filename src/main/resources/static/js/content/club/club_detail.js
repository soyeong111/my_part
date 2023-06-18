
//클럽 가입 신청 버튼 클릭 시
function joinClub(memId, clubCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      text: '로그인 후 가입신청이 가능합니다.\n로그인 하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
	  confirmButtonText: "확인",
	  cancelButtonText: "취소"
    }).then((result) => {
      if (result.isConfirmed) {
        location.href = `/member/loginForm`;
      }
    });
    return;
  }

  // Ajax start
  $.ajax({
    url: '/club/alreadyApplyAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { memId: memId, clubCode: clubCode },
    success: function (result) {
      if (result) {
        Swal.fire({
          text: '가입이력이 있습니다.',
          icon: 'warning',
        });
      } else {
        Swal.fire({
          text: '가입하시겠습니까?',
          icon: 'question',
          showCancelButton: true,
          confirmButtonText: "확인",
    	  cancelButtonText: "취소"
        }).then((result) => {
          if (result.isConfirmed) {
            joinClubAjax(clubCode);
          }
        });
      }
    },
    error: function () {
      Swal.fire({
        text: '실패',
        icon: 'error',
      });
    },
  });
  // Ajax end
}

//클럽 가입 Ajax
function joinClubAjax(clubCode) {
  // Ajax start
  $.ajax({
    url: '/club/joinClubAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { clubCode: clubCode },
    success: function (result) {
      Swal.fire({
        text: '가입 신청이 완료되었습니다.\n클럽장의 승인을 기다려주세요.',
        icon: 'success',
      });
    },
    error: function () {
      Swal.fire({
        text: '실패',
        icon: 'error',
      });
    },
  });
  // Ajax end
}



//클럽 삭제버튼 클릭시
function deleteClub(clubCode, mainMenuCode, subMenuCode) {
  Swal.fire({
    text: '이 북클럽을 삭제하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: "확인",
    cancelButtonText: "취소"
  }).then((result) => {
    if (result.isConfirmed) {
      location.href = `/club/deleteClub?clubCode=${clubCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
    }
  });
}

//커뮤니티 버튼 클릭 시 - 클럽 회원만 조회 가능
function memberOnly(memId, clubCode, mainMenuCode, subMenuCode) {
  // Ajax start
  $.ajax({
    url: '/club/isClubMemberAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { memId: memId, clubCode: clubCode },
    success: function (result) {
      if (result) {
        location.href = `/club/community?clubCode=${clubCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
      } else {
        Swal.fire({
          text: '클럽 회원만 입장할 수 있습니다.',
          icon: 'error',
        });
      }
    },
    error: function () {
      Swal.fire({
        text: '실패',
        icon: 'error',
      });
    },
  });
  // Ajax end
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



