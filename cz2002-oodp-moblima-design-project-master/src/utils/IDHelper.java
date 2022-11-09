package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Helps generate latest ID available so no overlap of ID exists.
 */
public class IDHelper {
	public static String getLatestID(String fileName) {
	    	
		String latestID = String.format("%08d", 00000000);
		
		try {
			// Get filepath
			String filePath = ProjectRootPathFinder.findProjectRootPath();
			
			if (filePath == null) {
				throw new IOException("Cannot find root of project.");
			} else {
				filePath = filePath + "/data/ids/" + fileName + "_id.txt";
			}
			
			// Open file and traverse it						
			FileReader frStream = new FileReader( filePath );
			BufferedReader brStream = new BufferedReader( frStream );
			String inputLine;
	
			inputLine = brStream.readLine(); // read in a line
			if (inputLine == null) {
				latestID = String.format("%08d", 00000000);
			}
			else {
				latestID = inputLine;
			}
			
			brStream.close(); // Close file
			
			// Open file in write mode
			FileWriter fwStream = new FileWriter(filePath, false); // Overwrite file
		    BufferedWriter bwStream = new BufferedWriter(fwStream);
		    
		    String newLatestID = String.format("%08d", Integer.valueOf(latestID) + 1);
		    
		    bwStream.write(newLatestID);
			
		    bwStream.close(); // Close file
		    
		    return latestID;
			
		} catch ( FileNotFoundException e ) {
			System.out.println( "File not found! " + e.getMessage() );
			System.exit( 0 );
		} catch ( IOException e ) {
			System.out.println( "IO Error! " + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}
		
		return latestID;           
	}

}
