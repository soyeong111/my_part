
// 대여하기 버튼 클릭 시 실행
function borrow(memId, bookCode) {
	if (memId == 'anonymousUser') {
		const result = confirm('먼저 로그인 하세요. \n로그인 하시겠습니까?');

		if (result) {
			// 로그인 페이지로 이동
			location.href = '/member/loginForm';
		}

		// 로그인 체크
		return;
	}

	borrowAjax(bookCode);

}

    
function borrowAjax(bookCode) {



	//ajax start
	$.ajax({
		url: '/book/borrowAjax', //요청경로
		type: 'post',
		data: {'bookCode': bookCode}, //필요한 데이터
		success: function(result) {

			const result1 = confirm('도서가 성공적으로 대여되었습니다.')

			if (result1) {
				location.href = `/book/bookDetail?bookCode=${bookCode}`;

			}
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end




}


