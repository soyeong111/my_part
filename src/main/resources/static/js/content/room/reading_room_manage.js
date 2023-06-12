

//전부 퇴실 버튼 클릭 시
function allCheckOut(){
	const allOutMsg = confirm('전부 퇴실시키겠습니까?');
	if(allOutMsg){
		location.href=`/aLibrary/allCheckOut`;
	}
}

//초기화 버튼 클릭 시
function allClear(){
	
}