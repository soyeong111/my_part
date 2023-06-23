init();

function init() {
	getChartData();
}

//연체 버튼 클릭 시 실행
function overDue(memId, bookCode) {
	Swal.fire({
		title: '연체상태로 바꾸시겠습니까?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: '네',
		cancelButtonText: '아니요',
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: '/aBook/overDueAjax',
				type: 'post',
				async: true,
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				data: { 'memId': memId, 'bookCode': bookCode },
				success: function(result) {
					Swal.fire('연체상태로 변경되었습니다.', '', 'success')
						.then(() => {
							location.reload();
						});
				},
				error: function() {
					Swal.fire('연체상태 변경에 실패했습니다.', '', 'error');
				}
			});
		}
	});

}

function getChartData() {
	$.ajax({
		url: '/aBook/cateBorrowAjax', //요청경로
		type: 'post',
		async: true,
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data: {}, //필요한 데이터
		success: function(result) {
			console.log(result);
			drawChart(result)


		},
		error: function() {
			alert('실패');
		}
	});
}

function drawChart(chartData) {
  const cateList = [];
  const cntList = [];

  chartData.forEach((data) => {
    cateList.push(data['CATE']);
    cntList.push(data['CNT']);
  });

  console.log(cateList);
  console.log(cntList);

  const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: cateList,
      datasets: [
        {
          data: cntList,
          backgroundColor: [
             'rgb(255, 209, 220)', // 파스텔 핑크
            'rgb(255, 221, 175)', // 파스텔 옐로우
            'rgb(182, 225, 189)', // 파스텔 그린
            'rgb(210, 191, 255)', // 파스텔 퍼플
            'rgb(255, 195, 210)', // 파스텔 로즈
            'rgb(189, 218, 255)', // 파스텔 블루
            'rgb(255, 238, 153)', // 파스텔 민트
            'rgb(255, 213, 184)', // 파스텔 코랄
            'rgb(204, 229, 255)' // 파스텔 스카이 블루
          ]
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'right',
        },
         title: {
        display: true,
        text: '카테고리별 대여 비율',
         font: {
        size: 23 // 원하는 크기로 설정
      }
        
			  }
		  }
	  },
  });
}



