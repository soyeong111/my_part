function updateNotice(selectedTag, noticeNo){
		//수정 버튼 클릭 시
	if(selectedTag.value == '수정'){
		//클릭한 수정 버튼에서 댓글 내용이 있는 태그 찾아가기
		noticeContentDiv = document.querySelector('#noticeContent');
		noticeContent = document.querySelector('#noticeContent').textContent;
		
		//댓글 내용 저장(지우기 위해 백업)
		const content = noticeContent;
		
		//선택한 태그 안의 내용 비우기
		noticeContentDiv.innerHTML = '';

		
		//태그에 input 추가 구문 문자열로 저장
		let str = ``;
		str += `<form id="updateNoticeForm" action="/info/updateNotice" method="post">`;
		str += `<input type="hidden" value="${noticeNo}" name="noticeNo">`;
		str += `<textarea  rows="10" cols="50" value="${noticeContent}" name="noticeContent"></textarea>`;
		str += `</form>`;
		
		
		//선택한 태그의 자식태그로 추가
		noticeContentDiv.insertAdjacentHTML('beforeend', str);
		//수정 버튼의 글자 변경
		selectedTag.value = '확인';		
	}else{
		//form태그 submint 시키기
		alert(1);
		document.querySelector('#updateNoticeForm').submit();
	}
	
}