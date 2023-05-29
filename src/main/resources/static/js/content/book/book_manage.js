//장바구니 상품 삭제
function deleteCart(cartCode){
	const result = confirm('장바구니에서 삭제할까요?');
	
	if(result){
		location.href = `/cart/deleteCart?cartCode=${cartCode}`;
		
	}
	
}



//선택삭제 버튼 클릭 시 실행
function deleteBook(){
	//체크한 체크박스
	const chks = document.querySelectorAll('.chk:checked');
	const result = confirm('장바구니에서 삭제할까요?');
	
	if(chks.length == 0){
		alert('선택한 상품이 없습니다.');
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
