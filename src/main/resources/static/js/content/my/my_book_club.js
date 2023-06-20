

// 취소 버튼 클릭 시
function deleteBtn(acceptCode, clubCode, clubMemStatus) {
  if (clubMemStatus == 1) {
    Swal.fire({
      title: '신청을 취소하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '확인',
      cancelButtonText: '닫기',
    }).then((result) => {
      if (result.isConfirmed) {
        deleteAjax(acceptCode, clubCode, clubMemStatus);
      }
    });
  }

  if (clubMemStatus == 2) {
    Swal.fire({
      title: '탈퇴 하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '확인',
      cancelButtonText: '닫기',
    }).then((result) => {
      if (result.isConfirmed) {
        deleteAjax(acceptCode, clubCode, clubMemStatus);
      }
    });
  }
}

function deleteAjax(acceptCode, clubCode, clubMemStatus) {
  //ajax start
  $.ajax({
    url: '/mClub/deleteBtnAjax', //요청경로
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'acceptCode': acceptCode }, //필요한 데이터
    success: function (result) {
      if (clubMemStatus == 1) {
        Swal.fire({
          title: '신청이 취소되었습니다.',
          icon: 'success',
          showConfirmButton: true,
          confirmButtonText: '확인',
          timer: 1500
        }).then(() => {
          location.href = `/mClub/myBookClub?clubCode=${clubCode}`;
        });
      } else if (clubMemStatus == 2) {
        Swal.fire({
          title: '탈퇴가 완료되었습니다.',
          icon: 'success',
          showConfirmButton: true,
          confirmButtonText: '확인',
          timer: 1500
        }).then(() => {
          location.href = `/mClub/myBookClub?clubCode=${clubCode}`;
        });
      }
    },
    error: function () {
      Swal.fire({
        title: '실패',
        icon: 'error',
        showConfirmButton: false,
        timer: 1500
      });
    }
  });
  //ajax end
}