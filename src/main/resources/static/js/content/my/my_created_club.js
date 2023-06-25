
//승인 버튼 클릭 시
function acceptMember(acceptCode, clubCode) {
  Swal.fire({
    title: '승인하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '확인',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      // Ajax request to accept the member
      $.ajax({
        url: '/mClub/acceptMemberAjax',
        type: 'post',
        async: true,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data: { 'acceptCode': acceptCode },
        success: function (result) {
          Swal.fire({
            title: '승인이 완료되었습니다.',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
          }).then(() => {
            location.href = `/mClub/myCreatedClub?clubCode=${clubCode}`;
          });
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
    }
  });
}

//거절 버튼 클릭 시
function refuse(acceptCode, clubCode) {
  Swal.fire({
    title: '거절하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: '확인',
    cancelButtonText: '취소'
  }).then((result) => {
    if (result.isConfirmed) {
      // Ajax request to refuse the member
      $.ajax({
        url: '/mClub/refuseMemberAjax',
        type: 'post',
        async: true,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data: { 'acceptCode': acceptCode },
        success: function (result) {
          Swal.fire({
            title: '거절이 완료되었습니다.',
            icon: 'success',
            showConfirmButton: true,
            confirmButtonText: '확인',
            timer: 1500
          }).then(() => {
            location.href = `/mClub/myCreatedClub?clubCode=${clubCode}`;
          });
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
    }
  });
}

//강퇴 버튼 클릭 시
function kickOut(acceptCode, clubCode) {
  Swal.fire({
    title: '강퇴하시겠습니까?',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: '확인',
    cancelButtonText: '취소',
  }).then((result) => {
    if (result.isConfirmed) {
      // Ajax request
      $.ajax({
        url: '/mClub/refuseMemberAjax',
        type: 'post',
        async: true,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        data: { 'acceptCode': acceptCode },
        success: function(result) {
          Swal.fire({
            title: '강퇴가 완료되었습니다.',
            icon: 'success',
            showConfirmButton: true,
            confirmButtonText: '확인',
          }).then(() => {
            location.href = `/mClub/myCreatedClub?clubCode=${clubCode}`;
          });
        },
        error: function() {
          Swal.fire({
            title: '실패',
            icon: 'error',
          });
        }
      });
    }
  });
}