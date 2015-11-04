package GameState;

import java.awt.event.MouseEvent;

public abstract class GameState 
{
	protected GameStateManager gameStateManager;
	
	public abstract void initialize();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void mouseClicked(MouseEvent mouse);
	public abstract void mouseEntered(MouseEvent mouse);
	public abstract void mouseExited(MouseEvent mouse);
	public abstract void mousePressed(MouseEvent mouse);
	public abstract void mouseReleased(MouseEvent mouse);
}
