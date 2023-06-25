
init();

function init() {
	
}

// 차트 그리기

// 사진 플러스 눌렀을 때
function add_icon_click() {
	document.querySelector('#mem-img-input').click();
}

// 사진 선택 시
function mem_img_change() {
	const selectedFile = document.querySelector('#mem-img-input').files[0];
	if (selectedFile != null) {
		if (selectedFile.type.startsWith('image/')) {
			const formData = new FormData();
			formData.append("memImg", selectedFile);
			$.ajax({
				url: '/mMember/updateMemImgAjax',
				type: 'post',
				processData: false,
				contentType: false,
				data: formData,
				success: function(result) {
					if (result == 1) {
						alert('사진이 등록되었습니다.');
						location.reload();
					} else {
						alert('사진 등록 실패');
					}
				},
				error: function() {
					alert('ajax 통신 실패');
				}
			});
		} else {
			alert('이미지를 선택해주세요.');
		}
	}
}

// 사진 엑스 눌렀을 때
function del_icon_click(memImgUrl) {
	if (confirm('사진을 삭제 하시겠습니까?')) {
		$.ajax({
			url: '/mMember/deleteMemImgAjax',
			type: 'post',
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			data: {'memImgUrl':memImgUrl},
			success: function(result) {
				if (result == 1) {
					alert('사진이 삭제되었습니다.');
					location.reload();
				} else {
					alert('사진 삭제 실패');
				}
			},
			error: function() {
				alert('ajax 통신 실패');
			}
		});
	}
}

// 차트 연도 셀렉트 바꿨을 때
function year_change(year_select, main_meunu_code, sub_menu_code) {
	location.href = `/mMember/myProfile?mainMenuCode=${main_meunu_code}&subMenuCode=${sub_menu_code}&nowYear=${year_select.value}`;
}
