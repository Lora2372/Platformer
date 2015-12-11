package GameState.Options;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Entity.Player.Player;

@SuppressWarnings("serial")
public class OptionKeyBindFrame extends JDialog
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
		
		int width = 600;
		int height = 400;

		int componentHeight = 30;
		int componentSpacing = 5;
		
		setSize(width, height);

		requestFocus();
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize( (int) (width * 0.67), player.getKeyBinds().length * (componentHeight + componentSpacing) + 20 );
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension( panel.getWidth(), panel.getHeight()));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0, panel.getWidth(), height);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		JLabel newLabel = new JLabel("Move left:");
		labels.add(newLabel);
		newLabel = new JLabel("Move right:");
		labels.add(newLabel);
		newLabel = new JLabel("Aim down/move down:");
		labels.add(newLabel);
		newLabel = new JLabel("Aim up/move up:");
		labels.add(newLabel);
		newLabel = new JLabel("Jump:");
		labels.add(newLabel);
		newLabel = new JLabel("Interact/progress conversation:");
		labels.add(newLabel);
		newLabel = new JLabel("Glide:");
		labels.add(newLabel);
		newLabel = new JLabel("Run:");
		labels.add(newLabel);
		newLabel = new JLabel("Cast small fireball:");
		labels.add(newLabel);
		newLabel = new JLabel("Cast large fireball:");
		labels.add(newLabel);
		newLabel = new JLabel("Dash:");
		labels.add(newLabel);
		newLabel = new JLabel("Punch:");
		labels.add(newLabel);
		
		for(int i = 1; i < 10; i++)
		{
			newLabel = new JLabel("Use inventory slot " + i + ":");
			labels.add(newLabel);
		}
		
		newLabel = new JLabel("Open inventory:");
		labels.add(newLabel);
		
		buttons = new ArrayList<JButton>();
		
		
		for(int i = 0; i < labels.size(); i++)
		{
			labels.get(i).setSize(150, 20);
			labels.get(i).setLocation(20, (componentHeight + componentSpacing) * i);
			JButton button = new JButton(KeyEvent.getKeyText(player.getKeyBind(i)));
			buttons.add(button);
			button.setLocation(20 + labels.get(i).getWidth() + 10, labels.get(i).getLocation().y-5);
			button.setSize(120, 30);
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
						}
					}	
				}
			});
			
			button.addKeyListener(new KeyListener()
			{
				public void keyPressed(KeyEvent e) 
				{
					if(settingKeyBinding != -1)
					{
						buttons.get(settingKeyBinding).setText( Character.toUpperCase(e.getKeyChar()) + "");
						getPlayer().setKeyBind(settingKeyBinding, e.getKeyCode());
						settingKeyBinding = -1;
					}
				}
				public void keyReleased(KeyEvent e) {} public void keyTyped(KeyEvent e) {}
			});
			
			panel.add(buttons.get(i));
			panel.add(labels.get(i));
			

		}
		
		JButton exitButton = new JButton("Exit");
		exitButton.setSize(120, 30);
		exitButton.setLocation(panel.getWidth() + panel.getLocation().x + ( (width / 3) / 2 )  - (exitButton.getWidth() / 2), height - exitButton.getHeight() - 40);
		add(exitButton);
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	protected Player getPlayer() { return player; }
}
