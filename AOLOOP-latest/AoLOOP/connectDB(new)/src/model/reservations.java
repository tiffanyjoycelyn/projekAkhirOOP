package model;

import java.util.ArrayList;

public class reservations {
	private Integer reservation_id;
	public String customer_name;
	private Integer employee_id;
	public Integer num_of_people;
	public String status; 
	public Integer table_count; 
	public ArrayList<String> type_id; 
	
	public reservations(String customer_name,Integer employee_id,Integer num_of_people,String status,Integer table_count,ArrayList<String> type_id) {
		this.customer_name = customer_name;
		this.setEmployee_id(employee_id);
		this.num_of_people = num_of_people;
		this.status = status;
		this.table_count = table_count;
		this.type_id = type_id;
	}

	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	
}
