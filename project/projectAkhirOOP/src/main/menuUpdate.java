package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import connect.Check;
import connect.Connect;
import connect.Show;
import model.Menu;
import model.SpecialMenu;

public class menuUpdate {
	static int checkStatus () {
		System.out.println("checking if menu is in order");
		
		return 1;
	}
	
	static void first (Scanner ns, Connection conn) {
		System.out.print("input employeee id: ");
		int employeeID = ns.nextInt();
		
		//searching employee branch
		int employeeBranchID = Check.checkEmployeeBranch(employeeID, conn);
		
		if(employeeBranchID == 0) {
			System.out.println("there's no employee with " + employeeID);
			return;
		}
		
		int choice = 0;
		
		do {
			System.out.print("1. adding menu\n2. delete menu\n3. modify menu\n>> ");
			choice = ns.nextInt();
		} while (choice != 1 && choice != 2 && choice != 3);
		
		switch (choice) {
		case 1:
			addingMenu(ns, conn, employeeBranchID);
			break;
		case 2:
			deleteMenu(ns, conn, employeeBranchID);
			break;
		case 3:
			modifyMenu(ns, conn, employeeBranchID);
			break;
		default:
			break;
		}
	}
	
	static void modifyMenu (Scanner ns, Connection conn, Integer branchID) {
		String query = "select * from menus where branch_id =" + Integer.toString(branchID);
		
		Show.showMenuAvailableOnBranch(conn, branchID);
		

		Integer menuIDtoModify; 
		
		do {
			System.out.print("input menuID to modify: ");
			menuIDtoModify = ns.nextInt();
		}while(Check.checkMenuID(conn, branchID, menuIDtoModify) != 1);
			

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
		
		int check = 0;
		
		switch (input) {
			case 1: {
				try {
					PreparedStatement pt = conn.prepareStatement("update menus set menu_name = ? where menu_id = ?");
					
					System.out.println("input new menu name: ");
					temp = ns.next();ns.nextLine();
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
					temp = ns.next();ns.nextLine();
				
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
		
		
		if(check != 0) {
			System.out.println("succesfully update data\n");
		}else {
			System.out.println("failed to update data make sure to input the right menu id\n");
		}
		
		
	}
	
	
	static void deleteMenu (Scanner ns, Connection conn, Integer branchID) {
		Show.showMenuAvailableOnBranch(conn, branchID);
		
		Integer menuID = 0;
		
		do {
			System.out.print("input menuID to delete: ");
			menuID = ns.nextInt();
		}while(Check.checkMenuID(conn, branchID, menuID) != 1);
		
		Connect.deleteMenu(conn, menuID);
	}
	
	
	static void addingMenu(Scanner ns, Connection conn, Integer branchID) {
		System.out.println("\nADDING NEW MENU");
		System.out.println("===============\n");
		
		String name;
		Double price;
		
		System.out.print("input menu name [use camelCase e.g: 'nasiGoreng']: ");
		name = ns.next();ns.nextLine();
		System.out.print("input price: ");
		price = ns.nextDouble();
		
		String temp;
		
		do {
			System.out.print("is it special menu? [Y/N]: ");
			temp = ns.next();
		}while (!temp.toLowerCase().equals("y") && !temp.toLowerCase().equals("n"));
		
		int type = 0;
		if(temp.equalsIgnoreCase("y")) {
			type = 1;
		}
		
		
		if(type == 0) {
			Menu m = new Menu (name, price, branchID);
			
			Connect.insertMenu(conn, m);
		}else if(type == 1) {
			System.out.println("input narrative: ");
			String narrative = ns.next();ns.nextLine();
			
			System.out.println("input location");
			String location = ns.next();ns.nextLine();
			
			SpecialMenu sm = new SpecialMenu (name, price, branchID, narrative, location);
			
			Connect.insertSpecialMenu(conn, sm);
		}
		
		
	}
}




















