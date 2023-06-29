
const email_obj = {}
const tell_obj = {}

/* 타이머 시작 */
function startTimer(obj) {
	obj.minute = 5;
	obj.second = 0;
	obj.timer = setInterval(countTimer, 1000, obj);
}

/* 1초 마다 실행 */
function countTimer(obj) {
	obj.span.replaceChildren();
	obj.span.textContent = `${obj.minute.toString().padStart(2, '0')}:${obj.second.toString().padStart(2, '0')}`;
	if (obj.second == 0) {
		if (obj.minute > 0) {
			obj.minute--;
			obj.second = 59;
		} else {
			clearInterval(obj.timer);
			obj.div.remove();
		}
	} else {
		obj.second--;
	}
}

// 필수 입력 사항 체크
function require_check(input) {
	if (input.value == '') {
		input.classList.remove('is-valid');
		input.classList.add('is-invalid');
	} else {
		input.classList.remove('is-invalid');
		input.classList.add('is-valid');
	}
}

// 기존 데이터와 비교
function origin_check(input) {
	const result = input.value == input.dataset.origin;
	if (result) {
		input.classList.remove('is-valid');
		input.classList.remove('is-invalid');
	}
	return result;
}

// 비밀번호 입력 시
function pw_keyup(pw_input) {
	pw_input.value = pw_input.value.replaceAll(' ', '');
	require_check(pw_input);
}

// 이름 입력 시
function name_keyup(name_input) {
	name_input.value = name_input.value.replace(/[^가-힣a-zA-Z ]/g, '');
	name_input.value = name_input.value.trimStart();
	if (origin_check(name_input)) {
		return;
	}
	require_check(name_input);
}

