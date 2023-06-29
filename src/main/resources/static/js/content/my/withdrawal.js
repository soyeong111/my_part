
/* 비밀번호 입력 시 */
function mem_pw_keyup(mem_pw_input) {
	mem_pw_input.value = mem_pw_input.value.replaceAll(' ', '');
}

/* 비밀번호 확인 */
function check_pw() {
	const mem_pw_input = document.querySelector('#mem-pw');
	$.ajax({
		url: '/mMember/checkPwAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memPw':mem_pw_input.value},
		success: function(result) {
			if (result) {
				Swal.fire({
					icon: 'question',
					title: '탈퇴 확인',
					text: '정말 탈퇴 하시겠습니까?',
					showCancelButton: true,
					confirmButtonText: '확인',
					cancelButtonText: '취소',
				}).then((result) => {
					if (result.isConfirmed) {
						withdrawal();
					} else {
						location.reload();
					}
				});
			} else {
				mem_pw_input.classList.add('is-invalid');
				mem_pw_input.focus();
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 회원 탈퇴
function withdrawal() {
	$.ajax({
		url: '/mMember/withdrawalAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {},
		success: function(result) {
			if (result) {
				Swal.fire({
					icon: 'success',
					title: '탈퇴 완료',
					text: '탈퇴 되었습니다.',
				}).then(() => {
					location.href = '/member/logout';
				});
			} else {
				Swal.fire({
					icon: 'error',
					title: '탈퇴 실패',
				});
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
