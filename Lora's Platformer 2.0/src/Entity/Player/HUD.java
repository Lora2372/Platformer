package Entity.Player;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import Entity.Projectile.ProjectileData;
import Main.Content;
import Main.DrawingConstants;
import Main.GamePanel;

public class HUD 
{
	protected Player player;
	
	
	protected BufferedImage playerWetBar;
	protected BufferedImage plyayerHeatBar;
	
	
	protected float breathFader = 0;
	protected float heatFader = 1;
	protected float wetFader = 1;
	
	protected int barWidth = 108;
	protected int barHeight = 30;
	
	protected BufferedImage bossBar;
	protected BufferedImage healthBar;
	
	protected ArrayList<BufferedImage> spellbarUsable = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> spellbarUnusable = new ArrayList<BufferedImage>();
	
	protected ArrayList<Integer> spellcost = new ArrayList<Integer>();
	
	protected Entity.Unit.Unit boss;
	
	protected boolean questFrameShow;
	protected int questCurrent;
	protected String[] questName;
	
	protected ArrayList<String> fadeMessageList;
	protected int fadeStaticTime;
	protected double fadeTime;
	protected double fadeStart;
	protected int fadingStage; // 1 = fading in, 2 = static, 3 = fading out
	
	
	protected int questFrameX = GamePanel.WIDTH - 60;
	protected int questFrameY = 150;

	protected int fadeMessageX = questFrameX;
	protected int fadeMessageY = questFrameY + 100;
	
	protected enum wetEnum
	{
		Soaked,
		Wet,
		Dry
	}
	
	protected enum heatEnum
	{
		Hot,
		Warm,
		Cold,
		Freezing
	}
	
