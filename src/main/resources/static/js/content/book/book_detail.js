init();


//초기 작업 실행
function init() {
    const reserveBtn = document.getElementById('reBtn');
    const borrowBtn = document.getElementById('brBtn');

    const bookStockCntElement = document.getElementById('bookBr').elements['bookStockCnt'];
    const bookStockCnt = parseInt(bookStockCntElement.value);

    const borrowCntElement = document.getElementById('bookBr').elements['borrowCnt'];
    const borrowCnt = parseInt(borrowCntElement.value);
    
    const reserveCntElement = document.getElementById('bookBr').elements['reserveCnt'];
    const reserveCnt = parseInt(reserveCntElement.value);
   
    const returnDateElement = document.getElementById('bookBr').elements['returnDate'];
    const returnDate = returnDateElement.value.toString();


    if (borrowCnt >= bookStockCnt && returnDate == null) {
        borrowBtn.disabled = true;
    } 
    
    if (reserveCnt >= 2){
		reserveBtn.disabled = true;
	}
}






// 대여하기 버튼 클릭 시 실행
function borrow(memId, bookCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      title: "먼저 로그인 하세요.",
      text: "로그인 하시겠습니까?",
      icon: "warning", //"info,success,warning,error" 중 택1
      showCancelButton: true,
      confirmButtonText: "확인",
      cancelButtonText: "취소"
    }).then(function(result) {
      if (result.isConfirmed) {
        // 확인 버튼을 클릭한 경우 로그인 페이지로 이동
        location.href = '/member/loginForm';
      }
    });

    // 로그인 체크
    return;
  }

  // 동시에 대여 가능 여부를 확인하고 대여를 처리
	borrowAjax(memId, bookCode);
}

/*
// 중복 대여 여부
function checkBorrow(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        Swal.fire({
          title: "대여 불가",
          text: "이미 대여한 책입니다.",
          icon: "error" //"info,success,warning,error" 중 택1
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}


// 초과 대여 여부
function checkBorrowLimit(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 4) {
        Swal.fire({
          title: "대여 불가",
          text: "대여 가능한 권수(4권)을 초과하였습니다.",
          icon: "error" //"info,success,warning,error" 중 택1
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}

*/

// 도서 대여
function borrowAjax(memId, bookCode) {
	$.ajax({
		url: '/book/borrowAjax',
		type: 'post',
		data: { 'bookCode': bookCode, 'memId': memId },
		success: function(result) {
			alert(result);

			if (result == 100) {
				Swal.fire({
					title: "대여 불가",
					text: "다른 회원이 예약한 도서는 대여가 불가합니다.",
					icon: "error"
				});
			}
			else if (result == 532) {

				Swal.fire({
					title: "대여 성공",
					text: "도서가 성공적으로 대여되었습니다.",
					icon: "success"
				}).then(function(result) {
					if (result) {
						location.href = `/book/bookDetail?bookCode=${bookCode}`;
					}
				});
			}
			else if (result == 300) {

				Swal.fire({
					title: "대여 불가",
					text: "대여가능한 도서가 없습니다.",
					icon: "error"
				});

			}
			else if (result == 1) {

				Swal.fire({
					title: "대여 불가",
					text: "이미 대여한 책입니다.",
					icon: "error"
				});

			}
			else if (result == 4) {

				Swal.fire({
					title: "대여 불가",
					text: "대여 가능한 권수(4권)을 초과하였습니다.",
					icon: "error"
				});

			}
		},
		error: function() {

		}
	});
}


//------------------------------예약하기


// 예약하기 버튼 클릭 시 실행
function reserve(memId, bookCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      title: "먼저 로그인 하세요.",
      text: "로그인 하시겠습니까?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "확인",
      cancelButtonText: "취소"
    }).then((result) => {
      if (result.isConfirmed) {
        // 확인 버튼을 클릭한 경우 로그인 페이지로 이동
        location.href = '/member/loginForm';
      }
    });

    // 로그인 체크
    return;
  }
  // 동시에 대여 가능 여부를 확인하고 대여를 처리
	checkBorrow2(memId, bookCode, function() {
		checkBorrow1(memId, bookCode, function() {
			checkReserve(memId, bookCode, function() {
				checkReserveLimit(memId, bookCode, function() {
					reserveAjax(memId, bookCode);
				});
			});
		});
	});
}


// 예약하기 버튼 클릭 시, 대여한 회원인지 아닌지 확인 여부
function checkBorrow2(memId, bookCode, callback) {
  $.ajax({
    url: '/book/reserveAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
	console.log(response);
      if (response == 100) {
        Swal.fire({
          title: "예약 불가",
          text:  "대여 가능한 책은 예약이 불가합니다.",
          icon: "error"	
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여한 회원인지 아닌지 확인 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}



// 중복 대여 여부
function checkBorrow1(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        Swal.fire({
          title: "예약 불가",
          text: "이미 대여한 책은 예약이 불가합니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "에러",
        text: "대여 가능 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}


// 중복 예약 여부
function checkReserve(memId, bookCode, callback) {
  $.ajax({
    url: '/book/reserveAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 11) {
        Swal.fire({
          title: "예약 불가",
          text: "이미 예약한 책입니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "중복 제약 여부를 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}



// 초과 예약 여부
function checkReserveLimit(memId, bookCode, callback) {
  $.ajax({
    url: '/book/reserveAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 2) {
        Swal.fire({
          title: "예약 불가",
          text: "예약 가능한 권수를 초과하였습니다.",
          icon: "error"
        });
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "초과 예약을 확인하는데 실패했습니다.",
        icon: "error"
      });
    }
  });
}

// 도서 예약
function reserveAjax(memId, bookCode) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'bookCode': bookCode, 'memId': memId },
    success: function(result) {
      Swal.fire({
        title: "성공",
        text: "도서가 성공적으로 예약되었습니다.",
        icon: "success"
      }).then(function() {
        location.href = `/book/bookDetail?bookCode=${bookCode}`;
      });
    },
    error: function() {
      Swal.fire({
        title: "오류",
        text: "대여에 실패했습니다.",
        icon: "error"
      });
    }
  });
}









