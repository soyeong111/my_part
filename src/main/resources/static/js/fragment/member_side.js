
/* side-main-menu-div에 마우스 올라갈 때 */
function main_menu_mouseenter(main_menu_div) {
	console.log('in-1');
	const collapse_div = main_menu_div.querySelector('.collapse');
	if (collapse_div != null) {
		const collapse = bootstrap.Collapse.getOrCreateInstance(collapse_div);
		collapse.show();
		console.log('in-X');
	}
	console.log('in-2');
}

/* side-main-menu-div에서 마우스 내려올 때 */
function main_menu_mouseleave(main_menu_div) {
	console.log('out-1');
	const collapse_div = main_menu_div.querySelector('.collapse');
	if (collapse_div != null) {
		const collapse = bootstrap.Collapse.getOrCreateInstance(collapse_div);
		collapse.hide();
		console.log('out-X');
	}
	console.log('out-2');
}
