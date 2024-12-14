package armor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fileApi.FileReader;
import fileApi.FileSaver;

public class Armor {

	private String formatVersion = "1.20.80"; 
	private String identifier;
	private String icon;
	private int durability;
	private int enchantable;
	private int protection;
	private String craftItem;
	private String repairItem;
	private String material;
	
	private int helmetDurability;
	private int helmetProtection;
	
	private int chestplateDurability;
	private int chestplateProtection;
	
	private int leggingsDurability;
	private int leggingsProtection;

	private int bootsDurability;
	private int bootsProtection;
	
	private String presetPath = "src/armor/armorPreset.txt";
	private String preset = "";
	
	private String genFolder = "bp" + File.separator + "item" + File.separator + "armor";

	
	Armor(String formatVersion, String modName, String material, int durability, int enchantable, int protection,String craftItem, String repairItem) throws FileNotFoundException{
		this.formatVersion = formatVersion;
		this.identifier = modName + ":" + material;
		this.icon = modName + "_" + material;
		this.durability = durability;
		this.enchantable = enchantable;
		this.protection = protection;
		this.craftItem = craftItem;
		this.repairItem = repairItem;
		this.material = material;
		
		calculateDurability();
		calculateProtection();
		
		FileReader fileReader = new FileReader(presetPath);
		preset = fileReader.getPreset();	
	}
	

	public void run() {
		generateArmor("helmet", this.helmetProtection, this.helmetDurability, "head", "armor_head");
		generateArmor("chestplate", this.chestplateProtection, this.chestplateDurability, "chest", "armor_torso");
		generateArmor("leggings", this.leggingsProtection, this.leggingsDurability,  "legs", "armor_legs");
		generateArmor("boots", this.bootsProtection, this.bootsDurability, "feet", "armor_feet");
	}


	private void calculateDurability(){
		this.helmetDurability = this.durability;
		this.chestplateDurability = (int) Math.round(this.durability * (80.0 / 55.0));
		this.leggingsDurability = (int) Math.round(this.durability * (75.0 / 55.0));
		this.bootsDurability = (int) Math.round(this.durability * (65.0 / 55.0));	
	}
	
	private void calculateProtection(){
		this.helmetProtection = this.protection;
		this.chestplateProtection = (int) Math.round(this.protection * (166.67 / 100.0));
		this.leggingsProtection = (int) Math.round(this.protection * (125.00 / 100.0));
		this.bootsProtection = (int) Math.round(this.protection * (100.00 / 100.0));	
	}
	
	
	private void generateArmor(String part, int protection, int durability, String slot, String slotEnch) {
		String armorPartIdentifier = this.identifier + "_" + part;
		String armorPartIcon = this.icon + "_" + part;
		String file = this.preset;
		file = replacePlaceholder(file, "formatVersion", this.formatVersion);
		file = replacePlaceholder(file, "identifier", armorPartIdentifier);
		file = replacePlaceholder(file, "part", part);
		file = replacePlaceholder(file, "enchantable", this.enchantable + "");
		file = replacePlaceholder(file, "slotEnch", slotEnch + "");
		file = replacePlaceholder(file, "durability", durability + "");
		file = replacePlaceholder(file, "icon", armorPartIcon);
		file = replacePlaceholder(file, "slot", slot);
		file = replacePlaceholder(file, "protection", protection + "");
		file = replacePlaceholder(file, "repairItem", this.repairItem);
		
		FileSaver fileSaver = new FileSaver(this.material + "_" + part + ".json", file, this.genFolder);
		fileSaver.saveFile();
	}
	
	private static String replacePlaceholder(String text, String placeholder, String replacement) {
        Pattern pattern = Pattern.compile("\\{" + placeholder + "\\}");
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(replacement);
    }
	
}
