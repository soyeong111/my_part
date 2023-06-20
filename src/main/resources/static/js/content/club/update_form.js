
function updateBoard(){
	const title_tag = document.querySelector('#title-tag');
    const content_tag = document.querySelector('#content-tag');

    title_tag.value = title_tag.value.trim();
    content_tag.value = content_tag.value.trim();

  	if (title_tag.value !== '' && content_tag.value !== '') {
		const update_form = document.querySelector('#updateForm');
		update_form.submit();
  	} else {
	    Swal.fire({
	      text: '제목과 내용은 필수입력입니다.',
	      icon: 'warning',
	    });
  	}
}