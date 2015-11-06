package GameState;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import TileMap.Background;

public class OptionState extends GameState implements ChangeListener
{
	private Background background;
	
	public OptionState(GameStateManager gameStateManager)
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
	
		this.gameStateManager = gameStateManager;
		
		try
		{
			background = new Background(getClass().getResource("/Art/HUD/Foregrounds/ScreenPaused.png"), 0);
			background.setVector(0, 0);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	public void update() 
	{
		System.out.println("Updating options");
	}

	public void draw(Graphics2D graphics) 
	{
		background.draw(graphics);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}


	public void stateChanged(ChangeEvent e) 
	{
		
	}

}
