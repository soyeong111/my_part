
init();

const book_search_modal = new bootstrap.Modal('#book-search-modal');

function init() {
	const book_search_modal_div = document.querySelector('#book-search-modal');
	// 검색 모달 닫힐 때 이벤트 생성
	book_search_modal_div.addEventListener('hidden.bs.modal', () => {
		let str = `<div class="row mt-3 pt-3 border-top">`;
		str += `<div class="col text-center">`;
		str += `검색어를 입력해주세요.`;
		str += `</div>`;
		str += `</div>`;
		const modal_change_div = document.querySelector('#modal-change-div');
		modal_change_div.replaceChildren();
		modal_change_div.insertAdjacentHTML('afterbegin', str);
		const book_search_value = document.querySelector('#book-search-value');
		book_search_value.value = '';
	});
}

// 책 검색 모달 열기
function open_book_search_modal() {
	book_search_modal.show();
}

// 책 검색 모달 닫기
function close_book_search_modal() {
	book_search_modal.hide();
}

// 책 검색
function book_search() {
	const book_search_value = document.querySelector('#book-search-value');
	book_search_value.value = book_search_value.value.trim();
	if (book_search_value.value == '') {
		book_search_value.focus();
		return;
	}
	const book_search_column = document.querySelector('#book-search-column');
	$.ajax({
		url: '/mBook/bookSearchAjax',
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify({'searchColumn':book_search_column.value, 'searchValue':book_search_value.value}),
		success: function(result) {
			drawBookSearchModal(result);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 검색된 책 리스트 그리기
function drawBookSearchModal(book_list) {
	let str = '';
	book_list.forEach((book) => {
		str += `<div class="row mt-3 pt-2 border-top">`;
		str += `<div class="offset-2 col-3">`;
		str += `<div class="m-2">`;
		str += `<a href="javascript:void(0);" onclick="bookSeleted('${book.imgList[0].attachedFileName}', '${book.bookTitle}', '${book.bookAuthor}', '${book.bookCode}');">`;
		str += `<img src="/image/book/${book.imgList[0].attachedFileName}" width="100%">`;
		str += `</a>`;
		str +=  `</div>`;
		str += `</div>`;
		str += `<div class="offset-1 col-6">`;
		str += `<div class="m-2">`;
		str += `<a href="javascript:void(0);" onclick="bookSeleted('${book.imgList[0].attachedFileName}', '${book.bookTitle}', '${book.bookAuthor}', '${book.bookCode}');">${book.bookTitle}</a>`;
		str +=  `</div>`;
		str += `<div class="m-2">${book.bookAuthor}</div>`;
		str += `<div class="m-2">${book.bookPublisher}</div>`;
		str += `<div class="m-2">${book.bookPublicationDate}</div>`;
		str += `</div>`;
		str += `</div>`;
	});
	if (book_list.length == 0) {
		str += `<div class="row mt-3 pt-3 border-top">`;
		str += `<div class="col text-center">`;
		str += `검색 결과가 없습니다.`;
		str += `</div>`;
		str += `</div>`;
	}
	const modal_change_div = document.querySelector('#modal-change-div');
	modal_change_div.replaceChildren();
	modal_change_div.insertAdjacentHTML('afterbegin', str);
}

// 책 선택
function bookSeleted(book_img, book_title, book_author, book_code) {
	document.querySelector('#book-img img').src = "/image/book/" + book_img;
	document.querySelector('.month-book-title').textContent = book_title;
	document.querySelector('.month-book-autho').textContent = book_author;
	const club_code = document.querySelector('#clubCode').value;
	
	
	close_book_search_modal();
	
	//ajax start
	$.ajax({
	   url: '/club/updateClubBookAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'clubCode':club_code, 'thisBookCode':book_code}, //필요한 데이터
	   success: function(result) {
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
	// 로컬 스토리지에 책 정보 저장
	//localStorage.setItem('book_img', book_img);
	//localStorage.setItem('book_title', book_title);
	//localStorage.setItem('book_author', book_author);
	
	
}

// 페이지 로드 시 저장된 책 정보 확인
/* window.addEventListener('DOMContentLoaded', function() {
	// 저장된 책 정보 가져오기
	const storedBookImg = localStorage.getItem('book_img');
	const storedBookTitle = localStorage.getItem('book_title');
	const storedBookAuthor = localStorage.getItem('book_author');

	// 책 정보가 있을 경우 업데이트
	if (storedBookImg && storedBookTitle && storedBookAuthor) {
		document.querySelector('#book-img img').src = "/image/book/" + storedBookImg;
		document.querySelector('#book-title').textContent = storedBookTitle;
		document.querySelector('#book-author').textContent = storedBookAuthor;
	}
}); */




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
	
	if (memId == 'anonymousUser') {
    Swal.fire({
      text: '로그인 후 클럽회원만 입장 가능합니다.\n로그인 하시겠습니까?',
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



function searchForm(){
	
}

















