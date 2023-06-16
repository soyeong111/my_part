
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


//alramCnt 클릭 시
function getAlramList(){
	
	//ajax start
	$.ajax({
	   url: '/alram/alramListAjax', //요청경로
	   type: 'post',
	   async : true,
	   contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	   data: {}, //필요한 데이터
	   success: function(result) {
	      const alram_div = document.querySelector('.modal-body');
	      alram_div.replaceChildren();
	      
	      let str = '';
	      
	      str += ` <table class="table text-center"> `;
		  str += `	<thead style="font-weight:bold;"> `;
		  str += `			<tr>`;
		  str += `				<td>내용</td>`;
		  str += `				<td>전송날짜</td>`;
		  str += `				<td>읽음</td>`;
		  str += `				<td>삭제</td>`;
		  str += `			</tr>`;
		  str += `		</thead>`;
		  
		  
		  str += `		<tbody>`;
		  if(result.length == 0){
			  str += `<tr><td colspan="4">알림이 없습니다.</td></tr>`;
			
		}
	      for(let i = 0 ; i < result.length ; i++){
		  str += `			<tr>`;
		  str += `				<td><a href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#openMsg" onclick="oepnMsg('${result[i].alramCode}')";>${result[i].acontent}</a></td>`;
		  str += `				<td>${result[i].alramDate}</td>`;
		  if(result[i].acheck == 'N'){
			  str += `				<td><a href="javascript:void(0);" onclick="updateAlramACheck('${result[i].alramCode}','Y');"><img src="/image/alram/envelope.png" width="28px;"></a></td>`;			
		  }
		  else{
			  str += `				<td><a><img src="/image/alram/envelope-open.png" width="28px;"></a></td>`;			
		}
		  str += `				<td><a href="javascript:void(0);" onclick="deleteAlram('${result[i].alramCode}');"><img src="/image/alram/trash.png" width="28px;"></a></td>`;
		  str += `			</tr>`;
          }
		  str += `		</tbody>`;
		  str += `	</table>`;
          
           alram_div.insertAdjacentHTML('afterbegin', str);
           

	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
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


