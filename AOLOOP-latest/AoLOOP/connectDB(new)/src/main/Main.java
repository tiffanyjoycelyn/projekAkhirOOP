package main;

import java.sql.Connection;

import java.util.Scanner;
import connectDB.Connect;
import model.Employee;
import model.reservations;

public class Main {
	
	public static void main(String[] args) {
		Scanner ns = new Scanner(System.in);
		Connection conn = null;
		conn = Connect.first(conn);
		landingMenu(ns, conn);
		ns.close();
	}
	static void landingMenu(Scanner ns, Connection conn) {
		int input = 0;
		while(input != 3) {
			do {
				System.out.println("\nWelcome to LaperAh Restaurant Chain Application\n");
				System.out.println("1. Get to Know Our Restaurant Branches and informartions!");
				System.out.println("2. START !");
				System.out.print(">>");
				input = ns.nextInt();	
			} while (input != 1 && input != 2 && input != 3);
			if(input == 1) {
				System.out.println("LaperAh Restaurant Branches located in");
				System.out.println("\n(Main Restaurant), on these main branches there are SPECIAL MENUS");
				System.out.println(" 1. Bandung \n 2. Jakarta\n 3. Bali");
				System.out.println("\n on these branches there are LOCAL SPECIALS");
				System.out.println(" 4. Surabaya\n 5. Samarinda\n 6. Padang\n");
			}
			if (input == 2) {
				menuUtama(ns, conn, null, null);
			}
		}
		return;
	}
	static void menuUtama (Scanner ns, Connection conn,reservations r, Employee e) {
		int input = 0;
		while(input != 3) {
			do {
				System.out.println("\n1. reservation");
				System.out.print("2. update menu\n3. exit\n>> ");
				input = ns.nextInt();	
			} while (input != 1 && input != 2 && input != 3);
			if(input == 1) {
				if(reservation.canHaveNewReservation() == true) {
					reservation.menuReserve(ns, conn);
				}else if(reservation.canHaveNewReservation() == false){
					System.out.println("Sorry there is uncompleted orders, please complete it");
					reservation.orderMenuPage(ns, conn, "In Order", reservation.BRANCH_ID, r, e);;
				}
			}
			if (input == 2) {
				menuUpdate.menu(ns, conn);
			}
		}
		return;	
	}
}



