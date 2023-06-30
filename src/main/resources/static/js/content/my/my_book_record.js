
init();

const book_search_modal = new bootstrap.Modal('#book-search-modal');

function init() {
	const book_search_modal_div = document.querySelector('#book-search-modal');
	// 검색 모달 닫힐 때 이벤트 생성
	book_search_modal_div.addEventListener('hidden.bs.modal', () => {
		let str = `<div class="row mt-3 pt-3 border-top">`;
		str += `<div class="col text-center">`;
		str += `검색어를 입력해주세요.`;
		str += `</div>`;
		str += `</div>`;
		const modal_change_div = document.querySelector('#modal-change-div');
		modal_change_div.replaceChildren();
		modal_change_div.insertAdjacentHTML('afterbegin', str);
		const book_search_value = document.querySelector('#book-search-value');
		book_search_value.value = '';
	});
}

// 책 검색 모달 열기
function open_book_search_modal() {
	book_search_modal.show();
}

// 책 검색 모달 닫기
function close_book_search_modal() {
	book_search_modal.hide();
}

// 책 검색
function book_search() {
	const book_search_value = document.querySelector('#book-search-value');
	book_search_value.value = book_search_value.value.trim();
	if (book_search_value.value == '') {
		book_search_value.focus();
		return;
	}
	const book_search_column = document.querySelector('#book-search-column');
	$.ajax({
		url: '/mBook/bookSearchAjax',
		type: 'post',
		contentType: 'application/json; charset=UTF-8',
		data: JSON.stringify({'searchColumn':book_search_column.value, 'searchValue':book_search_value.value}),
		success: function(result) {
			drawBookSearchModal(result);
		},
		error: function() {
			alert('ajax 통신 실패');
		}
	});
}

// 검색된 책 리스트 그리기
function drawBookSearchModal(book_list) {
	let str = '';
	book_list.forEach((book) => {
		str += `<div class="row mt-3 pt-2 border-top">`;
		str += `<div class="offset-2 col-3">`;
		str += `<div class="m-2">`;
		str += `<a href="javascript:void(0);" onclick="bookSeleted('${book.bookCode}', '${book.bookTitle}');">`;
		str += `<img src="/image/book/${book.imgList[0].attachedFileName}" width="100%">`;
		str += `</a>`;
		str +=  `</div>`;
		str += `</div>`;
		str += `<div class="offset-1 col-6">`;
		str += `<div class="m-2">`;
		str += `<a href="javascript:void(0);" onclick="bookSeleted('${book.bookCode}', '${book.bookTitle}');">${book.bookTitle}</a>`;
		str +=  `</div>`;
		str += `<div class="m-2">${book.bookAuthor}</div>`;
		str += `<div class="m-2">${book.bookPublisher}</div>`;
		str += `<div class="m-2">${book.bookPublicationDate}</div>`;
		str += `</div>`;
		str += `</div>`;
	});
	if (book_list.length == 0) {
		str += `<div class="row mt-3 pt-3 border-top">`;
		str += `<div class="col text-center">`;
		str += `검색 결과가 없습니다.`;
		str += `</div>`;
		str += `</div>`;
	}
	const modal_change_div = document.querySelector('#modal-change-div');
	modal_change_div.replaceChildren();
	modal_change_div.insertAdjacentHTML('afterbegin', str);
}

// 책 선택
function bookSeleted(book_code, book_title) {
	document.querySelector('#show-book-title').value = book_title;
	document.querySelector('#selected-book-code').value = book_code;
	close_book_search_modal();
}









// 종료일 변경 시
function end_date_change(end_date_input) {
	const start_date_input = document.querySelector('#start-date-input');
	start_date_input.max = end_date_input.value;
}

// 기록 삭제
function delete_record(record_code) {
	Swal.fire({
		icon: 'question',
		title: '삭제 확인',
		text: '삭제 하시겠습니까?',
		showCancelButton: true,
		confirmButtonText: '확인',
		cancelButtonText: '취소',
	}).then((res) => {
		if (res.isConfirmed) {
			$.ajax({
				url: '/mBook/deleteBookRecordAjax',
				type: 'post',
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				data: {'recordCode':record_code},
				success: function(result) {
					if (result) {
						location.reload();
					} else {
						Swal.fire({
							icon: 'error',
							title: '삭제 실패',
						});
					}
				},
				error: function() {
					alert('ajax 통신 실패');
				}
			});
		}
	});
}

// 태그 변경 (회색 수정 클릭)
function change_tag(record_code, update_a) {
	update_a.closest('.record-card-div').querySelector('.book-review-textarea').readOnly = false;
	update_a.closest('.record-card-div').querySelector('.start-date-input').readOnly = false;
	update_a.closest('.record-card-div').querySelector('.end-date-input').readOnly = false;
	const str = `<a href="javascript:void(0);" class="color-custom-r" onclick="update_record('${record_code}', this);">수정</a>`;
	update_a.insertAdjacentHTML('beforebegin', str);
	update_a.remove();
}

// 기록 수정 (빨간 수정 클릭)
function update_record(record_code, update_a) {
	const book_review_textarea = update_a.closest('.record-card-div').querySelector('.book-review-textarea');
	book_review_textarea.value = book_review_textarea.value.trim();
	if (book_review_textarea.value == '') {
		book_review_textarea.focus();
		return;
	}
	Swal.fire({
		icon: 'question',
		title: '수정 확인',
		text: '수정 하시겠습니까?',
		showCancelButton: true,
		confirmButtonText: '확인',
		cancelButtonText: '취소',
	}).then((res) => {
		if (res.isConfirmed) {
			const start_date_input = update_a.closest('.record-card-div').querySelector('.start-date-input');
			const end_date_input = update_a.closest('.record-card-div').querySelector('.end-date-input');
			$.ajax({
				url: '/mBook/updateBookRecordAjax',
				type: 'post',
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				data: {'recordCode':record_code, 'bookReview':book_review_textarea.value, 'startDate':start_date_input.value, 'endDate':end_date_input.value},
				success: function(result) {
					if (result) {
						start_date_input.value = start_date_input.value;
						end_date_input.value = end_date_input.value;
						update_a.closest('.record-card-div').querySelector('.book-review-textarea').readOnly = true;
						update_a.closest('.record-card-div').querySelector('.start-date-input').readOnly = true;
						update_a.closest('.record-card-div').querySelector('.end-date-input').readOnly = true;
						const str = `<a href="javascript:void(0);" class="text-secondary" onclick="change_tag('${record_code}', this);">수정</a>`;
						update_a.insertAdjacentHTML('beforebegin', str);
						update_a.remove();
					} else {
						Swal.fire({
							icon: 'error',
							title: '수정 실패',
						});
					}
				},
				error: function() {
					alert('ajax 통신 실패');
				}
			});
		}
	});
}

// 검색 클릭 시
function searchClick() {
	const search_value_s_tag = document.querySelector('#search-value-s');
	search_value_s_tag.value = search_value_s_tag.value.trim();
	if (search_value_s_tag.value == '') {
		search_value_s_tag.focus();
		return;
	}
	document.querySelector('#search-value-h').value = search_value_s_tag.value;
	document.querySelector('#search-column-h').value = document.querySelector('#search-column-s').value;
	document.querySelector('#search-order-h').value = document.querySelector('#search-order-s').value;
	document.querySelector('#now-page-num').value = 1;
	document.querySelector('#record-search-form').submit();
}

// 페이지 클릭 시
function pageClick(pageNo) {
	document.querySelector('#now-page-num').value = pageNo;
	document.querySelector('#record-search-form').submit();
}
