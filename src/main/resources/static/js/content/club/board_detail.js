
function deleteBoard(boardNum, clubCode){
	const result = confirm('게시글을 삭제하시겠습니까?');
	
	if(result){
		location.href=`/club/deleteBoard?boardNum=${boardNum}&clubCode=${clubCode}`;
		
	}
}

function updateReply(selectedTag, replyNum, boardNum){
	//수정 버튼 클릭 시
	if(selectedTag.value == '수정'){
		//클릭한 수정버튼에서 댓글 내용이 있는 태그를 찾아가기
		const contentDiv = selectedTag.parentElement.previousElementSibling;

		//댓글 내용 저장
		const content = contentDiv.textContent;

		//선택한 태그 안의 내용 비우기.
		contentDiv.innerHTML = '';

		//태그에 input 태그를 추가
		let str = ``;
		str += '<form id="updateReplyForm" action="/club/updateReply" method="post">';
		str += `<input type="hidden" value="${replyNum}" name="replyNum">`;
		str += `<input type="hidden" value="${boardNum}" name="boardNum">`;
		str += `<input type="text" value="${content}" name="replyContent">`;
		str += '</form>';

		//선택한 태그의 첫번째 자식 태그로 삽입
		contentDiv.insertAdjacentHTML('afterbegin', str);

		//수정 버튼의 글자를 변경
		selectedTag.value = '확인';
	}
	else{
		//form태그를 submit 시킨다.
		document.querySelector('#updateReplyForm').submit();
	}
}