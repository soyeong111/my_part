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

  checkBorrow(bookCode);
}


// 대여 가능 여부 확인
function checkBorrow(bookCode) {
  $.ajax({
    url: '/book/getBorrowCntAjax',
    type: 'post',
    data: {'bookCode': bookCode}, // bookCode를 객체 형태로 전달
    success: function(response) {
      var borrowCnt = parseInt(response);
      if (borrowCnt > 0) {
        alert('도서 대여가 불가능합니다.');
      } else {
        borrowAjax(bookCode);
      }
    },
    error: function() {
      alert('도서 대여 가능 여부를 확인하는데 실패했습니다.');
    }
  });
}


function borrowAjax(bookCode) {
  $.ajax({
    url: '/book/borrowAjax',
    type: 'post',
    data: { 'bookCode': bookCode },
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
