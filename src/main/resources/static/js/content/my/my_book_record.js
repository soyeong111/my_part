
// 종료일 변경 시
function end_date_change(end_date_input) {
	const start_date_input = document.querySelector('#start-date-input');
	start_date_input.max = end_date_input.value;
}

// 기록 삭제
function delete_record(record_code) {
	if (confirm('삭제 하시겠습니까?')) {
		$.ajax({
			url: '/mBook/deleteBookRecordAjax',
			type: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: {'recordCode':record_code},
			success: function(result) {
				if (result) {
					location.reload();
				} else {
					alert('삭제 실패');
				}
			},
			error: function() {
				alert('ajax 통신 실패');
			}
		});
	}
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
	if (confirm('수정 하시겠습니까?')) {
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
					alert('수정 실패');
				}
			},
			error: function() {
				alert('ajax 통신 실패');
			}
		});
	}
}

// 검색 클릭 시
function searchClick() {
	const search_value_s_tag = document.querySelector('#search-value-s');
	search_value_s_tag.value = search_value_s_tag.value.trim();
	if (search_value_s_tag.value == '') {
		alert('검색어를 입력하세요.');
		return;
	}
	document.querySelector('#search-value-h').value = search_value_s_tag.value;
	document.querySelector('#search-column-h').value = document.querySelector('#search-column-s').value;
	document.querySelector('#search-order-h').value = document.querySelector('#search-order-s')
	document.querySelector('#now-page-num').value = 1;
	document.querySelector('#record-search-form').submit();
}

// 페이지 클릭 시
function pageClick(pageNo) {
	document.querySelector('#now-page-num').value = pageNo;
	document.querySelector('#record-search-form').submit();
}
