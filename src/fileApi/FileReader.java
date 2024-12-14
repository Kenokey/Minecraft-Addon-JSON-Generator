package fileApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	private String filepath;
	
	public FileReader(String filepath){
		this.filepath = filepath;
	}
	
	public String getPreset() throws FileNotFoundException {
		String preset = "";
	
		File file = new File(this.filepath);
    	Scanner scan = new Scanner(file);
    	
    	while (scan.hasNextLine()) {
			preset = preset.concat(scan.nextLine() + "\n");
		}
    
		return preset;
    	
	}
	
}
