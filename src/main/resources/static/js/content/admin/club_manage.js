function deleteClub(clubCode, mainMenuCode, subMenuCode) {
  Swal.fire({
    title: '북클럽 삭제',
    text: '이 북클럽을 삭제하시겠습니까?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '삭제',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      // Ajax request
      $.ajax({
        url: '/aClub/deleteClubAjax',
        type: 'post',
        async: true,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data: { 'clubCode': clubCode },
        success: function(result) {
         Swal.fire('북클럽이 삭제되었습니다.', '', 'success')
						.then(() => {
							location.reload();
						});
        },
        error: function() {
          Swal.fire({
            icon: 'error',
            title: '실패',
            text: '삭제에 실패했습니다.'
          });
        }
      });
    }
  });
}