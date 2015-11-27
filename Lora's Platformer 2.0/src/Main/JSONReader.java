package Main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Entity.Doodad.Activatable.CreateDoodad;
import Entity.Doodad.Activatable.DoodadData;
import Entity.Item.CreateItem;
import Entity.Item.Item;
import Entity.Player.Player;
import Entity.Unit.CreateUnit;
import Entity.Unit.UnitData;
import GameState.GameStateManager;
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
			
			//*************************************************************************************************************************
			// Load player object                                                                                                     *
			//*************************************************************************************************************************
			JSONObject jsonObjectPlayer = (JSONObject) jsonObject.get("Player");
			boolean facingRight = (Boolean) jsonObjectPlayer.get("FacingRight");
			boolean friendly = (Boolean) jsonObjectPlayer.get("Friendly");
			boolean untouchable = (Boolean) jsonObjectPlayer.get("Untouchable");
			boolean invulnerable = (Boolean) jsonObjectPlayer.get("Invulnerable");
			boolean unkillable = (Boolean) jsonObjectPlayer.get("Unkillable");
			String name = (String) jsonObjectPlayer.get("Name");
			int health = ( (Long) jsonObjectPlayer.get("Health") ).intValue();
			double spawnLocationX = (Double) jsonObjectPlayer.get("SpawnLocationX");
			double spawnLocationY = (Double) jsonObjectPlayer.get("SpawnLocationY");
			String currentMap = (String) jsonObjectPlayer.get("Map");
			int silver = ( (Long) jsonObjectPlayer.get("Silver") ).intValue();
			int gold = ( (Long) jsonObjectPlayer.get("Gold") ).intValue();
			
			player.setFacingRight(facingRight);
			player.setCurrentMap(currentMap);
			player.setName(name);
			player.setSpawnLocationX(spawnLocationX);
			player.setSpawnLocationY(spawnLocationY);
			
			boolean usingMouse = (Boolean) jsonObjectPlayer.get("UseMouse");
			player.setUseMouse(usingMouse);
			
			boolean displayHealthBars = (Boolean) jsonObjectPlayer.get("DisplayHealthBars");
			player.setDisplayHealthBars(displayHealthBars);

			boolean displayNamePlates = (Boolean) jsonObjectPlayer.get("DisplayNamePlates");
			player.setDisplayNamePlates(displayNamePlates);

			player.addSilver(silver);
			
			player.addGold(gold);
			
			// Load player items:
			JSONObject jsonObjectItems = (JSONObject) jsonObjectPlayer.get("Items");
			
			int i = 0;
			do
			{
				String itemType = (String) jsonObjectItems.get("Item:" + (i < 10 ? "0" : "") + i);
								
				if(itemType != null)
				{
					int itemStack =  ( (Long) ( jsonObjectItems.get("Stack:" + (i < 10 ? "0" : 0) + i) ) ).intValue();
					player.getInventory().addItem(createItem.createItem(itemType, player, itemStack));
					i++;
				}
				else
				{
					i = -1;
				}
				
			} while(i != -1);
			
			//*************************************************************************************************************************
			//*************************************************************************************************************************
			//*************************************************************************************************************************
			

			// Load based on map:
			for(i = 0; i < GameStateManager.GameMaps.values().length; i++)
			{
				JSONObject jsonObjectMap = (JSONObject) jsonObject.get(GameStateManager.GameMaps.values()[i].toString());
				currentMap = GameStateManager.GameMaps.values()[i].toString();
				

				
				if(!currentMap.equals("Tutorial"))
				{
					boolean loading = (Boolean) jsonObjectMap.get("Loading");
					player.setLoading(i, loading);
					
					// Load regular doodads
					whileCounter = 0;
					while(jsonObjectMap.get("Doodad" + (whileCounter < 10 ? "0" : "") + whileCounter) != null)
					{
						
						JSONObject jsonObjectDoodad = (JSONObject) jsonObjectMap.get("Doodad" + (whileCounter < 10 ? "0" : "") + whileCounter);
						String doodadType = (String) jsonObjectDoodad.get("DoodadType");
						
						untouchable = (Boolean) jsonObjectDoodad.get("Untouchable");
						invulnerable = (Boolean) jsonObjectDoodad.get("Invulnerable");
						boolean active = (Boolean) jsonObjectDoodad.get("Active");
						int currentAction = ( (Long) jsonObjectDoodad.get("CurrentAction") ).intValue();
						boolean locked = (Boolean) jsonObjectDoodad.get("Locked");
						spawnLocationX = (Double) jsonObjectDoodad.get("SpawnLocationX");
						spawnLocationY = (Double) jsonObjectDoodad.get("SpawnLocationY");
						
						// Load doodad items:
						jsonObjectItems = (JSONObject) jsonObjectDoodad.get("Items");
						ArrayList<Item> items = new ArrayList<Item>();
						
						int x = 0;
						
						do
						{
							String itemType = (String) jsonObjectItems.get("Item:" + (i < 10 ? "0" : "") + x);
											
							if(itemType != null)
							{
								
								int itemStack =  ( (Long) ( jsonObjectItems.get("Stack:" + (x < 10 ? "0" : "") + x) ) ).intValue();
								items.add(createItem.createItem(itemType, null, itemStack));
								x++;
							}
							else
							{
								x = -1;
							}
							
						} while(x != -1);
						
						
						DoodadData unitData = new DoodadData
						(
							 
							 
							untouchable, 
							invulnerable, 
							active, 
							currentAction,
							locked,
							spawnLocationX, 
							spawnLocationY, 
							doodadType,
							items
						);
						
						CreateDoodad.addDoodad(currentMap, unitData);
						
						whileCounter++;
						
					}
					
					
					whileCounter = 0;
					// Load regular units:
					while(jsonObjectMap.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter) != null)
					{
						JSONObject jsonObjectUnit = (JSONObject) jsonObjectMap.get("Unit" + (whileCounter < 10 ? "0" : "") + whileCounter);
						String unitType = (String) jsonObjectUnit.get("UnitType");

						facingRight = (Boolean) jsonObjectUnit.get("FacingRight");
						friendly = (Boolean) jsonObjectUnit.get("Friendly");
						untouchable = (Boolean) jsonObjectUnit.get("Untouchable");
						invulnerable = (Boolean) jsonObjectUnit.get("Invulnerable");
						unkillable = (Boolean) jsonObjectUnit.get("Unkillable");
						name = (String) jsonObjectUnit.get("Name");
						health = ( (Long) jsonObjectUnit.get("Health") ).intValue();
						spawnLocationX = (Double) jsonObjectUnit.get("SpawnLocationX");
						spawnLocationY = (Double) jsonObjectUnit.get("SpawnLocationY");
						silver = ( (Long) jsonObjectUnit.get("Silver") ).intValue();
						gold = ( (Long) jsonObjectUnit.get("Gold") ).intValue();
						
						
						// Load unit items:
						jsonObjectItems = (JSONObject) jsonObjectUnit.get("Items");
						ArrayList<Item> items = new ArrayList<Item>();
						
						int x = 0;
						
						do
						{
							String itemType = (String) jsonObjectItems.get("Item:" + (i < 10 ? "0" : "") + x);
											
							if(itemType != null)
							{
								
								int itemStack =  ( (Long) ( jsonObjectItems.get("Stack:" + (x < 10 ? "0" : "") + x) ) ).intValue();
								items.add(createItem.createItem(itemType, null, itemStack));
								x++;
							}
							else
							{
								x = -1;
							}
							
						} while(x != -1);
						
						
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
							unitType,
							silver,
							gold,
							items
						);
						CreateUnit.addUnit(currentMap, unitData);
						
						
						whileCounter++;
					}
				}
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