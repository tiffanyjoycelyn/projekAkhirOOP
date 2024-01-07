package connectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowDB {
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
}
