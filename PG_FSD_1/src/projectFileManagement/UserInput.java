package projectFileManagement;

import java.util.Scanner;

public class UserInput {
	
	public int userSelection(int fromMenu, int toMenu) {
		
		System.out.print("Enter your choice : ");
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		// Lets ensure we get the right input that are valid
		do {	
		    while (!sc.hasNextInt()) {
		       System.out.print("Please enter a valid choice ["+ fromMenu+ " - " +toMenu + "] :");
		       sc.next();
		    }
		    choice = sc.nextInt();
		    if (choice > toMenu || choice < fromMenu ) 
		    	 System.out.print("Please enter a valid choice ["+ fromMenu+ " - " +toMenu + "] :");
		    
		} while (choice > toMenu || choice < fromMenu) ;
		
		// sc.close();
		return choice;
		
	}	
	
	public String userSelection(int choice) {
	
		Scanner sc = new Scanner(System.in);
		String filename= new String();
		
		// Lets get a valid file name
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
