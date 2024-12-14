package armor;

import java.io.FileNotFoundException;

public class Gen {

	public static void main(String[] args) throws FileNotFoundException {
		
		//THIS FORMAT
		
//		String formatVersion = "1.20.80"; //default is 1.20.80
//		String modName = "minecraftplus";
//		String material = "copper"; 
//		int helmetDurability = 68; 	//helmet durability, script is automatically calculating the other parts of the armor
//		int enchantable = 7;
//		int helmetProtection = 2; 	//helmet protection, script is automatically calculating the other parts of the armor
//		String craftItem = "minecraft:copper_ingot";
//		String repairItem = "minecraft:copper_ingot";
//		boolean generateRecipes = true;
// 		String layerTextureName = "acacia_log_layer"; //"_1" + "_2" is gen
//		String textureName = "acacia_log";  //"_helmet" is gen
		
		
		String formatVersion = "1.20.80"; 
		String modName = "minecraftplus";
		String material = "acacia_log"; 
		int helmetDurability = 28; 	
		int enchantable = 25;
		int helmetProtection = 1; 	
		String craftItem = "minecraft:acacia_log";
		String repairItem = "minecraft:acacia_log";
		String layerTextureName = "acacia_log_layer";
		String textureName = "acacia_log"; 
		
		Armor armor = new Armor(formatVersion, modName, material, helmetDurability, enchantable, helmetProtection, craftItem, repairItem);
		armor.run();
		ArmorRecipes armorRecipes = new ArmorRecipes(modName, material, craftItem);
		armorRecipes.run();
		ArmorTextures armorTextures = new ArmorTextures(formatVersion, modName, material, layerTextureName);
		armorTextures.run();
		ItemTextures itemTextures = new ItemTextures(modName, material, textureName);
		itemTextures.run();
		
	}

}
