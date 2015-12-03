package GameState.Conversation;

public class ConversationDataMysteriousDungeon 
{
	public ConversationDataMysteriousDungeon()
	{
		
	}
	
	
	public String[] mysteriousDungeonTorchMessage = new String[]
	{
			"Torches, someone must live here, or something..."
	};
	

	public String[] mysteriousDungeonDirectionMessage = new String[]
	{
		"Up: Guarded treasure \n " +
		"Down: No treasure",
		"Well, I guess I need to make a choice..."
	};
	
	public int[] mysteriousDungeonDirectionMessageWhoTalks = new int[]
	{
		2, 0
	};
}
