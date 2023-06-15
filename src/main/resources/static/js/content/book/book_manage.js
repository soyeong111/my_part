function editBook() {
  const checkedCheckboxes = document.querySelectorAll('#contentTable input.chk:checked');
  const editButton = document.getElementById('editBtn');
  const editMode = editButton.value === '완료';
	
	if (checkedCheckboxes.length === 0) {
	  swal({
	    title: "선택된 도서가 없습니다.",
	    text: "도서를 선택해주세요.",
	    icon: "warning" //"info,success,warning,error" 중 택1
	  });
	  return;
	}
	
	
	
  let categoryList;
  if(!editMode){
	
	//ajax start
   $.ajax({
      url: '/book/categoryListAjax', //요청경로
      type: 'post',
      async : false,
      contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
      data: {}, //필요한 데이터
      success: function(result) {
         console.log(result[0]['bookCateStr']);
         categoryList = result;
      },
      error: function() {
         alert('실패');
      }
   });
   //ajax end
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
      const bookCateNo = categoryCell.querySelector('select').value;
      const bookTitle = titleCell.querySelector('input').value;
      const bookAuthor = authorCell.querySelector('input').value;
      const bookPublisher = publisherCell.querySelector('input').value;
      const bookPublicationDate = publicationDateCell.querySelector('input').value;
      const isbn = isbnCell.querySelector('input').value;
      const bookStockCnt = stockCountCell.querySelector('input').value;

      // AJAX
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
		location.href = "/book/bookManage";
		
        },
        error: function(error) {
          // 업데이트 실패 또는 오류 발생시의 처리
        }
      });  

     
      
    } else {
	
      // 수정 버튼을 클릭한 경우
      const bookCateNo = categoryCell.dataset.bookno;
      const bookTitle = titleCell.innerText;
      const bookAuthor = authorCell.innerText;
      const bookPublisher = publisherCell.innerText;
      const bookPublicationDate = publicationDateCell.innerText;
      const isbn = isbnCell.innerText;
      const bookStockCnt = stockCountCell.innerText;


      // 입력 필드로 변경
      
     let str = `<select name="bookCateNo" class="form-select">`;

			for (let i = 0; i < categoryList.length; i++) {
			  str += `<option ${bookCateNo == categoryList[i].bookCateNo ? 'selected' : ''} 
			  value="${categoryList[i].bookCateNo}">${categoryList[i].bookCateStr}</option>`;
			}
			
			str += `</select>`;

      
      categoryCell.replaceChildren();
	  categoryCell.insertAdjacentHTML('afterbegin', str);
	   
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
	
	
	if (chks.length == 0) {
	  swal({
	    title: "선택된 도서가 없습니다.",
	    text: "도서를 선택해주세요.",
	    icon: "warning" //"info,success,warning,error" 중 택1
	  });
	  return;
	}
	
		swal({
		title: "도서 삭제",
		text: "해당 도서를 삭제하시겠습니까?",
		icon: "error",
		buttons: ["취소", "삭제"],
		dangerMode: true,
	})
	.then((confirmed) => {
		if (confirmed) {
			//bookCode를 여러개 담을 수 있는 배열 생성
			const bookCodeArr = [];
			
			chks.forEach(function(chk, index){
				bookCodeArr[index] = chk.value;
			});
				
			location.href = `/book/deleteBook?bookCodes=${bookCodeArr}`;
		}
	});
}

//-----이미지 모달창-----

//이미지 팝업 모달
const imgModal = new bootstrap.Modal('#imgModal');  	//부트스트랩 내용



//이미지명 클릭 시 이미지 모달 띄우기
function openImgModal(attachedFileName, originFileName){
	
	//모달 안에서 보여질 이미지 정보 세팅
	const modalTag = document.querySelector('#imgModal');		//
	modalTag.querySelector('img').src = `/image/book/${attachedFileName}`;								//img 태그 자체

	//제목 세팅
	modalTag.querySelector('h1').textContent = originFileName;

	
	//모달 오픈
	imgModal.show();									//부트스트랩 내용
}


