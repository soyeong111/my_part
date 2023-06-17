
function fixed_menu_mouseenter() {
	document.querySelector('.header-unfixed-div').classList.remove('d-none');
}

function fixed_menu_mouseleave() {
	document.querySelector('.header-unfixed-div').classList.add('d-none');
}

function public_menu_mouseover(index) {
	const menus = document.querySelectorAll('.header-sub-menu-div > div');
	menus[index].classList.add('main-sub-menu-hover');
}

function public_menu_mouseout(index) {
	const menus = document.querySelectorAll('.header-sub-menu-div > div');
	menus[index].classList.remove('main-sub-menu-hover');
}

// 알림 목록 가져오기 및 모달 열기
function getAlramList() {
  // AJAX 요청
  $.ajax({
    url: '/alram/alramListAjax',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: {},
    success: function(result) {
      const alramTable = document.getElementById('alramTableBody');
      alramTable.innerHTML = '';

      let str = '';
      str += `<tbody>`;
      if (result.length === 0) {
        str += `<tr><td colspan="4">알림이 없습니다.</td></tr>`;
      }
      for (let i = 0; i < result.length; i++) {
        str += `<tr>`;
        str += `<td><a href="javascript:void(0);" onclick="openAlramMessage('${result[i].alramCode}', 'Y')">${result[i].acontent}</a></td>`;
        str += `<td>${result[i].alramDate}</td>`;
        if (result[i].acheck === 'N') {
          str += `<td><img src="/image/alram/envelope.png" width="28px;"></td>`;
        } else {
          str += `<td><a><img src="/image/alram/envelope-open.png" width="28px;"></a></td>`;
        }
        str += `<td><a href="javascript:void(0);" onclick="deleteAlram('${result[i].alramCode}');"><img src="/image/alram/trash.png" width="28px;"></a></td>`;
        str += `</tr>`;
      }
      str += `</tbody>`;

      alramTable.insertAdjacentHTML('afterbegin', str);
    },
    error: function() {
      alert('알림 목록을 가져오는데 실패했습니다.');
    }
  });
}


//안열린 편지봉투 클릭 시 
function updateAlramACheck(alramCode, acheck){
	//ajax start
	$.ajax({
	   url: '/alram/updateAlramACheckAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'alramCode':alramCode, 'aCheck':acheck}, //필요한 데이터
	   success: function(result) {
	      getAlramList();
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}

//쓰레기통(알림삭제) 클릭 시 
function deleteAlram(alramCode){
	//ajax start
	$.ajax({
	   url: '/alram/deleteAlramAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {'alramCode':alramCode}, //필요한 데이터
	   success: function(result) {
	      getAlramList();
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}

// alramCnt 클릭 시 알림 내용 모달 열기
function openAlramMessage(alramCode, acheck) {
  // 알림 내용 가져오기 AJAX 요청
  $.ajax({
    url: '/alram/getAlramMessage',
    type: 'post',
    async: true,
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    data: { 'alramCode': alramCode, 'aCheck':acheck},
    success: function(result) {
	  $('#alramModal').modal('hide');
      // 알림 내용 모달창 열기
      $('#openMsg').modal('show');
      // 알림 내용을 테이블에 추가
      const alramMessageTable = document.getElementById("alramMessageTable");
      alramMessageTable.innerHTML = '';

      let str = '';
      str += `<thead>`;
      str += `<tr>`;
      str += `<td>보낸 사람</td>`;
      str += `<td>관리자</td>`;
      str += `<td>전송 날짜</td>`;
      str += `<td>${result.alramDate}</td>`;
      str += `</tr>`;
      str += `</thead>`;
      str += `<tbody>`;
      str += `<tr>`;
      str += `<td>내용</td>`;
      str += `<td colspan="3">${result.acontent}</td>`;
      str += `</tr>`;
	  str += `</tbody>`;
		
	  const alramMessageFooter = document.getElementById("alramFooter");
	  alramMessageFooter.innerHTML = '';
		
		let str2 = '';
		str += `<button type="button" class="btn btn-secondary ms-auto" >삭제</button>`;
		str += `<button type="button" class="btn btn-secondary ms-auto" data-bs-dismiss="modal">닫기</button>`;
		
		
		alramMessageTable.insertAdjacentHTML('afterbegin', str);
		alramMessageFooter.insertAdjacentHTML('afterbegin', str2);
		getAlramList();
		},
		error: function() {
		  alert('알림 내용을 가져오는데 실패했습니다.');
		}
	});
}


$(document).ready(function() {
  $('#openMsg').on('hidden.bs.modal', function() {
    // 알림 내용 모달이 닫힌 후에 alarmModal 모달 표시
    $('#alramModal').modal('show');
  });
});
