
//북클럽 만들기 버튼 클릭 시 실행
function regClub(memId, mainMenuCode, subMenuCode) {
  if (memId == 'anonymousUser') {
    Swal.fire({
      text: '로그인 후 이용가능합니다.\n로그인 하시겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: "확인",
      cancelButtonText: "취소"
    }).then((result) => {
      if (result.isConfirmed) {
        location.href = `/member/loginForm`;
      }
    });
    return;
  }

  // Ajax start
  $.ajax({
    url: '/club/hasClubAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { memId: memId },
    success: function (result) {
      if (result) {
        Swal.fire({
          text: '이미 만든 북클럽이 있습니다.\n마이페이지에서 확인해주세요.',
          icon: 'warning',
        });
      } else {
        location.href = `/club/regClubForm?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
      }
    },
    error: function () {
      Swal.fire({
        text: '실패',
        icon: 'error',
      });
    },
  });
  // Ajax end
}

