package connectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckDB {
    public static Integer checkEmployeeBranch (Integer x, Connection conn, String query){
    	
    	Integer id  = 0;
		Integer branchId = 0;
    	
    	try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			branchId = rs.getInt("branch_id");
			
		} catch (SQLException e) {}
    	
    	return branchId;
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
}
