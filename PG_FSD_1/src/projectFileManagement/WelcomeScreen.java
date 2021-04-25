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
		System.out.println("1: List of files ");
		System.out.println("2: File Management[Add/Delete/Search]");
		System.out.println("3: Exit Application ");
		System.out.println("**************************************************************************** ");
	}
	
	public static void fileManagementMenu() {
				// with all necessary options
				System.out.println("**************************************************************************** ");
				System.out.println("File Management Sysetem - Options");		
				System.out.println("4: Search 	a file");
				System.out.println("5: Add		a file");
				System.out.println("6: Delete 	a file");
				System.out.println("7: Return to Main Menu ");
				System.out.println("**************************************************************************** ");
	}
	
	
	public static void main(String[] args) {
		
		// Made it static as we need the same message for all instances.
		//WelcomeScreen.welcome();
	
		// Lets create a class to accept user inputs both menu option and the filename
		// with nice validation etc
		UserInput ui = new UserInput();
		
		// Lets create a class to return list of all valid files in a directory
		// exclude hidden files & directory
		DirectoryList dr = new DirectoryList();
		
		// Lets use users default directory for demo
		String current = System.getProperty("user.home");
		
		// current += "/test/test"; // For testing etc..remove before submission.
		
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
			WelcomeScreen.welcome();
			choice =ui.userSelection(1,3);
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
				do {
					WelcomeScreen:fileManagementMenu();	
					choice = ui.userSelection(4,7);	
					switch (choice) {
						case 4: // Search
							System.out.print ("\n\nEnter the filename to search or enter 7 to return to return:");
							filename = ui.userSelection(choice);
							
							// lets provide an escape route in case users does not know the file name
							if (filename.equalsIgnoreCase("7")) break;
							System.out.println("\n\n");
							
							if (list.containsKey(filename)) {
								System.out.println("Success => ["+filename + "] found details as below");
								System.out.println(list.get(filename));
												
							} else {
								System.out.println("Error => file[ " + filename + " ] not found, please check the directoy list ");
							}
							System.out.println("\n\n");
							break;
							
						case 5: // Add a file
							System.out.print ("\n\nEnter the filename to add or enter 7 to return to return:");
							filename = ui.userSelection(choice);
		
							// lets provide an escape route in case users does not know the file name
							if (filename.equalsIgnoreCase("7")) break;
							System.out.println("\n\n");
							
							if (list.containsKey(filename)) {
								System.out.println("Error => File:[" +filename + "] already exist  ");
								System.out.println("\n\n");
								break;
							}
								
							try {
									 File newfile = new File(current+"/"+filename);     
								      if (newfile.createNewFile()) {
								    	list = dr.getDirFileList(current);
								        System.out.println("File:["+ newfile.getName() + "] created" );
								        System.out.println(list.get(filename));
								        
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
							
							System.out.println("\n\n");
							break;
							
							case 6: // Delete a specified file
								
								System.out.print ("\n\nEnter the filename to delete or enter 7 to return to return:");
								filename = ui.userSelection(choice);
			
								// lets provide an escape route in case users does not know the file name
								if (filename.equalsIgnoreCase("7")) break;

							
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
						}
					
					}while (choice !=7);
				
				System.out.print ("\nReturning to main menu\n\n");
				//WelcomeScreen.welcome();
				break;
		
			case 3: // Exit 
				System.out.println("\nExiting Application - Thank you for using our application\n");
				break;
			default:
				System.out.println("Why are we here - as i have handled all exceptions ");
			}
	
			System.out.println("\n");
			// System.out.flush(); 
			// Flash welcome screen after each operations
			// WelcomeScreen.welcome();
			
		} while (choice !=3); // Continue until exit
	}

}

