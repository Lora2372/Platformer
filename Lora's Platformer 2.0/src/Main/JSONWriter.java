package Main;

import java.io.File;
import java.io.IOException;  
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;

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
        jsonObject.put("Name", "Lora");  
        jsonObject.put("Map", player.getCurrentMap());  
        jsonObject.put("SpawnLocationX", spawnLocationX);
        jsonObject.put("SpawnLocationY", spawnLocationY);
        
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