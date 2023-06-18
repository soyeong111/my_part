
//클럽 수정하기 버튼 클릭 시
function updateClub(){
	const short_info_tag = document.querySelector('#clubShortInfo');
	const intro_tag = document.querySelector('#clubIntro');

	short_info_tag.value = short_info_tag.value.trim();
	intro_tag.value = intro_tag.value.trim();
	
	if (short_info_tag.value !== '' && intro_tag.value !== '') {
	    const update_form = document.querySelector('#updateClubForm');
	    update_form.submit();
	} else {
	    Swal.fire({
	      text: '한 줄 소개, 북클럽 소개는 필수입력입니다.',
	      icon: 'warning',
	    });
	}
}
		
	
	