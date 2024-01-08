package model;

public class SpecialMenu extends Menu{
	public String narrative;
	public String location;
	Integer is_specialMenu;
	
	public SpecialMenu (String name, Double price, Integer branch_id, String narrative, String location) {
		super(name, price, branch_id);
		this.narrative = narrative;
		this.location = location;
		this.is_specialMenu = 1;
	}
}
