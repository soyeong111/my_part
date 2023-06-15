
// 종료일 변경 시
function end_date_change(end_date_input) {
	const start_date_input = document.querySelector('#start-date-input');
	start_date_input.max = end_date_input.value;
}
