package projectFileManagement;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class WelcomeScreen {

	public static void welcome() {
		// welcome screen for file management
		// with all necessary options
		System.out.println("**************************************************************************** ");
		System.out.println("Welcome to File Management Sysetem ");
		System.out.println("By Noel Chetty");
		System.out.println("\nKey Features Supported ");
		System.out.println("1: List of flies");
		System.out.println("2: Add a specified file			<file name>");
		System.out.println("3: Delete a specified file		<file name>");
		System.out.println("4: Find / Searcha specified file	<file name>");
		System.out.println("5: Exit Application ");
		System.out.println("**************************************************************************** ");
	}
	
	public static void main(String[] args) {
		
		// Made it static as we need the same message for all instances.
		WelcomeScreen.welcome();
	
		// Lets create a class to accept user inputs both menu option and the filename
		// with nice validation etc
		UserInput ui = new UserInput();
		
		// Lets create a class to return list of all valid files in a directory
		// exclude hidden files & directory
		DirectoryList dr = new DirectoryList();
		
		// Lets use users default directory for demo
		String current = System.getProperty("user.home");
		
		current += "/test/test"; // For testing etc..remove before submission.
		
	  	System.out.println ("\nYour Current Home Dir : "+ current);
	  	
	  	//Why TreeMap 
	  	//1.TreeMap sorts all its entries according to their natural ordering alphabetical /ascending order order. 
	  	//2.I would like to get the value string with nice formatted details of the file like full path, size etc. 
	  	TreeMap<String, String >list = dr.getDirFileList(current);
		
		String filename;
		int choice;
		
		//
		// Lets do in a do-while loop,until user plan to exit with option to exit
		//
		do {
			
			choice =ui.userSelection();
			System.out.println("\n");
			switch (choice) {
			case 1:
				if (list.isEmpty()) {
					System.out.println("Error => ["+ current +"]is not an valid directory path or file name - please check"); 
					break;
				}
				System.out.println("\n\nHere is the list for files/directries under " +current);  
				for(Map.Entry m:list.entrySet())  {  // already in ascending order as its set
			          //System.out.println("Key"+m.getKey()+" Value:"+m.getValue()); 
					System.out.println(m.getValue());  
			    }  
				break;
			
			case 2: // Add a file
				
				System.out.print ("Enter the filename to add or enter X/x to return to main menu:");
				filename = ui.userSelection(choice);
				
				// lets provide an escape route in case users does not know the file name
				if (filename.equalsIgnoreCase("x")) break;
				System.out.println("\n\n");
				
				if (list.containsKey(filename)) {
					System.out.println("Error => File:[" +filename + "] already exist  ");
					break;
				}
					
				try {
						 File newfile = new File(current+"/"+filename);     
					      if (newfile.createNewFile()) {
					    	list = dr.getDirFileList(current);
					        System.out.println("File:["+ newfile.getName() + "] created" );
					      } else {
					        System.out.println("Error => File:[" + newfile.getName() + "] already exists / unknow issue ");
					      }
				} catch (FileNotFoundException e) {
					      System.out.println("Error/IO Exception => File:[" + filename + "] not added");
					      System.out.println("Directory path or file does not exists");
					     // e.printStackTrace();
				} catch (IOException ex) {    // error handling separated from the main logic
						System.out.println("Error/IO Exception => File:[" + filename + "] not added");
					    System.out.println("Directory path or file does not exists");
				}
				//System.out.println("\n\n");
				break;
		
			case 3: // Delete a specified file
		
				System.out.print ("Enter the filename to add or enter X/x to return to main menu:");
				filename = ui.userSelection(choice);
				
				// lets provide an escape route in case users does not know the file name
				if (filename.equalsIgnoreCase("x")) break;
				
				System.out.println("\n\n");
				
				if (list.containsKey(filename)) {	 
					 File newfile = new File(current+"/"+filename); 
					 
					 if (newfile.delete()) {
					    list = dr.getDirFileList(current);
					    System.out.println("File:[ "+ newfile.getName()+"] is deleted: ");
					 } else {
						 System.out.println("Error => file [" + filename+ "] cannot be deleted / unknow issue ");
					 }
	
				} else {
					System.out.println("Error => file [" + filename + "] not found and not deleted");
				}
				
				System.out.println("\n\n");
				break;
		   
			case 4: // Search
				
				System.out.print ("Enter the filename to add or enter X/x to return to main menu:");
				filename = ui.userSelection(choice);
				
				// lets provide an escape route in case users does not know the file name
				if (filename.equalsIgnoreCase("x")) break;
				
				System.out.println("\n\n");
				
				if (list.containsKey(filename)) {
					System.out.println("Success = > ["+filename + "] found details as below");
					System.out.println(list.get(filename));
									
				} else {
					System.out.println("Error => file[ " + filename + " ] not found, please check the directoy list ");
				}
				break;
				
			case 5: // Exit 
					System.out.println("\n Thank you for using our application\n");
					break;
			default:
				System.out.println("Why are we here - as i have handled all exceptions ");
			}	
			System.out.println("\n");
			// System.out.flush(); 
			// Flash welcome screen after each operations
			WelcomeScreen.welcome();
			
		} while (choice !=5); // Continue until exit
	}

}

