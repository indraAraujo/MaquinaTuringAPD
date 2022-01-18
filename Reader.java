
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	static public int numberOfTransitions;
	File file;
	String fileName;
	Scanner scanner = null;
	int times = 0;

	public Reader(String filePath)
	{
		fileName = filePath;
		try{
			file = new File(fileName);
			scanner = new Scanner(file);
		}catch(FileNotFoundException fe){
			System.out.println("Exception: "+ fe);
		}
		numberOfTransitions = 0;
	} 

	public String[][] readFile (MaquinaTuring MT)
	{
		ArrayList<String> read = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			numberOfTransitions ++;
			String[] words = line.split("< ");
			for (int i = 0; i < words.length; i++) {
				if (!words[i].trim().isEmpty()){
					if(!words[i].contains("<")){
						read.add (words[i]);
					}
				}
			}
		}
		MT.setStates(read);
		String[][] result = MaquinaTuring.organizer(read);
		
		return result;
	}

	public void closeReader(){
		scanner.close();
	}
	
	public int getNumberOfTransitions()
	{
		return numberOfTransitions;
	}
}
