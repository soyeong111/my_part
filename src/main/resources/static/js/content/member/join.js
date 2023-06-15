
const join_email_obj = {}
const join_tell_obj = {}

/* 타이머 시작 */
function startTimer(obj) {
	obj.minute = 5;
	obj.second = 0;
	obj.timer = setInterval(countTimer, 1000, obj);
}

/* 1초 마다 실행 */
function countTimer(obj) {
	obj.div.textContent = `${obj.minute.toString().padStart(2, '0')}:${obj.second.toString().padStart(2, '0')}`;
	if (obj.second == 0) {
		if (obj.minute > 0) {
			obj.minute--;
			obj.second = 59;
		} else {
			clearInterval(obj.timer);
			const p_div = obj.div.parentElement;
			const s_div = p_div.nextElementSibling;
			p_div.remove();
			if (s_div.firstElementChild.classList.contains('invalid-text-div')) {
				s_div.remove();
			}
		}
	} else {
		obj.second--;
	}
}

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
		str = '<div class="col-10 offset-2 invalid-text-div">*아이디는 3~10자리의, 영문소문자로 시작하는, 영문소문자 혹은 숫자여야합니다.</div>';
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
				id_input.focus();
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
		str = '<div class="col-10 offset-2 invalid-text-div">*패스워드는 8~16자리의, 영문소문자 + 영문대문자 + 특수문자(! @ # $ % &) + 숫자여야합니다.</div>';
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
	const regexp = /[^가-힣a-zA-Z ]/g;
	name_input.value = name_input.value.replace(regexp, '');
	name_input.value = name_input.value.trimStart();
	if (name_input.value == '') {
		name_input.classList.remove('is-valid');
	} else {
		name_input.classList.add('is-valid');
	}
}

