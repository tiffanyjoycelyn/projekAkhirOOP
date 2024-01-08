package main;

import java.sql.Connection;
import java.util.Scanner;

import connect.Check;
import connect.Connect;
import connect.Show;

public class menuOrder {
	public static void orderMenuPage(Scanner ns, Connection conn, Integer employeeID) {
		Show.showAvaillableReservation(conn, employeeID);
		
		Integer id = 0;
		
		int employeeBranchID = Check.checkEmployeeBranch(employeeID, conn);
		
		do {
			System.out.print("input reservation id: ");
			id = ns.nextInt();
		} while (Check.checkReservationID(conn, id) != 1);
		
		Connect.changeStatus(conn, 1, id);
		
		
		orderMenuPage2(ns, conn, employeeID, employeeBranchID, id);
	}
	
	static void orderMenuPage2(Scanner ns, Connection conn, Integer employeeID, Integer employeeBranchID, Integer reservationID) {
		int input = 0;
		
		do {
			System.out.println("\n1. order menu");
			System.out.print("2. see current order\n3. back\n>> ");
			input = ns.nextInt();
			
		} while (input != 1 && input != 2 && input != 3 && input != 4);
		
		
		
		switch (input) {
		case 1:
			orderMenu(conn, ns,employeeBranchID, reservationID);
			break;
		case 2:
			Show.showCurrentOrder(conn, reservationID);
			break;
		case 3:
			
			return;
		default:
			
			break;
		}
	}
	
	static void orderMenu(Connection conn, Scanner ns, Integer employeeBranchID, Integer reservationID) {

		System.out.println();
		Show.showMenuAvailableOnBranch(conn, employeeBranchID);
		
		Integer menuID = 0;
		String yn = "y";
		Integer quantity = 0;
		
		while(yn.equalsIgnoreCase("y")) {
			do {
				System.out.print("input menuID to order: ");
				menuID = ns.nextInt();
			} while (Check.checkMenuID(conn, employeeBranchID, menuID) != 1);
			
			try {
				System.out.println("input quantity: ");
				quantity = ns.nextInt();
			} catch (Exception e) {
				System.out.println("error");
			}
			
			Connect.insertOrder(conn, reservationID, menuID, quantity);
			Connect.addTotal(conn, menuID, reservationID, quantity);
			System.out.println("do you want to add more [y/n]?");
			yn = ns.next();ns.nextLine();
		}
	}
	
	static void addOrder (Connection conn, Scanner ns,Integer employeeID) {
			System.out.println();
	
			Show.showAvaillableInOrder(conn, employeeID);
			
			System.out.println("input reservation id to check order: ");
			Integer id = ns.nextInt();
			
			if(Show.checkIfInOrder(conn, employeeID, id) == false) {
				System.out.println("cannot check order, current id : " + id + " status in not in_order ...");
				return;
			}
			
			int input = 0;
			
			do {
				System.out.print("\n1. add order\n2. see current order\n3. back\n>> ");
				input = ns.nextInt();
			} while (input != 1 && input != 2 && input != 3);
			
			Integer menuID = 0;
			String yn = "y";
			Integer quantity = 0;
			int employeeBranchID = Check.checkEmployeeBranch(employeeID, conn);
			
			if(input == 1) {
				Show.showMenuAvailableOnBranch(conn, employeeBranchID);;
				
				while(yn.equalsIgnoreCase("y")) {
					do {
						System.out.print("input menuID to order: ");
						menuID = ns.nextInt();
					} while (Check.checkMenuID(conn, employeeBranchID, menuID) != 1);
					
					try {
						System.out.println("input quantity: ");
						quantity = ns.nextInt();
					} catch (Exception e) {
						System.out.println("error");
					}
					
					Connect.insertOrder(conn, id, menuID, quantity);
					Connect.addTotal(conn, menuID, id, quantity);
					
					System.out.println("do you want to add more [y/n]?");
					yn = ns.next();ns.nextLine();
				}
			}else if(input == 2) {
				Show.showCurrentOrder(conn, id);
			}else if(input == 3) {
				return;
			}
	}
}