	protected HashMap<String, String> wetMessages;
	protected HashMap<String, String> heatMessages;
	
	
	public HUD(Player player)
	{
		this.player = player;
		fadeMessageList = new ArrayList<String>();
		
		wetMessages = new HashMap<String, String>();
		wetMessages.put(wetEnum.Soaked.toString(), "You feel completely soaked");
		wetMessages.put(wetEnum.Wet.toString(), "You feel wet");
		wetMessages.put(wetEnum.Dry.toString(), "You feel dry");
		
		heatMessages = new HashMap<String, String>();
		heatMessages.put(heatEnum.Hot.toString(), "You feel hot");
		heatMessages.put(heatEnum.Warm.toString(), "You feel warm");
		heatMessages.put(heatEnum.Cold.toString(), "You feel cold...");
		heatMessages.put(heatEnum.Freezing.toString(), "You're freezing!");
		
		
		try
		{			
			
			spellcost.add(ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallSmall.toString()));
			
			BufferedImage tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/FireBallSmallUsable.png"
						)
				);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/FireBallSmallUnusable.png"
						)
				);
			spellbarUnusable.add(tempImage);

			spellcost.add(ProjectileData.projectileCost.get(ProjectileData.Projectiles.FireBallLarge.toString()));

			
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/FireBallLargeUsable.png"
						)
				);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/FireBallLargeUnusable.png"
						)
				);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getDashStaminaCost());

			
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/DashUsable.png"
						)
				);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/DashUnusable.png"
						)
				);
			spellbarUnusable.add(tempImage);
			
			spellcost.add(player.getPunchStaminaCost());

			
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
							"/Art/HUD/SpellIcons/PunchUsable.png"
						)
				);
			spellbarUsable.add(tempImage);
			
			tempImage = ImageIO.read
				(
					getClass().getResource
						(
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
	
	public void fadeMessage(String message)
	{
		if(fadeMessageList.isEmpty())
		{
			this.fadeStart = System.currentTimeMillis();
			this.fadeTime = 1;
			fadeStaticTime = 4;
			this.fadingStage = 1;
		}
		
		for(int i = 0; i < wetEnum.values().length; i++)
		{
			if(message.equals(wetEnum.values()[i].toString()))
			{
				for(int j = 0; j < wetEnum.values().length; j++)
				{
					if(fadeMessageList.contains(wetMessages.get(wetEnum.values()[j].toString())))
					{
						fadeMessageList.remove(wetMessages.get(wetEnum.values()[j].toString()));
					}
				}
				fadeMessageList.add(wetMessages.get(message));
				return;
			}
		}
		
		for(int i = 0; i < heatEnum.values().length; i++)
		{
			if(message.equals(heatEnum.values()[i].toString()))
			{
				for(int j = 0; j < heatEnum.values().length; j++)
				{
					if(fadeMessageList.contains(heatMessages.get(heatEnum.values()[j].toString())))
					{
						fadeMessageList.remove(heatMessages.get(heatEnum.values()[j].toString()));
					}
				}
				fadeMessageList.add(heatMessages.get(message));
				return;
			}
		}
		
		fadeMessageList.add(message);
	}
	
	public AlphaComposite transparent(float transparency)
	{
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, transparency);
	}
	
	public void draw(Graphics2D graphics)
	{
		try
		{
			Composite originalComposite = graphics.getComposite();
			
			if(fadeMessageList.size() > 0)
			{	
				double elapsed = System.currentTimeMillis() - fadeStart;
				float transparency = 1;
				String currentString = fadeMessageList.get(0);
				if(fadingStage == 1)
				{
					transparency = (float) (elapsed / (fadeTime * 1000));
					
					if(transparency >= 1)
					{
						fadingStage = 2;
						fadeStart = System.currentTimeMillis();
						elapsed = 0;
						transparency = 1;
					}
				}
				
				if(fadingStage == 2)
				{
					if(elapsed >= fadeStaticTime * 1000)
					{
						elapsed = 0;
						fadingStage = 3;
						fadeStart = System.currentTimeMillis();
					}
				}
				
				if(fadingStage == 3)
				{
					transparency = (float) (1 - elapsed / (fadeTime * 1000));
					if(transparency <= 0)
					{
						fadeStart = System.currentTimeMillis();
						fadingStage = 1;
						fadeMessageList.remove(0);
						currentString = null;
					}
				}
				
				if(currentString != null)
				{
					graphics.setComposite(transparent(transparency));					
					
					int stringLength = DrawingConstants.getStringWidth(currentString, graphics);
					int stringHeight = DrawingConstants.getStringHeight(currentString, graphics);
					
					int tempFadeMessageX = fadeMessageX - stringLength;
					
					
					graphics.drawImage
						(
							Content.ConversationGUIEndConversation[0][0],
							tempFadeMessageX,
							fadeMessageY,
							stringLength + 20,
							stringHeight + 10,
							null
						);
					
					graphics.setFont(new Font("Arial", Font.PLAIN, 15));
					graphics.setColor(Color.WHITE);
					graphics.drawString(currentString, tempFadeMessageX + 10, fadeMessageY + 15);
					graphics.setComposite(originalComposite);
				}
			}
			
			// Draw buffs
			
			for(int i = 0; i < player.getBuffs().size(); i++)
			{
				player.getBuffs().get(i).draw(graphics);
			}
			
		
			// Draw the health bar
			double currentValue = player.getHealth();
			double maxValue = player.getMaxHealth();
			
			graphics.drawImage
			(
				Content.PlayerHealthBar[0][0], 
				94, 
				27, 
				(int)((currentValue/maxValue) * 225),
				30,
				null
			);
			
			
			// Draw the mana bar
			currentValue = player.getMana();
			maxValue = player.getMaxMana();
			
			graphics.drawImage
				(
					Content.PlayerManaBar[0][0], 
					113, 
					56, 
					(int)((currentValue/maxValue) * 193),
					25,
					null
				);
			
			// Draw the stamina bar
			currentValue = player.getStamina();
			maxValue = player.getMaxStamina();
			
			graphics.drawImage
				(
					Content.PlayerStaminaBar[0][0], 
					106, 
					86, 
					(int)((currentValue / maxValue) * 173),
					18,
					null
				);
			
			// Draw the player bar
			graphics.drawImage
				(
					Content.PlayerBar[0][0],
					0, 
					10, 
					null
				);
			
			
			// Draw breath bar
			if(player.getBreath() >= 100)
			{
				breathFader -= 0.01;
				if(breathFader < 0)
				{
					breathFader = 0;
				}
			}
			else
			{
				breathFader = 1;
			}
			
			graphics.setComposite(transparent(breathFader));					
			
			int length = 75;
			int height = 20;
			int drawX = (int)(player.getLocationXOnScreen()) - length / 2;
			int drawY = (int)(player.getLocationYOnScreen()) - player.getHeight() / 2 - 20;
			
			if(breathFader > 0)
			{
				graphics.drawImage
					(
						Content.BarFrame[0][0],
						drawX,
						drawY,
						length,
						height,
						null
					);
				graphics.setColor(Color.BLUE);
				graphics.fillRect
					(
						drawX + 1,
						drawY + 1,
						(int)((player.getBreath() / 100) * (length - 2)),
						height - 2
					);
			}
			graphics.setComposite(originalComposite);
			
			// Draw heat bar
			double timePassed = (System.currentTimeMillis() - player.getHeatTimer()) / 1000;
			if(timePassed >= 3)
			{
				heatFader -= 0.01;
				if(heatFader < 0)
				{
					heatFader = 0;
				}
			}
			else
			{
				heatFader = 1;
			}
			graphics.setComposite(transparent(heatFader));					
			
			
			
			if(heatFader > 0)
			{
				graphics.drawImage
					(
						Content.BarFrame[0][0],
						600,
						50,
						193,
						25,
						null
					);
				
				graphics.setColor(player.getHeatColour());
				graphics.fillRect
					(
						600 + 1,
						50 + 1,
						(int)((player.getHeat() / 100) * (193 - 2)),
						25 - 2
					);
			}
			graphics.setComposite(originalComposite);

			// Draw wet bar

			if(player.getWet() <= 0)
			{
				wetFader -= 0.01;
				if(wetFader < 0)
				{
					wetFader = 0;
				}
			}
			else
			{
				wetFader = 1;
			}
			graphics.setComposite(transparent(wetFader));					
			
			
			
			if(wetFader > 0)
			{
				graphics.drawImage
					(
						Content.BarFrame[0][0],
						800,
						50,
						193,
						25,
						null
					);
				
				graphics.setColor(player.getWetColour());
				graphics.fillRect
					(
						800 + 1,
						50 + 1,
						(int)((player.getWet() / 100) * (193 - 2)),
						25 - 2
					);
			}
			graphics.setComposite(originalComposite);
			
			// YARR, there be gold and silver a plenty!
			//			(Drawing currency)
			graphics.setFont(new Font("Arial", Font.PLAIN, 14));
			graphics.setColor(Color.WHITE);
			int currencyX = GamePanel.WIDTH - 100;
			int currencyY = GamePanel.HEIGHT - 100;
			graphics.drawString("" + player.getSilver(), currencyX, currencyY);
			graphics.drawImage
				(
					Content.CoinSilver[0][0],
					currencyX + 15,
					currencyY - 20,				
					30,
					30,
					null
				);
	
			currencyY += 30;
			
			graphics.drawImage
				(
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
				if(questName != null)
				{
					if(questName[questCurrent] != null)
					{
						double textWidth = graphics.getFontMetrics().stringWidth(questName[questCurrent]);
						
						double locationX = questFrameX - textWidth;
						double locationY = questFrameY;
						
						graphics.drawImage
							(
								Content.ConversationGUIEndConversation[0][0],
								(int)locationX - 20,
								(int)locationY - 20,
								(int)textWidth + 40,
								30,
								null
							);
						
						graphics.drawString(questName[questCurrent], (int)locationX, (int)locationY);
					}
				}
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
						graphics.drawImage
							(
								spellbarUsable.get(i),
								x + i * spellbarUsable.get(i).getWidth(),
								y,
								null
							);
					}
					else
					{
						graphics.drawImage
							(
								spellbarUnusable.get(i),
								x + i * spellbarUsable.get(i).getWidth(),
								y,
								null
							);
					}
				}
			}
			
			
			if(boss != null)
			{
				
				int imageWidth = GamePanel.WIDTH / 4;
				
				int x = GamePanel.WIDTH / 2 - imageWidth / 2;
				int y = 20;
				
				graphics.drawImage
					(
						bossBar,
						x,
						y,
						imageWidth,
						bossBar.getHeight(),
						null
					);
				
				currentValue = boss.getHealth();
				maxValue = boss.getMaxHealth();
	
				graphics.drawImage
					(
						healthBar, 
						x, 
						y, 
						(int)((currentValue/maxValue) * imageWidth),
						healthBar.getHeight(),
						null
					);
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}	
}