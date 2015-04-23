package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	private FileUtils() {
	}

	public static String loadAsString(String file) {
		
		String result = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String currentLine = "";
			
			while ((currentLine = reader.readLine()) != null) {
				result += currentLine + "\n";
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

}
