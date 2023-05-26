function searchQna(){
	
	//분류사항과 검색어를 갖다주기
	const searchOption = document.getElementsByName('searchOption')[0].value;
	const searchText = document.getElementsByName('searchText')[0].value;
	
	alert(searchOption);
	alert(searchText);
	
	//검색된 문의사항으로 채운 테이블 다시 생성
	//ajax start
	$.ajax({
		url: '/info/searchQnaAjax', //요청경로
		type: 'post',
		async: true,
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		data: {
			"searchOption" : searchOption,
			"searchText" : searchText
			
		}, //필요한 데이터
		success: function(data) {
			alert('ajax 통신 성공');
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
};


	//const qnaTableDiv = document.querySelector('#qnaTable');
	//const originContent = qnaTableDiv.textContent;
	
	//qnaTable.innerHTML = '';
	