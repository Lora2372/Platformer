package Entity.Doodad.Activatable;


import Main.Content;
import TileMap.TileMap;
import Entity.Doodad.Doodad;
import Entity.Player.Player;


public class Sign extends Doodad
{

	protected String[] conversation;
	protected Player player;
	protected int[] whoTalks;
	
	protected String name;
	
	// We need a class that draws the basic "sign background" and then write text on top of that, the stuff below is temporary...
	public Sign(
			TileMap tileMap, 
			double spawnLocationX,
			double spawnLocationY,
			Player player,
			String[] conversation,
			int[] whoTalks
			) 
	{
		super(tileMap, 
				spawnLocationX, 
				spawnLocationY, 
				50, 
				50,
				50,
				50,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				"Sign",
				CreateDoodad.doodadName.get("Sign")
				);
				
		this.conversation = conversation;
		
		this.whoTalks = whoTalks;
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.Sign[0];
		portrait = Content.PortraitSign[0];
	}
	
	public void startConversation()
	{
		player.inControl(false);
		active = true;
		
		player.getConversationState().startConversation(player, null, this, conversation, whoTalks);
	}
	
	public void interact(Player player)
	{
		try
		{
			this.player = player;
			
			if(!player.getInConversation()) 
			{
				startConversation();
			}
			
			if(player.getConversationState().getConversationTracker() >= conversation.length)
			{
				
				active = false;
				player.inControl(true);
				player.getConversationState().endConversation();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
		
	
	public void activateSound() 
	{ 
//		JukeBox.play("OpenChestCommon");
	}
	
}
