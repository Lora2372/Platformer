package Entity.Doodad.Activatable;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Conversation;
import Entity.Player.ConversationState;
import Entity.Player.Player;
import Main.Content;
import Main.JSONWriter;
import TileMap.TileMap;

public class StatueSave extends Doodad
{

	protected Player player;
	
	protected Conversation conversation;
	
	protected ConversationState conversationBox;
	
	protected int choiceMade;
	
	public StatueSave(
			TileMap tileMap, 
			double spawnX,
			double spawnY
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				116, 
				110,
				116,
				110,
				0.3, 
				8, 
				false, 
				true, 
				false,
				true,
				false,
				0,
				"StatueSave"
				);
		
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		
	}
	
	public void setDoodad(int currentAction)
	{
		sprites = Content.StatueSave[0];

	}
	
	public void interact(Player player)
	{
		
		if(this.player == null)
		{
			this.player = player;
			this.conversationBox = player.getConversationState();
			this.conversation = player.getConversation();
		}
		
		if(!player.getInConversation() && choiceMade == 0)
		{
			JukeBox.play("Restore");
			player.restoreHealth(player.getMaxHealth());
			player.restoreMana(player.getMaxMana());
			player.restoreStamina(player.getMaxStamina());
			player.getConversationState().startConversation(player, null, null, conversation.statueSave(), conversation.statuSaveWhoTalks());
			return;
		}
		
		if(player.getInConversation() && choiceMade == 0)
		{
			choiceMade = conversationBox.getChoiceMade();
			if(choiceMade == 1)
			{
				player.getConversationState().startConversation(player, null, null, conversation.statueSaveChoiceYes(), conversation.statuSaveChoiceYesWhoTalks());
				
				JSONWriter.saveFile(player, (int)spawnX, (int)spawnY);
				JukeBox.play("Save");
				return;
			}
			else
			{
				player.getConversationState().startConversation(player, null, null, conversation.statueSaveChoiceNo(), conversation.statuSaveChoiceNoWhoTalks());	
				return;
			}
		}
		
		if(conversationBox.getConversationOver())
		{
			choiceMade = 0;
			conversationBox.endConversation();
		}
	}
}