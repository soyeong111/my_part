function updateAnswer(selectedTag, answerCode, qnaCode){
	//수정 버튼 클릭 시
	if(selectedTag.value == '수정'){
		//클릭한 수정 버튼에서 댓글 내용이 있는 태그 찾아가기
		const contentDiv = selectedTag.parentElement.previousElementSibling;
		//댓글 내용 저장(지우기 위해 백업)
		const content = contentDiv.textContent;
	
		//선택한 태그 안의 내용 비우기
		contentDiv.innerHTML = '';
		
		//태그에 input 추가 구문 문자열로 저장
		let str = ``;
		str += `<form id="updateAnswerForm" action="/info/updateAnswer" method="post">`;
		str += `<input type="hidden" value="${qnaCode}" name="qnaCode">`;
		str += `<input type="hidden" value="${answerCode}" name="answerCode">`;
		str += `<textarea  class="form-control" required rows="10" cols="70"  value="${answerContent}" name="answerContent" style="resize: none; ></textarea>`;
		str += `</form>`;
		
		//선택한 태그의 자식태그로 추가
		contentDiv.insertAdjacentHTML('beforebegin', str);
		
		//수정 버튼의 글자 변경
		selectedTag.value = '확인';		
	}else{
		//form태그 submint 시키기
		document.querySelector('#updateAnswerForm').submit();
	}
	
}






function updateQna(selectedTag,  qnaCode){
	//수정 버튼 클릭 시
	if(selectedTag.value == '문의 수정'){
		//클릭한 수정 버튼에서 댓글 내용이 있는 태그 찾아가기
		const contentDiv1 = selectedTag.parentElement.parentElement.previousElementSibling;

		console.log(contentDiv1);
		//댓글 내용 저장(지우기 위해 백업)
		const content = contentDiv1.textContent;
		
		
		//선택한 태그 안의 내용 비우기
		contentDiv1.innerHTML = '';

		
		//태그에 input 추가 구문 문자열로 저장
		let str = ``;
		str += `<form id="updateQnaForm" action="/info/updateQna" method="post">`;
		str += `<input type="hidden" value="${qnaCode}" name="qnaCode">`;
		str += `<textarea  rows="10" cols="50" value="${qnaContent}" name="qnaContent"></textarea>`;
		str += `</form>`;
		
		
		//선택한 태그의 자식태그로 추가
		contentDiv1.insertAdjacentHTML('beforeend', str);
		//수정 버튼의 글자 변경
		selectedTag.value = '확인';		
	}else{
		//form태그 submint 시키기
		document.querySelector('#updateQnaForm').submit();
	}
	
}