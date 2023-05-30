function editBook() {
  const checkedCheckboxes = document.querySelectorAll('#contentTable input.chk:checked');

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

    const categoryValue = categoryCell.innerText;
    const titleValue = titleCell.innerText;
    const authorValue = authorCell.innerText;
    const publisherValue = publisherCell.innerText;
    const publicationDateValue = publicationDateCell.innerText;
    const isbnValue = isbnCell.innerText;
    const stockCountValue = stockCountCell.innerText;

    categoryCell.innerHTML = `<input type="text" class="form-control" value="${categoryValue}">`;
    titleCell.innerHTML = `<input type="text" class="form-control" value="${titleValue}">`;
    authorCell.innerHTML = `<input type="text" class="form-control"  value="${authorValue}">`;
    publisherCell.innerHTML = `<input type="text" class="form-control" value="${publisherValue}">`;
    publicationDateCell.innerHTML = `<input type="date" class="form-control" value="${publicationDateValue}">`;
    isbnCell.innerHTML = `<input type="text"class="form-control"  value="${isbnValue}">`;
    stockCountCell.innerHTML = `<input type="number" class="form-control" value="${stockCountValue}">`;

    const editButton = document.getElementById('editBtn');
    editButton.value = '완료';
    editButton.setAttribute('onclick', 'updateBook()');
  });
}

function updateBook() {
  const updatedData = [];

  const updatedCells = document.querySelectorAll('#contentTable td input.form-control');
  updatedCells.forEach((cell) => {
    updatedData.push(cell.value);
    cell.outerHTML = cell.value;
  });

  const editButton = document.getElementById('editBtn');
  editButton.value = '수정';
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
