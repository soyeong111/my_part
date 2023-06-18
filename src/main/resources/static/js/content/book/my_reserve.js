//취소 버튼 클릭 시 실행
function cancelReserve(btn, name, bookCode) {
    Swal.fire({
      title: '해당 도서를 취소하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '네',
      cancelButtonText: '아니요',
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: '/book/cancelReserveAjax',
          type: 'post',
          async: true,
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
          data: { 'memId': name , 'bookCode':bookCode},
          success: function(result) {
			   Swal.fire('예약이 성공적으로 \n취소되었습니다.', '', 'success')
              .then(() => {
                location.reload();
              });
          },
          error: function() {
            Swal.fire('취소에 실패했습니다.', '', 'error');
          }
        });
      }
    });
  
}






