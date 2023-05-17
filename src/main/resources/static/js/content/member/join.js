
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

/* 이메일 select 직접입력 선택시 input으로 */
function email_input_change(select_tag) {
	if (select_tag.value == 'self') {
		const str = '<input type="text" name="memEmail" id="join-mem-email-1" class="form-control" placeholder="직접입력">';
		select_tag.closest('.input-group').insertAdjacentHTML('beforeend', str);
		select_tag.remove();
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

/* 회원가입 버튼 클릭시 */
function join_btn_click() {
	
	const mem_id = document.querySelector('#join-mem-id');
	if (mem_id == '') {
		
	}
	
	alert(`//${mem_id.value}//`)
	
}














