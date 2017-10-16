package interpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	
	public static void main(String[] args)
	{
		Interpreter interpreter;
		
		File file = new File("source.txt");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> source = new ArrayList<String>();
		String line;
		
		try {
			while ((line = bufferedReader.readLine()) != null) {
				source.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		interpreter = new Interpreter(source);
	}
}
