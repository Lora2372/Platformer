package GameState;

import java.awt.event.MouseEvent;

import Audio.JukeBox;
import Entity.Player.Player;
import GameState.Conversation.ConversationState;
import GameState.Inventory.InventoryState;
import GameState.Maps.*;
import GameState.Options.OptionState;
import Main.Content;
import Main.GamePanel;
import TileMap.TileMap;

public class GameStateManager 
{
	protected PauseState pausestate;
	protected OptionState optionstate;
	protected InventoryState inventorystate;
	protected ConversationState conversationState;
	
	protected boolean paused;
	protected boolean browsingInventory;
	protected boolean options;
	
	
	private GameState[] gameStates;
	private int currentState;
	
	
	public static enum GameMaps
	{
		Tutorial,
		LorasCavern,
		MysteriousDungeon,
		FionasSanctum,
		DeepWoods
	}
	
	public static final int NUMGAMESTATES = GameMaps.values().length + 1;
	public static final int MENUSTATE = 0;
	public static final int TutorialState = 1;
	public static final int LorasCavern = 2;
	public static final int MysteriousDungeon = 3;
	public static final int FionasSanctum = 4;
	public static final int DeepWoods = 5;
	

	
	protected Player player;
	protected TileMap tileMap;
	
	public GameStateManager(GamePanel gamePanel) 
	{
		pausestate = new PauseState(this);
		optionstate = new OptionState(this, gamePanel);
		
		inventorystate = new InventoryState(this);
		
		conversationState = new ConversationState(this);
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
	}
	
	public void setPlayer(Player player) 
	{ 
		this.player = player;
		optionstate.setPlayer(player);
		inventorystate.initialize(player);
		conversationState.initialize(player);
	}
	
	public void setTileMap(TileMap tileMap) { this.tileMap = tileMap; }
	
	public void setBrowsingInventory(boolean b) { browsingInventory = b; }
	public boolean getBrowsingInventory() { return browsingInventory; }
	public void pause(boolean b) { paused = b; }
	public boolean getPaused() { return paused; }
	public void options(boolean well)
	{ 
		options = well;
	}

	
	private void loadState(int state) 
	{
		if(state == TutorialState)
		{
			JukeBox.loop("Tutorial");
			gameStates[state] = new Tutorial(this, tileMap, player, conversationState);		
		}
		else if(state == MENUSTATE)
		{		
			JukeBox.loop("Menu");
			gameStates[state] = new MenuState(this, conversationState);
			
		}
		else if(state == LorasCavern)
		{
			JukeBox.loop(GameStateManager.GameMaps.LorasCavern.toString());
			gameStates[state] = new LorasCavern(this, tileMap, player, conversationState);
		}
		else if(state == MysteriousDungeon)
		{
			JukeBox.loop(GameStateManager.GameMaps.MysteriousDungeon.toString());
			gameStates[state] = new MysteriousDungeon(this, tileMap, player, conversationState);
		}
		else if(state == FionasSanctum)
		{
			JukeBox.loop("FionasSanctum");
			gameStates[state] = new FionasSanctum(this, tileMap, player, conversationState);
		}
		else if(state == DeepWoods)
		{
			JukeBox.loop("DeepWoods");
			gameStates[state] = new DeepWoods(this, tileMap, player, conversationState);			
		}
	}
	
	public void stopMusic()
	{
		for(int i = 0; i < Content.mapMusic.values().length; i++)
		{
			JukeBox.stop(Content.mapMusic.values()[i] + "");
		}
	}
	
	
	private void unloadState(int state) 
	{
		stopMusic();
		gameStates[state].reset();
		gameStates[state] = null;
	}
	
	public void setState(int state)
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public GameState getState(int whichState) 
	{ 
		return gameStates[whichState]; 
	}
	
	public int getCurrentState() 
	{ 
		return currentState; 
	}
	
	
	public GameState[] getStates()
	{
		return gameStates;
	}
	
	public void update() 
	{
		try 
		{
			
			if(options)
			{
				optionstate.update();
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.update();
				}
				else
				{
					pausestate.update();
				}
				
				if(player.getInConversation())
				{
					conversationState.update();
				}
				
				return;
			}
			

			
			if(player.getInConversation())
			{
				conversationState.update();
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].update();
			}
			
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	public void draw(java.awt.Graphics2D graphics) 
	{
		try 
		{
			
			if(options)
			{
				optionstate.draw(graphics);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.draw(graphics);
				}
				else				
				{
					pausestate.draw(graphics);
				}
				
				if(player.getInConversation())
				{
					conversationState.draw(graphics);
				}
				
				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].draw(graphics);
			}
			
			if(player.getInConversation())
			{
				conversationState.draw(graphics);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	public void keyPressed(int key) 
	{
		try 
		{
			if(options)
			{
				optionstate.keyPressed(key);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.keyPressed(key);
				}
				else				
				{
					pausestate.keyPressed(key);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].keyPressed(key);
			}
			if(player.getInConversation())
			{
				conversationState.keyPressed(key);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	public void keyReleased(int key) 
	{
		try 
		{
			if(options)
			{
				optionstate.keyReleased(key);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.keyReleased(key);
				}
				else				
				{
					pausestate.keyReleased(key);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].keyReleased(key);
			}
			if(player.getInConversation())
			{
				conversationState.keyReleased(key);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	
	public void mouseClicked(MouseEvent mouse) 
	{
		try 
		{
			if(options)
			{
				optionstate.mouseClicked(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseClicked(mouse);
				}
				else				
				{
					pausestate.mouseClicked(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseClicked(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mouseClicked(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	public void mouseEntered(MouseEvent mouse) 
	{
		try 
		{
			if(options)
			{
				optionstate.mouseEntered(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseEntered(mouse);
				}
				else				
				{
					pausestate.mouseEntered(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseEntered(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mouseEntered(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	public void mouseExited(MouseEvent mouse) 
	{
		try 
		{
			if(options)
			{
				optionstate.mouseExited(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseExited(mouse);
				}
				else				
				{
					pausestate.mouseExited(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseExited(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mouseExited(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	public void mousePressed(MouseEvent mouse) 
	{
		try 
		{
			if(options)
			{
				optionstate.mousePressed(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mousePressed(mouse);
				}
				else				
				{
					pausestate.mousePressed(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mousePressed(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mousePressed(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		try 
		{
			if(options)
			{
				optionstate.mouseReleased(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseReleased(mouse);
				}
				else				
				{
					pausestate.mouseReleased(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseReleased(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mouseReleased(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	public void mouseMoved(MouseEvent mouse)
	{
		try 
		{
			if(options)
			{
				optionstate.mouseMoved(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseMoved(mouse);
				}
				else				
				{
					pausestate.mouseMoved(mouse);
				}
				

				return;
			}
			
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseMoved(mouse);
			}
			if(player.getInConversation())
			{
				conversationState.mouseMoved(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
	
	public void mouseDragged(MouseEvent mouse)
	{
		try 
		{
			if(options)
			{
				optionstate.mouseDragged(mouse);
				return;
			}
			
			if(paused)
			{
				if(browsingInventory)
				{
					inventorystate.mouseDragged(mouse);
				}
				else				
				{
					pausestate.mouseDragged(mouse);
				}
				

				return;
			}
			if(gameStates[currentState] != null)
			{
				gameStates[currentState].mouseDragged(mouse);
			}
			
			
			if(player.getInConversation())
			{
				conversationState.mouseDragged(mouse);
			}
			
		} catch(Exception exception) 
		{
			exception.printStackTrace();
		}
	}
}