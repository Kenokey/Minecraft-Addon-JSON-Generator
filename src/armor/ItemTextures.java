package armor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileApi.FileReader;
import fileApi.FileSaver;

public class ItemTextures {

	private String modName;
	private String textureName;
	private String material;

	private String presetPath = "src/armor/itemTexturesPreset.txt";
	private String preset = "";

	private String genFolder = "rp" + File.separator + "textures";
	
	
	ItemTextures(String modName, String material, String textureName) throws FileNotFoundException{
		this.modName = modName;	
		this.material = material;
		this.textureName = textureName;
		
		
		FileReader fileReader = new FileReader(presetPath);
		preset = fileReader.getPreset();	
	}
	
	public void run() {
		generateArmor();
	}
	
	private void generateArmor() {
		String file = this.preset;
		file = replacePlaceholder(file, "modName", this.modName);
		file = replacePlaceholder(file, "material", this.textureName);

		
		FileSaver fileSaver = new FileSaver(this.material + " item_texture.json", file, this.genFolder);
		fileSaver.saveFile();
	}
	
	private static String replacePlaceholder(String text, String placeholder, String replacement) {
        Pattern pattern = Pattern.compile("\\{" + placeholder + "\\}");
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(replacement);
    }
	
}

