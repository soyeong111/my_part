
let join_email_pw = '';

/* 아이디 유효성 검사 */
function id_validation(id_input) {
	id_input.value = id_input.value.replaceAll(' ', '');
	id_input.classList.remove('is-valid');
	id_input.classList.add('is-invalid');
	const invalid_text_div = id_input.closest('.row').querySelector('.invalid-text-div');
	if (invalid_text_div != null) {
		invalid_text_div.remove();
	}
	const regexp = /^[a-z][a-z0-9]{2,9}$/;
	let str = '';
	if (regexp.test(id_input.value)) {
		id_input.closest('.row').querySelector('button').disabled = false;
		str = '<div class="col-10 offset-2 invalid-text-div">*아이디 확인 버튼을 눌러주세요.</div>';
	} else {
		id_input.closest('.row').querySelector('button').disabled = true;
		if (id_input.value == '') {
			str = '<div class="col-10 offset-2 invalid-text-div">*아이디는 필수 입력입니다.</div>';
		} else {
			str = '<div class="col-10 offset-2 invalid-text-div">*아이디는 3~10자리의, 영문소문자로 시작하는, 영문소문자 혹은 숫자여야합니다.</div>';
		}
	}
	id_input.closest('.row').insertAdjacentHTML('beforeend', str);
}

