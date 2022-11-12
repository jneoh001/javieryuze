package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Helps find root path of files.
 */
public class ProjectRootPathFinder {
	public static String findProjectRootPath() {
		try {
			// Access root folder
			String rootFolderName = "cz2002-oodp-moblima-design-project-master";			
			File file = new File(".");
			
			if (file.getCanonicalFile().getName().equals(rootFolderName) == true) {
				return file.getCanonicalPath();
			} else {
				while (file.getName().equals(rootFolderName) != true) {
					file = file.getCanonicalFile().getParentFile();
					// System.out.println(file.getCanonicalPath());
				}
				
				String projectRootPath = file.getCanonicalPath();
				return projectRootPath;
			}		
		} catch ( FileNotFoundException e ) {
			System.out.println( "Error opening the input file!" + e.getMessage() );
			System.exit( 0 );
			return null;
		} catch ( IOException e ) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
			return null;
		}          
	}
	
}
