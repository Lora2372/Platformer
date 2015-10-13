package Entity.Player;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entity.Doodad.Doodad;
import Entity.Unit;
import Main.Content;
import Main.GamePanel;

public class ConversationBox 
{
	
	private Player player;
	private Unit otherPerson;
	private Doodad sign;
	private String[] conversation;
	private int[] whoTalks;
	
	
	private int conversationTracker;
	
	private boolean inConversation;
	
	private double locationX;
	private double locationY;
	
	private BufferedImage[] sprites;
	
	private boolean conversationLocked;
	
	
	public ConversationBox(Player player)
	{
		sprites = Content.ConversationGUI[0];
		
		inConversation = false;
		
		locationX = GamePanel.WIDTH / 3;
		locationY = GamePanel.HEIGHT - 138;
		
		this.player = player;
		
	}
	
	public boolean inConversation() { return inConversation; }
	
	public boolean conversationLocked() { return conversationLocked; }
	public void lockConversation(boolean b) { conversationLocked = b; }
	
	public void startConversation(
			Player newPlayer,
			Unit newOtherPerson, 
			Doodad newSign, 
			String[] newConversation, 
			int[] newWhoTalks
			)
	{
		otherPerson = newOtherPerson;
		conversation = newConversation;
		conversationTracker = 0;
		inConversation = true;
		whoTalks = newWhoTalks;
		
		player.inControl(false);
		player.invulnerable(true);
		
		if(newSign != null) sign = newSign;
		
		if(otherPerson != null)
			otherPerson.setInConversation(true);
		
		player.setInConversation(true);
		
	}
	
	public void endConversation()
	{
		conversationTracker = 0;
		inConversation = false;
		
		player.inControl(true);
		player.invulnerable(false);
		
		player.setInConversation(false);
		
		if(otherPerson != null)
			otherPerson.setInConversation(false);
	}
	
	
	public void progressConversation()
	{
		if(conversationLocked) return;
		conversationTracker++;
	}
	
	public int getConversationTracker()
	{
		return conversationTracker;
	}
	
	public int getConversationLength()
	{
		return conversation.length;
	}
	
	public void draw(Graphics graphics)
	{

		graphics.drawImage(
				sprites[0],
				(int) (locationX),
				(int) (locationY),
				null
			);
		
		player.inControl(false);
		
		String tempName = "";
		
		BufferedImage[] tempIcon = null;
		
		if(whoTalks[conversationTracker] == 0)
		{
			tempName = player.getName();
			tempIcon = player.getPortrait();
		}
		
		if(whoTalks[conversationTracker] == 1)
		{
			tempName = otherPerson.getName();
			tempIcon = otherPerson.getPortrait();
		}
		
		if(whoTalks[conversationTracker] == 2)
		{
			tempName = sign.getDoodadType();
			tempIcon = sign.getPortrait();
		}
		
		if(whoTalks[conversationTracker] == 4)
		{
			tempName = "Liadrin";
			tempIcon = Content.PortraitLiadrin[0];
		}
		
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		
		if(whoTalks[conversationTracker] != 3)
		{
			graphics.drawImage(
					tempIcon[0],
					(int) (locationX - 94),
					(int) (locationY + 35),
					null
				);
			
			graphics.drawString(tempName, (int)locationX + 21, (int)locationY + 25);	
		}
		

		String[] newString = conversation[conversationTracker].split("\n");
		
			

		for(int i = 0; i < newString.length; i++)
		{
			graphics.drawString(newString[i], (int)locationX + 21, (int)locationY + 70 + i*20);			
		}
		
		
		

//		graphics.drawString(conversation[conversationTracker], , y);
	
	
	}
}
