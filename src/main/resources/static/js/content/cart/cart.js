//장바구니 버튼 클릭 시 실행
function regCart(memId, goodsCode){
	if(memId == 'anonymousUser'){
		const result = confirm('먼저 로그인 해야 합니다.\n로그인 하시겠습니까?');
		
		if(result){
			const loginModal = new bootstrap.Modal('#loginModal');
			loginModal.show();
		}
		
		return ;
	}
	
	regCartAjax(goodsCode);
}


//장바구니 등록 ajax
function regCartAjax(goodsCode){
	const cartCnt = document.querySelector('#goodsCnt').value;

	//ajax start
	$.ajax({
	   url: '/myCart/regCartAjax', //요청경로
	   type: 'post',
	   data: {'goodsCode' : goodsCode, 'cartRegCnt' : cartCnt}, //필요한 데이터
	   success: function(result) {
			const result1 = confirm('장바구니에 상품을 추가했습니다.\n장바구니 목록 페이지로 가시겠습니까?');
			
			if(result1){
				location.href = '/myCart/cartList';
			}
			
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}



//굿즈 삭제
function deleteCart(cartCode){
	const result = confirm('해당 상품을 삭제할까요?');
	
	if(result){
		location.href = `/myCart/deleteCart?cartCode=${cartCode}`;
	}
	
}




function updateTotalPrice(updateCntTag){
	const updateCnt = updateCntTag.value;
	let originPrice = updateCntTag.closest('tr').querySelector('.goodsPriceTd').textContent;
	originPrice = originPrice.replace(/\D/g, '');
	//originPrice = originPrice.replaceAll(',', '');

	
	const totalPrice= Number(originPrice) * updateCnt;
	
	updateCntTag.closest('tr').querySelector('.totalPriceSpan').textContent = '￦' + totalPrice.toLocaleString();
	setFinalPrice();
	
}



//모든 체크박스 컨트롤하는 체크박스
function setCheckState(){
	const check_all = document.querySelector('#checkAll');
	const chks = document.querySelectorAll('.chk');
	
	if(check_all.checked){
		for(const chk of chks){
			chk.checked = true;
		}
	}
	else{
		for(const chk of chks){
			chk.checked = false;
		}
	}
	
	setFinalPrice();
	
}





//allCheck 버튼을 누르지 않아도 밑의 체크박스들 상태에 따라 allCheck버튼 활성화/비활성화
function check(){
	const check_cnt = document.querySelectorAll('.chk').length;
	const checked_cnt = document.querySelectorAll('.chk:checked').length;
	const check_all = document.querySelector('#checkAll');
	
	if(check_cnt == checked_cnt){
		check_all.checked = true;
	}
	else {
		check_all.checked = false;
	}
	
	setFinalPrice();
}





//체크된 체크박스의 상품의 최종 금액
function setFinalPrice (){
	let checked_chks = document.querySelectorAll('.chk:checked');
	let final_price = 0;
	
	for( const chk of checked_chks){
			let total_prices = chk.closest('tr').querySelector('.totalPriceSpan').textContent;
			real_number = total_prices.replace(/\D/g, '');
		
			final_price += 	Number(real_number);
			
	}
	
	//alert(final_price);
	//'￦' + final_price.toLocaleString();
	document.querySelector('#finalPriceSpan').textContent = '￦' + final_price.toLocaleString();
}



//선택삭제 버튼 클릭 시 실행
function deleteCarts(){
	//체크한 체크박스
	const chks = document.querySelectorAll('.chk:checked');
	
	if(chks.length == 0){
		alert('선택한 상품이 없습니다.');
		return ;
	}
	
	//cartCode를 여러개 담을 수 있는 배열 생성
	const cartCodeArr = [];
	
	chks.forEach(function(chk, index){
		cartCodeArr[index] = chk.value;
	});
	
	location.href = `/myCart/deleteCarts?cartCodes=${cartCodeArr}`;	
}



//선택 구매
function buys(){
	const checked_checkboxes = document.querySelectorAll('.chk:checked');
	
	if(checked_checkboxes.length == 0){
		alert('구매할 상품을 선택하세요.');
		return ; 
	}
	
	//넘길 데이터
	const detail_info_arr = [];
	for(let i = 0 ; i < checked_checkboxes.length ; i++){
		buy_detail_info = {
			'goods_code': checked_checkboxes[i].dataset.goodsCode,
			'buy_detail_cnt': checked_checkboxes[i].dataset.buyDetailCnt,
			'buy_detail_price': checked_checkboxes[i].dataset.buyDetailPrice
		};
		
		detail_info_arr[i] = buy_detail_info;
	}
	
	//총구매금액
	let final_price = document.querySelector('#finalPriceSpan').textContent;
	
	//숫자만 추출하는 정규식
	const regex = /[^0-9]/g;
	final_price = final_price.replace(regex, '');
	
	data = {
		'buyPrice':final_price,
		'buyDetailList' : detail_info_arr
	};
	
	//ajax start
	$.ajax({
	   url: '/mBuy/buysAjax', //요청경로
	   type: 'post',
	   async: true,
	   contentType : 'application/json; charset=UTF-8',
	   //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	 	data: JSON.stringify(data), //필요한 데이터
	   success: function(result) {
	      deleteCarts();
	      
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
}