// 이메일 입력 시
function email_keyup() {
	const email_check_div = document.querySelector('#email-check-div');
	if (email_check_div) {
		email_check_div.remove();
	}
	const email_0_input = document.querySelector('#mem-email-0');
	const email_1_input = document.querySelector('#mem-email-1');
	email_0_input.value = email_0_input.value.replaceAll(' ', '');
	email_1_input.value = email_1_input.value.replaceAll(' ', '');
	if (origin_check(email_0_input) && origin_check(email_1_input)) {
		email_0_input.classList.remove('is-valid');
		email_0_input.classList.remove('is-invalid');
		email_1_input.classList.remove('is-valid');
		email_1_input.classList.remove('is-invalid');
		return;
	}
	email_0_input.classList.remove('is-valid');
	email_0_input.classList.add('is-invalid');
	email_1_input.classList.remove('is-valid');
	email_1_input.classList.add('is-invalid');
	const regexp0 = /^[a-zA-Z0-9]+$/;
	const regexp1 = /^[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if (regexp0.test(email_0_input.value) && regexp1.test(email_1_input.value)) {
		draw_email_check();	
	}
}

// 이메일 인증 화면 그리기
function draw_email_check() {
	let str = '<div id="email-check-div" class="offset-1 col-9 mt-2">';
	str += '<div class="input-group">';
	str += '<span class="input-group-text w-25">인증번호</span>';
	str += '<input type="text" id="email-pw-input" class="form-control" disabled>';
	str += '<button type="button" class="btn custom-btn" onclick="send_email_pw(this);">발송</button>';
	str += '</div>';
	str += '</div>';
	const email_check_div = document.querySelector('#email-check-div')
	if (email_check_div != null) {
		email_check_div.remove();
	}
	const email_div = document.querySelector('#email-div');
	email_div.insertAdjacentHTML('afterend', str);
}

// 이메일 인증 번호 발송
function send_email_pw(btn) {
	btn.disabled = true;
	str = '<span id="email-timer-span" class="input-group-text">';
	str += '<span class="spinner-border spinner-border-sm text-secondary" role="status">';
	str += '<span class="visually-hidden"></span>';
	str += '</span>';
	str += '</span>';
	btn.insertAdjacentHTML('beforebegin', str);
	const email_0_input = document.querySelector('#mem-email-0');
	const email_1_input = document.querySelector('#mem-email-1');
	const email = email_0_input.value + '@' + email_1_input.value;
	$.ajax({
		url: '/mMember/emailAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'email':email},
		success: function(result) {
			email_obj.pw = result;
			email_obj.div = document.querySelector('#email-check-div');
			email_obj.span = document.querySelector('#email-timer-span');
			startTimer(email_obj);
			document.querySelector('#email-pw-input').disabled = false;
			const str = '<button type="button" class="btn custom-btn" onclick="email_pw_check();">확인</button>';
			btn.insertAdjacentHTML('afterend', str);
			btn.remove();
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 이메일 인증 번호 확인
function email_pw_check() {
	const email_pw_input = document.querySelector('#email-pw-input');
	if (email_pw_input.value == email_obj.pw) {
		const email_0_input = document.querySelector('#mem-email-0');
		const email_1_input = document.querySelector('#mem-email-1');
		email_0_input.classList.remove('is-invalid');
		email_0_input.classList.add('is-valid');
		email_1_input.classList.remove('is-invalid');
		email_1_input.classList.add('is-valid');
		document.querySelector('#email-check-div').remove();
		clearInterval(email_obj.timer);
	} else {
		email_pw_input.classList.add('is-invalid');
	}
}

// 연락처 입력 시
function tell_keyup() {
	const tell_check_div = document.querySelector('#tell-check-div');
	if (tell_check_div) {
		tell_check_div.remove();
	}
	const tell_0_tag = document.querySelector('#mem-tell-0');
	const tell_1_tag = document.querySelector('#mem-tell-1');
	const tell_2_tag = document.querySelector('#mem-tell-2');
	tell_1_tag.value = tell_1_tag.value.replace(/\D/g, '');
	tell_2_tag.value = tell_2_tag.value.replace(/\D/g, '');
	if (origin_check(tell_0_tag) && origin_check(tell_1_tag) && origin_check(tell_2_tag)) {
		tell_0_tag.classList.remove('is-valid');
		tell_0_tag.classList.remove('is-invalid');
		tell_1_tag.classList.remove('is-valid');
		tell_1_tag.classList.remove('is-invalid');
		tell_2_tag.classList.remove('is-valid');
		tell_2_tag.classList.remove('is-invalid');
		return;
	}
	tell_0_tag.classList.remove('is-valid');
	tell_0_tag.classList.add('is-invalid');
	tell_1_tag.classList.remove('is-valid');
	tell_1_tag.classList.add('is-invalid');
	tell_2_tag.classList.remove('is-valid');
	tell_2_tag.classList.add('is-invalid');
	const regexp1 = /^[0-9]{3,4}$/;
	const regexp2 = /^[0-9]{4}$/;
	if (regexp1.test(tell_1_tag.value) && regexp2.test(tell_2_tag.value)) {
		draw_tell_check();	
	}
}

// 연락처 인증 화면 그리기
function draw_tell_check() {
	let str = '<div id="tell-check-div" class="offset-1 col-9 mt-2">';
	str += '<div class="input-group">';
	str += '<span class="input-group-text w-25">인증번호</span>';
	str += '<input type="text" id="tell-pw-input" class="form-control" disabled>';
	str += '<button type="button" class="btn custom-btn" onclick="send_tell_pw(this);">발송</button>';
	str += '</div>';
	str += '</div>';
	const tell_check_div = document.querySelector('#tell-check-div')
	if (tell_check_div != null) {
		tell_check_div.remove();
	}
	const tell_div = document.querySelector('#tell-div');
	tell_div.insertAdjacentHTML('afterend', str);
}

// 연락처 인증 번호 발송
function send_tell_pw(btn) {
	btn.disabled = true;
	str = '<span id="tell-timer-span" class="input-group-text">';
	str += '<span class="spinner-border spinner-border-sm text-secondary" role="status">';
	str += '<span class="visually-hidden"></span>';
	str += '</span>';
	str += '</span>';
	btn.insertAdjacentHTML('beforebegin', str);
	const tell_0_tag = document.querySelector('#mem-tell-0');
	const tell_1_tag = document.querySelector('#mem-tell-1');
	const tell_2_tag = document.querySelector('#mem-tell-2');
	const tell = tell_0_tag.value + tell_1_tag.value + tell_2_tag.value;
	$.ajax({
		url: '/mMember/tellAuthAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'to':tell},
		success: function(result) {
			tell_obj.pw = result;
			tell_obj.div = document.querySelector('#tell-check-div');
			tell_obj.span = document.querySelector('#tell-timer-span');
			startTimer(tell_obj);
			document.querySelector('#tell-pw-input').disabled = false;
			const str = '<button type="button" class="btn custom-btn" onclick="tell_pw_check();">확인</button>';
			btn.insertAdjacentHTML('afterend', str);
			btn.remove();
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 연락처 인증 번호 확인
function tell_pw_check() {
	const tell_pw_input = document.querySelector('#tell-pw-input');
	if (tell_pw_input.value == tell_obj.pw) {
		const tell_0_tag = document.querySelector('#mem-tell-0');
		const tell_1_tag = document.querySelector('#mem-tell-1');
		const tell_2_tag = document.querySelector('#mem-tell-2');
		tell_0_tag.classList.remove('is-invalid');
		tell_0_tag.classList.add('is-valid');
		tell_1_tag.classList.remove('is-invalid');
		tell_1_tag.classList.add('is-valid');
		tell_2_tag.classList.remove('is-invalid');
		tell_2_tag.classList.add('is-valid');
		document.querySelector('#tell-check-div').remove();
		clearInterval(tell_obj.timer);
	} else {
		tell_pw_input.classList.add('is-invalid');
	}
}

/* 주소록 api 사용 */
function search_addr() {
	const addr_input = document.querySelector('#mem-addr');
	new daum.Postcode({
		oncomplete: function(data) {
			const road_addr = data.roadAddress;
			addr_input.value = road_addr;
		},
		onclose: function(state) {
			if (origin_check(addr_input)) {
				return;
			}
			require_check(addr_input);
		}
	}).open();
}

// 상세 주소 입력 시
function addr_detail_keyup(addr_detail_input) {
	addr_detail_input.value = addr_detail_input.value.trimStart();
	if (origin_check(addr_detail_input)) {
		return;
	}
	require_check(addr_detail_input);
}

// 수정 전 확인
function update_check() {
	document.querySelector('#mem-name').value = document.querySelector('#mem-name').value.trim();
	document.querySelector('#mem-addr-detail').value = document.querySelector('#mem-addr-detail').value.trim();
	const tags = document.querySelectorAll('.is-invalid');
	if (tags.length != 0) {
		tags[0].focus();
		return;
	}
	const mem_pw_input = document.querySelector('#mem-pw');
	$.ajax({
		url: '/mMember/checkPwAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'memPw':mem_pw_input.value},
		success: function(result) {
			if (result) {
				update_member();
			} else {
				mem_pw_input.classList.remove('is-valid');
				mem_pw_input.classList.add('is-invalid');
				mem_pw_input.focus();
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 수정
function update_member() {
	$.ajax({
		url: '/mMember/updateMemberAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: $('#my-info-form').serialize(),
		success: function(result) {
			const main_menu_code = document.querySelector('#main-menu-code').value;
			const sub_menu_code = document.querySelector('#sub-menu-code').value;
			if (result) {
				Swal.fire({
					icon: 'success',
					title: '내 정보 변경',
					text: '내 정보가 변경되었습니다.',
				}).then(() => {
					location.href = `/mMember/myInfo?mainMenuCode=${main_menu_code}&subMenuCode=${sub_menu_code}`;
				});
			} else {
				Swal.fire({
					icon: 'error',
					title: '내 정보 변경 실패',
				});
			}
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}
