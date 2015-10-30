package Entity.Doodad.Activatable;


import Main.Content;
import TileMap.TileMap;
import Entity.Doodad.Doodad;
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
				false, 
				true, 
				false,
				true,
				false,
				"ActivatableSign"
				);
		
		setDoodadType("Sign");
		
		this.conversation = conversation;
		
		this.whoTalks = whoTalks;
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Sign[0];
	}
	
	public void startConversation()
	{
		player.inControl(false);
		active = true;
		
		player.getConversationState().startConversation(player, null, this, conversation, whoTalks);
	}
	
	public void interact(Player player)
	{
		this.player = player;
		
		if(player == null)
			System.out.println("player is null");
		
		if(!player.getConversationState().inConversation()) 
			startConversation();
//		else 
//			player.getConversationState().progressConversation();
		
		if(player.getConversationState().getConversationTracker() >= conversation.length)
		{
			
			active = false;
			player.inControl(true);
			player.getConversationState().endConversation();
		}
	}
		
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}
