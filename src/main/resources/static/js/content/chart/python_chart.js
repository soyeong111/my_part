init();

// 파이썬에서 데이터 받아오기
function init() {
	$.ajax({
		url: '/chart/getChartDataAjax',
		type: 'post',
		data: {},
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(result) {
			draw_list_table(result['list_data']);
			draw_doughnut_chart(result['doughnut_data']);
			draw_bar_chart(result['bar_data']);
			draw_line_chart(result['line_data']);
		},
		error: function() {
			alert('통신 실패');
		}
	});
}

// 도서별 대출횟수 탑10 - 테이블목록
function draw_list_table(list_data) {
	const list_tbody = document.querySelector('#my-list-table tbody');
	let str = '';
	for (let i = 0; i < 10; i++) {
		str += `<tr>`;
		str += `<td>${i + 1}</td>`;
		str += `<td>${list_data['제목'][i]}</td>`;
		str += `<td>${list_data['저자'][i]}</td>`;
		str += `<td>${list_data['발행처'][i]}</td>`;
		str += `<td>${list_data['대출횟수'][i]}</td>`;
		str += `</tr>`;
	}
	list_tbody.insertAdjacentHTML('afterbegin', str);
}

// 발행처별 대출횟수 탑10 - 도넛차트
function draw_doughnut_chart(doughnut_data) {
	const doughnut_chart = document.querySelector('#my-doughnut-chart');
	const labels = []
	for (let key in doughnut_data['발행처']) {
		labels.push(doughnut_data['발행처'][key]);
	}
	const data = []
	for (let key in doughnut_data['대출횟수']) {
		data.push(doughnut_data['대출횟수'][key]);
	}
	new Chart(doughnut_chart, {
		type: 'doughnut',
		data: {
			labels: labels,
			datasets: [{
				label: '대출횟수',
				data: data,
				borderWidth: 1
			}]
		},
		options: {
			plugins: {
				legend: {
					position: 'right'
				}
			},
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
}

// 도서분류별 대출횟수 - 바차트
function draw_bar_chart(bar_data) {
	const bar_chart = document.querySelector('#my-bar-chart');
	const labels = []
	for (let key in bar_data['도서분류']) {
		labels.push(bar_data['도서분류'][key]);
	}
	const data = []
	for (let key in bar_data['대출횟수']) {
		data.push(bar_data['대출횟수'][key]);
	}
	new Chart(bar_chart, {
		type: 'bar',
		data: {
			labels: labels,
			datasets: [{
				label: '대출횟수',
				data: data,
				backgroundColor: [
					'rgba(255, 99, 132, 0.2)',
					'rgba(255, 159, 64, 0.2)',
					'rgba(255, 205, 86, 0.2)',
					'rgba(75, 192, 192, 0.2)',
					'rgba(54, 162, 235, 0.2)',
					'rgba(153, 102, 255, 0.2)',
					'rgba(201, 203, 207, 0.2)'
				],
				borderColor: [
					'rgb(255, 99, 132)',
					'rgb(255, 159, 64)',
					'rgb(255, 205, 86)',
					'rgb(75, 192, 192)',
					'rgb(54, 162, 235)',
					'rgb(153, 102, 255)',
					'rgb(201, 203, 207)'
				],
				borderWidth: 1
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
}

// 발행년도별 도서분류별 발행도서수 - 라인차트
function draw_line_chart(line_data) {
	const line_chart = document.querySelector('#my-line-chart');
	new Chart(line_chart, {
		type: 'line',
		data: {
			labels: line_data['labels'],
			datasets: line_data['datasets']
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
}
