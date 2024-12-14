package fileApi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileSaver {
	
	private String fileName;
	private String file;
	private String folder;


	public FileSaver(String fileName, String file, String folder){
		this.fileName = fileName;
		this.file = file;
		this.folder = folder;
	}

	
	public void saveFile() {
		String folderName = "gen" + File.separator + folder;
		String path = folderName + File.separator + fileName;
		try {
            File ordner = new File(folderName);
            if (!ordner.exists()) {
                ordner.mkdirs(); 
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(file);
            writer.close();
            System.out.println("File will be saved to: " + path);
		} catch (Exception e) {
			System.out.println("Couldn't generate file: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}
