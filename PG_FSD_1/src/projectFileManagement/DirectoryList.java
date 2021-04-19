package projectFileManagement;

import java.io.File;

import java.io.IOException;
import java.util.*;
import java.io.FileNotFoundException;


public class DirectoryList {

	// Return the file names in an ascending order with details of the file
	// Only non hidden files  
	
	public TreeMap<String,String> getDirFileList (String path ) {
		
		TreeMap<String,String> dirset= new TreeMap<String, String>(); 
		File dir = new File(path); 
		
		// Let check if file exists, not a directory and a regular file     
		boolean exists 		= dir.exists();      
		boolean isDirectory = dir.isDirectory(); 
		//boolean isFile 	= dir.isFile(); 
			
		if (dir.canRead() & exists && isDirectory) {
				  
			File[] files = dir.listFiles();
			File afile;
			String filedesc;
				  
			for (int i = 0; i < files.length; i++) {
				afile = files[i];
					  
				// Please note Ignoring hidden file & Directories
				//  if (exists ) { 
				if (!afile.isDirectory() && !afile.isHidden()) {
						
					//Lets format the file details nicely
					filedesc = String.format("%-20s",afile.getName());
					if ( afile.isDirectory() ) filedesc +=" [D] "; else filedesc +=" [F] " ;
					filedesc += "" + String.format("%-40s",afile.getAbsolutePath());
					filedesc += afile.length() +" MB";  
					dirset.put(afile.getName(),filedesc);
						
					//System.out.println("Name		:"+ afile.getName());
					//System.out.println("Path		:" + afile.getAbsolutePath());
					//System.out.println("Parent		:" + afile.getParent());
					//System.out.println("Directory ?:" + afile.isDirectory());
					//System.out.println(filedesc); 
				}else { 
						// System.out.println("\t Not a file - ignoring .."+ afile.getName());
				 }
			}	
		} 
		         	
	return dirset;
  }
}

	
	