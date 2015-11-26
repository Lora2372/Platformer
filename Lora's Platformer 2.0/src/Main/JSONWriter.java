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
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Player.Player;
import Entity.Unit.CreateUnit;
import Entity.Unit.UnitData;

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
            JSONObject jsonObjectOuter = new JSONObject();
            
            for(int characterListIndex = 0; characterListIndex < CreateUnit.getCharacterListSize(); characterListIndex++)
            {
            	if(characterListIndex == 1)
            	{
            		System.out.println(".");
            	}
    	    	for(int unitDataIndex = 0; unitDataIndex < CreateUnit.getUnitDataList(characterListIndex).size(); unitDataIndex++)
    	    	{
    	    		UnitData unit = CreateUnit.getUnitDataList(characterListIndex).get(unitDataIndex);
    	            JSONObject jsonObjectInner = new JSONObject();
    	            
    	    		jsonObjectInner.put("Name", unit.getName());
    	    		jsonObjectInner.put("Map", unit.getCurrentMap());
    	    		jsonObjectInner.put("SpawnLocationX", unit.getSpawnLocationX());
    	    		jsonObjectInner.put("SpawnLocationY", unit.getSpawnLocationY());
    	    		jsonObjectInner.put(CreateItem.Coins.Silver.toString(), unit.getSilver());
    	    		jsonObjectInner.put(CreateItem.Coins.Gold, unit.getGold());
    	    		jsonObjectInner.put("Friendly", unit.getFriendly());
    	    		jsonObjectInner.put("Health", unit.getHealth());
    	    		jsonObjectInner.put("UnitType", unit.getUnitType());
    	    		jsonObjectInner.put("FacingRight", unit.getFacingRight());
    	    		jsonObjectInner.put("Invulnerable", unit.getInvulnerable());
    	    		jsonObjectInner.put("Untouchable", unit.getUntouchable());
    	    		jsonObjectInner.put("Unkillable", unit.getUnkillable());
    	    		
    	    		if(unit.getUnitType().equals("Player"))
    	    		{
    	    			jsonObjectInner.put("UseMouse", player.getUseMouse());
    	    			jsonObjectInner.put("DisplayHealthBars", player.getDisplayHealthBars());
    	    			jsonObjectInner.put("DisplayNamePlates", player.getDisplayNamePlates());	
    	    		}
    	    		
    		        ArrayList<Item> items = unit.getItems();
    		        for(int i = 0; i < items.size(); i++)
    		        {
    		        	jsonObjectInner.put("Item:" + (i), items.get(i).getItemType());
	        			jsonObjectInner.put("Stack:" + (i), items.get(i).getStacks());
    		        }
//    		        jsonArray.add(jsonObjectInner);
    		        jsonObjectOuter.put("Unit" + (unitDataIndex < 10 ? "0" : "") + unitDataIndex, jsonObjectInner);
    	    	}
            }

	    	outputStream.write(jsonObjectOuter.toJSONString().getBytes(Charset.forName("UTF-8")));
//	        JSONObject jsonObject = new JSONObject();  
//	        jsonObject.put("Name", player.getName());  
//	        jsonObject.put("Map", player.getCurrentMap());  
//	        jsonObject.put("SpawnLocationX", spawnLocationX);
//	        jsonObject.put("SpawnLocationY", spawnLocationY);
//	        jsonObject.put(CreateItem.Coins.Silver.toString(), player.getSilver());
//	        jsonObject.put(CreateItem.Coins.Gold.toString(), player.getGold());
//	        jsonObject.put("UseMouse", player.getUseMouse());
//	        jsonObject.put("DisplayHealthBars", player.getDisplayHealthBars());
//	        
//	        Item[][] items = player.getInventory().getItems();
//	        for(int i = 0; i < player.getInventory().getNumberOfRows(); i++)
//	        {
//	        	for(int j = 0; j < player.getInventory().getNumberOfColumns(); j++)
//	        	{
//	        		if( items[i][j] != null)
//	        		{
//	        			jsonObject.put("Item:" + (i + j), items[i][j].getItemType());
//	        			jsonObject.put("Stack:" + (i + j), items[i][j].getStacks());
//	        		}
//	        	}
//	        }
	        
	
	       
	        
	        outputStream.flush();
	        outputStream.close();
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }
    }
} 