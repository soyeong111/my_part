function regBook() {
  // 입력값 가져오기
  const bookTitle = document.getElementById("#bookTitle").value;
  const bookAuthor = document.getElementById("#bookAuthor").value;
  const bookPublisher = document.getElementById("#bookPublisher").value;
  const isbn = document.getElementById("#isbn").value;
  const bookPublicationDate = document.getElementById("#bookPublicationDate").value;
  const mainImgFile = document.getElementById("#mainImg").files[0];
  const subImgFile = document.getElementById("#subImg").files[0];
  const bookIntro = document.getElementById("#bookIntro").value;





  // 입력값 유효성 검사
  if (bookTitle.trim() === "") {
    // 제목이 비어있는 경우
    alert("제목을 입력해주세요.");
    return;
  }

  if (bookAuthor.trim() === "") {
    // 작가가 비어있는 경우
    alert("작가를 입력해주세요.");
    return;
  }

  if (bookPublisher.trim() === "") {
    // 출판사가 비어있는 경우
    alert("출판사를 입력해주세요.");
    return;
  }

  if (isbn.trim() === "") {
    // ISBN이 비어있는 경우
    alert("ISBN을 입력해주세요.");
    return;
  }

  if (bookPublicationDate.trim() === "") {
    // 발행일이 비어있는 경우
    alert("발행일을 입력해주세요.");
    return;
  }

  if (mainImgFile === undefined) {
    // 앞 이미지가 선택되지 않은 경우
    alert("앞 이미지를 선택해주세요.");
    return;
  }

  if (subImgFile === undefined) {
    // 뒤 이미지가 선택되지 않은 경우
    alert("뒤 이미지를 선택해주세요.");
    return;
  }

  if (bookIntro.trim() === "") {
    // 도서 소개가 비어있는 경우
    alert("도서 소개를 작성해주세요.");
    return;
  }

  const regBook_form = document.querySelector('#regBookForm');
		regBook_form.submit();

}