package armor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileApi.FileReader;
import fileApi.FileSaver;

public class ArmorRecipes {

	private String formatVersion = "1.12"; 
	private String identifier;
	private String craftItem;
	private String material;
	
	private String helmetPattern = "\"CCC\",\r\n"
			+ "			\"C C\"";
	
	private String chestplatePattern = "\"C C\",\r\n"
			+ "			\"CCC\",\r\n"
			+ "			\"CCC\"";
	
	private String leggingsPattern = "\"CCC\",\r\n"
			+ "			\"C C\",\r\n"
			+ "			\"C C\"";
	
	private String bootsPattern = "\"C C\",\r\n"
			+ "			\"C C\"";
	
	private String presetPath = "src/armor/armorRecipePreset.txt";
	private String preset = "";

	private String genFolder = "bp" + File.separator + "recipe";
	
	
	ArmorRecipes(String modName, String material, String craftItem) throws FileNotFoundException{
		this.identifier = modName + ":" + material;
		this.craftItem = craftItem;
		this.material = material;
		
		FileReader fileReader = new FileReader(presetPath);
		preset = fileReader.getPreset();	
	}
	
	public void run() {
		generateArmor("helmet", this.helmetPattern);
		generateArmor("chestplate", this.chestplatePattern);
		generateArmor("leggings", this.leggingsPattern);
		generateArmor("boots", this.bootsPattern);
	}

	
	
	private void generateArmor(String part, String pattern) {
		String armorPartRecipeIdentifier = this.identifier + "_" + part + "_recipe";
		String armorResultItem = this.identifier + "_" + part;
		String file = this.preset;
		file = replacePlaceholder(file, "formatVersion", this.formatVersion);
		file = replacePlaceholder(file, "identifier", armorPartRecipeIdentifier);
		file = replacePlaceholder(file, "craftItem", this.craftItem);
		file = replacePlaceholder(file, "pattern", pattern);
		file = replacePlaceholder(file, "resultItem", armorResultItem);

		
		FileSaver fileSaver = new FileSaver(this.material + "_" + part + "_recipe.json", file, this.genFolder);
		fileSaver.saveFile();
	}
	
	private static String replacePlaceholder(String text, String placeholder, String replacement) {
        Pattern pattern = Pattern.compile("\\{" + placeholder + "\\}");
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(replacement);
    }
	
}
