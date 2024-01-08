package main;

import java.sql.Connection;
import java.util.Scanner;

import trivial.ReservationTrivial;

import connect.Check;
import connect.Connect;
import model.Reservation;
import java.util.Scanner;
public class menuReserve {
	public static void first (Scanner ns, Connection conn) {
		System.out.print("input employeee id: ");
		int employeeID = ns.nextInt();
		
		int employeeBranchID = Check.checkEmployeeBranch(employeeID, conn);
		
		if(employeeBranchID == 0) {
			System.out.println("there's no employee with " + employeeID);
			return;
		}
	
		int input = 0;
		do {
			System.out.print("1. add new reservation\n2. move in_reserve to in_order\n3. check order\n4. checkout\n5. exit\n>> ");
			input = ns.nextInt();
		}while(input != 1 && input != 2 && input != 3 && input != 4 && input != 5);
		
		if(input == 5) {
			return;
		}else if(input == 1) {
			newReservation(ns, conn, employeeID);
		}else if(input == 2) {
			menuOrder.orderMenuPage(ns, conn, employeeID);
		}else if(input == 3) {
			menuOrder.addOrder(conn, ns,employeeID);
		}else if (input == 4) {
			Checkout.checkout(ns, conn, employeeID);
		}
	}
	
	static void newReservation (Scanner ns, Connection conn, Integer employeeID) {
		String name = "";
		Integer tableCount = 0;
		Integer tableTypeID;
		Integer totalPersons = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("enter customer name: ");
		name = sc.nextLine();
		
		System.out.print("eneter number of table: ");
		tableCount = ns.nextInt();
		
		ReservationTrivial.listOfTable();
		
		String tableType;
		do {
			System.out.print("please enter the desired type of table [Romantic | General | Family ] : ");
			tableType = ns.next();ns.nextLine();
		}while(!(tableType.equalsIgnoreCase("Romantic"))&&!(tableType.equalsIgnoreCase("General"))&&!(tableType.equalsIgnoreCase("Family")));
		
		tableTypeID = ReservationTrivial.converTableTypetoID(tableType);
		totalPersons = ReservationTrivial.numbOfPerson(tableType, ns, tableCount);
		
		ReservationTrivial.reservationDetails(name, totalPersons, totalPersons, tableType);
		
		Reservation r = new Reservation (employeeID, name, tableCount, tableTypeID, totalPersons);
		
		Connect.insertNewReservation(conn, r);
	}
}























