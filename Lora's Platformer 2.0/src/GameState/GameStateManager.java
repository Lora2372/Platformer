package GameState;

import Audio.JukeBox;

public class GameStateManager 
{
	protected PauseState pausestate;
	protected HelpState helpstate;
	
	protected boolean paused;
	protected boolean options;
	
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 2;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	
	public GameStateManager() 
	{
		pausestate = new PauseState(this);
		helpstate = new HelpState(this);
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	public void pause(boolean b) { paused = b; } 
	public void options(boolean b) { options = b; }
	
	private void loadState(int state) 
	{
		if(state == MENUSTATE)
		{
			JukeBox.stop("Battle9");
			
			
			gameStates[state] = new MenuState(this);

		}
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
	}
	
	private void unloadState(int state) 
	{
		gameStates[state] = null;
	}
	
	public void setState(int state) 
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
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









