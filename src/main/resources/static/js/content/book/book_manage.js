init();


//초기 작업 실행
function init() {
    const borrowBtn = document.getElementById('brBtn');

    const bookStockCntElement = document.getElementById('bookBr').elements['bookStockCnt'];
    const bookStockCnt = parseInt(bookStockCntElement.value);

    const borrowCntElement = document.getElementById('bookBr').elements['borrowCnt'];
    const borrowCnt = parseInt(borrowCntElement.value);


    if (borrowCnt == bookStockCnt) {
        borrowBtn.disabled = true;
    }
}






// 대여하기 버튼 클릭 시 실행
function borrow(memId, bookCode) {
  if (memId == 'anonymousUser') {
    const result = confirm('먼저 로그인 하세요. \n로그인 하시겠습니까?');

    if (result) {
      // 로그인 페이지로 이동
      location.href = '/member/loginForm';
    }
    // 로그인 체크
    return;
  }
  // 동시에 대여 가능 여부를 확인하고 대여를 처리
  checkBorrow(memId, bookCode, function() {
    checkBorrowLimit(memId, bookCode, function() {
      borrowAjax(memId, bookCode);
    });
  });
}

// 중복 대여 여부
function checkBorrow(memId, bookCode, callback) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'memId': memId, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        alert('이미 대여한 책입니다.');
      
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      alert('대여 가능 여부를 확인하는데 실패했습니다.');
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
        alert('대여 가능한 권수를 초과하였습니다.');
      } else {
        callback(); // 대여 가능 여부 확인 완료 후 콜백 실행
      }
    },
    error: function() {
      alert('대여 가능 여부를 확인하는데 실패했습니다.');
    }
  });
}

// 도서 대여
function borrowAjax(memId, bookCode) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'bookCode': bookCode, 'memId': memId },
    success: function(result) {
      const result1 = confirm('도서가 성공적으로 대여되었습니다.');

      if (result1) {
        location.href = `/book/bookDetail?bookCode=${bookCode}`;
      }
    },
    error: function() {
      alert('대여에 실패했습니다.');
    }
  });
}




