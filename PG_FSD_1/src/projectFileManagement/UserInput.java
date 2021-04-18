package projectFileManagement;

import java.util.Scanner;

public class UserInput {
	
	public int userSelection() {
		System.out.print("Enter your choice :");
		// System.out.println("At any time enter 0 to return \n");
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {	
		    while (!sc.hasNextInt()) {
		       System.out.print("Please enter a valid choice [1 to 5 ]:");
		       sc.next();
		    }
		    choice = sc.nextInt();
		    if (choice > 5 || choice <= 0) 
		    	System.out.print("Please enter a valid choice [1 to 5 ]:");
		    // if (choice == 0) break;
		} while (choice > 5 || choice <= 0) ;
		
		// sc.close();
		return choice;
		
	}	
	
	public String userSelection(int choice) {
		//System.out.println("Option <"+ choice +" > selected, enter file name : or 0 to return to main menu");
		Scanner sc = new Scanner(System.in);
		String filename= new String();
		do {
			while (!sc.hasNext("[A-Za-z0-9-.]*")) {
		        System.out.println("Please enter a valid choice");
		        filename= sc.next();
		    }
		   filename = sc.nextLine();
		   break;
		} while(filename.isEmpty());
		
		
		//System.out.println("returing:"+filename);
		//sc.close();

		return filename;
	}	
	 
	/*
	 public int isInteger( String input ) {
	 
		int value = 1;
	    try {
	        value = Integer.parseInt( input );
	        return value;
	    }
	    catch( Exception e ) {
	        return value;
	    }
	}
	*/
	
}
