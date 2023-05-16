
/* 아이디 유효성 검사 */
function id_validation(id_input) {
	let str = '';
	const regExp = /\b[a-z][a-zA-Z0-9]{2,8}\b/;
	if (regExp) {
		str = '아이디 확인을 눌러주세요.';
	} else {
		str = '아이디는 3~9자리의 영문소문자로 시작하는 영문대소문자 혹은 숫자여야 합니다.';
	}
	
	
}
