package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Entity.Item.CreateItem;
import Entity.Player.Player;

public class JSONReader 
{
	public static boolean load(Player player)
	{
		CreateItem createItem = new CreateItem(player.getTileMap());
		JSONParser jsonParser = new JSONParser();
		
		
		try
		{
			System.out.println("Loading data...");
			
			Object object = jsonParser.parse(new FileReader(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json"));
			JSONObject jsonObject = (JSONObject) object;
			
			String name = (String) jsonObject.get("Name");
			System.out.println("name: " + name);
			
			
			String map = (String) jsonObject.get("Map");
			System.out.println("map: " + map);
			
			player.setCurrentMap(map);
			
			int spawnLocationX = ((Long) jsonObject.get("SpawnLocationX")).intValue();
			System.out.println("spawnLocationX: " + spawnLocationX);
			
			int spawnLocationY = ((Long) jsonObject.get("SpawnLocationY")).intValue();
			System.out.println("spawnLocationY: " + spawnLocationY);
			
			player.setSpawnPoint(spawnLocationX, spawnLocationY);
		
			
			boolean usingMouse = (Boolean) jsonObject.get("UseMouse");
			player.setUseMouse(usingMouse);
			System.out.println("usingMouse: " + usingMouse);
			
			boolean displayHealthBars = (Boolean) jsonObject.get("DisplayHealthBars");
			player.setDisplayHealthBars(displayHealthBars);
			System.out.println("displayHealthBars: " + displayHealthBars);

			int silver = ((Long)jsonObject.get(CreateItem.Coins.Silver.toString())).intValue();
			System.out.println("Silver: " + silver);
			player.addSilver(silver);
			
			int gold = ((Long)jsonObject.get(CreateItem.Coins.Gold.toString())).intValue();
			System.out.println("Gold: " + gold);
			player.addGold(gold);
			
			
			int i = 0;
			do
			{
				String itemType = (String) jsonObject.get("Item:" + i);
								
				if(itemType != null)
				{
					int itemStack =  ( (Long) ( jsonObject.get("Stack:" + i) ) ).intValue();
					player.getInventory().addItem(createItem.createItem(itemType, player, itemStack));
					i++;
				}
				else
				{
					i = -1;
				}
				
			}while(i != -1);
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
		catch(NullPointerException e5)
		{
			e5.printStackTrace();
			return false;
		}
		return true;
	}
}