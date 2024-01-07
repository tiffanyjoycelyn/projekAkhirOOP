package model;

public class specialMenu extends menu {
	public String menu_narrative;
	public String location;
	public specialMenu(Integer menuID,String buffMenuName,Integer buff_price,Integer buff_branch_id,String buff_narrative,String buff_location,Integer is_specialMenu) {
		super(menuID,buffMenuName,buff_price, buff_branch_id);
		this.menu_narrative = buff_narrative;
	}
}