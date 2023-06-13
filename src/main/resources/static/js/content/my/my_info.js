
// 비밀번호 입력 시
function pw_keyup(mem_pw_input) {
	mem_pw_input.value = mem_pw_input.value.replaceAll(' ', '');
}

// 이름 입력 시
function name_keyup(mem_name_input) {
	const regexp = /[^가-힣a-zA-Z ]/;
	
	
	
	
	
	
	mem_name_input.value = mem_name_input.value.replace(/[^가-힣a-zA-Z ]/g, '');
}
