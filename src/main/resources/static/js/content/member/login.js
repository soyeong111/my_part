
init();

function init() {
	const local_mem_id = localStorage.getItem('local_mem_id');
	document.querySelector('#login-mem-id').value = local_mem_id;
	document.querySelector('#id-save-btn').checked = local_mem_id != null;
}

/* 로그인 */
function login() {
	const mem_id_input = document.querySelector('#login-mem-id');
	mem_id_input.value = mem_id_input.value.replaceAll(' ', '');
	if (mem_id_input.value == '') {
		mem_id_input.focus();
		return;
	}
	const mem_pw_input = document.querySelector('#login-mem-pw');
	mem_pw_input.value = mem_pw_input.value.replaceAll(' ', '');
	if (mem_pw_input.value == '') {
		mem_pw_input.focus();
		return;
	}
	$.ajax({
		url: '/member/login',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memId':mem_id_input.value, 'memPw':mem_pw_input.value},
		success: function(result) { 
			if (result == 'success') {
				if (document.querySelector('#id-save-btn').checked) {
					localStorage.setItem('local_mem_id', mem_id_input.value);
				} else {
					localStorage.removeItem('local_mem_id');
				}
				check_mem_status(mem_id_input.value);
				
			} else {
				Swal.fire({
					icon: 'error',
					title: '아이디와 비밀번호를 확인해주세요.',
				});
				mem_pw_input.value = '';
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 로그인 회원 상태 조회 */
function check_mem_status(mem_id) {
	$.ajax({
		url: '/mMember/checkMemStatusAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memId':mem_id},
		success: function(result) {
			if (result == '휴면') {
				Swal.fire({
					icon: 'question',
					title: '휴면 계정',
					text: '휴면 계정입니다. 로그인 하시겠습니까?',
					showCancelButton: true,
					confirmButtonText: '로그인',
					cancelButtonText: '취소',
				}).then((result) => {
					if (result.isConfirmed) {
						update_login_date(mem_id);
					} else {
						location.href = '/member/logout';
					}
				});
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 마지막 로그인 날짜 수정 */
function update_login_date(mem_id) {
	$.ajax({
		url: '/mMember/updateLoginDateAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memId':mem_id},
		success: function(result) {
			Swal.fire({
				icon: 'success',
				title: '로그인되었습니다.',
			}).then(() => {
				location.href = '/';
			});
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
