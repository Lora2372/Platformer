package GameState.Conversation;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Item.Item;
import Entity.Player.Player;
import Entity.Unit.Unit;
import GameState.GameState;
import GameState.GameStateManager;
import Main.Content;
import Main.GamePanel;

public class ConversationState  extends GameState
{
	
	private Player player;
	private Unit otherPerson;
	private Doodad doodad;
	private Item item;
	private String[] conversation;
	private int[] whoTalks;
	
	protected BufferedImage[] endConversation;
	
	private int conversationTracker;

	private double locationX;
	private double locationY;
	
	private BufferedImage[] conversationGUI;
	
	private boolean conversationLocked;
	
	
	protected String endConversationString;
	
	protected int conversationBoxWidth = 454;
	protected int conversationBoxHeight = 138;
	
	protected ArrayList<Integer> choiceRows;
	protected int choiceAmount = 0;
	protected int choiceSelected = 1;
	protected int choiceMade = 0;
	
	protected boolean choiceRequested;
	
	protected boolean conversationOver;
	
	protected Rectangle mouseRectangle;
	protected Rectangle conversationRectangle;
	
	protected double mouseLocationX;
	protected double mouseLocationY;
	
	
	public ConversationState(GameStateManager gameStateManager)
	{
		
		this.gameStateManager = gameStateManager;
		
		conversationGUI = Content.ConversationGUI[0];
		endConversation = Content.ConversationGUIEndConversation[0];
		
		endConversationString = "End conversation";
		
	
		locationX = GamePanel.WIDTH / 3;
		locationY = GamePanel.HEIGHT - 138;
		
		conversationRectangle = new Rectangle(
				(int)locationX,
				(int)locationY,
				conversationBoxWidth,
				conversationBoxHeight
				);
		
	}
	
	public void initialize(Player player) 
	{
		this.player = player;
	}
	
	
	public boolean conversationLocked() { return conversationLocked; }
	public void lockConversation(boolean b) 
	{ 
		conversationLocked = b; 	
	}
	
	
	public void displayItem
		(
			Player player,
			Item item
		)
	{

		this.player = player;
		this.item = item;
		

		whoTalks = new int[] { 6 };
		
		player.setInConversation(true);
	}
	
	public void startConversation
		(
			Player newPlayer,
			Unit newOtherPerson,
			Doodad newDoodad,
			String[] newConversation,
			int[] newWhoTalks
		)
	{
		otherPerson = newOtherPerson;
		conversation = newConversation;
		conversationTracker = 0;
		player.setInConversation(true);
		whoTalks = newWhoTalks;
		
		player.inControl(false);
		player.setInvulnerable(true);
		
		conversationOver = false;
		
		if(newDoodad != null) doodad = newDoodad;
		
		if(otherPerson != null)
			otherPerson.setInConversation(true);
		
		player.setInConversation(true);
		choiceRows = new ArrayList<Integer>();
		
	}
	
	public void endConversation()
	{
		conversationTracker = 0;
		player.setInConversation(false);
		
		player.inControl(true);
		player.setInvulnerable(false);
		
		player.setInConversation(false);
		
		choiceRequested = false;
		choiceAmount = 0;
		choiceRows = null;
		choiceSelected = 1;
		choiceMade = 0;
		
		conversation = null;
		doodad = null;
		
		if(otherPerson != null)
		{
			otherPerson.setInConversation(false);
			otherPerson = null;
		}
		
	}
	
	
	public void progressConversation()
	{
		if(conversationLocked || conversationOver) 
		{
			return;
		}
		
		conversationTracker++;
		choiceMade = choiceSelected;
		
		if(conversationTracker >= conversation.length)
		{
			conversationOver = true;
		}
		
	}
	
	public int getConversationTracker()
	{
		return conversationTracker;
	}
	
	public int getConversationLength()
	{
		return conversation.length;
	}
	
	public int getChoiceMade()
	{
		return choiceMade;
	}
	
	public boolean getConversationOver() { return conversationOver; }
	
	public void updateChoice(int choice)
	{
		if(choiceSelected != choice)
		{
			choiceSelected = choice;
			JukeBox.play("DecisionChange");
		}
	}
	
