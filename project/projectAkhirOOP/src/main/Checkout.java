package main;

import java.sql.Connection;
import java.util.Scanner;

import connect.Connect;
import connect.Show;

public class Checkout {
	public static void checkout(Scanner ns, Connection conn, Integer employeeID) {
		Show.showAvaillableInOrder(conn, employeeID);
		
		System.out.print("input id to checkout: ");
		Integer reservationID = ns.nextInt();
		
		if(Show.checkIfInOrder(conn, employeeID, reservationID) == true) {
			Connect.changeStatus(conn, 2, reservationID);
			printReceipt(conn, reservationID);
		}else {
			System.out.println("can not check out reservationID: "+reservationID+ " status is not in_order ...");
		}
		
		
	}
	
	static void printReceipt (Connection conn, Integer reservationID) {
		System.out.println("========================================");
		System.out.println("\t\tReceipt");
		System.out.println("========================================");
		Show.showOrderedMenu(conn, reservationID);
		System.out.println("========================================");
		Double total = Show.showTotal(conn, reservationID);
		
		System.out.println("\t\tTotal: \t" + total);
		System.out.println("========================================");
	}
}
