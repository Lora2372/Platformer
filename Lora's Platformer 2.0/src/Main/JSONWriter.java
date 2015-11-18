package Main;

import java.io.File;
import java.io.IOException;  
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.simple.JSONObject;

import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Player.Player;

public class JSONWriter 
{  
  
    @SuppressWarnings("unchecked")
	public static void saveFile(
			Player player,
			int spawnLocationX,
			int spawnLocationY
			) 
    {  
  
    	
        JSONObject jsonObject = new JSONObject();  
        jsonObject.put("Name", player.getName());  
        jsonObject.put("Map", player.getCurrentMap());  
        jsonObject.put("SpawnLocationX", spawnLocationX);
        jsonObject.put("SpawnLocationY", spawnLocationY);
        jsonObject.put(CreateItem.Coins.Silver.toString(), player.getSilver());
        jsonObject.put(CreateItem.Coins.Gold.toString(), player.getGold());
        jsonObject.put("UsingMouse", player.getUsingMouse());
        
        Item[][] items = player.getInventory().getItems();
        for(int i = 0; i < player.getInventory().getNumberOfRows(); i++)
        {
        	for(int j = 0; j < player.getInventory().getNumberOfColumns(); j++)
        	{
        		if( items[i][j] != null)
        		{
        			jsonObject.put("Item:" + (i + j), items[i][j].getItemType());
        			jsonObject.put("Stack:" + (i + j), items[i][j].getStacks());
        		}
        	}
        }
        
        try 
        {  
            File myFile = new File(System.getProperty("user.home") + "\\Loras Platformer");
            myFile.mkdir();
            
            Path path = Paths.get(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json");
         
            OutputStream outputStream = Files.newOutputStream(path);
       
            outputStream.write(jsonObject.toJSONString().getBytes(Charset.forName("UTF-8")));
            outputStream.flush();
            outputStream.close();
        } 
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }
    }
} 