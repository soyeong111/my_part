//장바구니 버튼 클릭 시 실행
function regCart(memId, itemCode){
	if(memId == 'anonymousUser'){
		const result = confirm('먼저 로그인 해야 합니다.\n로그인 하시겠습니까?');
		
		if(result){
			const loginModal = new bootstrap.Modal('#loginModal');
			loginModal.show();
		}
		
		return ;
	}
	
	regCartAjax(itemCode);
}


//장바구니 등록 ajax
function regCartAjax(itemCode){
	const cartCnt = document.querySelector('#goodsCnt').value;
	
	//ajax start
	$.ajax({
	   url: '/cart/regCartAjax', //요청경로
	   type: 'post',
	   data: {'itemCode' : itemCode, 'cartCnt' : cartCnt}, //필요한 데이터
	   success: function(result) {
			const result1 = confirm('장바구니에 상품을 추가했습니다.\n장바구니 목록 페이지로 가시겠습니까?');
			
			if(result1){
				location.href = '/cart/cartList';
			}
			
	   },
	   error: function() {
	      alert('실패');
	   }
	});
	//ajax end
}