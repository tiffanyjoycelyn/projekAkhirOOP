package model;

public class menu {
	public Integer menu_id;
	public String menu_name; 
	public Integer price;
	public Integer branch_id;
	
	public menu(Integer menu_id, String menu_name, Integer price, Integer branch_id ) {
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.price = price;
		this.branch_id = branch_id;
	}
}
