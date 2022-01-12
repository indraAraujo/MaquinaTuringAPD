import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	File file;
	String fileName;
	Scanner scanner = null;
	int times = 0;
	public Reader(String filePath){
		fileName = filePath;
		try{
			file = new File(fileName);
			scanner = new Scanner(file);
		}catch(FileNotFoundException fe){
			System.out.println("Exception: "+ fe);
		}
	} 

	public ArrayList<String> readFile (MaquinaTuring MT){
		ArrayList<String> read = new ArrayList<String>();
		
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
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
		MaquinaTuring.organizer(read);
		return read;
	}

	public void closeReader(){
		scanner.close();
	}

}