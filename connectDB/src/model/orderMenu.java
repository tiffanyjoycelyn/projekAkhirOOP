package model;

import java.util.ArrayList;

public class orderMenu {
	private Integer reservation_id;
	public ArrayList<menu> menus;
	public Integer totalPrice;
	
	public orderMenu(ArrayList<menu> menus,Integer totalPrice) {
		this.menus = menus;
		this.totalPrice = totalPrice;
	}
	
	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
	
	
}
