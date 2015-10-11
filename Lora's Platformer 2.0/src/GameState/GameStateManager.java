package GameState;

import Audio.JukeBox;
import Entity.Player.Player;
import Main.Content;
import TileMap.TileMap;

public class GameStateManager 
{
	protected PauseState pausestate;
	protected HelpState helpstate;
	
	protected boolean paused;
	protected boolean options;
	
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 4;
	public static final int MENUSTATE = 0;
	public static final int LorasCavern = 1;
	public static final int MysteriousDungeon = 2;
	public static final int DeepWoods = 3;
	
	protected Player player;
	protected TileMap tileMap;
	
	public GameStateManager() 
	{
		pausestate = new PauseState(this);
		helpstate = new HelpState(this);
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	public void setPlayer(Player player) { this.player = player; }
	public void setTileMap(TileMap tileMap) { this.tileMap = tileMap; }
	
	public void pause(boolean b) { paused = b; } 
	public void options(boolean b) { options = b; }
	
	private void loadState(int state) 
	{
		if(state == MENUSTATE)
		{				
			gameStates[state] = new MenuState(this);
			JukeBox.loop("Menu");
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
			if(!options)pausestate.keyPressed(k);
			else helpstate.keyPressed(k);
			
			return;		
		}
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) 
	{
		gameStates[currentState].keyReleased(k);
	}	
}