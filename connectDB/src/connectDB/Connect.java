package connectDB;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Connect {
    public static Connection first(Connection conn){
        try {
            String url ="jdbc:mysql://localhost/restaurant";
            String user="root";
            String pass="";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,pass);
//            stmt = conn.createStatement();
            System.out.println("koneksi berhasil;\n");
            return conn;
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
            return null;
        }
    }

    public static Integer getLatestReservationID(Connection conn, String query) {
    	int size = 0;
    	Integer reservationID = 0;
    	try {
    		size = 0;
			Statement stmt = conn.createStatement();		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
					size++;
			}
		} catch (Exception e) {}
    	try {
			Statement stmt = conn.createStatement();		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
					if(rs.getRow()== size) {
						reservationID = rs.getInt("reservation_id");
						break;
					}
			}
		} catch (Exception e) {}
    	
    	return reservationID;
    }
    
    public static void deleteData (Connection conn, Integer x, Integer branchID) {
    	try {
    		PreparedStatement pt = conn.prepareStatement("delete from menus where menu_id = ? and branch_id = ?");
    		pt.setInt(1, x);
    		pt.setInt(2, branchID);	
    		int check = pt.executeUpdate();
    		if(check != 0) { 
    			System.out.println("succesfully delete data");
    		}else {
    			System.out.println("failed to delete data please make sure to enter the correct id");
    		}
		} catch (Exception e) {}
    }
    
    public static void insertMenu (Connection conn, String name, Double price, Integer branchID, int type, Scanner ns) {
    	String narrative = "";
    	String location = "";
//    	Scanner s = new Scanner (System.in);
//    	s.useDelimiter("\n");
    	if(type == 1) {
    		System.out.print("input narrative: ");
    		narrative = ns.next();ns.nextLine();
    		System.out.print("input location: ");
    		location = ns.next();ns.nextLine();
    	}
    	try {
			PreparedStatement pt = conn.prepareStatement
					("insert into menus values (default, ?, ?, ?, ?, ?, ?)");
			pt.setString(1, name);
			pt.setDouble(2, price);
			pt.setInt(3, branchID);
			pt.setString(4, narrative);
			pt.setString(5, location);
			pt.setInt(6, type);
			
			int  i = pt.executeUpdate();
			if(i != 0) {
				System.out.println("Menu inserted");
			}else {
				System.out.println("failed to insert data");
			}
		} catch (Exception e) {}
    	
    }
    
    public static void insertReservation (Connection conn, Integer employee_id, String name, Integer table_count, Integer type_id, Integer num_people) {
    	String status = "in_reserve";
    	
    	try {
        	
    		PreparedStatement pt = conn.prepareStatement("insert into reservations values (default, ?, ?, ?, ?, ?, ?)");
    		pt.setInt(1, employee_id);
    		pt.setString(2, name);
    		pt.setInt(3, table_count);
    		pt.setInt(4, type_id);
    		pt.setInt(5, num_people);
    		pt.setString(6, status);
    		
    		int i = pt.executeUpdate();
    		
    		if(i != 0) {
    			System.out.println("inserted asdklaskldjaskl;d");
    		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("failed");
		}
    }
}







