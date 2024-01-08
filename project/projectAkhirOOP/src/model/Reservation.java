package model;

public class Reservation {
	public Integer employeeID;
	public String customerName;
	public Integer tableCount;
	public Integer type_id;
	public Integer numOfPeople;
	public String status;
	public Integer totalPayment;
	
	public Reservation (Integer employeeID, String customerName, Integer tableCount, Integer type_id, Integer numOfPeople) {
		this.employeeID = employeeID;
		this.customerName = customerName;
		this.tableCount = tableCount;
		this.type_id = type_id;
		this.numOfPeople = numOfPeople;
		this.status = "in_reserve";
		this.totalPayment = 0;
	}
}
