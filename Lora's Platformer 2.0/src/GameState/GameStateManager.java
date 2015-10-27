package GameState;

import Audio.JukeBox;
import Entity.Player.Player;
import GameState.Inventory.InventoryState;
import GameState.Maps.DeepWoods;
import GameState.Maps.LorasCavern;
import GameState.Maps.MysteriousDungeon;
import GameState.Maps.Tutorial;
import Main.Content;
import TileMap.TileMap;

public class GameStateManager 
{
	protected PauseState pausestate;
	protected HelpState helpstate;
	protected InventoryState inventorystate;
	
	protected boolean paused;
	protected boolean browsingInventory;
	protected boolean options;
	
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 5;
	public static final int MENUSTATE = 0;
	public static final int TutorialState = 1;
	public static final int LorasCavern = 2;
	public static final int MysteriousDungeon = 3;
	public static final int DeepWoods = 4;
	
	protected Player player;
	protected TileMap tileMap;
	
	public GameStateManager() 
	{
		pausestate = new PauseState(this);
		helpstate = new HelpState(this);
		inventorystate = new InventoryState(this);
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	public void setPlayer(Player player) { this.player = player; }
	public void setTileMap(TileMap tileMap) { this.tileMap = tileMap; }
	
	public void browsingInventory(boolean b) { browsingInventory = b; }
	public boolean getBrowsingInventory() { return browsingInventory; }
	public void pause(boolean b) { paused = b; }
	public boolean getPaused() { return paused; }
	public void options(boolean b) { options = b; }
	
	private void loadState(int state) 
	{
		if(state == TutorialState)
		{
			JukeBox.loop("Tutorial");
			gameStates[state] = new Tutorial(this, tileMap, player);
		}
		else if(state == MENUSTATE)
		{		
			JukeBox.loop("Menu");
			gameStates[state] = new MenuState(this);
		}
		else if(state == LorasCavern)
		{
			JukeBox.loop("LorasCavern");
			gameStates[state] = new LorasCavern(this, tileMap, player);
		}
		else if(state == MysteriousDungeon)
		{
			JukeBox.loop("MysteriousDungeon");
			gameStates[state] = new MysteriousDungeon(this, tileMap, player);			
		}
		else if(state == DeepWoods)
		{
			JukeBox.loop("DeepWoods");
			gameStates[state] = new DeepWoods(this, tileMap, player);			
		}
	}
	
	private void unloadState(int state) 
	{
		for(int i = 0; i < Content.mapMusic.values().length; i++)
		{
			JukeBox.stop(Content.mapMusic.values()[i] + "");
		}
		gameStates[state] = null;
	}
	
	public void setState(int state) 
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public int getState() { return currentState; }
	
	public void update() 
	{
		try {
			if(paused)
			{
				if(browsingInventory)
					inventorystate.update();
				else
					pausestate.update();
				
				if(options){ helpstate.update(); }
				return;
			}			
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D graphics) 
	{
		try 
		{
			if(paused)
			{
				if(browsingInventory)
					inventorystate.draw(graphics);
				else				
					pausestate.draw(graphics);
				
				if(options) helpstate.draw(graphics);
				return;
			}
			gameStates[currentState].draw(graphics);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) 
	{
		if(paused)
		{
			if(browsingInventory)
				inventorystate.keyPressed(k);
			else 
			if
				(!options)pausestate.keyPressed(k);
			else 
				helpstate.keyPressed(k);
			
			return;		
		}
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) 
	{
		if(gameStates[currentState] != null)
			gameStates[currentState].keyReleased(k);
	}	
}