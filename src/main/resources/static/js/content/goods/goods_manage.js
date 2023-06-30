const imgModal = new bootstrap.Modal('#imgModal');

function openImgModal(atachedFileName, originFileName){
	const modalTag = document.querySelector('#imgModal');
	modalTag.querySelector('img').src = `/image/goods/${atachedFileName}`;
	
	//제목 세팅
	modalTag.querySelector('h1').textContent = originFileName;
	
	//모달 오픈
	imgModal.show(); 
}



function goodsDetail(goodsCode, mainMenuCode, subMenuCode){
	//ajax start
	$.ajax({
	   url: '/aGoods/goodsDetailAjax', //요청경로
	   type: 'post',
	   data: {'goodsCode':goodsCode}, //필요한 데이터
	   success: function(result) {
			console.log(result);
			console.log(result['goods']);
			console.log(result['goods'].goodsName);
		
			const goodsDetailDiv = document.querySelector('#goodsDetailDiv');
			goodsDetailDiv.replaceChildren();
			
			let str = '';
			                                                                                    
			str += `	<form action="/aGoods/updateGoods" method="post">                                                                      `;
	        str += `	<input type="hidden" name="goodsCode" value="${result['goods'].goodsCode}">`;
	        str += `	<input type="hidden" name="mainMenuCode" value="${mainMenuCode}">`;
	        str += `	<input type="hidden" name="subMenuCode" value="${subMenuCode}">`;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-9 sub-title">                                            `;
	        str += `                                                                 `;
	        str += `       </div>                                                                   `;
	        str += `       <div class="col-3 sub-title d-grid">                                     `;
	      
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	   
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">카테고리</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <select id="" name="goodsCateCode" class="form-select">                      `;
	        for(const e of result['cateList']){
				const selected = result['goods'].goodsCateCode == e.goodsCateCode ? 'selected' : '';
		        str += `                   <option value="${e.goodsCateCode}" ${selected}>${e.goodsCateName}</option>                               `;
			}
	        str += `                </select>                                                       `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">상품명</label>        `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsName" value="${result['goods'].goodsName}">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">판매가격</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsPrice" value="${result['goods'].goodsPrice}">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">상품소개</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <textarea class="form-control" rows="3" name="goodsIntro">${result['goods'].goodsIntro}</textarea>             `;
	        str += `             </div>                                                             `;
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-9 sub-title">                                            `;
	        str += `                                                              `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	       
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">상품상태</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <select id="" name="goodsStatus" class="form-select">                      `;
	        if(result['goods'].goodsStatus == 1){
				str += `                   <option value="1" selected>판매중</option>                               `;
	      		str += `                   <option value="2">준비중</option>                               `;
	        	str += `                   <option value="3">매진</option>                               `;
			}
			else if(result['goods'].goodsStatus == 2){
				str += `                   <option value="1">판매중</option>                               `;
	        	str += `                   <option value="2" selected>준비중</option>                               `;
	        	str += `                   <option value="3">매진</option>                               `;
			}
			else{
				str += `                   <option value="1">판매중</option>                               `;
	        	str += `                   <option value="2">준비중</option>                               `;
	        	str += `                   <option value="3" selected>매진</option>                               `;
			}
			
	        str += `                </select>                                                       `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">재 고</label>         `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsStockCnt" value="${result['goods'].goodsStockCnt}">                        `;
	        str += `             </div>                                                             `;
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col sub-title">                                              `;
	        str += `                                                         `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	   
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">메인 이미지</label>   `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="file" class="form-control">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label"></label>   `;
	        str += `             <div class="col-9">                                                `;
	       	for(const img of result['goods'].goodsImgList){
				if(img.isMain == 'Y'){
			        str += `                <label class="form-label"><a href="javascript:void(0);" onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a></label>                        `;
				}
			}
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label" style="text-align: center;">상세 이미지</label>   `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="file" class="form-control">                        `;
	        str += `             </div>   	                                                        `;
	       	for(const img of result['goods'].goodsImgList){
				if(img.isMain == 'N'){
					str += `             <label class="col-3 col-form-label"></label>   `;
	    		    str += `             <div class="col-9">                                                `;
			        str += `                <label class="form-label"><a href="javascript:void(0);" onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a></label>                        `;
			        str += `             </div> `;
				}
			}
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	          str += `          <button type="submit" class="btn button btnBorder btnLightBlue-search "  style="color: white; width: 40%; margin-left:10rem;" >                        `;
	        str += `              수 정                                                             `;
	        str += `          </button>                                                             `;
	        str += ` </form>                                                                        `;
			
			goodsDetailDiv.insertAdjacentHTML('afterbegin', str);

	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end

}

//검색 영역 체크박스 컨트롤

//전체 체크 박스
const checkAll = document.querySelector('#checkAll');

//전체를 제외한 체크박스들
const checkboxes = document.querySelectorAll('.chk');

checkAll.addEventListener('click', function(){
	if(checkAll.checked){
		for(const checkbox of checkboxes){
			checkbox.checked = true;
		}		
	}
	else{
		for(const checkbox of checkboxes){
			checkbox.checked = false;
		}		
	}
});

//카테고리 체크박스 중 전체를 제외한 체크박스 클릭 시
for(const checkbox of checkboxes){
	checkbox.addEventListener('click', function(){
		//전체를 제외한 체크박스 개수
		const totalCnt = checkboxes.length;
		
		//전체를 제외한 체크박스 중 체크된 체크박스 개수
		const checkedCnt = document.querySelectorAll('.chk:checked').length;
		
		if(totalCnt == checkedCnt){
			checkAll.checked = true;
		}
		else{
			checkAll.checked = false;
		}
	});
}
























