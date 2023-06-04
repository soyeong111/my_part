
function extend(btn, name, borrowCode, bookCode) {
	var button = document.getElementById('exBtn-' + borrowCode);
	if (button && button.value === '연장') {
		$.ajax({
			url: '/book/checkReserveBeforeExtendAjax',
			type: 'post',
			async: true,
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: { 'borrowCode': borrowCode, 'memId': name },
			success: function(result) {
				if (result === 1) {
					Swal.fire('다른 회원이 예약한 도서는 반납 기한 연장이 불가합니다.', '', 'error');
				} else {
					Swal.fire({
						title: '반납 기한을 연장하시겠습니까?',
						icon: 'question',
						showCancelButton: true,
						confirmButtonText: '네',
						cancelButtonText: '아니요',
					}).then((result) => {
						if (result.isConfirmed) {
							$.ajax({
								url: '/book/extendAjax',
								type: 'post',
								async: true,
								contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
								data: { 'borrowCode': borrowCode, 'memId': name },
								success: function(result) {
									Swal.fire('도서 반납 기한이 \n3일 연장되었습니다.', '', 'success')
										.then(() => {
											location.reload();
										});
								},
								error: function() {
									Swal.fire('연장에 실패했습니다.', '', 'error');
								}
							});
						}
					});
				}
			},
			error: function() {
				Swal.fire('예약 여부 확인에 실패했습니다.', '', 'error');
			}
		});
	}
}
