

//전부 퇴실 버튼 클릭 시
function allCheckOut(mainMenuCode, subMenuCode) {
  Swal.fire({
    text: '전부 퇴실시키겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '확인',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire(
        '전체 퇴실 완료',
        '전체 퇴실이 완료되었습니다.',
        'success'
      ).then(() => {
        location.href = `/aLibrary/allCheckOut?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
      });
    }
  });
}
