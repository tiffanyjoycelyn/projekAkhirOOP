package model;

public class restaurant {
	public Integer branch_id; 
	public String branch_name; 
	public Integer is_main_resto;
	
	public restaurant(Integer branch_id, String branch_name, Integer is_main_resto) {
		this.branch_id = branch_id;
		this.branch_name = branch_name;
		this.is_main_resto = is_main_resto;
	}

	
}