/* 아이디 중복 확인 */
function id_duplicate_check(btn) {
	btn.disabled = true;
	const id_input = document.querySelector('#join-mem-id');
	const invalid_text_div = id_input.closest('.row').querySelector('.invalid-text-div');
	$.ajax({
		url: '/member/idDuplicateCheckAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memId':id_input.value},
		success: function(result) {
			if(result) {
				id_input.classList.remove('is-invalid');
				id_input.classList.add('is-valid');
				invalid_text_div.remove();
			} else {
				invalid_text_div.textContent = '*이미 사용중인 아이디입니다.';
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 패스워드 유효성 검사 */
function pw_validation(pw_input) {
	pw_input.value = pw_input.value.replaceAll(' ', '');
	const pw_check_input = document.querySelector('#join-mem-pw-check');
	const invalid_text_div = pw_input.closest('.row').querySelector('.invalid-text-div');
	if (invalid_text_div != null) {
		invalid_text_div.remove();
	}
	const regexp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%&]).{8,16}$/;
	let str = '';
	if (regexp.test(pw_input.value)) {
		pw_input.classList.remove('is-invalid');
		pw_input.classList.add('is-valid');
		pw_check_input.disabled = false;
		if (pw_input.value == pw_check_input.value) {
			pw_check_input.classList.remove('is-invalid');
			pw_check_input.classList.add('is-valid');
			return;
		}
		str = '<div class="col-10 offset-2 invalid-text-div">*패스워드 확인을 입력해주세요.</div>';
	} else {
		pw_input.classList.remove('is-valid');
		pw_input.classList.add('is-invalid');
		pw_check_input.disabled = true;
		if (pw_input.value == '') {
			str = '<div class="col-10 offset-2 invalid-text-div">*패스워드는 필수 입력입니다.</div>';
		} else {
			str = '<div class="col-10 offset-2 invalid-text-div">*패스워드는 8~16자리의, 영문소문자 + 영문대문자 + 특수문자(! @ # $ % &) + 숫자여야합니다.</div>';
		}
	}
	pw_check_input.classList.remove('is-valid');
	pw_check_input.classList.add('is-invalid');
	pw_input.closest('.row').insertAdjacentHTML('beforeend', str);
}

/* 패스워드 확인 */
function pw_check(pw_check_input) {
	pw_check_input.value = pw_check_input.value.replaceAll(' ', '');
	const pw_input = document.querySelector('#join-mem-pw');
	const invalid_text_div = pw_check_input.closest('.row').querySelector('.invalid-text-div');
	if (invalid_text_div != null) {
		invalid_text_div.remove();
	}
	if (pw_check_input.value == pw_input.value) {
		pw_check_input.classList.remove('is-invalid');
		pw_check_input.classList.add('is-valid');
		return;
	}
	pw_check_input.classList.remove('is-valid');
	pw_check_input.classList.add('is-invalid');
	const str = '<div class="col-10 offset-2 invalid-text-div">*패스워드를 확인해주세요.</div>';
	pw_check_input.closest('.row').insertAdjacentHTML('beforeend', str);
}

/* 이름 유효성 검사  */
function name_validation(name_input) {
	const regexp = /^[가-힣a-zA-Z ]+$/;
	const invalid_text_div = name_input.closest('.row').querySelector('.invalid-text-div');
	if (invalid_text_div != null) {
		invalid_text_div.remove();
	}
	let str = '';
	if (name_input.value.trim() == '') {
		name_input.value = name_input.value.trim();
		str = '<div class="col-10 offset-2 invalid-text-div">*이름은 필수 입력입니다.</div>';
	} else if (regexp.test(name_input.value)) {
		name_input.classList.remove('is-invalid');
		name_input.classList.add('is-valid');
		return;
	} else {
		str = '<div class="col-10 offset-2 invalid-text-div">*이름을 확인해주세요.</div>';
	}
	name_input.classList.remove('is-valid');
	name_input.classList.add('is-invalid');
	name_input.closest('.row').insertAdjacentHTML('beforeend', str);
}

/* 이메일 유효성 검사 */
function email_validation(email_0_input, email_1_input) {
	email_0_input.classList.remove('is-valid');
	email_1_input.classList.remove('is-valid');
	const email_check_div = document.querySelector('#email-check-div');
	if (email_check_div != null) {
		email_check_div.remove();
	}
	const email_invalid_div = document.querySelector('#email-invalid-div');
	if (email_invalid_div != null) {
		email_invalid_div.remove();
	}
	const regexp0 = /^[a-zA-Z0-9]+$/;
	const regexp1 = /^[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if (regexp0.test(email_0_input.value) && regexp1.test(email_1_input.value)) {
		email_0_input.closest('.row').querySelector('button').disabled = false;
	} else {
		email_0_input.closest('.row').querySelector('button').disabled = true;
	}
}

/* 이메일 셀렉트 변경 */
function email_select_change(email_1_input) {
	if (email_1_input.value == 'self') {
		const str = '<input type="text" name="memEmailArr" id="join-mem-email-1" class="form-control" onkeyup="email_input_change();" placeholder="직접입력">';
		email_1_input.parentElement.insertAdjacentHTML('beforeend', str);
		email_1_input.remove();
	}
	const email_0_input = document.querySelector('#join-mem-email-0');
	email_validation(email_0_input, email_1_input);
}

/* 이메일 인풋 변경 */
function email_input_change() {
	const email_0_input = document.querySelector('#join-mem-email-0');
	const email_1_input = document.querySelector('#join-mem-email-1');
	email_0_input.value = email_0_input.value.replaceAll(' ', '');
	email_1_input.value = email_1_input.value.replaceAll(' ', '');
	email_validation(email_0_input, email_1_input)
}

/* 메일 인증 클릭 */
function email_pw_send(btn) {
	const email_0_input = document.querySelector('#join-mem-email-0');
	const email_1_input = document.querySelector('#join-mem-email-1');
	btn.disabled = true;
	const email = email_0_input.value + '@' + email_1_input.value;
	$.ajax({
		url: '/member/emailAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'email':email},
		success: function(result) {
			join_email_pw = result;
			let str = '<div id="email-check-div" class="row d-flex align-items-center mt-2">';
			str += '<div class="col-7 text-end">인증 메일이 발송되었습니다.</div>';
			str += '<div class="col-3"><input type="text" id="join-email-pw" class="form-control" placeholder="인증 번호 입력"></div>';
			str += '<div class="col-2 d-grid"><button type="button" class="btn custom-btn" onclick="email_pw_check(this);">확인</button></div></div>';
			btn.closest('.row').insertAdjacentHTML('afterend', str);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 메일 인증 확인 */
function email_pw_check(btn) {
	const email_pw = document.querySelector('#join-email-pw');
	if (email_pw.value == join_email_pw) {
		email_0_input.classList.add('is-valid');
		email_1_input.classList.add('is-valid');
		const email_check_div = document.querySelector('#email-check-div');
		if (email_check_div != null) {
			email_check_div.remove();
		}
		const email_invalid_div = document.querySelector('#email-invalid-div');
		if (email_invalid_div != null) {
			email_invalid_div.remove();
		}
		return;
	}
	
}




/* 주소록 api 사용 */
function search_addr() {
	new daum.Postcode({
		oncomplete: function(data) {
			const road_addr = data.roadAddress;
			document.querySelector('#join-form #join-mem-addr').value = road_addr;
		}
	}).open();
}

/* 회원가입 */
function join() {
	$.ajax({
		url: '/member/joinAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: $('#join-form').serialize(),
		success: function(result) {
			if (result) {
				alert('회원 가입 되었습니다!\n로그인 페이지로 이동합니다.');
				location.href = '/member/loginForm';
			} else {
				alert('회원 가입 실패');
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
