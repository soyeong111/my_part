function extend() {
  // 여기에서 연장 작업을 수행하는 코드를 작성합니다.
  
  // 연장 작업이 완료되면 버튼의 텍스트를 "완료"로 변경합니다.
  var button = document.getElementById('exBtn-' + borrowCode);
  button.value = "완료";
}
