package model;

public class checkout {
	private Integer checkout_id; 
	private Integer reservation_id;
	public Integer total_payment;
	
	public checkout(Integer total_payment) {
		this.total_payment = total_payment;
	}

	public Integer getCheckout_id() {
		return checkout_id;
	}

	public void setCheckout_id(Integer checkout_id) {
		this.checkout_id = checkout_id;
	}

	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
}
