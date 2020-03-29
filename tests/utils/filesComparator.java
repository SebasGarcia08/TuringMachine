package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class filesComparator {
	
	public static boolean filesAreEqual(String path_1, String path_2) {
		BufferedReader br1, br2;
		boolean are_equal = true;
		try {
			br1 = new BufferedReader( new FileReader( new File(path_1) ) );
			br2 = new BufferedReader( new FileReader( new File( path_2 ) ) );
			String pred = br1.readLine();
			String actual = br2.readLine();
			while( pred != null && actual != null) {
				if(!pred.trim().equals(actual.trim())) {
					are_equal = false;
					System.out.println(actual + " != " + pred);
				}
				pred = br1.readLine();
				actual = br2.readLine();
			}
			br1.close();
			br2.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return are_equal;
	}
}
