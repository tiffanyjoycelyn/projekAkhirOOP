package main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import connectDB.Connect;

public class menuUpdate {
	static boolean canUpdatemenu (Integer menuIDtoModify) {
		if(reservation.Orders.isEmpty()) {
			return true;
		}
		for (int i = 0; i < reservation.Orders.size(); i++) {
			if(menuIDtoModify == Integer.parseInt(reservation.Orders.get(i))) {
				System.out.println("cannot made any changes right now, this menu id : " + reservation.Orders.get(i) + "status is in order ");
				return false;
			}
		} 
		return true;
	}
	
	static void menu (Scanner ns, Connection conn) {
		System.out.print("input employeee id: ");
		int employeeID = ns.nextInt();
		Integer branchID = Connect.checkEmployeeBranch(employeeID, conn, "select * from employees");
		if(branchID == 0) {
			System.out.println("there is no employee with id: " + employeeID + "\n\n");
			return;
		}
		int choice = 0;
		do {
			System.out.print("\n1. adding menu\n2. delete menu\n3. modify menu\n>> ");
			choice = ns.nextInt();
		} while (choice != 1 && choice != 2 && choice != 3);
		switch (choice) {
		case 1:
			menuAdd(ns, branchID, conn);
			break;
		case 2:
			menuDelete(ns, branchID, conn);
			break;
		case 3:
			menuModify(ns, branchID, conn);
			break;	
		default:
			break;
		}
		
		return;
	}
	
	static void menuDelete (Scanner ns ,Integer branchID, Connection conn) {
		System.out.println("\nmenu available to delete in branch: " + branchID);
		String query = "select * from menus where branch_id =" + Integer.toString(branchID);
		Connect.showMenu(conn, query);
		System.out.print("input id to delete: ");
		Integer idToDelete = 0;
		try {
			idToDelete = ns.nextInt();
		} catch (Exception e) {}
		
		if(canUpdatemenu(idToDelete) == false) {
			return;
		}
		Connect.deleteData(conn, idToDelete, branchID);
	}
	
	
	static void menuAdd (Scanner ns, Integer branchID, Connection conn) {
		System.out.print("input menu name: ");
		String name = ns.next();ns.nextLine();
		Double price = 0.0;
		System.out.print("input price: ");
		price = ns.nextDouble();
		String temp;
		do {
			System.out.print("is it special menu? [Y/N]: ");
			temp = ns.next();
		}while (!temp.toLowerCase().equals("y") && !temp.toLowerCase().equals("n"));
		int type = 0;
		if(temp.toLowerCase().equals("y")) {
			type = 1;
		}
		Connect.insertMenu(conn, name, price, branchID, type, ns);
	}
	static void menuModify (Scanner ns ,Integer branchID, Connection conn) {
		System.out.println("\nmenu available to modify in branch: " + branchID);
		String query = "select * from menus where branch_id =" + Integer.toString(branchID);
		Connect.showMenu(conn, query);
		System.out.print("input menuID to modify: ");
		Integer menuIDtoModify = ns.nextInt();
		
		if(canUpdatemenu(menuIDtoModify) == false) {
			return;
		}
		query = "select * from menus where branch_id = " + Integer.toString(branchID) 
				+ " and menu_id = " + Integer.toString(menuIDtoModify);
		int type = 0;
		String category = "";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			
			type = rs.getInt("is_specialMenu");

		} catch (SQLException e) {}
		
		int input = 0;
		
		if(type == 0) {
			do {
				System.out.print("choose category to modify\n1. name\n2. price\n>> ");
				input = ns.nextInt();
			}while(input < 1 && input > 2);
			
		}else {
			do {
				System.out.print("choose category to modify\n1. name\n2. price\n3. narrative\n4. location\n>> ");
				input = ns.nextInt();
			}while(input < 1 && input > 4);
		}
		
		String temp;
		Integer temp2;
		
//		System.out.println("hello there you are");
		
		//update menus set menu_name = 'AYAM PENYET' where menu_id = 7
		
		int check = 0;
		
		switch (input) {
			case 1: {
				try {
					PreparedStatement pt = conn.prepareStatement("update menus set menu_name = ? where menu_id = ?");
					
					System.out.println("input new menu name: ");
					temp = ns.next();ns.nextLine();
//					
					pt.setString(1, temp);
					pt.setInt(2, menuIDtoModify);
					
					
					check = pt.executeUpdate();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;
			}
			case 2:{
				
				try {
					PreparedStatement pt = conn.prepareStatement("update menus set price = ? where menu_id = ?");
					
					System.out.print("input new price: ");
					temp2 = ns.nextInt();
					
					
					pt.setDouble(1, temp2);
					pt.setInt(2, menuIDtoModify);
					
					check = pt.executeUpdate();
				} catch (Exception e) {}
				
				break;
			}
			case 3: {
				
				try {
					PreparedStatement pt = conn.prepareStatement("update menus set narrative = ? where menu_id = ?");
					category = "narrative";
					System.out.println("input new narrative: ");
					
//					ns.useDelimiter("\n");
					
					temp = ns.next();ns.nextLine();
					
//					ns.useDelimiter(" ");
					
					pt.setString(1, temp);
					pt.setInt(2, menuIDtoModify);
					
					check = pt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			case 4:{
				try {
					PreparedStatement pt = conn.prepareStatement("update menus set location = ? where menu_id = ?");
					
					System.out.println("input new location: ");
					temp = ns.next();ns.nextLine();
					
					pt.setString(1, temp);
					pt.setInt(2, menuIDtoModify);
					
					check = pt.executeUpdate();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			default:
				break;
		}
		
//		System.out.println("hello there");

		
		if(check != 0) {
			System.out.println("succesfully update data\n");
		}else {
			System.out.println("failed to update data make sure to input the right menu id\n");
		}
		
	}
	

}