function getBookDetail(bookCode) {
  // ajax start
  $.ajax({
    url: '/book/imgListAjax', // 요청경로
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'bookCode': bookCode }, // 도서 코드 전달
    success: function(result) {
      console.log(result); // 이미지 데이터 확인

      //const imgList = result.imgList; // 이미지 데이터

      const bookDetailDiv = document.querySelector('#bookDetailDiv');
      bookDetailDiv.replaceChildren();
		
	// 숨길 버튼 요소들을 가져옴
      const editBtn = document.querySelector('#editBtn');
      const deleteBtn = document.querySelector('.btn.custom-btn-del');

      // 버튼들을 숨김
      editBtn.style.display = 'none';
      deleteBtn.style.display = 'none';
      
      let str = '';

		// 메인 이미지 처리
		str += `    <div class="row">                                                           `;
		str += `       <div class="col sub-title">                                              `;
		str += `          <h5>도서 이미지</h5>                                                   `;
		str += `       </div>                                                                   `;
		str += `       <div class="col sub-title">                                              `;
		str += `          <h5>도서 소개</h5>                                                   `;
		str += `       </div>                                                                   `;
		str += `    </div>                                                                      `;
		str += `    <div class="row">                                                           `;
		str += `       <div class="col-1"></div>                                                `;
		str += `       <div class="col-5">                                                     `;
		str += `          <div class="row update-content">                                      `;
		str += `             <label class="col-3 col-form-label text-end">메인 이미지</label>   `;
		str += `             <div class="col-9">                                                `;
		str += `                <input type="file" class="form-control">                        `;
		str += `             </div>                                                             `;
		str += `             <label class="col-3 col-form-label text-end"></label> 				  `;
		str += `             <div class="col-9">                                                `;
		// 메인 이미지 데이터 처리
		for (const img of result) {
			if (img.isMainImg === 'Y') {
				str += `<label class="form-label">
           <a href="javascript:void(0)" 
           onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a>
          	 　<img width="20px;" src="/image/icon-del.png" id="mainImgDel" 
          	 onclick="deleteMainImg('${img.attachedFileName}', '${img.bookCode}', '${img.bookImgCode}', '${img.originFileName}');">
           </label>`;

			}
		}
		str += `             </div>                                                             `;
		str += `             <label class="col-3 col-form-label text-end">상세 이미지</label>   `;
		str += `             <div class="col-9">                                                `;
		str += `                <input type="file" class="form-control">                        `;

		// 서브 이미지 데이터 처리
		for (const img of result) {
			if (img.isMainImg === 'N') {
				str += `<label class="form-label">
            <a href="javascript:void(0)" 
            onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a>
            　<img width="20px;" src="/image/icon-del.png" id="subImgDel" 
            onclick="deleteSubImg('${img.attachedFileName}', '${img.bookCode}', '${img.bookImgCode}', '${img.originFileName}');">
            </label>`;


				str += `</div>`;
				str += `          </div>                                                                `;
				str += `       </div>   																  `;
				str += `       <div class="col-5">                                                     `;
				str += `          <div class="row update-content">                                      `;
				str += `             <label class="col-2 col-form-label text-end">소개</label>   `;
				str += `             <div class="col-10">                                                `;
				str += `                <textarea class="form-control" rows="5" name = "bookIntro" style="resize: none;">${img.bookIntro}</textarea>             `;
				str += `             </div>                                                             `;
			}
		}
		str += `       </div>   																  `;
		str += `       </div>   																  `;
		str += `       </div>   																  `;
		str += `<div class="offset-5 col-2 d-grid">												  `;
		str += `				<input type="button" class="btn custom-btn"		  `;
		str += `					value="수정" onclick="editBookDetail();">					  `;
		str += `			</div>																  `;


      bookDetailDiv.insertAdjacentHTML('afterbegin', str);
    },
    error: function() {
      alert('실패');
    }
  });
  // ajax end
}


// 이미지, 소개 수정
function editBookDetail(bookIntro, originFileName, attachedFileName, isMainImg, bookCode){

  
  // AJAX를 사용하여 /book/bookdelete로 요청 보내기
  $.ajax({
    url: '/book/updateBookDetail',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'attachedFileName': attachedFileName , 'bookCode':bookCode, 'bookIntro':bookIntro, 'originFileName':originFileName, 'isMainImg':isMainImg},
    success: function(result) {
      console.log(result); // 성공 처리

      // 이미지 삭제 성공 후의 추가 로직 구현

    },
    error: function() {
      alert('실패'); // 실패 처리
    }
  });
	
}


  // 이미지 삭제 
function deleteMainImg(attachedFileName, bookCode) {
  
  
  // AJAX
  $.ajax({
    url: '/book/updateBookDetail',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'attachedFileName': attachedFileName , 'bookCode':bookCode},
    success: function(result) {
      console.log(result); // 성공 처리

      // 이미지 삭제 성공 후의 추가 로직 구현

    },
    error: function() {
      alert('실패'); // 실패 처리
    }
  });
}


function deleteSubImg(attachedFileName, bookCode) {
  // 이미지 삭제 로직 구현

  // AJAX를 사용하여 /book/bookdelete로 요청 보내기
  $.ajax({
    url: '/book/deleteSubImg',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'attachedFileName': attachedFileName , 'bookCode':bookCode},
    success: function(result) {
      console.log(result); // 성공 처리

      // 이미지 삭제 성공 후의 추가 로직 구현

    },
    error: function() {
      alert('실패'); // 실패 처리
    }
  });
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
