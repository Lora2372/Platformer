package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Entity.Player.Player;

public class JSONReader 
{
	public static boolean load(Player player)
	{
		
		JSONParser jsonParser = new JSONParser();
		
		
		try
		{			
			Object object = jsonParser.parse(new FileReader(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json"));
			JSONObject jsonObject = (JSONObject) object;
			
			String name = (String) jsonObject.get("Name");
			System.out.println("Name: " + name);
			
			
			String map = (String) jsonObject.get("Map");
			System.out.println("Map: " + map);
			
			player.setCurrentMap(map);
			
			int spawnLocationX = ((Long) jsonObject.get("SpawnLocationX")).intValue();
			System.out.println("SpawnLocationX: " + spawnLocationX);
			
			int spawnLocationY = ((Long) jsonObject.get("SpawnLocationY")).intValue();
			System.out.println("SpawnLocationY: " + spawnLocationY);
			
			player.setSpawnPoint(spawnLocationX, spawnLocationY);
			
		}
		catch(FileNotFoundException e2)
		{
			e2.printStackTrace();
			return false;
		}
		catch(IOException e3)
		{
			e3.printStackTrace();
			return false;
		}
		catch(ParseException e4)
		{
			e4.printStackTrace();
			return false;
		}
		return true;
		
	
	}
}