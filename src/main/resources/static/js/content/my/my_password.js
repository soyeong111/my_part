
/* 타이머 시작 */
function startTimer() {
	setTimeout(() => {
		const main_menu_code = document.querySelector('#main-menu-code').value;
		const sub_menu_code = document.querySelector('#sub-menu-code').value;
		location.href = `/mMember/myPassword?mainMenuCode=${main_menu_code}&subMenuCode=${sub_menu_code}`;
	}, 300000);
}

/* 현재 비밀번호 입력 시 */
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
				draw_pw_change();
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

/* 비밀번호 변경 화면 그리기 */
function draw_pw_change() {
	const pw_change_div = document.querySelector('#my-password-change-div');
	let str = '<div class="offset-1 col-8 mt-5 validation-div text-end">';
	str += '8~16자리의, 영문소문자 + 영문대문자 + 특수문자(! @ # $ % &) + 숫자';
	str += '</div>';
	str += '<div class="offset-1 col-8 mt-3">';
	str += '<div class="input-group">';
	str += '<span class="input-group-text">변경 비밀번호</span>';
	str += '<input type="password" class="form-control" id="new-pw" onkeyup="new_pw_validation(this)" maxlength="16">';
	str += '</div>';
	str += '</div>';
	str += '<div class="offset-1 col-8 mt-4">';
	str += '<div class="input-group">';
	str += '<span class="input-group-text">비밀번호 확인</span>';
	str += '<input type="password" class="form-control" id="new-pw-check" onkeyup="new_pw_check_validation(this)" maxlength="16">';
	str += '</div>';
	str += '</div>';
	str += '<div class="offset-1 col-8 mt-5 d-grid">';
	str += '<button type="button" class="btn btn-primary custom-btn" onclick="change_pw();">변경</button>';
	str += '</div>';
	pw_change_div.replaceChildren();
	pw_change_div.insertAdjacentHTML('afterbegin', str);
	startTimer();
}

/* 새비밀번호 유효성 검사 */
function new_pw_validation(pw_input) {
	pw_input.value = pw_input.value.replaceAll(' ', '');
	const pw_check_input = document.querySelector('#new-pw-check');
	const regexp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&]).{8,16}$/;
	if (regexp.test(pw_input.value)) {
		pw_input.classList.remove('is-invalid');
		pw_input.classList.add('is-valid');
		if (pw_input.value == pw_check_input.value) {
			pw_check_input.classList.remove('is-invalid');
			pw_check_input.classList.add('is-valid');
			return;
		}
	} else {
		pw_input.classList.remove('is-valid');
		pw_input.classList.add('is-invalid');
	}
	pw_check_input.classList.remove('is-valid');
	pw_check_input.classList.add('is-invalid');
}

/* 새비밀번호 확인 유효성 검사 */
function new_pw_check_validation(pw_check_input) {
	pw_check_input.value = pw_check_input.value.replaceAll(' ', '');
	const pw_input = document.querySelector('#new-pw');
	if (pw_input.value == pw_check_input.value && pw_input.classList.contains('is-valid')) {
		pw_check_input.classList.remove('is-invalid');
		pw_check_input.classList.add('is-valid');
		return;
	}
	pw_check_input.classList.remove('is-valid');
	pw_check_input.classList.add('is-invalid');
}

/* 비밀번호 변경 */
function change_pw() {
	const pw_input = document.querySelector('#new-pw');
	if (!pw_input.classList.contains('is-valid')) {
		pw_input.focus();
		return;
	}
	const pw_check_input = document.querySelector('#new-pw-check');
	if (!pw_check_input.classList.contains('is-valid')) {
		pw_check_input.focus();
		return;
	}
	$.ajax({
		url: '/mMember/changePwAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memPw':pw_input.value},
		success: function(result) {
			if (result == 2) {
				Swal.fire({
					icon: 'error',
					title: '비밀번호 변경 실패',
					text: '현재 사용중인 비밀번호로는 변경할 수 없습니다.',
				});
			}
			if (result == 1) {
				Swal.fire({
					icon: 'success',
					title: '비밀번호 변경',
					text: '비밀번호가 변경되었습니다. 다시 로그인해주세요.',
				}).then(() => {
					location.href = '/member/logout';
				});
			}
			if (result == 0) {
				Swal.fire({
					icon: 'error',
					title: '비밀번호 변경 실패',
				});
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
