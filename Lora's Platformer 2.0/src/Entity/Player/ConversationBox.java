package Entity.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entity.Doodad.Doodad;
import Entity.Character;
import Main.Content;
import Main.GamePanel;

public class ConversationBox 
{
	
	private Player player;
	private Character otherPerson;
	private Doodad sign;
	private String[] conversation;
	private int[] whoTalks;
	
	
	private int conversationTracker;
	
	private boolean inConversation;
	
	private double locationX;
	private double locationY;
	
	private BufferedImage[] sprites;
	
	
	
	public ConversationBox(Player player)
	{
		sprites = Content.ConversationGUI[0];
		
		inConversation = false;
		
		locationX = GamePanel.WIDTH / 3;
		locationY = GamePanel.HEIGHT - 138;
		
		this.player = player;
		
	}
	
	public boolean inConversation() { return inConversation; }
	
	
	public void startConversation(
			Player newPlayer,
			Character newOtherPerson, 
			Doodad newSign, 
			String[] newConversation, 
			int[] newWhoTalks
			)
	{
		otherPerson = newOtherPerson;
		sign = newSign;
		conversation = newConversation;
		conversationTracker = 0;
		inConversation = true;
		whoTalks = newWhoTalks;
		
		player.inControl(false);
		player.invulnerable(true);
	}
	
	public void endConversation()
	{
		conversationTracker = 0;
		inConversation = false;
		
		player.inControl(true);
		player.invulnerable(false);
	}
	
	
	public void progressConversation()
	{
		conversationTracker++;
	}
	
	public int getConversationTracker()
	{
		return conversationTracker;
	}
	

	
	public void draw(Graphics graphics)
	{
		
//		g.drawImage(image[1], 
//				94, 
//				27, 
//				(int)((t1/t2) * image[1].getWidth()),
//				image[1].getHeight(),
//				null);

		graphics.drawImage(
				sprites[0],
				(int) (locationX),
				(int) (locationY),
				null
			);
		
		
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
		}
		
		if(whoTalks[conversationTracker] == 2)
		{
			tempName = sign.getName();
			tempIcon = sign.getPortrait();
		}
		
		graphics.drawImage(
				tempIcon[0],
				(int) (locationX + 10),
				(int) (locationY + 25),
				null
			);
		
		graphics.drawString(tempName, (int)locationX + 135, (int)locationY + 25);		
		
		String[] newString = conversation[conversationTracker].split("\n");
		
			

		for(int i = 0; i < newString.length; i++)
		{
			graphics.drawString(newString[i], (int)locationX + 130, (int)locationY + 70 + i*20);			
		}
		
		
		

//		graphics.drawString(conversation[conversationTracker], , y);
	
	
	}
}
