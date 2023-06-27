
init();

function init() {
	const year_select = document.querySelector('#year-select');
	$.ajax({
		url: '/mMember/getChartDataAjax',
		type: 'post',
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8', /* application/json; charset=UTF-8 */
		data: {'nowYear':year_select.value}, /* data: JSON.stringify(data), data: $('#buyForm').serialize(), */
		success: function(result) {
			drawChart(result);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 차트 그리기
function drawChart(chart_data) {
	const chart_canvas = document.querySelector('#profile-chart');
	const datasets = [];
	chart_data.forEach((cate_data, index) => {
		const setting = {};
		setting['label'] = cate_data['CATE'];
		const data = [];
		for (let i = 1; i <= 12; i++) {
			data.push(cate_data[i]);
		}
		setting['data'] = data;
		setting['backgroundColor'] = `rgba(${250 - (index * 20)}, ${20 + (index * 20)}, ${70 + (index * 10)}, 0.2)`;
		setting['borderColor'] = `rgba(${255 - (index * 20)}, ${25 + (index * 20)}, ${75 + (index * 10)})`;
		setting['borderWidth'] = 1;
		datasets.push(setting);
	});
	new Chart(chart_canvas, {
		type: 'bar',
		data: {
			datasets: datasets,
			labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
		},
		options: {
			scales: {
				x: {
					stacked: true,
					ticks: {
						callback: function(value) {
							return value + 1 + '월';
						}
					}
				},
				y: {
					stacked: true,
					beginAtZero: true,
					ticks: {
						stepSize: 1
					}
				}
			}
		}
	});
}

// 차트 연도 셀렉트 바꿨을 때
function year_change(year_select, main_meunu_code, sub_menu_code) {
	location.href = `/mMember/myProfile?mainMenuCode=${main_meunu_code}&subMenuCode=${sub_menu_code}&nowYear=${year_select.value}`;
}

// 사진 플러스 눌렀을 때
function add_icon_click() {
	document.querySelector('#mem-img-input').click();
}

// 사진 선택 시
function mem_img_change() {
	const selectedFile = document.querySelector('#mem-img-input').files[0];
	if (selectedFile != null) {
		if (selectedFile.type.startsWith('image/')) {
			const formData = new FormData();
			formData.append("memImg", selectedFile);
			$.ajax({
				url: '/mMember/updateMemImgAjax',
				type: 'post',
				processData: false,
				contentType: false,
				data: formData,
				success: function(result) {
					if (result == 1) {
						alert('사진이 등록되었습니다.');
						location.reload();
					} else {
						alert('사진 등록 실패');
					}
				},
				error: function() {
					alert('ajax 통신 실패');
				}
			});
		} else {
			alert('이미지를 선택해주세요.');
		}
	}
}

// 사진 엑스 눌렀을 때
function del_icon_click(memImgUrl) {
	if (confirm('사진을 삭제 하시겠습니까?')) {
		$.ajax({
			url: '/mMember/deleteMemImgAjax',
			type: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: {'memImgUrl':memImgUrl},
			success: function(result) {
				if (result == 1) {
					alert('사진이 삭제되었습니다.');
					location.reload();
				} else {
					alert('사진 삭제 실패');
				}
			},
			error: function() {
				alert('ajax 통신 실패');
			}
		});
	}
}
