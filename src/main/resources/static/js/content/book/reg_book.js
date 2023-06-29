function regBook() {
	
  // 입력값 가져오기
  const bookTitle = document.querySelector("#bookTitle");
  bookTitle.value = bookTitle.value.trim();
  
  const bookAuthor = document.querySelector("#bookAuthor");
  bookAuthor.value = bookAuthor.value.trim();
  
  const bookPublisher = document.querySelector("#bookPublisher");
  bookPublisher.value = bookPublisher.value.trim();
  
  const isbn = document.querySelector("#isbn");
  isbn.value = isbn.value.trim();
  
  
  
  const mainImgFile = document.querySelector("#mainImg").files[0];
  const subImgFile = document.querySelector("#subImg").files[0];
  
  const bookIntro = document.querySelector("#bookIntro");
  bookIntro.value = bookIntro.value.trim();





  // 입력값 유효성 검사
  if (bookTitle.value === "") {
    // 제목이 비어있는 경우
     Swal.fire("도서명은 필수입니다.");
    return;
  }

  if (bookAuthor.value === "") {
    // 작가가 비어있는 경우
   Swal.fire("작가명은 필수입니다.");
    return;
  }

  if (bookPublisher.value === "") {
    // 출판사가 비어있는 경우
     Swal.fire("출판사는 필수입니다.");
    return;
  }

  if (!/^\d+$/.test(isbn.value)) {
    // ISBN이 비어있는 경우
    Swal.fire("ISBN은 숫자만 입력 가능합니다.");
    return;
  }


  if (mainImgFile) {
    var mainImgFileType = mainImgFile.type;
    if (mainImgFileType !== "image/jpeg") {
      Swal.fire("이미지는 JPG 형식만 가능합니다.");
      return false;
    }
  }

  if (subImgFile) {
    var subImgFileType = subImgFile.type;
    if (subImgFileType !== "image/jpeg") {
      Swal.fire("이미지는 JPG 형식만 가능합니다.");
      return false;
    }
  }
  
  
  if (bookIntro.value === "") {
    // 도서 소개가 비어있는 경우
    Swal.fire("도서 소개는 필수입니다.");
    return;
  }

  
   $.ajax({
        url: '/book/regBookProcessAjax',
        type: 'post',
        //cache: false,
        processData: false,
        contentType: false,
        //data: $('#regBookForm').serialize(),
        data: new FormData($('#regBookForm')[0]),
        success: function(result) {
			if(result == 1){
				 Swal.fire("도서 등록이 완료되었습니다.").then(() => {
				 	location.reload();
				 });
			}
			else{
				Swal.fire("도서 등록이 실패했습니다.");
			}
        },
        error: function() {
           alert('ajax 통신 실패');
        }
     });

  
 
}

