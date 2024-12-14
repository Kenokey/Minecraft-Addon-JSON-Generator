package armor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileApi.FileReader;
import fileApi.FileSaver;

public class ArmorTextures {

	private String formatVersion = "1.20.80"; 
	private String identifier;
	private String material;
	private String layerTextureName;
	private String textureName;

	private String presetPath = "src/armor/armorTexturePreset.txt";
	private String preset = "";

	private String genFolder = "rp" + File.separator + "attachables";
	
	
	ArmorTextures(String formatVersion, String modName, String material, String layerTextureName) throws FileNotFoundException{
		this.formatVersion = formatVersion;
		this.identifier = modName + ":" + material;
		this.material = material;
		this.layerTextureName = layerTextureName;
			
		FileReader fileReader = new FileReader(presetPath);
		preset = fileReader.getPreset();	
	}
	
	public void run() {
		generateArmor("helmet", this.layerTextureName + "_1", "helmet");
		generateArmor("chestplate", this.layerTextureName + "_1", "chest");
		generateArmor("leggings", this.layerTextureName + "_2", "leg");
		generateArmor("boots", this.layerTextureName + "_1", "boot");
	}
	
	private void generateArmor(String part, String layer, String parentSetup) {
		String armorPartRecipeIdentifier = this.identifier + "_" + part;
		String file = this.preset;
		file = replacePlaceholder(file, "formatVersion", this.formatVersion);
		file = replacePlaceholder(file, "identifier", armorPartRecipeIdentifier);
		file = replacePlaceholder(file, "layer", layer);
		file = replacePlaceholder(file, "geometry", part);
		file = replacePlaceholder(file, "parentSetup", parentSetup);
		
		FileSaver fileSaver = new FileSaver(this.material + "_" + part + ".json", file, this.genFolder);
		fileSaver.saveFile();
	}
	
	private static String replacePlaceholder(String text, String placeholder, String replacement) {
        Pattern pattern = Pattern.compile("\\{" + placeholder + "\\}");
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(replacement);
    }
	
}

