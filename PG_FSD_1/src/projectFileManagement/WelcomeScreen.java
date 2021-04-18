package projectFileManagement;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class WelcomeScreen {

	public void welcome() {
		System.out.println("\n\nWelcome to File Management Sysetem ");
		System.out.println("By Noel Chetty");
		System.out.println("Key Features Supported :");
		System.out.println("\n1: List of flies");
		System.out.println("2: Add a specified file			<file name >");
		System.out.println("3: Delete a specified file		<file name >");
		System.out.println("4: Find / Searcha specified file	<file name >");
		System.out.println("5: Exit Application \n ");

	}
	
	public static void main(String[] args) {
		
		WelcomeScreen wc = new WelcomeScreen();
		wc.welcome();
		UserInput ui = new UserInput();
		DirectoryList dr = new DirectoryList();
		
		String current = System.getProperty("user.home");
		current += "/test/test"; // remove before submission.
		
	  	System.out.println ("Your Home Dir : "+ current);	  
		//TreeSet list = dr.getDirFileList("/Users/noelchetty/test");
		
	  	TreeMap<String, String >list = dr.getDirFileList(current);
		
		String filename;
		int choice;
	
		do {
			choice =ui.userSelection();
			
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
			
				if (filename.equalsIgnoreCase("x")) break;
				
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
					      System.out.println("Dir path or file does not exists");
					     // e.printStackTrace();
				} catch (IOException ex) {    // error handling separated from the main logic
						System.out.println("Error/IO Exception => File:[" + filename + "] not added");
					    System.out.println("Dir path or file does not exists");
				}
				//System.out.println("\n\n");
				break;
		
			case 3: // Delete a specified file
				System.out.print ("Enter the filename to add or enter X/x to return to main menu:");
				filename = ui.userSelection(choice);
				
				if (filename.equalsIgnoreCase("x")) break;
				
				if (list.containsKey(filename)) {	 
					 File newfile = new File(current+"/"+filename); 
					 
					 if (newfile.delete()) {
					    list = dr.getDirFileList(current);
					    System.out.println("File:[ "+ newfile.getName()+"] is deleted: ");
					 } else {
						 System.out.println("Error:[" + filename+ "] file cannot be deleted / unknow issue ");
					 }
	
				} else {
					System.out.println("File:[" + filename + "] not found / not deleted");
				}
				
				System.out.println("\n\n");
				break;
		   
			case 4: // Search
				System.out.print ("Enter the filename to add or enter X/x to return to main menu:");
				filename = ui.userSelection(choice);
				
				if (filename.equalsIgnoreCase("x")) break;
				
				if (list.containsKey(filename)) {
					System.out.println("Success = > ["+filename + "] found ");
									
				} else {
					System.out.println("Error =>] " + filename + " ] not found ");
				}
				break;
				
			case 5: // Exit 
					System.out.println("\n Thank you for using our application\n");
					break;
			default:
				System.out.println("Why are we here - as i have handled all exceptions ");
			}	
			// System.out.flush(); 
			wc.welcome();
		} while (choice !=5);
		
	}

}

