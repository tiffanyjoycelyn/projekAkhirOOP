package connectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchDB {
    public static String searchBranchNameFromBranchID (Integer x, Connection conn, String query){

		Integer id  = 0;
		String branchName = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("branch_id");
				if(id == x) {
					branchName = rs.getString("branch_name");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return branchName;
    }
    
    public static String searchMenuNameFromMENUID (Integer x, Connection conn, String query){
		Integer id  = 0;
		String menuName = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					menuName = rs.getString("menu_name");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return menuName;
    }
    
    public static Integer searchMenuPriceFromMENUID (Integer x, Connection conn, String query){
		Integer id  = 0;
		Integer price = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					price = rs.getInt("price");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return price;
    }
    
    public static Integer searchMenuBranchIDFromMENUID (Integer x, Connection conn, String query){
		Integer id  = 0;
		Integer branchID = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					branchID = rs.getInt("branch_id");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return branchID;
    }
    
    public static Integer searchIsSpecialMenuFromMENUID (Integer x, Connection conn, String query){
		Integer id  = 0;
		Integer is_specialMenu = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					is_specialMenu = rs.getInt("is_specialMenu");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return is_specialMenu;
    }
    
    public static String searchNarrativeFromMENUID (Integer x, Connection conn, String query){
		Integer id  = 0;
		String narrative = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					narrative = rs.getString("narrative");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return narrative;
    }
    
    public static String searchLocationFromMENUID (Integer x, Connection conn, String query){

		Integer id  = 0;
		String location = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("menu_id");
				if(id == x) {
					location = rs.getString("location");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return location;
    }
    
    public static String searchEmployeeName (Integer x, Connection conn, String query){
		Integer id  = 0;
		String employeeName = null;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("employee_id");
				if(id == x) {
					employeeName = rs.getString("employee_name");
					break;
				}
			}
		} catch (SQLException e) {}
   
    	return employeeName;
    } 
    
    public static Integer searchlastTransactionID(Connection conn, String Query) {
    	Integer transactionID = 0;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(Query);			
				rs.last();
				transactionID = rs.getInt("reservation_id");				
				System.out.println(rs.getInt("reservation_id"));
				System.out.println(rs.getInt("employee_id"));			
		} catch (SQLException e) {}
    	
    	return transactionID;
    }
}


