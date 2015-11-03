package Entity.Player;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import Main.Content;
import Main.GamePanel;

public class HUD 
{
	private Player player;
	
	private BufferedImage playerBar;
	private BufferedImage playerHealthBar;
	private BufferedImage playerManaBar;
	private BufferedImage playerStaminaBar;
	private BufferedImage bossBar;
	private BufferedImage bossHealthBar;
	
	private ArrayList<BufferedImage> spellbarUsable = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> spellbarUnusable = new ArrayList<BufferedImage>();
	
	private ArrayList<Integer> spellcost = new ArrayList<Integer>();
	
	private Entity.Unit.Unit boss;
	
	protected boolean questFrameShow;
	protected int questCurrent;
	protected String[] questName;
	
	public HUD(Player player)
	{
		this.player = player;
		
		try{
			playerBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerBar.png"
							)
					);
			
			playerHealthBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerHealthBar.png"
							)
					);
			
			playerManaBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerManaBar.png"
							)
					);
			
			playerStaminaBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/PlayerStaminaBar.png"
							)
					);
			
			bossHealthBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/BossHealthBar.png"
							)
					);
			
			
			
			bossBar = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/Bars/BossHealthBarFrame.png"
							)
					);
			
			
			spellcost.add(player.getFireBallSmallManaCost());
			
			BufferedImage tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallSmallUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallSmallUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);

			spellcost.add(player.getFireBallLargeManaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallLargeUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/FireBallLargeUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getDashStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/DashUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/DashUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getPunchStaminaCost());

			
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/PunchUsable.png"
							)
					);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read(
					getClass().getResource(
							"/Art/HUD/SpellIcons/PunchUnusable.png"
							)
					);
			spellbarUnusable.add(tempImage);


			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean getShowQuestFrame() { return questFrameShow; }
	public void setShowQuestFrame(boolean well) { questFrameShow = well; }
	
	public int getQuestCurrent() { return questCurrent; }
	public void setQuestCurrent(int current) { questCurrent = current; }
	
	public void setQuest(String[] newQuest) { questName = newQuest; }
	
	
	public void addBoss(Entity.Unit.Unit boss)
	{
		this.boss = boss;
	}
	
	public void removeBoss()
	{
		this.boss = null;
	}
	
	public void draw(Graphics2D graphics)
	{
		
		// Draw the health bar
		double currentValue = player.getHealth();
		double maxValue = player.getMaxHealth();
		
		graphics.drawImage(playerHealthBar, 
				94, 
				27, 
				(int)((currentValue/maxValue) * playerHealthBar.getWidth()),
				playerHealthBar.getHeight(),
				null);
		
		
		// Draw the mana bar
		currentValue = player.getMana();
		maxValue = player.getMaxMana();
		
		graphics.drawImage(playerManaBar, 
				113, 
				56, 
				(int)((currentValue/maxValue) * playerManaBar.getWidth()),
				playerManaBar.getHeight(),
				null);
		
		// Draw the stamina bar
		currentValue = player.getStamina();
		maxValue = player.getMaxStamina();
		
		graphics.drawImage(playerStaminaBar, 
				106, 
				86, 
				(int)((currentValue/maxValue) * playerStaminaBar.getWidth()),
				playerStaminaBar.getHeight(),
				null);
		
		// Draw the player bar
		graphics.drawImage(playerBar, 
				0, 
				10, 
				null);
		
		
		// YARR, there be gold and silver a plenty!
		//			(Drawing currency)
		graphics.setFont(new Font("Arial", Font.PLAIN, 14));
		int currencyX = GamePanel.WIDTH - 100;
		int currencyY = GamePanel.HEIGHT - 100;
		graphics.drawString("" + player.getSilver(), currencyX, currencyY);
		graphics.drawImage(
				Content.CoinSilver[0][0],
				currencyX + 15,
				currencyY - 20,				
				30,
				30,
				null
				);

		currencyY += 30;
		
		graphics.drawImage(
				Content.CoinGold[0][0],
				currencyX + 15,
				currencyY - 20,
				30,
				30,
				null
				);		
		graphics.drawString("" + player.getGold(), currencyX, currencyY);
		
		// The gold is ours, let's head back to the ship!
		
		
		// Draw the quest frame
		if(questFrameShow)
		{

			
			double textWidth = graphics.getFontMetrics().stringWidth(questName[questCurrent]);
			
			double locationX = GamePanel.WIDTH - 100 - textWidth;
			double locationY = 100;
			
			graphics.drawImage(
					Content.ConversationGUIEndConversation[0][0],
					(int)locationX - 20,
					(int)locationY - 20,
					(int)textWidth + 40,
					30,
					null
					);
			
			graphics.drawString(questName[questCurrent], (int)locationX, (int)locationY);
		}
		
		
		// Draw the action bar
		if(!player.getInConversation())
		{
			int x = GamePanel.WIDTH / 2 - (spellbarUsable.get(0).getWidth() * 2);
			int y = GamePanel.HEIGHT - spellbarUsable.get(0).getHeight() - 5;
			
			for(int i = 0; i < spellbarUsable.size(); i++)
			{
				if( (player.getMana() >= spellcost.get(i) && i < 2) || (player.getStamina() >= spellcost.get(i) && i >=2) )
				{
					graphics.drawImage(spellbarUsable.get(i), x + i * spellbarUsable.get(i).getWidth(), y, null);
				}
				else
					graphics.drawImage(spellbarUnusable.get(i), x + i * spellbarUsable.get(i).getWidth(), y, null);
			}
		}
		
		
		if(boss != null)
		{
			
			int imageWidth = GamePanel.WIDTH / 4;
			
			int x = GamePanel.WIDTH / 2 - imageWidth / 2;
			int y = 20;
			
			graphics.drawImage(bossBar,
					x,
					y,
					imageWidth,
					bossBar.getHeight(),
					null
					);
			
			currentValue = boss.getHealth();
			maxValue = boss.getMaxHealth();

			graphics.drawImage(bossHealthBar, 
					x, 
					y, 
					(int)((currentValue/maxValue) * imageWidth),
					bossHealthBar.getHeight(),
					null);			
		}
		
		
	}
	
}
