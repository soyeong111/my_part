
function regBoard() {
  const title_tag = document.querySelector('#title-tag');
  const content_tag = document.querySelector('#content-tag');

  title_tag.value = title_tag.value.trim();
  content_tag.value = content_tag.value.trim();

  if (title_tag.value !== '' && content_tag.value !== '') {
    const reg_form = document.querySelector('#regForm');
    reg_form.submit();
  } else {
    Swal.fire({
      text: '제목과 내용은 필수입력입니다.',
      icon: 'warning',
    });
  }
}