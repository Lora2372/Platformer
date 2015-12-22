package GameState.Options;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Audio.JukeBox;
import Entity.Player.Player;

@SuppressWarnings("serial")
public class OptionVolumeFrame extends JFrame
{
	protected Player player;
	
	protected JDialog optionKeyBindDialog;

	protected ArrayList<JSlider> sliders = new ArrayList<JSlider>();
	protected String[] sliderTypes = new String[]
	{
		"Music",
		"Character",
		"Effect",
		"Background"
	};
	public OptionVolumeFrame(Player player)
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
	    
		JLabel label = new JLabel("Edit volume levels below.");
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
		
		JLabel newLabel = new JLabel("Music volume:");
		labels.add(newLabel);
		newLabel = new JLabel("Character volume:");
		labels.add(newLabel);
		newLabel = new JLabel("Effect volume:");
		labels.add(newLabel);
		newLabel = new JLabel("Background volume:");
		labels.add(newLabel);
		
		for(int i = 0; i < 4; i++)
		{
			label = labels.get(i);
			label.setSize(label.getPreferredSize());
			label.setLocation(0, i * 70 + 20);
			keybindPanel.add(label);
			
			JSlider musicSlider = new JSlider(JSlider.HORIZONTAL, JukeBox.minVolume, JukeBox.maxVolume, JukeBox.getVolume(JukeBox.soundCategories.values()[i]));
			sliders.add(musicSlider);
			musicSlider.setMajorTickSpacing(10);
			musicSlider.setPaintTicks(true);
			musicSlider.setSize(musicSlider.getPreferredSize());
			musicSlider.setSize(musicSlider.getWidth(), 40);
			musicSlider.setLocation(scrollPane.getWidth() - musicSlider.getWidth() - 20, label.getLocation().y);
			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
			labelTable.put( new Integer( JukeBox.minVolume ), new JLabel("Low") );
			labelTable.put( new Integer( JukeBox.maxVolume ), new JLabel("High") );
			musicSlider.setPaintLabels(true);
			musicSlider.setLabelTable( labelTable );
			musicSlider.putClientProperty(JukeBox.minVolume, "Low");
			musicSlider.putClientProperty(JukeBox.maxVolume, "High");
			
			musicSlider.addChangeListener(new ChangeListener()
			{
				public void stateChanged(ChangeEvent event) 
				{
					for(int i = 0; i < sliders.size(); i++)
					{
						if(event.getSource().equals(sliders.get(i)))
						{
							JukeBox.setVolume(JukeBox.soundCategories.values()[i], sliders.get(i).getValue());
							System.out.println("changeEvent: " + sliders.get(i).getValue());
						}
					}
				}
			});
			keybindPanel.add(musicSlider);
		}
		

//		characterSlider = new JSlider(JukeBox.minVolume, JukeBox.maxVolume, 0);
//		scrollPane.add(characterSlider);
//		effectSlider = new JSlider(JukeBox.minVolume, JukeBox.maxVolume, 0);
//		scrollPane.add(effectSlider);
//		backgroundSlider = new JSlider(JukeBox.minVolume, JukeBox.maxVolume, 0);
//		scrollPane.add(backgroundSlider);
		
		

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