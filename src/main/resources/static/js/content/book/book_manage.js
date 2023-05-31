function editBook() {
  const checkedCheckboxes = document.querySelectorAll('#contentTable input.chk:checked');
  const editButton = document.getElementById('editBtn');
  const editMode = editButton.value === '완료';
  
    if (checkedCheckboxes.length === 0) {
    alert('도서를 선택하세요.');
    return;
  }

  checkedCheckboxes.forEach((checkbox) => {
    const row = checkbox.parentNode.parentNode;
    const bookCode = checkbox.value;
    const categoryCell = row.querySelector('td:nth-child(3)');
    const titleCell = row.querySelector('td:nth-child(4)');
    const authorCell = row.querySelector('td:nth-child(5)');
    const publisherCell = row.querySelector('td:nth-child(6)');
    const publicationDateCell = row.querySelector('td:nth-child(7)');
    const isbnCell = row.querySelector('td:nth-child(8)');
    const stockCountCell = row.querySelector('td:nth-child(10)');

    if (editMode) {
      // 완료 버튼을 클릭한 경우
      const bookCateNo = categoryCell.querySelector('input').value;
      const bookTitle = titleCell.querySelector('input').value;
      const bookAuthor = authorCell.querySelector('input').value;
      const bookPublisher = publisherCell.querySelector('input').value;
      const bookPublicationDate = publicationDateCell.querySelector('input').value;
      const isbn = isbnCell.querySelector('input').value;
      const bookStockCnt = stockCountCell.querySelector('input').value;

      // AJAX를 통해 수정된 데이터를 서버에 전송하여 DB에 저장
      $.ajax({
        url: '/book/updateBookAjax',
        type: 'post',
        data: {
          'bookCode': bookCode,
          'bookCateNo': bookCateNo,
          'bookTitle': bookTitle,
          'bookAuthor': bookAuthor,
          'bookPublisher': bookPublisher,
          'bookPublicationDate': bookPublicationDate,
          'isbn': isbn,
          'bookStockCnt': bookStockCnt
        },
        success: function(result) {
          // 성공적으로 업데이트되었을 때의 처리
        },
        error: function(error) {
          // 업데이트 실패 또는 오류 발생시의 처리
        }
      });  

      // 입력 필드의 값을 셀 내부에 반영
      categoryCell.innerHTML = bookCateNo;
      titleCell.innerHTML = bookTitle;
      authorCell.innerHTML = bookAuthor;
      publisherCell.innerHTML = bookPublisher;
      publicationDateCell.innerHTML = bookPublicationDate;
      isbnCell.innerHTML = isbn;
      stockCountCell.innerHTML = bookStockCnt;
    } else {
      // 수정 버튼을 클릭한 경우
      const bookCateNo = categoryCell.innerText;
      const bookTitle = titleCell.innerText;
      const bookAuthor = authorCell.innerText;
      const bookPublisher = publisherCell.innerText;
      const bookPublicationDate = publicationDateCell.innerText;
      const isbn = isbnCell.innerText;
      const bookStockCnt = stockCountCell.innerText;

      // 입력 필드로 변경
      categoryCell.innerHTML = `<input type="text" class="form-control" value="${bookCateNo}">`;
      titleCell.innerHTML = `<input type="text" class="form-control" value="${bookTitle}">`;
      authorCell.innerHTML = `<input type="text" class="form-control"  value="${bookAuthor}">`;
      publisherCell.innerHTML = `<input type="text" class="form-control" value="${bookPublisher}">`;
      publicationDateCell.innerHTML = `<input type="date" class="form-control" value="${bookPublicationDate}">`;
      isbnCell.innerHTML = `<input type="text"class="form-control"  value="${isbn}">`;
      stockCountCell.innerHTML = `<input type="number" class="form-control" value="${bookStockCnt}">`;
    }
  });

  // 버튼 상태 변경
  editButton.value = editMode ? '수정' : '완료';
  editButton.setAttribute('onclick', 'editBook()');
}



//------------삭제-------------



//삭제 버튼 클릭 시 실행
function deleteBook(){
	//체크한 체크박스
	const chks = document.querySelectorAll('.chk:checked');
	const result = confirm('해당 도서를 삭제하시겠습니까?');
	
	if(chks.length == 0){
		alert('선택한 도서가 없습니다.');
		return;
	}
	
	//bookCode를 여러개 담을 수 있는 배열 생성
	const bookCodeArr = [];
	
	chks.forEach(function(chk, index){
		
		bookCodeArr[index] = chk.value;
	});
		
	location.href = `/book/deleteBook?bookCodes=${bookCodeArr}`;
}

	
	
//------------체크박스-------------

	
// 카테고리 제목줄 체크박스 클릭 시 실행
function setCheckStatus1(){
	const checkAll = document.querySelector('#checkAll1');
	const chks = document.querySelectorAll('.chk1');
	
	
	//하나씩 빼서 하나를 true
	if(checkAll.checked){
		for(const chk of chks){
			chk.checked = true;
		}
		
	}else{
		for(const chk of chks){
			chk.checked = false;
	}
	
	}
	
}


//제목줄 체크박스 상태 변경
function setCheckAllStatus1(){
	
	//체크박스 개수
	const cnt = document.querySelectorAll('.chk1').length;
	
	// 체크된 상품의 개수
	const checkedCnt = document.querySelectorAll('.chk1:checked').length;
	const checkAll = document.querySelector('#checkAll1');
	
	if(cnt == checkedCnt){
		checkAll.checked = true;
		
	} else{
		checkAll.checked = false;
		
	}
	
}
	

// 테이블 제목줄 체크박스 클릭 시 실행
function setCheckStatus(){
	const checkAll = document.querySelector('#checkAll');
	const chks = document.querySelectorAll('.chk');
	
	
	//하나씩 빼서 하나를 true
	if(checkAll.checked){
		for(const chk of chks){
			chk.checked = true;
		}
		
	}else{
		for(const chk of chks){
			chk.checked = false;
	}
	
	}
	
}


//제목줄 체크박스 상태 변경
function setCheckAllStatus(){
	
	//체크박스 개수
	const cnt = document.querySelectorAll('.chk').length;
	
	// 체크된 상품의 개수
	const checkedCnt = document.querySelectorAll('.chk:checked').length;
	const checkAll = document.querySelector('#checkAll');
	
	if(cnt == checkedCnt){
		checkAll.checked = true;
		
	} else{
		checkAll.checked = false;
		
	}
	
}
