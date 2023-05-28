
// 연장하기 버튼 클릭 시 실행
function extend(memId, borrowCode) {
// 버튼을 비활성화
  const extendBtn = document.getElementById('exBtn');
  extendBtn.disabled = true; // 버튼 비활성화
	
  $.ajax({
    url: '/book/extendAjax',
    type: 'post',
    data: { 'borrowCode': borrowCode, 'memId': memId },
    success: function(result) {
      const result1 = confirm('반납 기한이 3일 연장되었습니다.');

      if (result1) {
		 localStorage.setItem('extendButtonState', 'disabled');
        location.href = `/book/myBorrow?memId=${memId}`;
      }
    },
    error: function() {
      alert('연장에 실패했습니다.');
    }
  });
}

// 페이지 로딩 시 버튼 상태 확인 및 설정
document.addEventListener('DOMContentLoaded', function() {
  const extendBtn = document.getElementById('exBtn');
  const buttonState = localStorage.getItem('extendButtonState');

  if (buttonState === 'disabled') {
    extendBtn.disabled = true; // 버튼 비활성화
  }
});