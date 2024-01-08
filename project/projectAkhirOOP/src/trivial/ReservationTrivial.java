package trivial;

import java.util.Scanner;

public class ReservationTrivial {
	public static Integer converTableTypetoID(String tableType) {
		Integer tableTypeID = 0;
		
		if(tableType.equalsIgnoreCase("Romantic")) {
			tableTypeID = 1;
		}else if(tableType.equalsIgnoreCase("General")) {
			tableTypeID = 2;
		}else {
			tableTypeID = 3;
		}
		
		return tableTypeID;
	}
	
	public static void listOfTable () {
		System.out.println("\n===============================================================================");
		System.out.println("||\t\t\t   Table Available                                   ||");
		System.out.println("===============================================================================");
		System.out.println("||\ta.\t|\tRomantic\t\t|\tMAX PERSONS : 2      ||");
		System.out.println("||\tb.\t|\tGeneral\t\t\t|\tMAX PERSONS : 4      ||");
		System.out.println("||\tc.\t|\tFamily\t\t\t|\tMAX PERSONS : 10     ||");
		System.out.println("===============================================================================");
	}
	
	public static Integer numbOfPerson (String tableType, Scanner ns, Integer totalTable) {
		Integer numberOfPersons = 0;
		int flagnumPerson = 1;
		
		do {
			numberOfPersons = 0;
			flagnumPerson = 1;
			
			System.out.print("\nplease enter number of person(s) :");
			numberOfPersons = ns.nextInt();
			if(tableType.equalsIgnoreCase("Romantic") && numberOfPersons > (2 * totalTable)) {
				System.out.println("Sorry, 2 persons maximum for Romantic Table " );
				flagnumPerson = 0;
			}else if(tableType.equalsIgnoreCase("General") && numberOfPersons > (4 * totalTable)) {
				System.out.println("Sorry, 4 people maximum for General Table " );
				flagnumPerson = 0;
			}else if(tableType.equalsIgnoreCase("Family") && numberOfPersons > (10 * totalTable)) {
				System.out.println("Sorry, 10 people maximum for Family Table " );
				flagnumPerson = 0;
			}
		}while(flagnumPerson == 0);
		
		return numberOfPersons;
	}
	
	public static void reservationDetails (String customerName, Integer totalPerson, Integer numTables, String tableTypes) {
		System.out.println("\n==========================================================");
		System.out.println("||\t\tReservation Details\t\t        ||");
		System.out.println("==========================================================");
		System.out.println("||	Customer name \t\t\t: " + customerName + "\t||");
		System.out.println("||	Total Person \t\t\t: " + totalPerson + "\t\t||");
		System.out.println("||	Total Tables Reserved \t\t: " + numTables + "\t\t||");
		System.out.println("||  \tTable types\t\t\t: " + tableTypes + "\t||");
		System.out.println("==========================================================");
	}
}
