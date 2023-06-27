setFinalPrice();

//장바구니 버튼 클릭 시 실행
function regCart(memId, goodsCode, cartRegCnt, mainMenuCode, subMenuCode){
	if(memId == 'anonymousUser'){
		const result = confirm('먼저 로그인 해야 합니다.\n로그인 하시겠습니까?');
		
		if(result){
			const loginModal = new bootstrap.Modal('#loginModal');
			loginModal.show();
		}
		
		return ;
	}
	
	regCartAjax(goodsCode, mainMenuCode, subMenuCode);
}


//장바구니 등록 ajax
function regCartAjax(goodsCode, mainMenuCode, subMenuCode){
	const cartCnt = document.querySelector('#goodsCnt').value;

	//ajax start
	$.ajax({
	   url: '/mCart/regCartAjax', //요청경로
	   type: 'post',
	   data: {'goodsCode' : goodsCode, 'cartRegCnt' : cartCnt}, //필요한 데이터
	   success: function(result) {
			const result1 = confirm('장바구니에 상품을 추가했습니다.\n장바구니 목록 페이지로 가시겠습니까?');
			
			if(result1){
				location.href = `/mCart/cartList?mainMenuCode=${mainMenuCode}&subMenuCode=${subMenuCode}`;
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
		location.href = `/mCart/deleteCart?cartCode=${cartCode}`;
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

function setPrice(updateCntTag){
	const updateCnt = updateCntTag.value;
	
	let originPrice = document.querySelector('#price').textContent;
	
	let originPrice_real = originPrice.replace(/[^0-9]/g, '');
	let price = parseInt(originPrice_real);
	
	let finalPrice = price*updateCnt;
	document.querySelector('#finalPrice').textContent =  '￦ ' + finalPrice.toLocaleString();;
	
	
	
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
	//updateTotalPrice();
	
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
	
	location.href = `/mCart/deleteCarts?cartCodes=${cartCodeArr}`;	
}



function buyKakao(){
		var IMP = window.IMP; // 생략가능
		IMP.init('imp04555812'); 
		// i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
		// ''안에 띄어쓰기 없이 가맹점 식별코드를 붙여넣어주세요. 안그러면 결제창이 안뜹니다.
		alert(1);
		IMP.request_pay({
			
			pg: 'kakaopay',
			pay_method: 'card',
			merchant_uid: 'merchant_' + new Date().getTime(),
			/* 
			 *  merchant_uid에 경우 
			 *  https://docs.iamport.kr/implementation/payment
			 *  위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
			 */
			name: '주문명 : 아메리카노',
			// 결제창에서 보여질 이름
			// name: '주문명 : ${auction.a_title}',
			// 위와같이 model에 담은 정보를 넣어 쓸수도 있습니다.
			amount: 2000,
			// amount: ${bid.b_bid},
			// 가격 
			buyer_name: '이름',
			// 구매자 이름, 구매자 정보도 model값으로 바꿀 수 있습니다.
			// 구매자 정보에 여러가지도 있으므로, 자세한 내용은 맨 위 링크를 참고해주세요.
			buyer_postcode: '123-456',
			
			}, function (rsp) {
				console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg;
				
			buys();
  				
				// success.submit();
				// 결제 성공 시 정보를 넘겨줘야한다면 body에 form을 만든 뒤 위의 코드를 사용하는 방법이 있습니다.
				// 자세한 설명은 구글링으로 보시는게 좋습니다.
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
	
}




    	





// 구매
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
		'final_price':final_price,
		'detail_info_arr' : detail_info_arr
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
	      window.location.href = '/mBuy/buyList';
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
	
}

  