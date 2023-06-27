
/* side-main-menu-div에 마우스 올라갈 때 */
function main_menu_mouseenter(main_menu_div) {
	const collapse_div = main_menu_div.querySelector('.collapse');
	if (collapse_div != null) {
		const collapse = bootstrap.Collapse.getOrCreateInstance(collapse_div);
		collapse.show();
	}
}

/* side-main-menu-div에서 마우스 내려올 때 */
function main_menu_mouseleave(main_menu_div) {
	const collapse_div = main_menu_div.querySelector('.collapse');
	if (collapse_div != null) {
		const collapse = bootstrap.Collapse.getOrCreateInstance(collapse_div);
		collapse.hide();
	}
}
