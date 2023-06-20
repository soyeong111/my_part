  	
  	var IMP = window.IMP; // 생략 가능
    	IMP.init("imp04555812"); // 예: imp00000000
    	
    	
    	
function requestPay() {
	
  	alert(111);
	
	
      // IMP.request_pay(param, callback) 결제창 호출
      IMP.request_pay({ // param
          pg: "kakaopay",
          pay_method: "card",
          merchant_uid: "ORD20180131-0000011", //주문번호
          name: "노르웨이 회전 의자",
          amount: 333,
          buyer_email: "gildong@gmail.com",
          buyer_name: "홍길동",
          buyer_tel: "010-4242-4242",
          buyer_addr: "서울특별시 강남구 신사동",
          buyer_postcode: "01181" //우편번호
      }, function (rsp) { // callback
          if (rsp.success) {
              alert(1);
              // 결제 성공 시 로직,
              
          } else {
            alert(2);
              // 결제 실패 시 로직,
             
          }
      });
    }

