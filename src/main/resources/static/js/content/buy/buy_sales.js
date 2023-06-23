//html 열리지마자 ajax 실행
getSaleStatusByCategoryAjax();

function getSaleStatusByCategoryAjax(){
	//ajax start
	$.ajax({
	   url: '/aBuy/getSaleStatusByCategoryAjax', //요청경로
	   type: 'post',
	   async: true,
	   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   data: {}, //필요한 데이터
	   success: function(result) {
			drawChart(result);
			drawTable(result);
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}

//테이블을 그리는 함수
function drawTable(data){
	//테이블이 그려질 div 태그 선택
	const tableDiv = document.querySelector('.tableDiv');
	
	//그려질 테이블 태그를 문자열로 작성
	let str = ``;
	str += `<table class="table text-center">`;
	str += `<thead>`;
	str += `<tr>`;
	str += `<td>No</td>`;
	str += `<td>카테고리명</td>`;
	str += `<td>누적 판매 개수</td>`;
	str += `</tr>`;
	str += `</thead>`;
	str += `<tbody>`;

	for(let i = 0 ; i < data.length ; i++){
		str += `<tr>`;
		str += `<td>${data.length - i}</td>`;
		str += `<td>${data[i]['CATE_NAME']}</td>`;
		str += `<td>${data[i]['SUM_BUY_CNT']}</td>`;
		str += `</tr>`;
	}
	
	str += ``;
	str += ``;
	str += ``;
	str += `</tbody>`;
	str += `</table>`;
	
	//테이블이 들어갈 div 태그에 위에서 만든 코드 삽입
	tableDiv.insertAdjacentHTML('afterbegin', str);
}

function drawChart(data){
	//data에 들어있는 모든 데이터 중 CATE_NAME데이터만 추출
	//추출한 데이터를 배열로 생성
	
	//data에 들어있는 모든 데이터 중 SUM_BUY_CNT데이터만 추출
	//추출한 데이터를 배열로 생성
	
	const cateNameArr = [];
	const sumBuyCntArr = [];
	
	data.forEach(function(item, index){
		cateNameArr[index] = item['CATE_NAME'];
		sumBuyCntArr[index] = item['SUM_BUY_CNT'];
	});
	
	const ctx = document.getElementById('categoryPieChart');

	new Chart(ctx, {
		plugins: [ChartDataLabels],
		type: 'pie',
		data: {
			labels: cateNameArr,
			datasets: [
				{
					label: 'Dataset 1',
					data: sumBuyCntArr,
					//backgroundColor: Object.values(Utils.CHART_COLORS),
				}
			]
		},
		options: {
			responsive: false,
			plugins: {
				legend: {
					position: 'top'
				},
				title: {
					display: true,
					text: '카테고리별 매출 현황'
				},
				datalabels:{
					color:'#4e342e',
					align: 'end',
					formatter:function(value,context){
						let sum = 0;
						let datas = context.chart.data.datasets[0].data;

						for(const e of datas){
							sum += e;
						}
						
						const pct = (value * 100 / sum).toFixed(2) + '%';
						return pct;
					}
				}
			}
		}
	});
}