	public void draw(Graphics2D graphics)
	{
		try
		{
			graphics.drawImage
				(
					conversationGUI[0],
					(int) (locationX),
					(int) (locationY),
					null
				);
			
			player.inControl(false);
			
			String tempName = "";
			
			int tempWidth = 0;
			int tempHeight = 0;
			
			BufferedImage[] tempIcon = null;
			
			
			if(conversationTracker >= whoTalks.length)
			{
//				endConversation();
				return;
			}
			
			if(whoTalks[conversationTracker] == 0)
			{
				tempName = player.getName();
				tempIcon = player.getPortrait();
				tempWidth = 94;
				tempHeight = 94;
			}
			
			if(whoTalks[conversationTracker] == 1)
			{
				tempName = otherPerson.getName();
				tempIcon = otherPerson.getPortrait();
				tempWidth = 94;
				tempHeight = 94;
			}
			
			if(whoTalks[conversationTracker] == 2)
			{
				
				tempName = doodad.getDoodadName();
				tempIcon = doodad.getSprites();
				tempWidth = 60;
				tempHeight = 60;
			}
			
			if(whoTalks[conversationTracker] == 4)
			{
				tempName = "Liadrin";
				tempIcon = Content.PortraitSuccubus[0];
			}
			
			if(whoTalks[conversationTracker] == 5)
			{
				tempName = "Unknown";
				tempIcon = Content.PortraitSuccubus[0];
				tempWidth = 94;
				tempHeight = 94;
			}
			
			if(whoTalks[conversationTracker] == 6)
			{
				if(item == null)
				{
					conversation = new String[] { "" };
					tempName = "";
					tempIcon = null;
				}
				else
				{
					conversation = new String[] { item.getDescription() };
					tempName = item.getDescriptionName();
					tempIcon = item.getSprites();
					tempWidth = 60;
					tempHeight = 60;
				}
			}
						
			
			if(whoTalks[conversationTracker] != 3)
			{
				int portraitLocationX = (int)locationX - 104;
				int portraitLocationY = (int)locationY + 30;
				
				int portraitWidth = 104;
				int portraitHeight = 104;
				
				graphics.drawImage
					(
						Content.BuffIcon[0][0],
						portraitLocationX,
						portraitLocationY,
						portraitWidth,
						portraitHeight,
						null
					);
				
				if(tempIcon != null)
				{
					graphics.drawImage
						(
							tempIcon[0],
							portraitLocationX + 5 + (portraitWidth / 2 - tempWidth / 2),
							portraitLocationY + 5 + (portraitHeight / 2 - tempHeight / 2),
							tempWidth,
							tempHeight,
							null
						);
				}
				
				int textLocationX = (int)locationX + 21;
				int textLocationY = (int)locationY + 25;
				
				
				graphics.setFont(new Font("Arial", Font.PLAIN, 14));				
				graphics.setColor(Color.WHITE);
				graphics.drawString(tempName, textLocationX, textLocationY);	
			}
			
			if(conversationTracker >= conversation.length)
			{
				return;
			}
			
			String[] myString = conversation[conversationTracker].split(" ");
			int tempX = 0;
			int line = 0;
			
			int tempChoiceAmount = 0;
			ArrayList<Integer> tempChoiceRows = new ArrayList<Integer>();
	
			mouseRectangle = new Rectangle((int)mouseLocationX, (int)mouseLocationY, 1, 1);
	
			for(int i = 0; i < myString.length; i++)
			{
				int tempInt = graphics.getFontMetrics().stringWidth(myString[i]);
				
				if( (tempX + tempInt > conversationBoxWidth -40) || myString[i].equals("\n"))
				{
					graphics.setColor(Color.WHITE);
					line++;
					tempX = 0;
					
				}
				
				int textLocationX = (int)locationX + 21 + tempX;
				int textLocationY = (int)locationY + 70 + 20 * line;
				
				int textWidth = (textLocationX - (int)locationX) + conversationBoxWidth - 40;
				int textHeight = (int) graphics.getFontMetrics().getStringBounds(myString[i], graphics).getHeight();
					
				
				if(myString[i].equals("-"))
				{
					tempChoiceAmount++;
					
					Rectangle textRectangle = new Rectangle
						(
							textLocationX,
							textLocationY - textHeight / 2,
							textWidth,
							textHeight						
						);
					
					if(mouseRectangle.intersects(textRectangle))
					{
						updateChoice(tempChoiceAmount);
					}
					
					if(choiceSelected == tempChoiceAmount)
					{
						graphics.setColor(Color.YELLOW);
					}
					else
					{
						graphics.setColor(Color.RED);
					}
					tempChoiceRows.add(line);
					choiceRequested = true;
	
				}			
				
				graphics.drawString(myString[i], textLocationX, textLocationY);
				
				tempX += tempInt + 3;
			}
			
			choiceAmount = tempChoiceAmount;
			choiceRows = tempChoiceRows;
			
			if( !choiceRequested)
			{
				if(conversationTracker == conversation.length - 1)
				{
					tempX = (int) locationX + 454;
					int tempY = (int) locationY + 98;
					
					int stringLength = graphics.getFontMetrics().stringWidth(endConversationString);
					
					graphics.drawImage(
							endConversation[0], 
							tempX, 
							tempY,
							stringLength + 20,
							35,
							null);
					
					graphics.drawString(endConversationString, tempX + 12, tempY + 20);
					
				}
			}
		}
		catch(Exception exception)
		{
			System.out.println("whoTalks.length: " + whoTalks.length + "\nconversationTracker: " + conversationTracker);
			exception.printStackTrace();
		}
	}

	public void keyPressed(int key) 
	{
		if(player.getInConversation())
		{
			if(key == player.getKeyBind(Player.KeyBind.AimUp))
			{
				if(choiceSelected > 1)
				{
					choiceSelected--;
				}
			}
			if(key == player.getKeyBind(Player.KeyBind.AimDown))
			{
				if(choiceSelected < choiceAmount)
				{
					choiceSelected++;
				}
			}
		}
	}

	public void update() 
	{
		
	}
	
	public void saveToRAM()
	{
		
	}



	public void keyReleased(int key) 
	{
		
	}
	
	public void mouseClicked(MouseEvent mouse) 
	{
		if(conversationRectangle.intersects(mouseRectangle))
		{
			Robot myRobot;
			try 
			{
				myRobot = new Robot();
				myRobot.keyPress(player.getKeyBind(Player.KeyBind.Interact));
				myRobot.keyRelease(player.getKeyBind(Player.KeyBind.Interact));
			} catch (AWTException exception) 
			{
				exception.printStackTrace();
			}

		}
	}


	public void mouseEntered(MouseEvent mouse) 
	{
		
	}

	public void mouseExited(MouseEvent mouse) 
	{
		
	}

	public void mousePressed(MouseEvent mouse) 
	{
		
	}

	public void mouseReleased(MouseEvent mouse) 
	{
		
	}
	
	public void mouseMoved(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}
	
	public void mouseDragged(MouseEvent mouse) 
	{
		mouseLocationX = mouse.getX();
		mouseLocationY = mouse.getY();
	}

	public void initialize() 
	{

	}
}
