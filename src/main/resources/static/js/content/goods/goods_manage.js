//이미지 팝업 모달
const imgModal = new bootstrap.Modal('#imgModal');

//이미지명 클릭 시 이미지 모달 띄움
function openImgModal(atachedFileName, originFileName){
	//모달 안에서 보여질 이미지 정보 세팅
	const modalTag = document.querySelector('#imgModal');
	modalTag.querySelector('img').src = `/upload/${atachedFileName}`;
	
	//제목 세팅
	modalTag.querySelector('h1').textContent = originFileName;
	
	//모달 오픈
	imgModal.show(); //imgModal.hide() -> 모달 숨김
}



function goodsDetail(goodsCode){
	//ajax start
	$.ajax({
	   url: '/goods/goodsDetailAjax', //요청경로
	   type: 'post',
	   data: {'goodsCode':goodsCode}, //필요한 데이터
	   success: function(result) {
			console.log(result);
			console.log(result['goods']);
			console.log(result['goods'].goodsName);
		
			const goodsDetailDiv = document.querySelector('#goodsDetailDiv');
			goodsDetailDiv.replaceChildren();
			
			let str = '';
			                                                                                    
			str += `	<form action="/goods/updateGoods" method="post">                                                                      `;
	        str += `	<input type="hidden" name="goodsCode" value="${result['goods'].goodsCode}">`;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-9 sub-title">                                            `;
	        str += `          상품 기본 정보                                                        `;
	        str += `       </div>                                                                   `;
	        str += `       <div class="col-3 sub-title d-grid">                                     `;
	        str += `          <button type="submit" class="btn btn-primary">                        `;
	        str += `              수 정                                                             `;
	        str += `          </button>                                                             `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-1"></div>                                                `;
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label text-end">카테고리</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <select id="" name="goodsCateCode" class="form-select">                      `;
	        for(const e of result['cateList']){
				const selected = result['goods'].goodsCateCode == e.goosdCateCode ? 'selected' : '';
		        str += `                   <option value="${e.goodsCateCode}" ${selected}>${e.goodsCateName}</option>                               `;
			}
	        str += `                </select>                                                       `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label text-end">상품명</label>        `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsName" value="${result['goods'].goodsName}">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label text-end">판매가격</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsPrice" value="${result['goods'].goodsPrice}">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label text-end">상품소개</label>      `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <textarea class="form-control" rows="3" name="goodsIntro">${result['goods'].goodsIntro}</textarea>             `;
	        str += `             </div>                                                             `;
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-9 sub-title">                                            `;
	        str += `          상품 판매 정보                                                        `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-1"></div>                                                `;
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label text-end">상품상태</label>      `;
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
	        str += `             <label class="col-3 col-form-label text-end">재 고</label>         `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="text" class="form-control" name="goodsStockCnt" value="${result['goods'].goodsStockCnt}">                        `;
	        str += `             </div>                                                             `;
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col sub-title">                                              `;
	        str += `          상품 이미지 정보                                                      `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += `    <div class="row">                                                           `;
	        str += `       <div class="col-1"></div>                                                `;
	        str += `       <div class="col-11">                                                     `;
	        str += `          <div class="row update-content">                                      `;
	        str += `             <label class="col-3 col-form-label text-end">메인 이미지</label>   `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="file" class="form-control">                        `;
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label text-end"></label>   `;
	        str += `             <div class="col-9">                                                `;
	       	for(const img of result['goods'].goodsImgList){
				if(img.isMain == 'Y'){
			        str += `                <label class="form-label"><a href="javascript:void(0);" onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a></label>                        `;
				}
			}
	        str += `             </div>                                                             `;
	        str += `             <label class="col-3 col-form-label text-end">상세 이미지</label>   `;
	        str += `             <div class="col-9">                                                `;
	        str += `                <input type="file" class="form-control">                        `;
	        str += `             </div>   	                                                        `;
	       	for(const img of result['goods'].goodsImgList){
				if(img.isMain == 'N'){
					str += `             <label class="col-3 col-form-label text-end"></label>   `;
	    		    str += `             <div class="col-9">                                                `;
			        str += `                <label class="form-label"><a href="javascript:void(0);" onclick="openImgModal('${img.attachedFileName}', '${img.originFileName}');">${img.originFileName}</a></label>                        `;
			        str += `             </div> `;
				}
			}
	        str += `          </div>                                                                `;
	        str += `       </div>                                                                   `;
	        str += `    </div>                                                                      `;
	        str += ` </form>                                                                        `;
			
			goodsDetailDiv.insertAdjacentHTML('afterbegin', str);

	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end

}