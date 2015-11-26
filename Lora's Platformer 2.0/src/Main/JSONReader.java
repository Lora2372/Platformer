package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Entity.Item.CreateItem;
import Entity.Item.Item;
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
			
			// Load regular units:
			while(jsonObject.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter) != null)
			{
				JSONObject jsonObjectInner = (JSONObject) jsonObject.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter);
				String unitType = (String) jsonObjectInner.get("UnitType");
				System.out.println("unitType: " + unitType);

				boolean facingRight = (Boolean) jsonObjectInner.get("FacingRight");
				boolean friendly = (Boolean) jsonObjectInner.get("Friendly");
				boolean untouchable = (Boolean) jsonObjectInner.get("Untouchable");
				boolean invulnerable = (Boolean) jsonObjectInner.get("Invulnerable");
				boolean unkillable = (Boolean) jsonObjectInner.get("Unkillable");
				String name = (String) jsonObjectInner.get("Name");
				int health = ( (Long) jsonObjectInner.get("Health") ).intValue();
				double spawnLocationX = (Double) jsonObjectInner.get("SpawnLocationX");
				double spawnLocationY = (Double) jsonObjectInner.get("SpawnLocationY");
				String currentMap = (String) jsonObjectInner.get("Map");
				int silver = ( (Long) jsonObjectInner.get("Silver") ).intValue();
				int gold = ( (Long) jsonObjectInner.get("Gold") ).intValue();
				
				ArrayList<Item> items = new ArrayList<Item>();
				
				
				
				if(!unitType.equals("Player"))
				{
					int i = 0;
					do
					{
						String itemType = (String) jsonObject.get("Item:" + i);
										
						if(itemType != null)
						{
							int itemStack =  ( (Long) ( jsonObject.get("Stack:" + (i < 10 ? "0" : "") + i) ) ).intValue();
							items.add(createItem.createItem(itemType, null, itemStack));
							i++;
						}
						else
						{
							i = -1;
						}
						
					} while(i != -1);
					
					
					UnitData unitData = new UnitData
							(
									facingRight, 
									friendly, 
									untouchable, 
									invulnerable, 
									unkillable, 
									name,
									health,
									spawnLocationX, 
									spawnLocationY, 
									currentMap, 
									unitType,
									silver,
									gold,
									items
							);
					CreateUnit.addUnit(currentMap, unitData);
				}
				else
				{
					System.out.println("map: " + currentMap);
					
					player.setCurrentMap(currentMap);
					player.setFacingRight(facingRight);
					player.setName(name);
					player.setSpawnLocationX(spawnLocationX);
					player.setSpawnLocationY(spawnLocationY);
					
					boolean usingMouse = (Boolean) jsonObjectInner.get("UseMouse");
					player.setUseMouse(usingMouse);
					System.out.println("usingMouse: " + usingMouse);
					
					boolean displayHealthBars = (Boolean) jsonObjectInner.get("DisplayHealthBars");
					player.setDisplayHealthBars(displayHealthBars);
					System.out.println("displayHealthBars: " + displayHealthBars);

					boolean displayNamePlates = (Boolean) jsonObjectInner.get("DisplayNamePlates");
					player.setDisplayNamePlates(displayNamePlates);
					System.out.println("displayNamePlates: " + displayNamePlates);

					System.out.println("Silver: " + silver);
					player.addSilver(silver);
					
					System.out.println("Gold: " + gold);
					player.addGold(gold);

					int i = 0;
					do
					{
						String itemType = (String) jsonObjectInner.get("Item:" + i);
										
						if(itemType != null)
						{
							int itemStack =  ( (Long) ( jsonObjectInner.get("Stack:" + i) ) ).intValue();
							player.getInventory().addItem(createItem.createItem(itemType, player, itemStack));
							i++;
						}
						else
						{
							i = -1;
						}
						
					} while(i != -1);
				}
				
				
				whileCounter++;
			}
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