
function deleteBoard(boardNum, clubCode){
	const result = confirm('게시글을 삭제하시겠습니까?');
	
	if(result){
		location.href=`/club/deleteBoard?boardNum=${boardNum}&clubCode=${clubCode}`;
		
	}
}