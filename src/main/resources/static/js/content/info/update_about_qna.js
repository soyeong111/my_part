

function updateQna(selectedTag,  qnaCode, mainMenuCode, subMenuCode){
	//수정 버튼 클릭 시
	if(selectedTag.value == '문의 수정'){
		//클릭한 수정 버튼에서 댓글 내용이 있는 태그 찾아가기
		const contentDiv1 = document.querySelector('.updateForm');
		qnaTitle = contentDiv1.querySelector('.aTitle').textContent;
		qnaContent = document.querySelector('#qnaContent').textContent;
		console.log(contentDiv1);
		//댓글 내용 저장(지우기 위해 백업)
		const content = contentDiv1.textContent;
		
		
		//선택한 태그 안의 내용 비우기
		contentDiv1.innerHTML = '';

		
		//태그에 input 추가 구문 문자열로 저장
		let str = ``;
		str += `<form id="updateForm" action="/info/updateQna" method="post">`;
		str += `<input type="hidden" value="${qnaCode}" name="qnaCode">`;
		str += `<input type="hidden" value="${mainMenuCode}" name="mainMenuCode">`;
		str += `<input type="hidden" value="${subMenuCode}" name="subMenuCode">`;
		str += `<input type="text" class="form-control" name="qnaTitle" value="${qnaTitle}">`;
		//str += `<textarea class="form-control" required rows="2" cols="50" name="qnaTitle" style="resize: none;">${qnaTitle}</textarea>`;
		str += `<textarea class="form-control" required  rows="10" cols="70" name="qnaContent" style="resize: none; margin-top:1rem; margin-bottom: 2rem;">${qnaContent}</textarea>`;
		str += `</form>`;
		
		
		//선택한 태그의 자식태그로 추가
		contentDiv1.insertAdjacentHTML('afterbegin', str);
		//수정 버튼의 글자 변경
		selectedTag.value = '확인';		
	}else{
		//form태그 submint 시키기
		document.querySelector('#updateForm').submit();
	}
	
}





function updateAnswer(selectedTag, answerCode, qnaCode, mainMenuCode, subMenuCode){
	console.log(answerCode);
	console.log(qnaCode);
	//수정 버튼 클릭 시
	if(selectedTag.value == '수정'){
		//클릭한 수정 버튼에서 댓글 내용이 있는 태그 찾아가기
		const contentDiv = document.querySelector('#answerContent');
		console.log(contentDiv);
		//댓글 내용 저장(지우기 위해 백업)
		const answerContent = contentDiv.textContent;
		//선택한 태그 안의 내용 비우기
		contentDiv.innerHTML = '';
	console.log(contentDiv);
		
		//태그에 input 추가 구문 문자열로 저장
		let str = ``;
		str += `<form id="updateAnswerForm" action="/info/updateAnswer" method="post">`;
		str += `<input type="hidden" value="${qnaCode}" name="qnaCode">`;
		str += `<input type="hidden" value="${answerCode}" name="answerCode">`;
		str += `<input type="hidden" value="${mainMenuCode}" name="mainMenuCode">`;
		str += `<input type="hidden" value="${subMenuCode}" name="subMenuCode">`;
		str += `<textarea  class="form-control" required rows="10" cols="50" name="answerContent" style="resize: none;" >${answerContent}</textarea>`;
		str += `</form>`;
		
		//선택한 태그의 자식태그로 추가
		contentDiv.insertAdjacentHTML('afterbegin', str);
		console.log(contentDiv);
		//수정 버튼의 글자 변경
		selectedTag.value = '확인';		
	}else{
		//form태그 submint 시키기
		document.querySelector('#updateAnswerForm').submit();
	}
	
}


//qna 삭제, 강제삭제 버튼 클릭시
function deleteQna(qnaCode, mainMenuCode, subMenuCode){
	const result = confirm('삭제하시겠습니까?');
	if(result){
		alert('삭제되었습니다.');
		location.href = `/info/deleteQna?qnaCode=${qnaCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
	}
	
}

//qna 답변 삭제 버튼 클릭 시
function deleteAnswer(qnaCode, answerCode, mainMenuCode, subMenuCode){
	const result = confirm('삭제하시겠습니까?');
	if(result){
		alert('삭제되었습니다.');
		location.href = `/info/deleteAnswer?qnaCode=${qnaCode}&answerCode=${answerCode}&mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
	}
}












