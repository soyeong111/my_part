
//북클럽 만들기 버튼 클릭 시 실행
function regClub(memId){
	
	if(memId == 'anonymousUser'){
		const result = confirm('로그인 후 이용가능합니다.\n로그인 하시겠습니까?');
		
		if(result){
			location.href = `/member/loginForm`;
		}
		return ;
	}
	
	location.href = '/club/regClub';
}