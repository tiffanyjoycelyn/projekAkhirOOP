package connect;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Menu;
import model.Reservation;
import model.SpecialMenu;



public class Connect {
    public static Connection first(Connection conn){
        try {
            String url ="jdbc:mysql://localhost/restaurant";
            String user="root";
            String pass="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn = DriverManager.getConnection(url,user,pass);
            
            System.out.println("koneksi berhasil;\n");
            
            return conn;
        } catch (Exception e) {
            System.err.println("koneksi gagal\n" +e.getMessage());
            return null;
        }
    }

    
    public static void insertNewReservation (Connection conn, Reservation r) {
    	try {
			PreparedStatement pt = conn.prepareStatement("insert into reservations values (default, ?, ?, ?, ?, ?, ?, 0)");
			pt.setInt(1, r.employeeID);
			pt.setString(2, r.customerName);
			pt.setInt(3, r.tableCount);
			pt.setInt(4, r.type_id);
			pt.setInt(5, r.numOfPeople);
			pt.setString(6, r.status);
			
			
			int i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("reservation have been made");
			}else {
				System.out.println("failed to add reservation");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
    }
    
    public static void deleteMenu (Connection conn, Integer menuID) {
    	try {
			PreparedStatement pt = conn.prepareStatement("delete from menus where menu_id = ?");
			pt.setInt(1, menuID);
			
			int i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("menu have been deleted");
			}else {
				System.out.println("failed to delete menu");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
    }
    
    public static void insertMenu (Connection conn, Menu m) {
    	try {
			PreparedStatement pt = conn.prepareStatement("insert into menus values (default, ?, ?, ?, '', '', 0)");
			pt.setString (1, m.name);
			pt.setDouble(2, m.price);
			pt.setInt(3, m.branch_id);
			
			int i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("Menu inserted");
			}else {
				System.out.println("failed to insert menu");
			}
		} catch (Exception e) {
			System.out.println("error");
		}
    }

    public static void insertSpecialMenu (Connection conn, SpecialMenu sm) {

    	try {
    		PreparedStatement pt = conn.prepareStatement("insert into menus values (default, ?, ?, ?, ?, ?, 1)");
			pt.setString (1, sm.name);
			pt.setDouble(2, sm.price);
			pt.setInt(3, sm.branch_id);
			pt.setString(4, sm.narrative);
			pt.setString (5, sm.location);
			
			int  i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("Menu inserted");
			}else {
				System.out.println("failed to insert menu");
			}
		} catch (Exception e) {
			System.out.println("error");
		}
    }
    
    public static void changeStatus(Connection conn, Integer x, Integer reservationID) {
    	String status = "";
    	
    	if(x == 1) {
    		status  = "in_order";
    	}else if(x == 2) {
    		status = "finalized";
    	}
    	
    	try {
			PreparedStatement pt = conn.prepareStatement("update reservations set status = ? where reservation_id = ?");
			pt.setString(1, status);
			pt.setInt(2, reservationID);
			
			int i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("status changed to" + status);
			}else {
				System.out.println("failed to change status");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
    }
    
    public static void insertOrder (Connection conn, Integer reservationID ,Integer menuID, Integer qty) {
    	try {
			PreparedStatement pt = conn.prepareStatement("insert into order_menu values (default, ?, ?, ?)");
			pt.setInt(1, reservationID);
			pt.setInt(2, menuID);
			pt.setInt(3, qty);
			
			int i = pt.executeUpdate();
			
			if(i != 0) {
				System.out.println("order added");
			}else {
				System.out.println("failed to add order");
			}
		} catch (Exception e) {
			System.out.println("error");
		}
    	
    }
    
    public static Double addTotal (Connection conn, Integer menuID, Integer reservationID, Integer qty) {
		String query = "select * from menus where menu_id = " + menuID;
		Double price = 0.0;
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			price = rs.getDouble("price");
		} catch (Exception e) {
			System.out.println("error");
		}
		
		try {
			PreparedStatement pt = conn.prepareStatement("update reservations set total_payment = total_payment + ? where reservation_id = ?");

			pt.setDouble(1, price * qty);
			pt.setInt(2, reservationID);
			
			int i = pt.executeUpdate ();
			
			if(i != 0) {
				System.out.println("added to total");
			}else {
				System.out.println("failed to add to total");
			}
			
		} catch (Exception e) {
			System.out.println("error");
		}
		
		return price;
    }
    
}

















