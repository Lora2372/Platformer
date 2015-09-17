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
			Player player,
			String[] conversation,
			int[] whoTalks
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
		
		this.conversation = conversation;
		
		this.whoTalks = whoTalks;
		
	}
	
	public void startConversation()
	{
		player.inControl(false);
		active = true;
		
		player.getConversationBox().startConversation(player, null, this, conversation, whoTalks);
	}
	
	public void interact(Player player)
	{
		this.player = player;
		
		if(player == null)
			System.out.println("player is null");
		
		if(!player.getConversationBox().inConversation()) 
			startConversation();
		else 
			player.getConversationBox().progressConversation();
		
		if(player.getConversationBox().getConversationTracker() >= conversation.length)
		{
			
			active = false;
			player.inControl(true);
			player.getConversationBox().endConversation();
		}
	}
		
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}
