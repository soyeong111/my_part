

function orderStatus(selectedTag) {
	const status = selectedTag.parentElement.parentElement.querySelector('table').querySelector('tbody').querySelector('#orderStr').textContent;
	let orderHeadDiv = document.querySelector('.status').children[0];
	let orderIconDiv = document.querySelector('.status').children[1];
	let orderProgressDiv = document.querySelector('.status').children[2];
	
	let orderHead = document.querySelector('.status').firstElementChild.textContent;


	if (status == '주문접수') {
		orderHeadDiv.replaceChildren();
	 	orderHeadDiv.insertAdjacentHTML("afterbegin", '<span>주문을 접수하고 있습니다.</span> ');
	 	
	 	orderIconDiv.replaceChildren();
		let strIcon = '';
		
		strIcon += `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">`;
		strIcon += `<path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>`;
		strIcon += `</svg>`;
	 	orderIconDiv.insertAdjacentHTML("afterbegin", strIcon);
	 	
	 	orderProgressDiv.replaceChildren();
	 	let strPro = '';
	 	strPro += `<div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">`;
	 	strPro += `<div class="progress-bar" style="width: 0%"></div>`;
	 	strPro += `</div>`;
	 	orderProgressDiv.insertAdjacentHTML("afterbegin", strPro);
	 	
	}

	else if(status == '배송준비'){
		orderHeadDiv.replaceChildren();
		orderHeadDiv.insertAdjacentHTML("afterbegin", '<span>배송을 준비하고 있습니다.</span>');
		
		orderIconDiv.replaceChildren();
		let strIcon = '';
		
		strIcon += `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-all" viewBox="0 0 16 16">`;
		strIcon += `<path d="M8.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L2.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093L8.95 4.992a.252.252 0 0 1 .02-.022zm-.92 5.14.92.92a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 1 0-1.091-1.028L9.477 9.417l-.485-.486-.943 1.179z"/>`;
		strIcon += `</svg>`;
	 	orderIconDiv.insertAdjacentHTML("afterbegin", strIcon);
	 	
	 	orderProgressDiv.replaceChildren();
	 	let strPro = '';
	 	strPro += `<div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">`;
	 	strPro += `<div class="progress-bar" style="width: 25%;   background-color: #6c96d8;"></div>`;
	 	strPro += `</div>`;
	 	orderProgressDiv.insertAdjacentHTML("afterbegin", strPro);
	}
	
	
	
	else if(status == '배송중'){
		orderHeadDiv.replaceChildren();
	 	orderHeadDiv.insertAdjacentHTML("afterbegin", '<span>배송중 입니다.</span>');
	 	
	 	orderIconDiv.replaceChildren();
		let strIcon = '';
		
		strIcon += `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-truck" viewBox="0 0 16 16">`;
		strIcon += `<path d="M0 3.5A1.5 1.5 0 0 1 1.5 2h9A1.5 1.5 0 0 1 12 3.5V5h1.02a1.5 1.5 0 0 1 1.17.563l1.481 1.85a1.5 1.5 0 0 1 .329.938V10.5a1.5 1.5 0 0 1-1.5 1.5H14a2 2 0 1 1-4 0H5a2 2 0 1 1-3.998-.085A1.5 1.5 0 0 1 0 10.5v-7zm1.294 7.456A1.999 1.999 0 0 1 4.732 11h5.536a2.01 2.01 0 0 1 .732-.732V3.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5v7a.5.5 0 0 0 .294.456zM12 10a2 2 0 0 1 1.732 1h.768a.5.5 0 0 0 .5-.5V8.35a.5.5 0 0 0-.11-.312l-1.48-1.85A.5.5 0 0 0 13.02 6H12v4zm-9 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm9 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>`;
		strIcon += `</svg>`;
	 	orderIconDiv.insertAdjacentHTML("afterbegin", strIcon);
	 	
	 	orderProgressDiv.replaceChildren();
	 	let strPro = '';
	 	strPro += `<div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">`;
	 	strPro += `<div class="progress-bar" style="width: 50%;   background-color: #6c96d8;"></div>`;
	 	strPro += `</div>`;
	 	orderProgressDiv.insertAdjacentHTML("afterbegin", strPro);
	 	
	 	
	 	
	}
	
	
	
	
	else if(status == '배송완료'){
			orderHeadDiv.replaceChildren();
		 	orderHeadDiv.insertAdjacentHTML("afterbegin", '<span>배송이 완료되었습니다.</span>');
		 	
	 	orderIconDiv.replaceChildren();
		let strIcon = '';
		
		strIcon += `<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-emoji-smile" viewBox="0 0 16 16">`;
		strIcon += `<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>`;
		strIcon += `<path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>`;
		strIcon += `</svg>`;
	 	orderIconDiv.insertAdjacentHTML("afterbegin", strIcon);
	 	
	 	orderProgressDiv.replaceChildren();
	 	let strPro = '';
	 	strPro += `<div class="progress" role="progressbar" aria-label="Basic example" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100">`;
	 	strPro += `<div class="progress-bar" style="width: 100%;   background-color: #6c96d8;"></div>`;
	 	strPro += `</div>`;
	 	orderProgressDiv.insertAdjacentHTML("afterbegin", strPro);
	 	
		 	
		}
		
		
		
		
		
		
		
		


}