/* 이메일 유효성 검사 */
function email_validation() {
	const email_0_input = document.querySelector('#join-mem-email-0');
	const email_1_input = document.querySelector('#join-mem-email-1');
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
function email_select_change(email_select) {
	if (email_select.value == 'self') {
		const str = '<input type="text" name="memEmailArr" id="join-mem-email-1" class="form-control" onkeyup="email_input_change(this);" placeholder="직접입력">';
		email_select.parentElement.insertAdjacentHTML('beforeend', str);
		email_select.remove();
	}
	email_validation();
}

/* 이메일 인풋 변경 */
function email_input_change(email_input) {
	email_input.value = email_input.value.replaceAll(' ', '');
	email_validation()
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
			join_email_obj.pw = result;
			let str = '<div id="email-check-div" class="row d-flex align-items-center mt-2">';
			str += '<div class="col-6 text-end">인증 메일이 발송되었습니다.</div>';
			str += '<div id="email-timer-div" class="col-1 text-end invalid-text-div"></div>';
			str += '<div class="col-3"><input type="text" id="join-email-pw" class="form-control" placeholder="인증 번호 입력"></div>';
			str += '<div class="col-2 d-grid"><button type="button" class="btn custom-btn" onclick="email_pw_check(this);">확인</button></div></div>';
			btn.closest('.row').insertAdjacentHTML('afterend', str);
			join_email_obj.div = document.querySelector('#email-timer-div');
			startTimer(join_email_obj);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 메일 인증 확인 */
function email_pw_check(btn) {
	const email_check_div = document.querySelector('#email-check-div');
	const email_invalid_div = document.querySelector('#email-invalid-div');
	if (email_invalid_div != null) {
		email_invalid_div.remove();
	}
	const email_pw_input = document.querySelector('#join-email-pw');
	if (email_pw_input.value == join_email_obj.pw) {
		const email_0_input = document.querySelector('#join-mem-email-0');
		const email_1_input = document.querySelector('#join-mem-email-1');
		email_0_input.classList.add('is-valid');
		email_1_input.classList.add('is-valid');
		email_check_div.remove();
		return;
	}
	let str = '<div id="email-invalid-div" class="row mt-2">';
	str += '<div class="col-10 text-end invalid-text-div">*인증 번호가 일치하지 않습니다.</div></div>';
	btn.closest('.row').insertAdjacentHTML('afterend', str);
	email_check_div.querySelector('input').focus();
}

/* 전화번호 유효성 검사 */
function tell_validation() {
	const tell_0_input = document.querySelector('#join-mem-tell-0');
	const tell_1_input = document.querySelector('#join-mem-tell-1');
	const tell_2_input = document.querySelector('#join-mem-tell-2');
	tell_0_input.classList.remove('is-valid');
	tell_1_input.classList.remove('is-valid');
	tell_2_input.classList.remove('is-valid');
	const tell_check_div = document.querySelector('#tell-check-div');
	if (tell_check_div != null) {
		tell_check_div.remove();
	}
	const tell_invalid_div = document.querySelector('#tell-invalid-div');
	if (tell_invalid_div != null) {
		tell_invalid_div.remove();
	}
	const regexp1 = /^[0-9]{3,4}$/;
	const regexp2 = /^[0-9]{4}$/;
	if (regexp1.test(tell_1_input.value) && regexp2.test(tell_2_input.value)) {
		tell_0_input.closest('.row').querySelector('button').disabled = false;
	} else {
		tell_0_input.closest('.row').querySelector('button').disabled = true;
	}
}

/* 전화번호 인풋 변경 */
function tell_change(tell_input) {
	tell_input.value = tell_input.value.replace(/\D/, '');
	tell_validation();
}

/* 전화번호 인증 클릭 */
function tell_pw_send(btn) {
	const tell_0_input = document.querySelector('#join-mem-tell-0');
	const tell_1_input = document.querySelector('#join-mem-tell-1');
	const tell_2_input = document.querySelector('#join-mem-tell-2');
	btn.disabled = true;
	const tell = tell_0_input.value + tell_1_input.value + tell_2_input.value;
	$.ajax({
		url: '/member/tellAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'to':tell},
		success: function(result) {
			join_tell_obj.pw = result;
			let str = '<div id="tell-check-div" class="row d-flex align-items-center mt-2">';
			str += '<div class="col-6 text-end">인증 문자가 발송되었습니다.</div>';
			str += '<div id="tell-timer-div" class="col-1 text-end invalid-text-div"></div>';
			str += '<div class="col-3"><input type="text" id="join-tell-pw" class="form-control" placeholder="인증 번호 입력"></div>';
			str += '<div class="col-2 d-grid"><button type="button" class="btn custom-btn" onclick="tell_pw_check(this);">확인</button></div></div>';
			btn.closest('.row').insertAdjacentHTML('afterend', str);
			join_tell_obj.div = document.querySelector('#tell-timer-div');
			startTimer(join_tell_obj);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 전화번호 인증 확인 */
function tell_pw_check(btn) {
	const tell_check_div = document.querySelector('#tell-check-div');
	const tell_invalid_div = document.querySelector('#tell-invalid-div');
	if (tell_invalid_div != null) {
		tell_invalid_div.remove();
	}
	const tell_pw_input = document.querySelector('#join-tell-pw');
	if (tell_pw_input.value == join_tell_obj.pw) {
		const tell_0_input = document.querySelector('#join-mem-tell-0');
		const tell_1_input = document.querySelector('#join-mem-tell-1');
		const tell_2_input = document.querySelector('#join-mem-tell-2');
		tell_0_input.classList.add('is-valid');
		tell_1_input.classList.add('is-valid');
		tell_2_input.classList.add('is-valid');
		tell_check_div.remove();
		return;
	}
	let str = '<div id="tell-invalid-div" class="row mt-2">';
	str += '<div class="col-10 text-end invalid-text-div">*인증 번호가 일치하지 않습니다.</div></div>';
	btn.closest('.row').insertAdjacentHTML('afterend', str);
	tell_check_div.querySelector('input').focus();
}

/* 주소록 api 사용 */
function search_addr() {
	const addr_input = document.querySelector('#join-mem-addr');
	new daum.Postcode({
		oncomplete: function(data) {
			const road_addr = data.roadAddress;
			addr_input.value = road_addr;
		},
		onclose: function(state) {
			if (addr_input.value != '') {
				addr_input.classList.add('is-valid');
			}
		}
	}).open();
}

/* 상세주소 유효성 검사  */
function addr_validation(addr_input) {
	addr_input.value = addr_input.value.trimStart();
	if (addr_input.value == '') {
		addr_input.classList.remove('is-valid');
	} else {
		addr_input.classList.add('is-valid');
	}
}

/* 필수 사항 체크 */
function require_check() {
	document.querySelector('#join-mem-name').value = document.querySelector('#join-mem-name').value.trim();
	document.querySelector('#join-mem-addr-detail').value = document.querySelector('#join-mem-addr-detail').value.trim();
	const tags = document.querySelectorAll('.is-valid');
	if (tags.length == 11) {
		join();
	} else {
		alert('생년월일과 성별을 제외한\n모든 정보는 필수 입력 사항입니다.');
	}
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
