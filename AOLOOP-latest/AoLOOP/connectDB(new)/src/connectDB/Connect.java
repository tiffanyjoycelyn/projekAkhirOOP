package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static void showMenu (Connection conn, String query) {
    	try {
			Statement stmt = conn.createStatement();		
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
					System.out.println("id \t: " + rs.getInt("menu_id"));
					System.out.println("name\t: " + rs.getString("menu_name"));
					System.out.println("price\t: " + rs.getDouble("price"));
					System.out.println("branch id:" +rs.getInt("branch_id"));
					if(rs.getInt("is_specialMenu") == 1) {
						System.out.println("Special menu, " + rs.getString("narrative") + ", "+ rs.getString("location")); 
					}else {
						System.out.println("normal menu");
					}
					System.out.println();
				}
		} catch (Exception e) {}
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
    public static void showMenutoAdd (Connection conn, String query,Integer x) {
    	Integer id = 0;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println("==================================================================");
			System.out.println("||                                       Showing Available Menu ||");
			System.out.println("==================================================================");
			while (rs.next()) {
				id = rs.getInt("branch_id");
				if(id == x) {;
				System.out.println("||\tID \t: " + rs.getInt("menu_id"));
				System.out.println("||\tName\t: " + rs.getString("menu_name"));
				System.out.println("||\tPrice\t: " + rs.getDouble("price"));
				if(rs.getInt("is_specialMenu") == 1) {
					System.out.println("||\tSpecial menu, " + rs.getString("narrative") + ", "+ rs.getString("location")); 
				}else {
					System.out.println("||\tNormal menu");
				}
				System.out.println("==================================================================");
				}
			}
		} catch (Exception e) {}
    }
    public static boolean checkmenuIDontheBranch(Integer x,Integer y, Connection conn, String query) {
    	Integer branchid  = 0;
    	Integer menuid = 0;
		Integer flag = 0;
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				branchid = rs.getInt("branch_id");
				menuid = rs.getInt("menu_id");
				if(branchid == x) { //check menu on the branch
					if(menuid == y) {
						flag = menuid;
						break;
					}
				}
			}
		} catch (SQLException e) {}
    	
    	if(flag != 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    public static Integer checkEmployeeBranch (Integer x, Connection conn, String query){
		Integer id  = 0;
		Integer branchId = 0;	
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				id = rs.getInt("employee_id");
				if(id == x) {
					branchId = rs.getInt("branch_id");
					break;
				}
			}
		} catch (SQLException e) {}
    	
    	return branchId;
    }
    
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
    public static void showCurrentMenu (String x, Connection conn, String query){
		Integer menuid  = 0;
    	Integer id = Integer.parseInt(x);
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);			
			while(rs.next()) {
				menuid = rs.getInt("menu_id");				
				if(menuid == id) {
					System.out.println(rs.getString("menu_name"));
					System.out.println(rs.getInt("price"));
					break;
				}
			}
		} catch (SQLException e) {}
    	
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
}







