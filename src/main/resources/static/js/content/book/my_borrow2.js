

// 연장 전 예약 확인
function checkReserve(btn, name, borrowCode, bookCode) {
  $.ajax({
    url: '/book/checkReserveBeforeExtendAjax',
    type: 'post',
    data: { 'memId': name, 'bookCode': bookCode },
    success: function(response) {
      if (response == 1) {
        Swal.fire({
          title: '연장 불가',
          text: '다른 회원이 예약한 도서는 연장이 불가합니다.',
          icon: 'error',
        });
      } else {
        extend(btn, name, borrowCode, bookCode);
      }
    },
    error: function() {
      Swal.fire({
        title: '오류',
        text: '대여 가능 여부를 확인하는데 실패했습니다.',
        icon: 'error',
      });
    }
  });
}

// 반납 기한 연장
function extend(btn, name, borrowCode, bookCode) {
  var button = document.getElementById('exBtn-' + borrowCode);
  if (button && button.value === '연장') {
    Swal.fire({
      title: '반납 기한을 연장하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '네',
      cancelButtonText: '아니요',
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: '/book/extendAjax',
          type: 'post',
          async: true,
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
          data: { 'borrowCode': borrowCode, 'memId': name },
          success: function(result) {
			   Swal.fire('도서 반납 기한이 \n3일 연장되었습니다.', '', 'success')
              .then(() => {
                location.reload();
              });
          },
          error: function() {
            Swal.fire('연장에 실패했습니다.', '', 'error');
          }
        });
      }
    });
  }
}


//반납 버튼 클릭 시 실행
function returnBook(btn, name, borrowCode, bookCode) {
  var button = document.getElementById('reBtn-' + borrowCode);
  if (button && button.value === '반납') {
    Swal.fire({
      title: '해당 도서를 반납하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '네',
      cancelButtonText: '아니요',
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: '/book/returnBookAjax',
          type: 'post',
          async: true,
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
          data: { 'borrowCode': borrowCode, 'memId': name },
          success: function(result) {
			   Swal.fire('도서가 성공적으로 \n반납되었습니다.', '', 'success')
              .then(() => {
                location.reload();
              });
          },
          error: function() {
            Swal.fire('연장에 실패했습니다.', '', 'error');
          }
        });
      }
    });
  }
}


























// 페이지 로드 시 상태를 복원
//window.addEventListener('load', function() {
//  var buttons = document.querySelectorAll('input.btn.custom-btn');
//  buttons.forEach(function(button) {
//    var borrowCode = button.id.substring(6); // borrowCode 추출
 //   var extendStatus = localStorage.getItem('extendStatus-' + borrowCode);
 //   if (extendStatus === 'completed') {
  //    button.style.display = 'none'; // 버튼 숨기기
    //  var span = document.createElement('span'); // 텍스트 엘리먼트 생성
   //   span.textContent = '완료'; // 텍스트 설정
   //   button.parentNode.appendChild(span); // 텍스트 추가
   // }
 // });
//});


