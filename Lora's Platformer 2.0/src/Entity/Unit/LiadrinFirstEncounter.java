package Entity.Unit;
import java.util.ArrayList;

import TileMap.TileMap;
import Entity.Doodad.SummoningEffect;
import Entity.Item.CreateItem;
import Entity.Item.Potion;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import GameState.MainMap;
import Main.Content;
public class LiadrinFirstEncounter extends Unit
{
	
	protected int[] whoTalks;
	protected String[] conversation;
	protected Player player;
	protected boolean used;
	protected boolean active;
	protected int choiceMade;
	
	protected SummoningEffect summoningEffect;
	
	protected ConversationState conversationBox;
	
	public LiadrinFirstEncounter(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnX,
			double spawnY,
			MainMap mainMap
			) 
	{
		super(
				tileMap,  															// TileMap
				100, 	 															// Width
				100, 	 															// Height
				100, 	 															// Collision width
				100, 	 															// Collision height
				0.1, 	 															// Move speed
				1.0, 	 															// Max speed
				0.4, 	 															// stopSpeed
				0.3, 	 															// fallSpeed
				8.0, 	 															// maxFallSpeed
				-9.6, 	 															// jumpStart
				0.6, 	 															// stopJumpSpeed
				facingRight,														// facingRight
				true,  																// inControl
				5,		 															// health
				5, 		 															//maxHealth
				30,		 															// healthCounter
				100,	 																// stamina
				100, 	 																// maxStamina
				30,		 															// staminaCounter
				800,																// sightRange
				0,
				0,	 	 															// punchCost
				0, 		 															// punchDamage
				0,	 	 															// punchRange
				0,		 															// dashCost
				2,		 															// dashDamage
				40,		 															// dashRange
				20, 	 															// dashSpeed
				100,		 															// mana
				100,		 															// maxMana
				30,		 															// manaCounter
				20,		 															// smallFireballManaCost
				20,		 															// smallFireballDamage
				40,		 															// largeFireballManaCost
				50, 																	// largeFireballDamage
				30,																	// electricBallManaCost
				70,																	// electricBallDamage
				0,
				0,
				"/Art/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,1,2,0,0,1,2,1,2,3,0,0,0,0},						// animationStates
				new int[]{7, 2, 2, 1, 2, 0, 0, 0, 0},								// numImages
				new int[] { 400, 400, 400, 400, 125, 120, 100, 100, 300, 300, 300,300, 500, 400, 400, 400, 400 }, // animationDelay
				0,																	// damageOnTouch
				friendly,															// friendly				
				untouchable,
				invulnerable,
				unkillable,
				true,
				name,
				spawnX,
				spawnY,
				mainMap
				);
		
		active = true;
		
		portrait = Content.PortraitLiadrin[0];
	}
	

	
	public void interact(Player player)
	{
		
		// If we've already talked to her, we should not be able to do so again.
		if(!active)
		{
			return;
		}
		
		// If the player is null for whatever reason, update it with the player interacting with it:
		if(this.player == null)
		{
			this.player = player;
			conversationBox = player.getConversationState();
		}
		
		// If the player has not yet started talking to Liadrin, do so.
		if(!player.getConversationState().inConversation() && summoningEffect == null && choiceMade == 0)
		{
			player.getConversationState().startConversation(player, this, null, player.getConversation().liadrinFirstEncounter(), player.getConversation().liadrinFirstEncounterWhoTalks());
			return;
		}
		
		// If the player is currently in a conversation but has not yet made the choice
		if(player.getInConversation() && choiceMade == 0)
		{
			if(conversationBox.getConversationTracker() >= player.getConversation().liadrinFirstEncounter().length)
			{
				choiceMade = conversationBox.getChoiceMade();
				if(choiceMade == 1)
				{
					player.getConversationState().startConversation(player, this, null, player.getConversation().liadrinFirstEncounterChoiceEasy(), player.getConversation().liadrinFirstEncounterChoiceEasyWhoTalks());
				}
				else
				{
					player.getConversationState().startConversation(player, this, null, player.getConversation().liadrinFirstEncounterChoiceHard(), player.getConversation().liadrinFirstEncounterChoiceHardWhoTalks());
				}
			}
		}

		// Player thought it was easy:
		if(player.getInConversation() && choiceMade == 1)
		{
			if(conversationBox.getConversationTracker() >= player.getConversation().liadrinFirstEncounterChoiceEasy().length)
			{
				active = false;
			}
		}
		
		// Player thought it was hard:
		if(player.getInConversation() && choiceMade == 2)
		{
			if(conversationBox.getConversationTracker() >= player.getConversation().liadrinFirstEncounterChoiceHard().length)
			{
				Potion healingPotion = new Potion(tileMap, false, 0, 0, player, 2, CreateItem.Potions.PotionHealing.toString());
				
				player.getInventory().addItem(healingPotion);
				active = false;
			}
		}
		
		if(!active)
		{
			player.getConversationState().endConversation();
			summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
			mainMap.addEffect(summoningEffect);
		}
	}
	
	public void updateAI(ArrayList<Unit> characterList)
	{
		if(summoningEffect != null)
		{
			if(summoningEffect.getRemoveMe())
			{
				removeMe = true;
			}
		}
	}
}