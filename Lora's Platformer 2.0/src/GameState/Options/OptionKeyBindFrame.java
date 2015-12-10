package GameState.Options;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Entity.Player.Player;

@SuppressWarnings("serial")
public class OptionKeyBindFrame extends JFrame
{
	
//	protected boolean settingKeyBinding = false;
	protected int settingKeyBinding = -1;
	
	ArrayList<JButton> buttons;
	
	JButton castFireBallSmallButton;
	JButton castFireBallLargeButton;
	
	protected Player player;
	
	
	public OptionKeyBindFrame(Player player)
	{
		this.player = player;
		

		setSize(600, 400);

		requestFocus();
		setLayout(null);
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		JLabel newLabel = new JLabel("Cast small fireball: ");
		labels.add(newLabel);
		newLabel = new JLabel("Cast large fireball: ");
		labels.add(newLabel);
		newLabel = new JLabel("Dash: ");
		labels.add(newLabel);
		newLabel = new JLabel("Punch: ");
		labels.add(newLabel);
		newLabel = new JLabel("Jump: ");
		labels.add(newLabel);
		newLabel = new JLabel("Open inventory: ");
		labels.add(newLabel);

		buttons = new ArrayList<JButton>();		

		
		for(int i = 0; i < labels.size(); i++)
		{
			labels.get(i).setSize(150, 20);
			labels.get(i).setLocation(20, 30 * i);
			JButton button = new JButton(KeyEvent.getKeyText(player.getKeyBind(i)));
			buttons.add(button);
			button.setLocation(20 + labels.get(i).getWidth() + 10, 30 * i - 5);
			button.setSize(40, 30);
			button.setFocusable(true);
			
			button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent event) 
				{
					System.out.println("Performing an action!");
					for(int i = 0; i < buttons.size(); i++)
					{
						if(event.getSource() == buttons.get(i))
						{
							settingKeyBinding = i;
							System.out.println("settingKeyBinding: " + i);
						}
					}	
				}
			});
			
			button.addKeyListener(new KeyListener()
			{
				public void keyPressed(KeyEvent e) 
				{
					
					System.out.println("KIRBY! <(^-^)>");
					buttons.get(settingKeyBinding).setText( Character.toUpperCase(e.getKeyChar()) + "");
					System.out.println("Key code: " + e.getKeyCode());
					getPlayer().setKeyBind(settingKeyBinding, e.getKeyCode());
				}
				public void keyReleased(KeyEvent e) {} public void keyTyped(KeyEvent e) {}
			});
			
			add(buttons.get(i));
			add(labels.get(i));
			

		}
		
		setVisible(true);
	}
	
	protected Player getPlayer() { return player; }
}
