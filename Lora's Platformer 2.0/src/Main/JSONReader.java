package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Entity.Item.CreateItem;
import Entity.Player.Player;
import Entity.Unit.CreateUnit;
import Entity.Unit.UnitData;
import TileMap.TileMap;

public class JSONReader 
{
	public static boolean load(Player player, TileMap tileMap)
	{
		CreateItem createItem = new CreateItem(player.getTileMap());
		JSONParser jsonParser = new JSONParser();
		
		
		try
		{
			System.out.println("Loading data...");
			
			Object object = jsonParser.parse(new FileReader(System.getProperty("user.home") + "\\Loras Platformer\\SaveFile.json"));
			JSONObject jsonObject = (JSONObject) object;
			
			int whileCounter = 0;
			
			System.out.println("whileCheck: " + "Unit" + (whileCounter < 10 ? "0" : "") + whileCounter);
			
			while(jsonObject.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter) != null)
			{
				JSONObject jsonObjectInner = (JSONObject) jsonObject.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter);
				String unitType = (String) jsonObjectInner.get("UnitType");
				System.out.println("unitType: " + unitType);

				boolean facingRight = (Boolean) jsonObjectInner.get("FacingRight");
				boolean friendly = (Boolean) jsonObjectInner.get("Friendly");
				boolean untouchable = (Boolean) jsonObjectInner.get("Untouchable");
				boolean invulnerable = (Boolean) jsonObjectInner.get("Invulnerable");
				boolean unkillable = (Boolean) jsonObjectInner.get("unkillable");
				String name = (String) jsonObjectInner.get("Name");
				double spawnLocationX = (Double) jsonObjectInner.get("SpawnLocationX");
				double spawnLocationY = (Double) jsonObjectInner.get("SpawnLocationY");
				String currentMap = (String) jsonObjectInner.get("Map");
				
				UnitData unitData = new UnitData(facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, currentMap, unitType);
				CreateUnit.loadUnitList(currentMap, unitData);
				
				
				whileCounter++;
			}
			
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