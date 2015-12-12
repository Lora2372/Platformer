package GameState.Options;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Entity.Player.Player;

@SuppressWarnings("serial")
public class OptionKeyBindFrame extends JFrame
{
	
//	protected boolean settingKeyBinding = false;
	protected int settingKeyBinding = -1;
	
	protected ArrayList<JButton> buttons;
	
	protected JButton castFireBallSmallButton;
	protected JButton castFireBallLargeButton;
	
	protected Player player;
	
	protected JDialog optionKeyBindDialog;
	
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
		
	    optionKeyBindDialog = new JDialog(this,"",Dialog.ModalityType.APPLICATION_MODAL)
	    {
	    	@Override
	        public void dispose()
	    	{
	    		super.dispose();
	    	}
	    };
	    
	    JPanel outerPanel = new JPanel();
	    outerPanel.setSize(width, height);
	    outerPanel.setLayout(null);
	    outerPanel.setPreferredSize(new Dimension(width, height));
	    
		JLabel label = new JLabel("Edit keybindings below.");
	    label.setSize(label.getPreferredSize());
	    
		outerPanel.add(label);
	    
		JPanel keybindPanel = new JPanel();
		keybindPanel.setSize( (int) (width - 40), player.getKeyBinds().length * (componentHeight + componentSpacing) );
		keybindPanel.setLayout(null);
		keybindPanel.setPreferredSize(new Dimension( keybindPanel.getWidth(), keybindPanel.getHeight()));
		JScrollPane scrollPane = new JScrollPane(keybindPanel);
		scrollPane.setBounds(0, 0, keybindPanel.getWidth(), height - 100);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setLocation(20, 20 + label.getHeight());
		outerPanel.add(scrollPane);
		
		label.setLocation(scrollPane.getWidth() / 2 - label.getWidth() / 2, scrollPane.getLocation().y - label.getHeight());

		
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
			labels.get(i).setSize(labels.get(i).getPreferredSize());
			labels.get(i).setLocation(20, (componentHeight + componentSpacing) * i);
			JButton button = new JButton(KeyEvent.getKeyText(player.getKeyBind(i)));
			buttons.add(button);
			button.setSize(120, 30);
			button.setLocation(scrollPane.getWidth() - button.getWidth() - 20, labels.get(i).getLocation().y);
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
			
			keybindPanel.add(buttons.get(i));
			keybindPanel.add(labels.get(i));
		}

		JButton exitButton = new JButton("Exit");
		exitButton.setSize(exitButton.getPreferredSize());
		exitButton.setLocation(scrollPane.getLocation().x + scrollPane.getWidth() / 2 - exitButton.getWidth() / 2 , scrollPane.getLocation().y + scrollPane.getHeight());
		outerPanel.add(exitButton);
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				optionKeyBindDialog.dispose();
			}
		});
	    optionKeyBindDialog.setContentPane(outerPanel);
	    optionKeyBindDialog.setSize(width, height);
	    optionKeyBindDialog.setLocationRelativeTo(null);
	    optionKeyBindDialog.setResizable(false);
		
		optionKeyBindDialog.setVisible(true);
	}
	
	protected Player getPlayer() { return player; }
}
