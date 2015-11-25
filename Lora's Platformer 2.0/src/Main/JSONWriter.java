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
import Entity.Unit.Unit;

public class JSONWriter 
{  
  
    @SuppressWarnings("unchecked")
	public static void saveFile
	(
		Player player,
		int spawnLocationX,
		int spawnLocationY,
		ArrayList<Unit> characterList
		
	) 
    {  
    	
        try 
        {  
            File myFile = new File(System.getProperty("user.home") + "\\Loras Platformer");
            myFile.mkdir();
            
            Path path = Paths.get(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json");
         
            OutputStream outputStream = Files.newOutputStream(path);
            JSONObject jsonObjectOuter = new JSONObject();
            
            int z = 0;
            
	    	for(int x = 0; x < characterList.size(); x++)
	    	{
	    		Unit unit = characterList.get(x);
	            JSONObject jsonObjectInner = new JSONObject();
	            
	    		jsonObjectInner.put("Name", unit.getName());
	    		jsonObjectInner.put("Map", unit.getCurrentMap());
	    		jsonObjectInner.put("SpawnLocationX", unit.getSpawnX());
	    		jsonObjectInner.put("SpawnLocationY", unit.getSpawnY());
	    		jsonObjectInner.put(CreateItem.Coins.Silver.toString(), unit.getSilver());
	    		jsonObjectInner.put(CreateItem.Coins.Gold, unit.getGold());
	    		jsonObjectInner.put("Friendly", unit.getFriendly());
	    		jsonObjectInner.put("Health", unit.getHealth());
	    		jsonObjectInner.put("UnitType", unit.getUnitType());
	    		jsonObjectInner.put("FacingRight", unit.getFacingRight());
	    		jsonObjectInner.put("Invulnerable", unit.getInvulnerable());
	    		jsonObjectInner.put("Untouchable", unit.getUntouchable());
	    		jsonObjectInner.put("Unkillable", unit.getUnkillable());
	    		
	    		
		        Item[][] items = unit.getInventory().getItems();
		        for(int i = 0; i < unit.getInventory().getNumberOfRows(); i++)
		        {
		        	for(int j = 0; j < unit.getInventory().getNumberOfColumns(); j++)
		        	{
		        		if( items[i][j] != null)
		        		{
		        			jsonObjectInner.put("Item:" + (i + j), items[i][j].getItemType());
		        			jsonObjectInner.put("Stack:" + (i + j), items[i][j].getStacks());
		        		}
		        	}
		        }
//		        jsonArray.add(jsonObjectInner);
		        if(unit.isPlayer())
		        {
		        	z = 1;
		        }
		        jsonObjectOuter.put((unit.isPlayer() ? "Player" : "Unit" + (x - z < 10 ? "0" : "") + (x - z)), jsonObjectInner);
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