
function getSeat(memId, seatCode, mainMenuCode, subMenuCode) {
  if (memId === 'anonymousUser') {
    Swal.fire({
      text: '회원만 입실 가능합니다.\n로그인 하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: '확인',
      cancelButtonText: '취소'
    }).then((result) => {
      if (result.isConfirmed) {
        location.href = '/member/loginForm';
      }
    });
    return;
  }

  // ajax start
  $.ajax({
    url: '/room/isUsingSeatAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'memId': memId, 'seatCode': seatCode },
    success: function (result) {
      Swal.fire({
        text: '입실하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '확인',
        cancelButtonText: '취소'
      }).then((seatMsg) => {
        if (seatMsg.isConfirmed) {
          if (result) {
            Swal.fire({
              text: '이미 사용중인 좌석이 있습니다.',
              icon: 'warning'
            });
            return;
          } else {
            // ajax start
            $.ajax({
              url: '/room/getSeatAjax',
              type: 'post',
              async: true,
              contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
              data: { 'seatCode': seatCode },
              success: function (result) {
                Swal.fire({
                  text: '입실이 완료되었습니다.',
                  icon: 'success'
                }).then(() => {
                  location.href = `/room/readingRoom?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
                });
              },
              error: function () {
                Swal.fire({
                  text: '실패',
                  icon: 'error'
                });
              }
            });
            // ajax end
          }
        }
      });
    },
    error: function () {
      Swal.fire({
        text: '실패',
        icon: 'error'
      });
    }
  });
  // ajax end
}



function checkOutSeat(seatCode, seatUseCode, mainMenuCode, subMenuCode) {
  Swal.fire({
    text: '퇴실하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '확인',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire(
        '퇴실 완료',
        '퇴실이 완료되었습니다.',
        'success'
      ).then(() => {
        location.href = `/room/checkOutSeat?seatCode=${seatCode}&seatUseCode=${seatUseCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
      });
    }
  });
}


