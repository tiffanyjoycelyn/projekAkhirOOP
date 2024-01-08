package connect;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Show {
	public static void showMenuAvailableOnBranch (Connection conn, Integer branchID) {
		String query = "select * from menus where branch_id =" + branchID;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			System.out.println("Available Menu");
			System.out.println("======================================");
			rs.next();
			System.out.println("branchID\t: " +rs.getInt("branch_id"));
			System.out.println("======================================");
			
			do {
				System.out.println("id \t: " + rs.getInt("menu_id"));
				System.out.println("name\t: " + rs.getString("menu_name"));
				System.out.println("price\t: " + rs.getDouble("price"));

				
				if(rs.getInt("is_specialMenu") == 1) {
					System.out.println("Special menu, " + rs.getString("narrative") + ", "+ rs.getString("location")); 
				}else {
					System.out.println("normal menu");
				}
				
				System.out.println();
			}while(rs.next());
			
		} catch (Exception e) {}
	}
	
	public static void showAvaillableReservation (Connection conn, Integer employeeID) {
		String query = "select * from reservations where employee_id = " + employeeID;
		System.out.println("Available Reservations");
		System.out.println("======================================");
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getString("status").equals("in_reserve")) {
					System.out.println("id   \t: " + rs.getInt("reservation_id"));
					System.out.println("name \t: " + rs.getString("customer_name"));
					
					String tableType;
					
					if(rs.getInt("type_id") == 1) {
						tableType = "Romantic";
					}else if(rs.getInt("type_id") == 2) {
						tableType = "General";
					}else {
						tableType = "Family";
					}
					
					System.out.println("table\t: " + rs.getInt("table_count") + ", " + tableType);
					
					System.out.println(rs.getInt("num_of_people") + " Persons");
				}
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static String showMenuFromID (Connection conn, Integer menuID) {
		String query = "select * from menus where menu_id = " + menuID;
		String menuName = "";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			menuName = rs.getString("menu_name");
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return menuName;
	}
	
	public static Double showMenuPriceFromID (Connection conn, Integer menuID) {
		Double price = 0.0;
		String query = "select * from menus where menu_id = " + menuID;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			price = rs.getDouble("price");
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return price;
	}
	
	public static void showCurrentOrder (Connection conn, Integer reservationID) {
		String query = "select * from order_menu where reservation_id = " + reservationID;
		
		System.out.println("================================");
		System.out.println("Current Order");
		System.out.println("================================");
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Integer menuID = rs.getInt("menu_id");
				
				String menuName = showMenuFromID(conn, menuID);
				
				System.out.println("||\t " + menuName + "\t|  " + rs.getInt("quantity") + "  ||");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
		
		System.out.println("================================");
	}
	
	public static void showAvaillableInOrder (Connection conn, Integer employeeID) {

		String query = "select * from reservations where employee_id = " + employeeID;
		System.out.println("Available in_order customers");
		System.out.println("======================================");
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getString("status").equals("in_order")) {
					System.out.println("id   \t: " + rs.getInt("reservation_id"));
					System.out.println("name \t: " + rs.getString("customer_name"));
					
					String tableType;
					
					if(rs.getInt("type_id") == 1) {
						tableType = "Romantic";
					}else if(rs.getInt("type_id") == 2) {
						tableType = "General";
					}else {
						tableType = "Family";
					}
					
					System.out.println("table\t: " + rs.getInt("table_count") + ", " + tableType);
					
					System.out.println(rs.getInt("num_of_people") + " Persons");
				}
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static boolean checkIfInOrder (Connection conn, Integer employeeID, Integer reservationID) {

		String query = "select * from reservations where employee_id = " + employeeID;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				if(rs.getString("status").equals("in_order")) {
					if(rs.getInt("reservation_id") == reservationID) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	}
	
	public static void showOrderedMenu (Connection conn, Integer reservationID) {
		String query = "select * from order_menu where reservation_id = " + reservationID;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Integer menuID = rs.getInt("menu_id");
				String menuName = showMenuFromID(conn, menuID);
				Double price = showMenuPriceFromID(conn, menuID);
				
				System.out.println("||\t " + menuName + "\t|  " + rs.getInt("quantity") + "|" + price * rs.getInt("quantity")  + "  ||");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	public static Double showTotal (Connection conn, Integer reservatonID) {
		Double total = 0.0;
		String query = "select * from reservations where reservation_id = " + reservatonID;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			total = rs.getDouble("total_payment");
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return total;
		
	}
}
 










