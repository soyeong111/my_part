//연체 버튼 클릭 시 실행
function delReserve(memId, bookCode) {
    Swal.fire({
      title: '예약을 삭제하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '네',
      cancelButtonText: '아니요',
    }).then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          url: '/aBook/delReserveAjax',
          type: 'post',
          async: true,
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
          data: { 'memId': memId , 'bookCode':bookCode},
          success: function(result) {
			   Swal.fire('삭제되었습니다.', '', 'success')
              .then(() => {
                location.reload();
              });
          },
          error: function() {
            Swal.fire('삭제에 실패했습니다.', '', 'error');
          }
        });
      }
    });
  
}






