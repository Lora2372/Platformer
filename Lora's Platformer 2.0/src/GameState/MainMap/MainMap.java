package GameState.MainMap;

import java.awt.Color;
import java.awt.Graphics2D;
import Main.GamePanel;
import TileMap.*;
import Entity.Explosion.Explosion;
import Entity.Item.Bag;
import Entity.Item.Coin;
import Entity.Item.ItemData;
import Entity.Item.Item;
import Entity.Item.TemporaryItemData;
import Entity.Item.Key;
import Entity.Item.Potion;
import Entity.MapObject;
import Entity.Doodad.*;
import Entity.Doodad.Activatable.*;
import Entity.Player.*;
import Entity.Projectile.Projectile;
import Entity.Unit.*;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.Conversation.ConversationData;
import GameState.Conversation.ConversationState;
import Audio.JukeBox;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class MainMap extends GameState
{
	protected TileMap tileMap;
	protected Background background;
	protected GameOver gameoverScreen;
	protected Player player;
	protected ArrayList<Unit> characterList;
	protected ArrayList<Doodad> stuff;
	protected ArrayList<Doodad> activatables;
	protected ArrayList<Doodad> alterableDoodads;
	protected ArrayList<Item> items;
	protected ArrayList<Projectile> projectiles;
	protected ArrayList<Explosion> explosions;
	protected boolean doneInitializing;
	
	protected boolean raining = false;
	
	protected ConversationState conversationState;
	protected ConversationData conversationData;
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	protected boolean mousePressed;
	
	protected boolean holdingInteractButton;
	
	
	protected boolean displayHealthBars;
	protected boolean displayNamePlates;
	
	
	protected long soundTimer;
	
	protected boolean gameover;
	
	protected String currentMap;
	
	public SpawnUnit spawnUnit;
	public SpawnDoodad spawnDoodad;
	public SpawnItem spawnItem;
	
	protected int[] numberofSounds;
	public enum soundTypes 
	{ 
		Rain 
	}
	
	protected Liadrin liadrin;
	
	public MainMap
		(
			GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState,
			String currentMap
			
		)
	{
		this.gameStateManager = gameStatemanager;
		this.conversationState = conversationState;
		this.tileMap = tileMap;
		this.player = player;
		this.currentMap = currentMap;
		
		conversationData = new ConversationData(player);
		
		spawnUnit	= new SpawnUnit(this);
		spawnDoodad	= new SpawnDoodad(this);
		spawnItem	= new SpawnItem(this);
		
		initialize();
	}
	
	public void initialize()
	{

		numberofSounds = new int[soundTypes.values().length];
		for(int i = 0; i < numberofSounds.length; i++)
		{
			int tempInt = 0;
			while(JukeBox.checkIfClipExists(soundTypes.values()[i] + (i < 10 ? "0" : "") + (tempInt + 1)))
			{
				tempInt++;
			}
			numberofSounds[i] = tempInt;
		}
		
		characterList = new ArrayList<Unit>();
		stuff = new ArrayList<Doodad>();
		activatables = new ArrayList<Doodad>();
		alterableDoodads = new ArrayList<Doodad>();
		items = new ArrayList<Item>();
		
		projectiles = new ArrayList<Projectile>();
		explosions = new ArrayList<Explosion>();
		soundTimer = 0;
		
		if(player == null)
		{
			player = new Player("Lora", tileMap, conversationState);
		}
		player.setMainMap(this);
		characterList.add(player);
		
		int index = 0;
		for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
		{
			if(currentMap.equals(GameStateManager.GameMaps.values()[i].toString()))
			{
				index = i;
			}
		}
		
		if(player.getLoading(index))
		{
			
			ArrayList<TemporaryItemData> itemDataArray = ItemData.getItemDataList(currentMap);
			for(int i = 0; i < itemDataArray.size(); i++)
			{
				spawnItem.spawn(itemDataArray.get(i));
			}
			
			ArrayList<TemporaryDoodadData> doodadDataArray = DoodadData.getDoodadDataList(currentMap);
			
			for(int i = 0; i < doodadDataArray.size(); i++)
			{
				spawnDoodad(doodadDataArray.get(i));
			}
			
			
			ArrayList<TemporaryUnitData> unitDataArray = CreateUnit.getUnitDataList(currentMap);
			
			for(int i = 0; i < unitDataArray.size(); i++)
			{
				spawnUnit.spawn(unitDataArray.get(i));
			}
		}
	}
	
	public void GameOverUpdate()
	{
		
		long elapsed = (System.nanoTime() - soundTimer) / 1000000;
		if(elapsed/1000 > 9)
		{
			JukeBox.stop("GameOver");
			loadMenu();
		}
	}
	
	public void loadMenu()
	{
		gameStateManager.setState(0);
	}
	
	public void checkProjectiles()
	{
		//********************************************************************************
		//*Projectile                                                                    *
		//********************************************************************************	
		for(int j = 0; j < projectiles.size(); j++)
		{
			Projectile projectile = projectiles.get(j);
			for(int k = 0; k < projectiles.size(); k++)
			{
				if(projectiles.get(j).getFriendly() != projectiles.get(k).getFriendly())
				{
					if(projectiles.get(j).intersects(projectiles.get(k)))
					{
						projectiles.get(j).setHit();
						projectiles.get(k).setHit();
					}
				}

			}
			
			for(int i = 0; i < characterList.size(); i++)
			{
				
				Unit character = characterList.get(i);
				if(character.getFriendly() != projectiles.get(j).getFriendly() && !character.getUntouchable() && !character.getInvulnerable())
				{
					character.checkProjectile(projectile);
					
					if(projectiles.get(j).intersects(character))
					{
						projectiles.get(j).setHit();
					}	
				}
			}
			
			for(int i = 0; i < stuff.size(); i++)
			{
				Doodad thing = stuff.get(i);
				if(thing.getDoodadType().equals("MagicShield"))
				{
					if(projectiles.get(j).intersects(thing))
					{
						projectiles.get(j).bounce();
					}
				}
			}
			
		}
	}
	
	
	public void addEffect(Doodad effect)
	{
		stuff.add(effect);
	}
	
	public void addProjectile(Projectile projectile)
	{
		projectiles.add(projectile);
	}
	
	public void addExplosion(Explosion explosion)
	{
		explosions.add(explosion);
	}
	
	public ArrayList<Projectile> getProjectiles()
	{
		return projectiles;
	}
	
	public ArrayList<Explosion> getExplosions()
	{
		return explosions;
	}
	
	public ArrayList<Unit> getCharacterList()
	{
		return characterList;
	}
	
	public ArrayList<Doodad> getDoodadList()
	{
		return stuff;
	}
	
	public void update()
	{
		if(!doneInitializing) return;
		// Update Characters
		
		displayHealthBars = player.getDisplayHealthBars();
		displayNamePlates = player.getDisplayNamePlates();
		
		if(raining)
		{
			double startX = player.getLocationX() - GamePanel.WIDTH;
			if(startX < 0) startX = 20;
			double endX = player.getLocationX() + GamePanel.WIDTH;
			
//			System.out.println((endX - startX) / 500);
			
			for(double i = startX; i < endX; i += 500)
			{
				if(RNG(1, 20) < 3)
				{
					spawnDoodad.spawnRainDrop(i + RNG(-400, 400), 0);
				}
			}
		}
		
		if(mousePressed)
		{
			player.setFacingRight(player.getLocationX() + tileMap.getX() < mouseLocationX);
			player.setRight(player.getFacingRight());
			player.setLeft(!player.getFacingRight());
		}

		
		if(gameover)
		{
			GameOverUpdate();
			return;
		}
		
		// Update Projectiles
		if(projectiles != null)
		{
			for(int i = 0; i < projectiles.size(); i++)
			{
				projectiles.get(i).update(characterList);
				if(projectiles.get(i).getRemoveMe())
				{
					projectiles.remove(i);
					i--;
				}
			}
		}
		checkProjectiles();
		
		// Update explosions
		if(explosions != null)
		{
			for(int i = 0; i < explosions.size(); i++)
			{
				explosions.get(i).update(characterList);
				if(explosions.get(i).getRemoveMe())
				{
					explosions.remove(i);
					i--;
				}
			}
		}
		
		// Update all characters
		if(characterList != null)
		{	
			for(int i = 0; i < characterList.size(); i++)
			{
				Unit character = characterList.get(i);
				
				if(character.getRemoveMe())
				{
					characterList.remove(character);
					i--;
				}
				
				else if(!character.isDead())
				{
					character.checkAttack(characterList);
					
					character.update(characterList);
					if(character != player)
					{
						character.updateAI(characterList);
					}
					
				}
				else
				{
					if(character != player)
					{
						Poff poff = new Poff(tileMap, this, characterList.get(i).getLocationX(), characterList.get(i).getLocationY());
						stuff.add(poff);
						
						int tempRows = character.getInventory().getNumberOfRows();
						int tempColumns = character.getInventory().getNumberOfColumns();
						
						Item items[][] = character.getInventory().getItems();
						
						for(int x = 0; x < tempRows; x++)
						{
							for(int y = 0; y < tempColumns; y++)
							{
								if(items[x][y] != null)
								{
									items[x][y].drop();
								}
							}
						}
						
						characterList.remove(i);
						i--;
					}
					else
					{
						// Player died...
						
						gameover = true;
						
						gameStateManager.stopMusic();
						
						JukeBox.play("GameOver");						
						soundTimer = System.nanoTime();
					}
				}
			}
		}
		
	// Update character
		if(characterList != null)
		{
			tileMap.setPosition(
					GamePanel.WIDTH / 2 - player.getLocationX(), 
					GamePanel.HEIGHT / 2 - player.getLocationY()
					);
		}
		
		// Update doodads
		if(stuff != null)
		{
			for(int i = 0; i < stuff.size(); i++)
			{
				if(stuff.get(i).getRemoveMe())
				{
					stuff.remove(i);
					i--;
				}
				else
				{
					stuff.get(i).update();					
				}
			}
		}
		
		if(items != null)
		{
			for(int i = 0; i < items.size(); i++)
			{
				if(items.get(i).getRemoveMe())
				{
					items.remove(i);
				}
				else
				{
					items.get(i).update();
				}
			}
		}
		
		// Set background
		if(background != null)
		{
			background.setPosition(tileMap.getX(), tileMap.getY());
		}
		
	}
	
	public void saveToRAM()
	{

		CreateUnit.resetUnitList(currentMap);
		DoodadData.resetDoodadList(currentMap);
		ItemData.resetItemList(currentMap);
		
		for(int i = 0; i < GameStateManager.GameMaps.values().length; i++)
		{
			if(currentMap.equals(GameStateManager.GameMaps.values()[i].toString()))
			{
				player.setLoading(i, true);
			}
		}
		
		for(int i = 0; i < items.size(); i++)
		{
			
			Item item = items.get(i);
			if(item.getInWorld())
			{
				TemporaryItemData itemData = new TemporaryItemData
					(
						item.getItemType(),
						item.getStacks(),
						item.getLocationX(), 
						item.getLocationY()
					);
				ItemData.addItem(currentMap, itemData);
			}
		}
		
		
		for(int i = 0; i < alterableDoodads.size(); i++)
		{
			Doodad thing = alterableDoodads.get(i);
			
			ArrayList<Item> tempItems = new ArrayList<Item>();
			for(int y = 0; y < thing.getInventory().getNumberOfRows(); y++)
			{
				for(int x = 0; x < thing.getInventory().getNumberOfColumns(); x++)
				{
					if(thing.getInventory().getItem(x, y) != null)
					{
						tempItems.add(thing.getInventory().getItem(x, y));
					}
				}
			}
			
			TemporaryDoodadData doodadData = new TemporaryDoodadData
				(
					thing.getUntouchable(),
					thing.getInvulnerable(), 
					thing.getActive(), 
					thing.getCurrentAction(), 
					thing.getLocked(), 
					thing.getSpawnLocationX(),
					thing.getSpawnLocationY(),
					thing.getDoodadType(),
					thing.getUniqueID(),
					tempItems
				);
			
			DoodadData.addDoodad(currentMap, doodadData);
		}
		
		
		for(int i = 0; i < characterList.size(); i++)
		{
			Unit unit = characterList.get(i);
			if(unit != player)
			{
				ArrayList<Item> tempItems = new ArrayList<Item>();
				for(int y = 0; y < unit.getInventory().getNumberOfRows(); y++)
				{
					for(int x = 0; x < unit.getInventory().getNumberOfColumns(); x++)
					{
						if(unit.getInventory().getItem(x, y) != null)
						{
							tempItems.add(unit.getInventory().getItem(x, y));
						}
					}
				}
				
				TemporaryUnitData unitData = new TemporaryUnitData
				(
					unit.getFacingRight(), 
					unit.getFriendly(), 
					unit.getUntouchable(), 
					unit.getInvulnerable(), 
					unit.getUnkillable(), 
					unit.getName(),
					(int)unit.getHealth(),
					unit.getSpawnLocationX(), 
					unit.getSpawnLocationY(),
					unit.getUnitType(),
					unit.getSilver(),
					unit.getGold(),
					tempItems
				);
				
				CreateUnit.addUnit(currentMap, unitData);
			}

		}
	}
	

	
	public void draw(Graphics2D graphics)
	{
		if(!doneInitializing) return;

		if(gameover)
		{
			gameoverScreen.draw(graphics);
			return;
		}
		
		
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		
		// Draw brackground
		if(background != null)
		{
			background.draw(graphics);			
		}
		
		
		
		// Draw tilemap
		if(tileMap != null)
		{
			
			tileMap.draw(graphics);
		}
		
		
		if(stuff != null)
		{
			for(int i = 0; i < stuff.size(); i++)
			{
				try
				{
					stuff.get(i).draw(graphics);					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		if(items != null)
		{
			try
			{
				for(int i = 0; i < items.size(); i++)
				{
					if(items.get(i) != null)
					{
						items.get(i).draw(graphics);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		// Draw Characters!
		if(characterList != null)
		{
			try
			{
				for(int i = 0; i < characterList.size(); i++)
				{
					Unit unit = characterList.get(i);
					unit.draw(graphics);
					if(player.getDisplayHealthBars())
					{
						unit.drawHealthBars(graphics);
					}
					
					if(displayHealthBars)
					{
						unit.drawHealthBars(graphics);
					}
					if(displayNamePlates)
					{
						unit.drawNamePlate(graphics);
					}
				}
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}

		// Draw Projectiles
		for(int i = 0; i < projectiles.size(); i++)
		{
			projectiles.get(i).draw(graphics);
		}
		
		// Draw Explosions
		for(int i = 0; i < explosions.size(); i++)
		{
			explosions.get(i).draw(graphics);
		}
		
		
		// Draw HUD		
		if(player.getHUD() != null)
		{
			player.getHUD().draw(graphics);			
		}
	}
	
	public int RNG(int min, int max)
	{
		if(max < min)
		{
			return -1;
		}
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}
	
	public Doodad spawnDoodad(TemporaryDoodadData doodadData)
	{
		Doodad doodad = null;
		
		for(int i = 0; i < DoodadData.Doors.values().length; i++)
		{
			if(doodadData.getDoodadType().equals(DoodadData.Doors.values()[i].toString()))
			{
				doodad = spawnDoodad.spawnDoor
					(
						doodadData.getSpawnLocationX(),
						doodadData.getSpawnLocationY(),
						doodadData.getLocked(),
						doodadData.getUniqueID(),
						doodadData.getCurrentAction(),
						doodadData.getDoodadType()
					);
			}
		}
		
		for(int i = 0; i < DoodadData.Chests.values().length; i++)
		{
			if(doodadData.getDoodadType().equals(DoodadData.Chests.values()[i].toString()))
			{
				doodad = spawnDoodad.spawnChest
					(
						doodadData.getSpawnLocationX(),
						doodadData.getSpawnLocationY(),
						doodadData.getLocked(),
						doodadData.getCurrentAction(),
						doodadData.getDoodadType()
					);
			}
		}
		
		if(doodadData.getDoodadType().equals(DoodadData.Other.Lever.toString()))
		{
			doodad = spawnDoodad.spawnLever
				(
					doodadData.getSpawnLocationX(),
					doodadData.getSpawnLocationY(),
					doodadData.getUniqueID(),
					doodadData.getCurrentAction()
				);
		}
		
		if(doodad != null)
		{
			for(int i = 0; i < doodadData.getItems().size(); i++)
			{
				Item item = doodadData.getItems().get(i);
				doodad.getInventory().addItem(item);
				items.add(item);
			}
			return doodad;
		}
		
		return null;
	}
	
	
	

	
	public void dropPotion(String potionType, int chance, int stacks, MapObject owner)
	{
		int oneToOneHundred = RNG(0, 100);
		int potionDropped = RNG(1,3);
		
		try
		{
			if(oneToOneHundred <= chance)
			{
				Potion potion = null;
				if(!potionType.equals("Any"))
				{
					potion = new Potion(tileMap, this, false, 0, 0, owner, stacks, potionType);
				}
				else
				{
					if(potionDropped == 1)
					{
						potion = new Potion(tileMap, this, false, 0, 0, owner, stacks, ItemData.Potions.Healing.toString());
					}
					else if(potionDropped == 2)
					{
						potion= new Potion(tileMap, this, false, 0, 0, owner, stacks, ItemData.Potions.Mana.toString());
					}
					else if(potionDropped == 3)
					{
						potion = new Potion(tileMap, this, false, 0, 0, owner, stacks, ItemData.Potions.Stamina.toString());
					}
				}
				if(potion == null)
				{
					return;
				}
				owner.getInventory().addItem(potion);
				items.add(potion);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void dropKey(String keyType, int chance, Unit owner)
	{
		int oneToOneHundred = RNG(0, 100);
		if(oneToOneHundred <= chance)
		{
			Key key = new Key(tileMap, this, false, 0, 0, owner, 1, keyType);

			owner.getInventory().addItem(key);
			items.add(key);
		}
	}
	
	public void dropCoin(String coinType, int chance, int stacks, MapObject owner)
	{
		int oneToOneHundred = RNG(0, 100);
		if(oneToOneHundred <= chance)
		{
			Coin coin = new Coin(tileMap, this, false, 0, 0, owner, stacks, coinType);

			owner.getInventory().addItem(coin);
			items.add(coin);
		}
	}
	
	// useDoodad differs from interact in that interact occurs whenever you interact with any object, 
	// useDoodad only occurs if you successfully use the doodad. 
	// Not all interactive doodads are usable (at the moment only the lever is).
	public void useDoodad(Doodad doodad)
	{
		// Runs the useDoodad in the map that inherits this class.
	}
	
	public void useUnit(Unit unit)
	{
		// Runs the useUnit in the map that inherits this class.
	}
	
	public void interact()
	{
		if(holdingInteractButton)
		{
			return;
		}
		holdingInteractButton = true;
		if(player.getInConversation())
		{
			player.getConversationState().progressConversation();
		}
		for(int i = 0; i < activatables.size(); i++)
		{
			if(player.intersects(activatables.get(i)))
			{
				activatables.get(i).interact(player);
			}
		}
		
		for(int i = 0; i < items.size(); i++)
		{
			if(player.intersects(items.get(i)))
			{
				items.get(i).interact(player);
			}
		}
		
		for(int i = 0; i < characterList.size(); i++)
		{
			if(!characterList.get(i).getSpawning() && !characterList.get(i).getDeSpawning())
			{
				if(player.intersects(characterList.get(i)) && characterList.get(i).getActivatable())
				{
					characterList.get(i).interact(player);
				}
			}
		}
	}
	
	public GameStateManager getGameStateManager()
	{
		return gameStateManager;
	}
	
	public void GPS()
	{
		System.out.println("player X: " + player.getLocationX() + ", playerY: " + player.getLocationY());
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ESCAPE) 
		{
			if(gameover)
			{
				gameStateManager.setState(GameStateManager.MENUSTATE);
				return;
			}
			gameStateManager.setPaused(!gameStateManager.getPaused());
			gameStateManager.setBrowsingInventory(false);
		}
		
		if(key == player.getKeyBind(Player.KeyBind.OpenInventory))
		{
			if(player.getInConversation())
			{
				return;
			}
			gameStateManager.setBrowsingInventory(!gameStateManager.getBrowsingInventory());
			gameStateManager.setPaused(gameStateManager.getBrowsingInventory());
		}
		
		if(gameStateManager.getPaused()) return;
		
		if(key == player.getKeyBind(Player.KeyBind.MoveLeft)) player.setLeft(true);
		if(key == player.getKeyBind(Player.KeyBind.MoveRight)) player.setRight(true);
		if(key == player.getKeyBind(Player.KeyBind.AimDown)) player.setDown(true);
		if(key == player.getKeyBind(Player.KeyBind.AimUp)) player.setUp(true);
		if(key == player.getKeyBind(Player.KeyBind.Jump)) player.setJumping(true);
		if(key == player.getKeyBind(Player.KeyBind.Interact)) interact();
		if(key == player.getKeyBind(Player.KeyBind.Glide))player.setGliding(true); 
		if(key == player.getKeyBind(Player.KeyBind.Run))
		{
			player.startRunning();
		}
		
		if(key == player.getKeyBind(Player.KeyBind.CastSmallFireBall)) player.setCastingSmallFireBall();
		if(key == player.getKeyBind(Player.KeyBind.CastLargeFireBall)) player.setCastingLargeFireBall();
		if(key == player.getKeyBind(Player.KeyBind.CastPunch)) player.setPunching();
		if(key == player.getKeyBind(Player.KeyBind.CastDash)) player.setDashing(true);
		
		if(key == player.getKeyBind(Player.KeyBind.UseItem1)) player.useItem(0, 0);
		if(key == player.getKeyBind(Player.KeyBind.UseItem2)) player.useItem(1, 0);
		if(key == player.getKeyBind(Player.KeyBind.UseItem3)) player.useItem(2, 0);
		if(key == player.getKeyBind(Player.KeyBind.UseItem4)) player.useItem(3, 0);
		if(key == player.getKeyBind(Player.KeyBind.UseItem5)) player.useItem(4, 0);
		
		if(key == player.getKeyBind(Player.KeyBind.UseItem6)) player.useItem(0, 1);
		if(key == player.getKeyBind(Player.KeyBind.UseItem7)) player.useItem(1, 1);
		if(key == player.getKeyBind(Player.KeyBind.UseItem8)) player.useItem(2, 1);
		if(key == player.getKeyBind(Player.KeyBind.UseItem9)) player.useItem(3, 1);
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_J)
		{
			Entity.Item.Bomb bomb = new Entity.Item.Bomb(tileMap, this, false, 0, 0, player, 1, ItemData.Bombs.Regular);
			player.getInventory().addItem(bomb);
			items.add(bomb);
		}
		
		
		if(key == KeyEvent.VK_G) player.setCastingMagicShield();
		if(key == KeyEvent.VK_H) player.emoteExclamation();
		if(key == KeyEvent.VK_U)
		{
			for(int i = 0; i < characterList.size(); i++)
			{
				if(characterList.get(i).getName().equals("Fiona"))
				{
					characterList.get(i).hit(9001, player);
				}
			}
		}
		
		if(key == KeyEvent.VK_T)
		{
			JukeBox.setVolume("Rain", 10);
		}
		
		if(key == KeyEvent.VK_Y)
		{
			if(!raining)
			{
				raining = true;
				int RNG = RNG(1, numberofSounds[0]);
				if(RNG == -1)
					return;
				JukeBox.loop(soundTypes.Rain.toString() + (RNG < 10 ? "0" : "") + RNG);	
			}
			else
			{
				raining = false;
				for(int i = 0; i < numberofSounds[0]; i++)
				{
					JukeBox.stop(soundTypes.Rain.toString() + (i < 10 ? "0" : "") + i);
				}
				
			}
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_I)
		{
			Bag myBag = new Bag(tileMap, this, false, 0, 0, player, 1, ItemData.Bags.Small);
			player.getInventory().addItem(myBag);
			items.add(myBag);
		}
		
		
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_M)
		{
			if(player.getFacingRight())
			{
				player.setPosition(player.getLocationX() + 200, player.getLocationY());
			}
			else
			{
				player.setPosition(player.getLocationX() - 200, player.getLocationY()); 
			}
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_N)
		{
			player.setPosition(player.getLocationX(), player.getLocationY() - 200);
		}

		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_L)
		{
			Key myKey = new Key(tileMap, this, false, 0, 0, player, 1, "Boss");
			player.getInventory().addItem(myKey);
			items.add(myKey);
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_K)
		{
			Key myKey = new Key(tileMap, this, false, 0, 0, player, 1, "Uncommon");
			player.getInventory().addItem(myKey);
			items.add(myKey);
		}
		
		// Note: This is a built in cheat that is not supposed to be used to get the real game experience.
		if(key == KeyEvent.VK_P)
		{
			Potion myPotion = new Potion(tileMap, this, false, 0, 0, player, 1, ItemData.Potions.Healing.toString());
			player.getInventory().addItem(myPotion);
			items.add(myPotion);
		}
		
		if(key == KeyEvent.VK_G) GPS(); 
	}
	
	
	public void keyReleased(int key)
	{
		if(key == player.getKeyBind(Player.KeyBind.MoveLeft)) player.setLeft(false);
		if(key == player.getKeyBind(Player.KeyBind.MoveRight)) player.setRight(false);
		if(key == player.getKeyBind(Player.KeyBind.AimDown)) player.setDown(false);
		if(key == player.getKeyBind(Player.KeyBind.Jump)) player.setJumping(false);
		if(key == player.getKeyBind(Player.KeyBind.AimUp)) player.setUp(false);
		if(key == player.getKeyBind(Player.KeyBind.Glide)) player.setGliding(false);
		if(key == player.getKeyBind(Player.KeyBind.Interact)) holdingInteractButton = false;
		if(key == player.getKeyBind(Player.KeyBind.Run))
		{
			player.setRunning(false);
		}
	}


	public void mouseClicked(MouseEvent mouse) 
	{
	}


	public void mouseEntered(MouseEvent mouse) 
	{
		
	}

	public void mouseExited(MouseEvent mouse) 
	{
		
	}

	public void mousePressed(MouseEvent mouse) 
	{
		player.mousePressed(mouse);
		mousePressed = true;
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		mousePressed = false;
		player.setRight(false);
		player.setLeft(false);
	}

	public void mouseMoved(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
		
		player.mouseMoved(mouse);
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
		
		player.mouseDragged(mouse);
	}
}
