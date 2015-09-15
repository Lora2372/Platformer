package Entity.NPC.Activatable;
import java.util.ArrayList;

import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Character;
import Entity.Doodad.SummoningEffect;
import Entity.Player.Conversation;
import Entity.Player.Player;
import GameState.Level1State;
import Main.Content;
public class LiadrinFirstEncounter extends Character
{
	
	protected int[] whoTalks;
	protected String[] conversation;
	protected Player player;
	protected boolean active;
	
	protected SummoningEffect summoningEffect;
	
	public LiadrinFirstEncounter(
			TileMap tileMap,
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			String name,
			double spawnX,
			double spawnY,
			Level1State level1state
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
				"/Sprites/Characters/Succubus.png",									// spritePath
				new int[] {0,0,0,0,0,0,0,0,4,2,4,2,4,0,0,0,0},				// animationStates
				new int[]{7, 2, 6, 2, 1, 4, 4, 1, 6},								// numImages
				0,																	// damageOnTouch
				friendly,															// friendly				
				untouchable,
				invulnerable,
				true,
				name,
				spawnX,
				spawnY,
				level1state
				);
		
		active = false;
		
		portrait = Content.PortraitLiadrin[0];
	}
	
	public void startConversation()
	{
		
		conversation = new String[]
		{
			"Greetings, you must be " + player.getName(),
			
			"How did you know that",
			
			"I'm Liadrin, a pleasure.\n"
			+ "I've been expecting your arrival for some time now.",
			
			"Expecting me?",
			
			"Why yes, I won't get into any details now but,\n"
			+ "I'm supposed to tell you that the way forward\n"
			+ "is past the depths of the earth.",
			
			"Thank you, I think...",
			
			"You're quite welcome, now if you don't mind,\n"
			+ "I'll take my leave."
		};
		
		whoTalks = new int[]
		{
			1,
			0,
			1,
			0,
			1,
			0,
			1
		};
		
		
		active = true;	
		player.getConversationBox().startConversation(player, this, null, conversation, whoTalks);
		
	}
	
	public void interact(Player player)
	{
		this.player = player;
		
		if(!player.getConversationBox().inConversation())startConversation();
		else player.getConversationBox().progressConversation();
		
		if(player.getConversationBox().getConversationTracker() >= conversation.length)
		{
			
			active = false;
			player.getConversationBox().endConversation();
			
			summoningEffect = new SummoningEffect(tileMap, locationX, locationY);
			level1state.addEffect(summoningEffect);
			JukeBox.play("teleport");
			
		}
	}
	
	public void updateAI(ArrayList<Character> characterList)
	{
		if(summoningEffect != null)
		{
			if(summoningEffect.removeMe())
			{
				removeMe = true;
			}
		}
	}
		
	
	public void activateSound() 
	{ 
		JukeBox.play("OpenChestCommon");
	}
	

}
