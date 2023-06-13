
function fixed_menu_mouseenter() {
	document.querySelector('.header-unfixed-div').classList.remove('d-none');
}

function fixed_menu_mouseleave() {
	document.querySelector('.header-unfixed-div').classList.add('d-none');
}

function public_menu_mouseover(index) {
	const menus = document.querySelectorAll('.header-sub-menu-div > div');
	menus[index].classList.add('main-sub-menu-hover');
}

function public_menu_mouseout(index) {
	const menus = document.querySelectorAll('.header-sub-menu-div > div');
	menus[index].classList.remove('main-sub-menu-hover');
}


//alramCnt 클릭 시
function getAlramList(){
	
}