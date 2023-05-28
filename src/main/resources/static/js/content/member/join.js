
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
