
let minute;
let second;
let timer;

let auth_pw;

init();

/* 초기 설정 함수 */
function init() {
	draw_tell_find_id();
}

/* 타이머 시작 */
function startTimer() {
	minute = 5;
	second = 0;
	timer = setInterval(countTimer, 1000);
}

/* 1초 마다 실행 */
function countTimer() {
	const auth_pw_label = document.querySelector('#auth-pw-label');
	auth_pw_label.textContent = `${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
	if (second == 0) {
		if (minute > 0) {
			minute--;
			second = 59;
		} else {
			clearInterval(timer);
			location.href = '/member/findIdForm';
		}
	} else {
		second--;
	}
}

/* 연락처로 아이디 찾기 화면 그리기 */
function draw_tell_find_id() {
	auth_pw = '';
	document.querySelector('#find-id-value-input').value = '';
	let str = '<div class="col-12">';
	str += '<div class="input-group">';
	str += '<select id="find-id-val-tell0" class="form-select">';
	str += '<option value="010">010</option>';
	str += '<option value="011">011</option>';
	str += '</select>';
	str += '<span class="input-group-text">-</span>';
	str += '<input type="text" id="find-id-val-tell1" class="form-control" onkeyup="tell_keyup(this);" maxlength="4">';
	str += '<span class="input-group-text">-</span>';
	str += '<input type="text" id="find-id-val-tell2" class="form-control" onkeyup="tell_keyup(this);" maxlength="4">';
	str += '</div>';
	str += '</div>';
	str += '<div class="col-12 d-grid mt-5">';
	str += '<button type="button" class="btn btn-primary custom-btn" onclick="tell_find_id();">아이디 찾기</button>';
	str += '</div>';
	const find_id_change_div = document.querySelector('.find-id-change-div');
	find_id_change_div.replaceChildren();
	find_id_change_div.insertAdjacentHTML('afterbegin', str);
}

/* 이메일로 아이디 찾기 화면 그리기 */
function draw_email_find_id() {
	auth_pw = '';
	document.querySelector('#find-id-value-input').value = '';
	let str = '<div class="col-12">';
	str += '<span style="display:none;"><input type="text"></span>';
	str += '<div class="input-group">';
	str += '<input type="text" id="find-id-val-email0" class="form-control" onkeyup="email_keyup(this);">';
	str += '<span class="input-group-text">@</span>';
	str += '<select id="find-id-val-email1" class="form-select" onchange="email_selected(this);">';
	str += '<option value="naver.com">naver.com</option>';
	str += '<option value="gmail.com">gmail.com</option>';
	str += '<option value="self">직접입력</option>';
	str += '</select>';
	str += '</div>';
	str += '</div>';
	str += '<div class="col-12 d-grid mt-5">';
	str += '<button type="button" class="btn btn-primary custom-btn" onclick="email_find_id();">아이디 찾기</button>';
	str += '</div>';
	const find_id_change_div = document.querySelector('.find-id-change-div');
	find_id_change_div.replaceChildren();
	find_id_change_div.insertAdjacentHTML('afterbegin', str);
}

/* 인증 번호 확인 화면 그리기 */
function draw_auth_pw_check() {
	let str = '<div class="col-12">';
	str += '<span style="display:none;"><input type="text"></span>';
	str += '<div class="input-group">';
	str += '<span class="input-group-text">인증 번호</span>';
	str += '<div class="form-floating">';
	str += '<input type="text" id="auth-pw-input" class="form-control">';
	str += '<label for="auth-pw-input" id="auth-pw-label"></label>';
	str += '</div>';
	str += '</div>';
	str += '<div class="col-12 d-grid mt-5">';
	str += '<button type="button" class="btn btn-primary custom-btn" onclick="auth_pw_check();">인증 확인</button>';
	str += '</div>';
	str += '</div>';
	const find_id_change_div = document.querySelector('.find-id-change-div');
	find_id_change_div.replaceChildren();
	find_id_change_div.insertAdjacentHTML('afterbegin', str);
	startTimer();
}

/* 인증 번호 확인 */
function auth_pw_check() {
	const auth_pw_input = document.querySelector('#auth-pw-input');
	if (auth_pw_input.value == auth_pw) {
		clearInterval(timer);
		find_id();
	} else {
		auth_pw_input.classList.add('is-invalid');
		auth_pw_input.focus();
	}
}

/* 연락처 입력 시 */
function tell_keyup(tell_input) {
	tell_input.value = tell_input.value.replace(/\D/, '');
}

/* 이메일 셀렉트 선택 시 */
function email_selected(email_select) {
	if (email_select.value == 'self') {
		const str = '<input type="text" id="find-id-val-email1" class="form-control" onkeyup="email_keyup(this);">';
		email_select.insertAdjacentHTML('beforebegin', str);
		email_select.remove();
	}
}

/* 이메일 입력 시 */
function email_keyup(email_input) {
	email_input.value = email_input.value.replaceAll(' ', '');
}

/* 연락처로 아이디 찾기 버튼 클릭 시 */
function tell_find_id() {
	const tell0_tag = document.querySelector('#find-id-val-tell0');
	const tell1_tag = document.querySelector('#find-id-val-tell1');
	const tell2_tag = document.querySelector('#find-id-val-tell2');
	const regexp1 = /^\d{3,4}$/;
	const regexp2 = /^\d{4}$/;
	if (!regexp1.test(tell1_tag.value)) {
		tell1_tag.focus();
		return;
	}
	if (!regexp2.test(tell2_tag.value)) {
		tell2_tag.focus();
		return;
	}
	document.querySelector('#find-id-value-input').value = `${tell0_tag.value}-${tell1_tag.value}-${tell2_tag.value}`;
	tell_auth(tell0_tag.value + tell1_tag.value + tell2_tag.value);
}

/* 연락처로 인증 Ajax */
function tell_auth(tell) {
	$.ajax({
		url: '/member/tellAuthAjax',
		type: 'post',
		async: false,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'to':tell},
		success: function(result) {
			auth_pw = result;
			draw_auth_pw_check();
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 이메일로 아이디 찾기 버튼 클릭 시 */
function email_find_id() {
	const email0_tag = document.querySelector('#find-id-val-email0');
	const email1_tag = document.querySelector('#find-id-val-email1');
	const regexp0 = /^[a-zA-Z0-9]+$/;
	const regexp1 = /^[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	if (!regexp0.test(email0_tag.value)) {
		email0_tag.focus();
		return;
	}
	if (!regexp1.test(email1_tag.value)) {
		email1_tag.focus();
		return;
	}
	document.querySelector('#find-id-value-input').value = email0_tag.value + '@' + email1_tag.value;
	email_auth(email0_tag.value + '@' + email1_tag.value);
}

/* 이메일로 인증 Ajax */
function email_auth(email) {
	$.ajax({
		url: '/member/emailAuthAjax',
		type: 'post',
		async: false,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {'email':email},
		success: function(result) {
			auth_pw = result;
			draw_auth_pw_check();
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

/* 아이디 찾기 */
function find_id() {
	$.ajax({
		url: '/member/findIdAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: $('#find-id-form').serialize(),
		success: function(result) {
			auth_pw = '';
			document.querySelector('#find-id-value-input').value = '';
			let str = '';
			if (result.length == 0) {
				str += '<div class="col-12 text-center mem-none-div">';
				str += '가입된 회원이 없습니다.';
				str += '</div>';
			} else {
				str += `<table class="table table-hover text-center w-100">`;
				str += `<thead class="thead-bg-color">`;
				str += `<tr>`;
				str += `<th>아이디</th>`;
				str += `<th>가입날짜</th>`;
				str += `</tr>`;
				str += `</thead>`;
				str += `<tbody>`;
				result.forEach((member) => {
					str += `<tr>`;
					str += `<td>${member['memId']}</td>`;
					str += `<td>${member['memJoinDate']}</td>`;
					str += `</tr>`;
				});
				str += `</tbody>`;
				str += `</table>`;
			}
			str += '<div class="col-12 mt-5 text-center">';
			str += '<a href="/member/findIdForm">아이디 찾기</a>';
			str += '<span class="m-2">/</span>';
			str += '<a href="/member/findPwForm">비밀번호 찾기</a>';
			str += '</div>';
			const find_id_change_div = document.querySelector('.find-id-change-div');
			find_id_change_div.replaceChildren();
			find_id_change_div.insertAdjacentHTML('afterbegin', str);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

