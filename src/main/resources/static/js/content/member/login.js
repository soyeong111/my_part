
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
				alert('로그인 되었습니다!');
				location.href = '/';
			} else {
				alert('아이디와 비밀번호를 확인해주세요.');
				mem_pw_input.value = '';
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
