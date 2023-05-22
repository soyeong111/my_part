
function menu_mouseenter() {
	document.querySelector('.header-sub-menu-div').classList.remove('d-none');
}

function menu_mouseleave() {
	document.querySelector('.header-sub-menu-div').classList.add('d-none');
}

function main_menu_mouseenter(index) {
	console.log(index);
}

function main_menu_mouseleave(index) {
	console.log(index);
}
