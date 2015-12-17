package Main;

import java.io.File;
import java.io.IOException;  
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Entity.Doodad.Activatable.DoodadData;
import Entity.Doodad.Activatable.TemporaryDoodadData;
import Entity.Item.ItemData;
import Entity.Item.Item;
import Entity.Item.TemporaryItemData;
import Entity.Player.Player;
import Entity.Unit.CreateUnit;
import Entity.Unit.TemporaryUnitData;
import GameState.GameStateManager;

public class JSONWriter 
{  
  
    @SuppressWarnings("unchecked")
	public static void saveFile
		(
			Player player
		
		) 
    {  
    	
        try 
        {  
            File myFile = new File(System.getProperty("user.home") + "\\Loras Platformer");
            myFile.mkdir();
            
            Path path = Paths.get(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json");
            
            OutputStream outputStream = Files.newOutputStream(path);
            
            // Main jsonobject, contains everything
            JSONObject jsonObjectOuter = new JSONObject();
            
            // Player object, contains all player data
            JSONObject jsonObjectPlayer = new JSONObject();
    		jsonObjectPlayer.put("Name", player.getName());
    		jsonObjectPlayer.put("Map", player.getCurrentMap());
    		jsonObjectPlayer.put("SpawnLocationX", player.getSpawnLocationX());
    		jsonObjectPlayer.put("SpawnLocationY", player.getSpawnLocationY());
    		jsonObjectPlayer.put(ItemData.Coins.Silver.toString(), player.getSilver());
    		jsonObjectPlayer.put(ItemData.Coins.Gold, player.getGold());
    		jsonObjectPlayer.put("Friendly", player.getFriendly());
    		jsonObjectPlayer.put("Health", (int)player.getHealth());
    		jsonObjectPlayer.put("Mana", (int)player.getMana());
    		jsonObjectPlayer.put("Stamina", (int)player.getStamina());
    		jsonObjectPlayer.put("Warmth", (int)player.getWarmth());
    		jsonObjectPlayer.put("Wet", (int)player.getWet());
    		jsonObjectPlayer.put("UnitType", player.getUnitType());
    		jsonObjectPlayer.put("FacingRight", player.getFacingRight());
    		jsonObjectPlayer.put("Invulnerable", player.getInvulnerable());
    		jsonObjectPlayer.put("Untouchable", player.getUntouchable());
    		jsonObjectPlayer.put("Unkillable", player.getUnkillable());
			jsonObjectPlayer.put("UseMouse", player.getUseMouse());
			jsonObjectPlayer.put("DisplayHealthBars", player.getDisplayHealthBars());
			jsonObjectPlayer.put("DisplayNamePlates", player.getDisplayNamePlates());
			
			JSONObject jsonObjectKeyBindings = new JSONObject();
			for(int i = 0; i < player.getKeyBinds().length; i++)
			{
				jsonObjectKeyBindings.put(Player.KeyBind.values()[i].toString(), player.getKeyBind(i));
			}
			jsonObjectPlayer.put("KeyBindings", jsonObjectKeyBindings);
			
			JSONObject jsonObjectItems = new JSONObject();
			
			
	        Item[][] items = player.getInventory().getItems();
	        for(int i = 0; i < player.getInventory().getNumberOfRows(); i++)
	        {
	        	for(int j = 0; j < player.getInventory().getNumberOfColumns(); j++)
	        	{
	        		if( items[i][j] != null)
	        		{
	        			int currentItem = i + j;
	        			jsonObjectItems.put("Item:" + (currentItem < 10 ? "0" : "") + currentItem, items[i][j].getItemType());
	        			jsonObjectItems.put("Stack:" + (currentItem < 10 ? "0" : "") + currentItem, items[i][j].getStacks());
	        		}
	        	}
	        }
			jsonObjectPlayer.put("Items", jsonObjectItems);
			jsonObjectOuter.put("Player", jsonObjectPlayer);
            
            // Map objects, contains all the maps
			for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
			{
				String gameMap = GameStateManager.GameMaps.values()[i].toString();
				JSONObject jsonObjectMap = new JSONObject();
				jsonObjectMap.put("Loading", player.getLoading(i));
				
				
				if(!gameMap.equals("Tutorial"))
				{	
					
					// Saving units
					JSONObject jsonObjectUnits = new JSONObject();
	    	    	for(int j = 0; j < CreateUnit.getUnitDataList(gameMap).size(); j++)
	    	    	{
	    	    		TemporaryUnitData unit = CreateUnit.getUnitDataList(gameMap).get(j);
	    	            JSONObject jsonObjectUnit = new JSONObject();
	    	            
		    	    	jsonObjectUnit.put("Name", unit.getName());
		    	    	jsonObjectUnit.put("SpawnLocationX", unit.getSpawnLocationX());
	    	    		jsonObjectUnit.put("SpawnLocationY", unit.getSpawnLocationY());
	    	    		jsonObjectUnit.put(ItemData.Coins.Silver.toString(), unit.getSilver());
	    	    		jsonObjectUnit.put(ItemData.Coins.Gold, unit.getGold());
	    	    		jsonObjectUnit.put("Friendly", unit.getFriendly());
	    	    		jsonObjectUnit.put("Health", (int)unit.getHealth());
	    	    		jsonObjectUnit.put("UnitType", unit.getUnitType());
	    	    		jsonObjectUnit.put("FacingRight", unit.getFacingRight());
	    	    		jsonObjectUnit.put("Invulnerable", unit.getInvulnerable());
	    	    		jsonObjectUnit.put("Untouchable", unit.getUntouchable());
	    	    		jsonObjectUnit.put("Unkillable", unit.getUnkillable());
	    	    		
	    	    		jsonObjectItems = new JSONObject();
	    	    		
	    		        ArrayList<Item> itemsList = unit.getItems();
	    		        for(int x = 0; x < itemsList.size(); x++)
	    		        {
	    		        	jsonObjectItems.put("Item:" + (x < 10 ? "0" : "") + x, itemsList.get(x).getItemType());
		        			jsonObjectItems.put("Stack:" + (x < 10 ? "0" : "") + x, itemsList.get(x).getStacks());
	    		        }
	    		        jsonObjectUnit.put("Items", jsonObjectItems);
	    		        jsonObjectUnits.put("Unit" + (j < 10 ? "0" : "") + j, jsonObjectUnit);
	    	    	}
	    	    	jsonObjectMap.put("Units", jsonObjectUnits);
					
	    	    	
					// Saving doodads
					JSONObject jsonObjectDoodads = new JSONObject();
					for(int j = 0; j < DoodadData.getDoodadDataList(gameMap).size(); j++)
					{
						TemporaryDoodadData doodad = DoodadData.getDoodadDataList(gameMap).get(j);
						JSONObject jsonObjectDoodad = new JSONObject();
						
						jsonObjectDoodad.put("Untouchable", doodad.getUntouchable());
						jsonObjectDoodad.put("Invulnerable", doodad.getInvulnerable());
						jsonObjectDoodad.put("Active", doodad.getActive());
						jsonObjectDoodad.put("CurrentAction", doodad.getCurrentAction());
						jsonObjectDoodad.put("Locked", doodad.getLocked());
		    	    	jsonObjectDoodad.put("SpawnLocationX", doodad.getSpawnLocationX());
	    	    		jsonObjectDoodad.put("SpawnLocationY", doodad.getSpawnLocationY());
						jsonObjectDoodad.put("DoodadType", doodad.getDoodadType());
						jsonObjectDoodad.put("UniqueID", doodad.getUniqueID());
						
	    	    		jsonObjectItems = new JSONObject();
	    	    		
	    		        ArrayList<Item> itemsList = doodad.getItems();
	    		        for(int x = 0; x < itemsList.size(); x++)
	    		        {
	    		        	jsonObjectItems.put("Item:" + (x < 10 ? "0" : "") + x, itemsList.get(x).getItemType());
		        			jsonObjectItems.put("Stack:" + (x < 10 ? "0" : "") + x, itemsList.get(x).getStacks());
	    		        }
	    		        jsonObjectDoodad.put("Items", jsonObjectItems);
	    		        jsonObjectDoodads.put("Doodad" + (j < 10 ? "0" : "") + j, jsonObjectDoodad);
					}
					jsonObjectMap.put("Doodads", jsonObjectDoodads);
					
					
					// Saving items in the world:
					jsonObjectItems = new JSONObject();
					
					for(int j = 0; j < ItemData.getItemDataList(gameMap).size(); j++)
					{
						TemporaryItemData item = ItemData.getItemDataList(gameMap).get(j);
						JSONObject jsonObjectItem = new JSONObject();
						jsonObjectItem.put("ItemType", item.getItemType());
						jsonObjectItem.put("Stack", item.getStacks());
						jsonObjectItem.put("SpawnLocationX", item.getSpawnLocationX());
						jsonObjectItem.put("SpawnLocationY", item.getSpawnLocationY());
						
						jsonObjectItems.put("Item" + (j < 10 ? "0" : "") + j, jsonObjectItem);
					}
					jsonObjectMap.put("Items", jsonObjectItems);
					
					// Saving all of the map's data
					jsonObjectOuter.put(GameStateManager.GameMaps.values()[i], jsonObjectMap); 
				}
			}

	    	outputStream.write(jsonObjectOuter.toJSONString().getBytes(Charset.forName("UTF-8")));
	        outputStream.flush();
	        outputStream.close();
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }
    }
} 