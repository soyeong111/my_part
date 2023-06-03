
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


