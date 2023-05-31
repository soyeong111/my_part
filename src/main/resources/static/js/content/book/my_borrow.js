
// 연장 버튼 클릭 시 실행
function extend(memId, borrowCode) {
// 버튼을 비활성화
  const extendBtn = document.getElementById('exBtn'); //연장 버튼 요소 가져오기
  
  $.ajax({
    url: '/book/extendAjax',
    type: 'post',
    async : true,
	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'borrowCode': borrowCode, 'memId': memId },
    success: function(result) {
      const extend = confirm('반납 기한을 연장하시겠습니까?');
      if (extend) {
        // 도서 반납 연장 후
        exBtn.style.display = 'none'; // 버튼 숨기기
        const extendStatus = document.createElement('span');
        extendStatus.textContent = '완료';
        exBtn.parentNode.appendChild(extendStatus); // 버튼 대신 텍스트 요소 추가
        alert('반납 기한이 3일 연장되었습니다.');

        // 연장 완료 상태 저장
        localStorage.setItem('extendButtonState', 'disabled');
      } else {
        // 연장 취소 시 버튼 활성화
        exBtn.disabled = false;
        exBtn.value = '연장';
      }
    },
    error: function() {
      // 연장 실패 시 버튼 활성화
      exBtn.disabled = false;
      exBtn.value = '연장';
      alert('연장에 실패했습니다.');
    }
  });
}

// 페이지 로드 시 연장 완료 상태 확인 및 처리
window.addEventListener('load', function() {
  const exBtn = document.getElementById('exBtn'); // 연장 버튼 요소 가져오기
  const extendButtonState = localStorage.getItem('extendButtonState'); // 연장 버튼 상태 가져오기

  if (extendButtonState === 'disabled') {
    exBtn.style.display = 'none'; // 버튼 숨기기
    const extendStatus = document.createElement('span');
    extendStatus.textContent = '완료';
    exBtn.parentNode.appendChild(extendStatus); // 버튼 대신 텍스트 요소 추가
  }
});

//-------------반납--------
// 반납 버튼 클릭 시 실행
function returnBook(memId, borrowCode) {
  const extendBtn = document.getElementById('exBtn'); //연장 버튼 요소 가져오기	
  const returnBtn = document.getElementById('reBtn'); // 반납 버튼 요소 가져오기

  $.ajax({
    url: '/book/returnBookAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'borrowCode': borrowCode, 'memId': memId },
    success: function(result) {
      const returnBook = confirm('도서를 반납하시겠습니까?');
      if (returnBook) {
        // 도서 반납 완료 후
        returnBtn.style.display = 'none'; // 버튼 숨기기
        const returnStatus = document.createElement('span');
        returnStatus.textContent = '완료';
        returnBtn.parentNode.appendChild(returnStatus); // 버튼 대신 텍스트 요소 추가
        
        // 연장 버튼도 완료로 바뀌게
        exBtn.style.display = 'none'; // 버튼 숨기기
        const extendStatus = document.createElement('span');
        extendStatus.textContent = '완료';
        exBtn.parentNode.appendChild(extendStatus); // 버튼 대신 텍스트 요소 추가
        
        alert('도서를 성공적으로 반납했습니다.');

        // 반납 완료 상태 저장
        localStorage.setItem('returnButtonState', 'disabled');
         // 연장 완료 상태 저장
        localStorage.setItem('extendButtonState', 'disabled');
      } else {
        // 반납 취소 시 버튼 활성화
        returnBtn.disabled = false;
        returnBtn.value = '반납';
        
        // 반납 취소 시  연장 버튼 활성화
        exBtn.disabled = false;
        exBtn.value = '연장';
        
      }
    },
    error: function() {
      // 반납 실패 시 버튼 활성화
      returnBtn.disabled = false;
      returnBtn.value = '반납';
      alert('반납에 실패했습니다.');
      
      // 반납 실패 시 연장 버튼 활성화
      exBtn.disabled = false;
      exBtn.value = '연장';
      alert('연장에 실패했습니다.');
    }
  });
}

// 페이지 로드 시 반납 완료 상태 확인 및 처리
window.addEventListener('load', function() {
  const returnBtn = document.getElementById('reBtn'); // 반납 버튼 요소 가져오기
  const returnButtonState = localStorage.getItem('returnButtonState'); // 반납 버튼 상태 가져오기

  if (returnButtonState === 'disabled') {
    returnBtn.style.display = 'none'; // 버튼 숨기기
    const returnStatus = document.createElement('span');
    returnStatus.textContent = '완료';
    returnBtn.parentNode.appendChild(returnStatus); // 버튼 대신 텍스트 요소 추가
    
    
    
  }
});
