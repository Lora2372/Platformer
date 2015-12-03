package GameState.Conversation;

import Entity.Player.Player;

public class ConversationData 
{
	
	protected Player player;
	
	public ConversationData(Player player)
	{
		this.player = player;
	}
	
	
	public String[] unlockObject(String object)
	{
		return new String[]
		{
			"The " + object + " is locked.",
			"- Try to unlock it. \n " +
			"- Ignore the " + object + "."
		};
	}
	
	public int[] unlockObjectWhoTalks()
	{
		return new int[]
		{
			2,
			2
		};
	}
	
	public String[] unlockObjectChoiceYesSuccess(String object)
	{
		return new String[]
		{
			"The " + object + " was unlocked!"
		};
	}
	
	public int[] unlockObjectChoiceYesSuccessWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	
	public String[] unlockObjectChoiceYesFailure(String object)
	{
		return new String[]
		{
			"You struggle to open the " + object + " to no avail, perhaps if you had a key..."
		};
	}
	
	public int[] unlockObjectChoiceYesFailureWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	
	public String[] unlockObjectChoiceNo(String object)
	{
		return new String[]
		{
			"You step away from the " + object + "."
		};
	}
	
	public int[] unlockObjectChoiceNoWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	

	
	public String[] statueSave()
	{
		return new String[]
		{
			"The shrine fills you with energy.",
			"Do you wish to save? \n " +
			"- Yes \n " +
			"- No"
		};
	}
	
	public int[] statueSaveWhoTalks()
	{
		return new int[]
		{
			2,
			2
		};
	}
	
	public String[] statueSaveChoiceYes()
	{
		return new String[]
		{
			"The game was saved!"
		};
	}
	
	public int[] statueSaveChoiceYesWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	
	public String[] statueSaveChoiceNo()
	{
		return new String[]
		{
			"You step away from the shrine."
		};
	}
	
	public int[] statueSaveChoiceNoWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
}
