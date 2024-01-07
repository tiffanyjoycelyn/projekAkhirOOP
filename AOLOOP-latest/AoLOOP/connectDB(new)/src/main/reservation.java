package main;

import java.sql.Connection;

import java.util.Scanner;
import java.util.ArrayList;
import connectDB.Connect;

import model.Customer;
import model.Employee;
import model.menu;
import model.orderMenu;
import model.reservations;
import model.specialMenu;

public class reservation {
	
	static ArrayList<String> Orders = new ArrayList<>();
	static Integer BRANCH_ID = null;
	static String customerName = null;
	static boolean canHaveNewReservation() {
		if(Orders.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	static void showMenuAvailable(Connection conn, Integer branchID) {
		Connect.showMenutoAdd(conn, "select * from menus", branchID);
	}
	static void menuReserve(Scanner ns, Connection conn) {
		String menuStatus = null;
		Scanner sc = new Scanner(System.in);
		System.out.print("input employeee id: ");
		int employeeID = ns.nextInt();
		Integer branchID = Connect.checkEmployeeBranch(employeeID, conn, "select * from employees");
		BRANCH_ID = branchID;
		if(branchID == 0) {
			System.out.println("there is no employee with id: " + employeeID + "\n\n");
			return;
		}else {
			ArrayList<String>tables = new ArrayList<>();
			String employeeName = Connect.searchEmployeeName(employeeID, conn, "select * from employees");
			String branchName = Connect.searchBranchNameFromBranchID(branchID, conn, "select * from restaurant_branchs");
			Integer totalPerson = 0;
			Integer numTables = 0;
			System.out.println("\n================================================");
			System.out.println("|| Hello...\t    " + employeeName + " !\t\t      ||");
			System.out.println("================================================");
			System.out.println("|| Branch Location: " + branchName +"\t\t      ||");
			System.out.println("================================================");
			System.out.print("\nplease enter customer name : ");
			customerName = sc.nextLine();
			
			System.out.print("\nplease enter the desired number of tables :");
			numTables = ns.nextInt();
			if(numTables <= 0) {
				System.out.println("cannot make any reservation if number of table below 1\n");
				return;
			}
			for(int i = 0; i<numTables; i++) {
				String tableType = null;
				Integer numberOfPersons = 0;
				int flagnumPerson = 1;
				System.out.println("\n===============================================================================");
				System.out.println("||\t\t\t   Table Available                                   ||");
				System.out.println("===============================================================================");
				System.out.println("||\ta.\t|\tRomantic\t\t|\tMAX PERSONS : 2      ||");
				System.out.println("||\tb.\t|\tGeneral\t\t\t|\tMAX PERSONS : 4      ||");
				System.out.println("||\tc.\t|\tFamily\t\t\t|\tMAX PERSONS : 10     ||");
				System.out.println("===============================================================================");
				do {
					System.out.print("please enter the desired type of table [Romantic | General | Family ] : ");
					tableType = ns.next();ns.nextLine();
				}while(!(tableType.equalsIgnoreCase("Romantic"))&&!(tableType.equalsIgnoreCase("General"))&&!(tableType.equalsIgnoreCase("Family")));
				
				do {
					numberOfPersons = 0;flagnumPerson = 1;
					System.out.print("\nplease enter number of person(s) :");
					numberOfPersons = ns.nextInt();
					if(tableType.equalsIgnoreCase("Romantic") && numberOfPersons > 2) {
						System.out.println("Sorry, 2 persons maximum for Romantic Table " );
						flagnumPerson = 0;
					}else if(tableType.equalsIgnoreCase("General") && numberOfPersons > 4) {
						System.out.println("Sorry, 4 people maximum for General Table " );
						flagnumPerson = 0;
					}else if(tableType.equalsIgnoreCase("Family") && numberOfPersons > 10) {
						System.out.println("Sorry, 10 people maximum for Family Table " );
						flagnumPerson = 0;
					}
				}while(flagnumPerson == 0);
				totalPerson += numberOfPersons;
				tables.add(tableType.concat(" Table \t").concat(numberOfPersons.toString()).concat(" person(s)"));
				if(numberOfPersons == 1) {
					System.out.println("Successfully reserved " + tableType + " table for " + numberOfPersons.toString()+ " person !");
				}else if(numberOfPersons == 2) {
					System.out.println("Successfully reserved " + tableType + " table for " + numberOfPersons.toString()+ " persons !");
				}else if(numberOfPersons > 2) {
					System.out.println("Successfully reserved " + tableType + " table for " + numberOfPersons.toString()+ " people !");
				}
				
			}
			System.out.println("\n===================================================");
			System.out.println("||\t\tReservation Details\t     ||");
			System.out.println("===================================================");
			System.out.println("||	Customer name \t\t\t: " + customerName + "\t ||");
			System.out.println("||	Total Person \t\t\t: " + totalPerson + "\t ||");
			System.out.println("||	Total Tables Reserved \t\t: " + numTables + "\t ||");
			System.out.println("===================================================");
			for (int j = 0; j < tables.size(); j++) {
				System.out.print("||	"+(j+1)+ ". | ");
				System.out.println(tables.get(j));
			}
			System.out.println("===================================================");
			Integer transactionID = Connect.getLatestReservationID(conn, "select * from reservations");
			transactionID++;
			menuStatus = "In Reserve";
			reservations newReservations = new reservations(customerName,employeeID,totalPerson,menuStatus,numTables,tables);
			Employee newEmployee = new Employee(employeeName);
			newReservations.setReservation_id(transactionID);
			newReservations.setEmployee_id(employeeID);
			newEmployee.setBranch_id(branchID);
			newEmployee.setBranch_name(branchName);
			newEmployee.setEmployee_id(employeeID);
			orderMenuPage(ns, conn, menuStatus, branchID,newReservations, newEmployee);
			
		}
		
	}
	static void printReceipt(Connection conn, reservations r, Employee e, orderMenu o) {
		System.out.println("\n======================================================================");
		System.out.println("||		                  	Transaction Details	||");
		System.out.println("======================================================================");
		System.out.println("||	Reservation ID \t\t: "+r.getReservation_id());
		System.out.println("||	Menu Status \t\t: "+ r.status);
		System.out.println("======================================================================");
		System.out.println("||	Branch Location \t: " + e.getBranch_name());
		System.out.println("||	Employee Name \t\t: " + e.name);
		System.out.println("||	Customer Name \t\t: " + r.customer_name);
		System.out.println("======================================================================");
		System.out.println("||	Total Person \t\t: " + r.num_of_people);
		System.out.println("||	Total Tables Reserved \t: " +r.table_count);
		System.out.println("======================================================================");
		for (int j = 0; j < r.type_id.size(); j++) {
			System.out.print("||	"+(j+1)+ ". | ");
			System.out.println(r.type_id.get(j));
		}
		System.out.println("======================================================================");
		if(o.menus.isEmpty() == true) {
			System.out.println("no order yet...");
			System.out.println("======================================================================");
		}else {
			for (int i = 0; i < o.menus.size(); i++) {
				System.out.println(o.menus);
			}
		}
	}
	static void orderMenuPage(Scanner ns, Connection conn, String menuStatus, Integer branchID, reservations r,Employee e) {
		int input = 0;
		orderMenu buffOrder = new orderMenu(null,null);
		while(input != 4 || input !=3) {
			do {
				System.out.println("\n1. order menu");
				System.out.print("2. see current order\n3. checkout (this action cannot be undone)\n4. back\n>> ");
				input = ns.nextInt();
				
			} while (input != 1 && input != 2 && input != 3 && input != 4);
			if(input == 1) {
				String buff; String buffmenuName = null;
				int flag = 0; // not done 
				Integer menuID = 0;
				Integer buff_price;
				Integer buff_branch_id;
				String buff_narrative = null;
				String buff_location = null;
				Integer is_specialMenu = null;
				Integer totalPrice = 0;
				ArrayList<menu> listmenu = new ArrayList<menu>();
				do {
					
					buffmenuName = null;
					showMenuAvailable(conn, branchID);
					boolean found = false;
					do {
						found = false;
						System.out.print("Please enter the menu ID : ");
						menuID = ns.nextInt();
						if(Connect.checkmenuIDontheBranch(branchID, menuID, conn, "select * from menus") == true) {
							found = true;
						}else {
							System.out.println("\nPlease enter the available menu ID on this branch..");
						}
					}while(found == false);
					
					Orders.add(menuID.toString());
					is_specialMenu = Connect.searchIsSpecialMenuFromMENUID(menuID, conn, "select * from menus");
					buff_branch_id = Connect.searchMenuBranchIDFromMENUID(menuID, conn, "select * from menus");
					buff_price = Connect.searchMenuPriceFromMENUID(menuID, conn, "select * from menus");
					buffmenuName=Connect.searchMenuNameFromMENUID(menuID, conn, "select * from menus");
					
					totalPrice += buff_price;
					
					if(is_specialMenu == 1) {
						buff_narrative = Connect.searchNarrativeFromMENUID(menuID, conn, "select * from menus");
						buff_location = Connect.searchLocationFromMENUID(menuID, conn, "select * from menus");
						specialMenu sm = new specialMenu(menuID, buffmenuName, buff_price, buff_branch_id, buff_narrative, buff_location, is_specialMenu);
						
					}else {
						menu m = new menu(menuID, buffmenuName, buff_price, buff_branch_id);
						
					}

					
					System.out.println("\nSuccessfully added "+buffmenuName+" to the order list !");
					System.out.print("do you want add another order ? [y|n] : ");
					buff = ns.next();ns.nextLine();
					if(buff.equalsIgnoreCase("y")) {
						flag = 0;
					}else if(buff.equalsIgnoreCase("n")) {
						flag = 1;
					}
				}while(flag == 0);

				orderMenu newOrder = new orderMenu(listmenu,totalPrice);
				buffOrder.menus = newOrder.menus;
				buffOrder.totalPrice = newOrder.totalPrice;
				r.status = "In Order";
				System.out.println();
			}
			if (input == 2) {
				printReceipt(conn, r,e,buffOrder);
			}
			if(input == 3) {
				System.out.println("Order FINALIZED");
				r.status = "Finalized";
				printReceipt(conn, r,e,buffOrder);
			}
			if(input == 4) {
				Main.menuUtama(ns, conn, r, e);
			}
		}
	}

}
