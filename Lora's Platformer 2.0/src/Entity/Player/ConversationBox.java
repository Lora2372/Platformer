package Entity.Player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Entity.Doodad.Doodad;
import Entity.Character;
import Main.Content;
import Main.GamePanel;

public class ConversationBox 
{
	
	private static Player player;
	private static Character otherPerson;
	private static Doodad sign;
	private static String[] conversation;
	private static int[] whoTalks;
	
	
	private static int conversationTracker;
	
	private static boolean inConversation;
	
	private static double locationX;
	private static double locationY;
	
	private static BufferedImage[] sprites;
	
	
	
	public static void initializeConversationBox()
	{
		sprites = Content.ConversationGUI[0];
		
		inConversation = false;
		
		locationX = GamePanel.WIDTH / 3;
		locationY = GamePanel.HEIGHT - 138;
		
	}
	
	public static boolean inConversation() { return inConversation; }
	
	
	public static void startConversation(
			Player newPlayer,
			Character newOtherPerson, 
			Doodad newSign, 
			String[] newConversation, 
			int[] newWhoTalks
			)
	{
		player = newPlayer;
		otherPerson = newOtherPerson;
		sign = newSign;
		conversation = newConversation;
		conversationTracker = 0;
		inConversation = true;
		whoTalks = newWhoTalks;
	}
	
	public static void endConversation()
	{
		player = null;
		otherPerson = null;
		sign = null;
		conversation = null;
		conversationTracker = 0;
		inConversation = false;
	}
	
	
	public static void progressConversation()
	{
		conversationTracker++;
	}
	
	public static int getConversationTracker()
	{
		return conversationTracker;
	}
	

	
	public static void draw(Graphics graphics)
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
