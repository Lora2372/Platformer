package Entity.Doodad.Activatable;


import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.ConversationBox;
import Entity.Player.Player;


public class ActivatableSign extends Doodad
{

	protected String[] conversation;
	protected Player player;
	protected int[] whoTalks;
	
	protected String name;
	
	// We need a class that draws the basic "sign background" and then write text on top of that, the stuff below is temporary...
	public ActivatableSign(
			TileMap tileMap, 
			double spawnX,
			double spawnY,
			Player player
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				50, 
				50,
				50,
				50,
				"/Sprites/Doodads/Sign.png", 
				new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				new int[] {1},
				false, 
				true, 
				false,
				true,
				false
				);
		
		setName("Sign");
		
		conversation = new String[]
				{
					"Greetings mortal, I am a welcome sign intended to make you feel,\n well... Welcome!",
					"What an odd sign..."
				};
		
		whoTalks = new int[]
				{
					2,
					0
				};
		
		this.player = player;
	}
	
	public void startConversation()
	{
		player.inControl(false);
		active = true;
		
		ConversationBox.startConversation(player, null, this, conversation, whoTalks);
	}
	
	public void interact()
	{
		if(!ConversationBox.inConversation())startConversation();
		else ConversationBox.progressConversation();
		
		if(ConversationBox.getConversationTracker() >= conversation.length)
		{
			
			active = false;
			player.inControl(true);
			ConversationBox.endConversation();
		}
	}
		
	
	public void activateSound() 
	{ 
		JukeBox.play("OpenChestCommon");
	}
	
}
