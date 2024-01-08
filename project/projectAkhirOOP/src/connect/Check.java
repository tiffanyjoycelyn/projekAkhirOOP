package connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Check {
	public static Integer checkEmployeeBranch (Integer employeeID, Connection conn) {
		
		String query = "select * from employees where employee_id = " + employeeID;
		Integer branchID = 0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			rs.next();
			
			branchID = rs.getInt("branch_id");
			
		} catch (Exception e) {}
		
		return branchID;
	}
	
	public static Integer checkMenuID (Connection conn , Integer branchID, Integer menuID) {
		int isTrue = 0;
		String query = "select * from menus where branch_id = " + branchID;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getInt("menu_id") == menuID) {
					isTrue = 1;
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return isTrue;
	}
	
	public static Integer checkReservationID (Connection conn, Integer id) {
		Integer isTrue = 0;
		String query = "select * from reservations where reservation_id = " + id;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getInt("reservation_id") == id) {
					isTrue = 1;
					break;
				}
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return isTrue;
	}
}






















