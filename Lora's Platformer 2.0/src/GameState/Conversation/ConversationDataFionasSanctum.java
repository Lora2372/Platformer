package GameState.Conversation;

public class ConversationDataFionasSanctum 
{

	public String[] interactWithFionasShrine()
	{
		return new String[]
		{
			"The shrine glows faintly, there appears to be some pulsing orb at its core.",
			"- Touch the orb \n " +
			"- Step away"
		};
	};
	
	public int[] interactWithFinonasShrineWhoTalks()
	{
		return new int[]
		{
			3,
			0
		};
	}
	
	
	public String[] interactWithFionasShrineChoiceYes()
	{
		return new String[]
		{
			"The shrine hums at your touch...",														// 3
			"Laughter echo through the tunnels",													// 3
			"The door behind you closed...",														// 3
			"Well look what I found, rummaging through my sanctum...",								// 5
			"Who's there? Reveal yourself!",														// 0
			"Another apprentice thrown to the wolves I see.",										// 1
			"I won't be an apprentice for much longer!",											// 0
			"No? \n We'll see.",																	// 1
			"I'm Fiona, by the way, keeper of this sanctum.",										// 1
			"Defeat me and you will have your freedom. \n " +										// 1
			"Lose, and you will be thrown back whence you came",
			"Sounds simple enough.",																// 0
			"Fight if you must, it will make my victory all the sweeter..."							// 1
		};
	}
	
	public int[] interactWithFinonasShrineChoiceYesWhoTalks()
	{
		return new int[]
		{
			3,
			3,
			3,
			5,
			0,
			1,
			0,
			1,
			1,
			1,
			0,
			1
		};
	}

	
	public String[] interactWithFionasShrineChoiceNo()
	{
		return new String[]
		{
			"You step away from the shrine..."
		};
	}
	
	public int[] interactWithFinonasShrineChoiceNoWhoTalks()
	{
		return new int[]
		{
			3
		};
	}
	

	
	public String[] fionaDefeated = new String[]
	{
		"Ugh... \n Well done, I see you are an apprentice no longer.",							// 1
		"As keeper of this sanctum I hearby release you, you're free to go.",					// 1
		"Just like that?",																		// 0
		"But of course, were you expecting some form of reward? \n Until we meet again...",		// 1
		"The door up ahead opens up."															// 3
	};
	
	public int[] fionaDefeatedWhoTalks = new int[]
	{
		1,
		1,
		0,
		1,
		3
	};

}
