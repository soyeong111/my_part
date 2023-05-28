
let join_email_pw;

/* 아이디 유효성 검사 */
function id_validation(id_input) {
	id_input.value = id_input.value.replaceAll(' ', '');
	id_input.classList.remove('is-valid');
	id_input.classList.add('is-invalid');
	const invalid_div = document.querySelector('#join-mem-id-invalid');
	if (invalid_div != null) {
		invalid_div.remove();
	}
	const regExp = /\b[a-z][a-zA-Z0-9]{2,8}\b/;
	let str = '';
	if (regExp.test(id_input.value)) {
		id_input.closest('.row').querySelector('button').disabled = false;
		str = '<div id="join-mem-id-invalid" class="col-10 offset-2 message-is-invalid">*아이디 확인 버튼을 눌러주세요.</div>';
	} else {
		id_input.closest('.row').querySelector('button').disabled = true;
		str = '<div id="join-mem-id-invalid" class="col-10 offset-2 message-is-invalid">*아이디는 3~9자리의 영문소문자로 시작하는 영문대소문자 혹은 숫자여야 합니다.</div>';
	}
	id_input.closest('.row').insertAdjacentHTML('beforeend', str);
}

/* 아이디 중복 검사 */
function id_duplicate_check(btn) {
	btn.disabled = true;
	const id_input = document.querySelector('#join-mem-id');
	const invalid_div = document.querySelector('#join-mem-id-invalid');
	$.ajax({
		url: '/member/idDuplicateCheckAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memId':id_input.value},
		success: function(result) {
			if(result) {
				id_input.classList.remove('is-invalid');
				id_input.classList.add('is-valid');
				invalid_div.remove();
			} else {
				invalid_div.textContent('*이미 사용중인 아이디입니다.');
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 패스워드 유효성 검사 */
function pw_validation(pw_input) {
	const pw_check_input = document.querySelector('#join-mem-pw-check');
	pw_check_input.value = '';
	pw_check_input.classList.remove('is-invalid');
	pw_check_input.classList.remove('is-valid');
	pw_input.value = pw_input.value.replaceAll(' ', '');
	const invalid_div = document.querySelector('#join-mem-pw-invalid');
	if (invalid_div != null) {
		invalid_div.remove();
	}
	const regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,12}$/;
	if (regExp.test(pw_input.value)) {
		pw_check_input.disabled = false;
		pw_input.classList.remove('is-invalid');
		pw_input.classList.add('is-valid');
	} else {
		pw_check_input.disabled = true;
		pw_input.classList.remove('is-valid');
		pw_input.classList.add('is-invalid');
		const str = '<div id="join-mem-pw-invalid" class="col-10 offset-2 message-is-invalid">*비밀번호는 영문대소문자 + 숫자 + 특수문자 조합의 8~12자리 글자여야 합니다.</div>';
		pw_input.closest('.row').insertAdjacentHTML('beforeend', str);
	}
}

/* 패스워드 확인 유효성 검사 */
function pw_check_validation(pw_check_input) {
	pw_check_input.value = pw_check_input.value.replaceAll(' ', '');
	const invalid_div = document.querySelector('#join-mem-pw-invalid');
	if (invalid_div != null) {
		invalid_div.remove();
	}
	const pw_input = document.querySelector('#join-mem-pw');
	if (pw_input.value == pw_check_input.value) {
		pw_check_input.classList.remove('is-invalid');
		pw_check_input.classList.add('is-valid');
	} else {
		pw_check_input.classList.remove('is-valid');
		pw_check_input.classList.add('is-invalid');
		const str = '<div id="join-mem-pw-invalid" class="col-10 offset-2 message-is-invalid">*비밀번호와 비밀번호 확인이 다릅니다.</div>';
		pw_input.closest('.row').insertAdjacentHTML('beforeend', str);
	}
}

/* 이메일 유효성 검사 */
function email_validation() {
	const email_0_input = document.querySelector('#join-mem-email-0');
	const email_1_input = document.querySelector('#join-mem-email-1');
	email_0_input.value = email_0_input.value.replaceAll(' ', '');
	email_1_input.value = email_1_input.value.replaceAll(' ', '');
	const email = email_0_input.value + '@' + email_1_input.value;
	const regExp = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if (regExp.test(email)) {
		email_0_input.closest('.row').querySelector('button').disabled = false;
	} else {
		email_0_input.closest('.row').querySelector('button').disabled = true;
	}
}

/* 이메일 select 직접입력 선택시 input으로 */
function email_input_change(select_tag) {
	if (select_tag.value == 'self') {
		const str = '<input type="text" name="memEmailArr" id="join-mem-email-1" class="form-control" onkeyup="email_validation();" placeholder="직접입력">';
		select_tag.closest('.input-group').insertAdjacentHTML('beforeend', str);
		select_tag.closest('.row').querySelector('button').disabled = true;
		select_tag.remove();
	}
}

/* 이메일 인증 */
function email_auth(btn) {
	const email_0_input = document.querySelector('#join-mem-email-0');
	const email_1_input = document.querySelector('#join-mem-email-1');
	email_0_input.readOnly = true;
	email_1_input.readOnly = true;
	btn.disabled = true;
	const email = email_0_input.value + '@' + email_1_input.value;
	$.ajax({
		url: '/member/emailAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'email':email},
		success: function(result) {
			join_email_pw = result;
			let str = '<div class="row d-flex align-items-center mt-2">';
			str += '<div class="col-7 text-end">메일을 확인해주세요</div>';
			str += '<div class="col-3"><input type="text" id="join-email-pw" class="form-control" placeholder="인증 번호"></div>';
			str += '<div class="col-2 d-grid"><button type="button" class="btn custom-btn" onclick="email_pw_check(this);">확인</button></div></div>';
			btn.closest('.row').insertAdjacentHTML('afterend', str);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 이메일 인증 확인 ------------ 이메일 인증은 빼자 */
function email_pw_check(btn) {
	const email_pw_input = document.querySelector('#join-email-pw');
	if (join_email_pw == email_pw_input.value) {
		btn.closest('.row').remove();
		document.querySelector('#join-mem-email-0').classList.add('is-valid');
		document.querySelector('#join-mem-email-1').classList.add('is-valid');
		return;
	}
	alert('다시 확인해주세요.');
}

/* 전화번호 유효성 검사 */
function tell_validation(tell_input) {
	tell_input.value = tell_input.value.replaceAll(' ', '');
	const regExp = /^\d{3,4}$/;
	if (regExp.test(tell_input.value)) {
		tell_input.classList.remove('is-invalid');
		tell_input.classList.add('is-valid');
	} else {
		tell_input.classList.remove('is-valid');
		tell_input.classList.add('is-invalid');
	}
}





/* 전화번호 인증 */
function tell_auth(btn) {
	const tell_0 = document.querySelector('#join-mem-tell-0');
	const tell_1 = document.querySelector('#join-mem-tell-1');
	const tell_2 = document.querySelector('#join-mem-tell-2');
	const tell = tell_0.value + tell_1.value + tell_2.value;
	$.ajax({
		url: '/member/tellAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'to':tell},
		success: function(result) {
			alert('ajax 통신 성공');
			console.log(result);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
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

/* 회원가입 버튼 클릭시 */
function join_btn_click() {
	const mem_id_input = document.querySelector('#join-mem-id');
	if (mem_id_input.value == '') {
		mem_id_input.focus();
		return;
	}
	const mem_pw_input = document.querySelector('#join-mem-pw');
	if (mem_pw_input.value == '') {
		mem_pw_input.focus();
		return;
	}
	const mem_pw_check_input = document.querySelector('#join-mem-pw-check');
	if (mem_pw_check_input.value == '') {
		if (mem_pw_check_input.disabled) {
			mem_pw_input.focus();
		} else {
			mem_pw_check_input.focus();
		}
		return;
	}
	const mem_name_input = document.querySelector('#join-mem-name');
	mem_name_input.value = mem_name_input.value.trim();
	if (mem_name_input.value == '') {
		mem_name_input.focus();
		return;
	}
	const mem_email_0_input = document.querySelector('#join-mem-email-0');
	if (mem_email_0_input.value == '') {
		mem_email_0_input.focus();
		return;
	}
	const mem_email_1_input = document.querySelector('#join-mem-email-1');
	if (mem_email_1_input.value == '') {
		mem_email_1_input.focus();
		return;
	}
	const mem_tell_1_input = document.querySelector('#join-mem-tell-1');
	if (mem_tell_1_input.value == '') {
		mem_tell_1_input.focus();
		return;
	}
	const mem_tell_2_input = document.querySelector('#join-mem-tell-2');
	if (mem_tell_2_input.value == '') {
		mem_tell_2_input.focus();
		return;
	}
	const mem_addr_input = document.querySelector('#join-mem-addr');
	if (mem_addr_input.value == '') {
		mem_addr_input.focus();
		return;
	}
	const mem_addr_detail_input = document.querySelector('#join-mem-addr-detail');
	if (mem_addr_detail_input.value == '') {
		mem_addr_detail_input.focus();
		return;
	}
	const is_valid_list = document.querySelectorAll('#join-form .is-valid');
	if (is_valid_list.length != 7) {
		return;
	}
	go_join();
}

/* 회원가입 */
function go_join() {
	$.ajax({
		url: '/member/joinAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: $('#join-form').serialize(),
		success: function(result) {
			if (result) {
				alert('회원 가입 되었습니다!\n로그인 페이지로 이동합니다.\n가입 성공 메일 보내기!!!');
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














