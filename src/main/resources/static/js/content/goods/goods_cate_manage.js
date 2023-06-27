
//카테고리 생성
function regCategory(){
	//카테고리명이 빈 값인지 확인
	const goodsCateNameTag = document.querySelector('#goodsCateName');
	
	if(goodsCateNameTag.value == ''){
		alert('카테고리명은 필수입니다.');
		return ;
	}
	
	//카테고리명 중복 확인
	if(checkCateName(goodsCateNameTag.value)){
		alert('카테고리명이 중복됩니다.\n다른 카테고리명을 입력하세요.');
		goodsCateNameTag.value = '';
		return ;
	}
	
	//ajax start
	$.ajax({
	   url: '/aGoods/regGoodsCategoryAjax', //요청경로
	   type: 'post',
	   async: false, 
	   data: {'goodsCateName':goodsCateNameTag.value}, //필요한 데이터
	   contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   success: function(result) {
	      alert('카테고리 등록 완료');
	      
	      //카테고리 목록 데이터 다시 조회
	      selectGoodsCateListAjax();
	      goodsCateNameTag.value = '';
	      
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
}

//카테고리명 중복 확인
function checkCateName(goodsCateName){
	let isDuplicate = false; 
	
	//ajax start
	$.ajax({
	   url: '/aGoods/checkCateNameAjax', 
	   type: 'post',
	   async: false, 
	   data: {'goodsCateName': goodsCateName}, 
	   success: function(result) {
			
	      	if(result == 1){
				isDuplicate = true;
			}
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
	return isDuplicate;
}

//카테고리 등록 후 실행되는 목록 조회 기능
function selectGoodsCateListAjax(){
	//ajax start
	$.ajax({
	   url: '/aGoods/selectGoodsCateListAjax', //요청경로
	   type: 'post',
	   async: false,
	   data: {}, //필요한 데이터
	   success: function(result) {
			//카테고리 목록 테이블의 tbody 태그를 선택
			const tbodyTag = document.querySelector('#goodsCateListTable tbody');
			
			//해당 태그 안의 모든 내용 삭제
			tbodyTag.replaceChildren();
			
			//새롭게 조회된 카테고리 목록 데이터로 테이블 채워줌
			let str = '';
			for(let i = 0 ; i < result.length ; i++){
				str += '<tr>';
				str += `<td>${i + 1}</td>`;
				str += `<td>${result[i].goodsCateName}</td>`;
				str += '<td>';
				str += '	<div class="row">';
				str += `		<div class="form-check col-6">                                                                                                                                                        `;
				if(result[i].goodsCateIsUse == 'Y'){
					str += `			<input name="isUse_${i+1}" type="radio" class="form-check-input" onchange="changeIsUse('${result[i].goodsCateCode}');" checked>사용중`; 
				}else{
					str += `			<input name="isUse_${i+1}" type="radio" class="form-check-input" onchange="changeIsUse('${result[i].goodsCateCode}');">사용중`; 
				}
				str += `		</div>                                                                                                                                                                                `;
				str += `		<div class="form-check col-6">                                                                                                                                                        `;
				if(result[i].goodsCateIsUse == 'N'){
					str += `			<input name="isUse_${i+1}" type="radio" class="form-check-input" onchange="changeIsUse('${result[i].goodsCateCode}');" checked>미사용`; 
				}else{
					str += `			<input name="isUse_${i+1}" type="radio" class="form-check-input" onchange="changeIsUse('${result[i].goodsCateCode}');">미사용`; 
				}
				str += `		</div>                                                                                                                                                                                `;
				str += `	</div>`;
				str += `</td>`;
				str += `<td>${result[i].goodsCateOrderNo}</td>`;
				str += `<td><input type="button" value="삭제" class="btn btn-secondary" onclick="location.href='/goods/deleteGoodsCategory?goodsCateCode=${result[i].goodsCateCode}';"></td>`;
				str += '</tr>';
			}
			
			tbodyTag.insertAdjacentHTML('afterbegin', str);
			
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}


//카테고리 사용여부 변경
function changeIsUse(goodsCateCode){
	//ajax start
	$.ajax({
	   url: '/aGoods/changeIsUseAjax', //요청경로
	   type: 'post',
	   data: {'goodsCateCode':goodsCateCode}, //필요한 데이터
	   success: function(result) {
			if(result == 1){
				alert('사용 여부가 변경되었습니다.');
			}
			else{
				alert('일시적 오류가 발생했습니다.');
			}
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}







