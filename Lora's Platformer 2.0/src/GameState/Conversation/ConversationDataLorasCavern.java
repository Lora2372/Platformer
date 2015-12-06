package GameState.Conversation;

public class ConversationDataLorasCavern 
{
	public ConversationDataLorasCavern()
	{
		
	}
	
	public String[] lorasCavernWelcomeMessage = new String[]
	{
		"Ah, so you have finally awakened.",
		"I feel dizzy... Where am I?",
		"In the realm of mortals, this is the final test .",
		"In order to succeed, you must make you way to the keeper's sanctum and prove your worth to her.",
		"Sounds simple enough... How do I get out of here?",
		"There should be a lever that opens the door, see it?",
		"- Yes \n " +
		"- No..."
	};
	
	public int[] lorasCavernWelcomeMessageWhoTalks = new int[]
	{
		1,
		0,
		1,
		1,
		0,
		1,
		0
	};
	
	public String[] lorasCavernWelcomeMessageChoiceYes = new String[]
	{
		"Great!, unfortunately I have other things that require my attention, but I will drop by to help if I'm able."			
	};
	
	public int[] lorasCavernWelcomeMessageWhoTalksChoiceYes = new int[]
	{
		1
	};
	
	public String[] lorasCavernWelcomeMessageChoiceNo = new String[]
	{
		"It should be right in front of you... Try looking around for a bit, I'm sure you'll find it.",
		"Unfortunately I have other things that require my attention, but I will drop by later to help if I'm able."
	};
	
	public int[] lorasCavernWelcomeMessageWhoTalksChoiceNo = new int[]
	{
		1,
		1
	};
	
	
	public String[] leverToLiadrinOpen()
	{
			return new String[]
			{
				"Somewhere, a door just opened..."
			};
			
	}
	
	public int[] leverToLiadrinOpenWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	
	public String[] leverToLiadrinClose()
	{
			return new String[]
			{
				"You hear the sound of a door closing..."
			};
			
	}
	
	public int[] leverToLiadrinCloseWhoTalks()
	{
		return new int[]
		{
			2
		};
	}
	
	public String[] liadrinFirstEncounter()
	{
			return new String[]
			{
				"Greetings yet again.",
				"Greetings Liadrin.",
				"How does your test fair thus far?",
				"- It's easy! \n " +
				"- It's hard..."
			};
			
	}
	
	public int[] liadrinFirstEncounterWhoTalks()
	{
		return new int[]
		{
			1,
			0,
			1,
			0
		};
	}
	
	public String[] liadrinFirstEncounterChoiceHard()
	{
		return new String[]
		{
			"Don't give up ok? \n Here, take these potions, I'm sure they will come in handy.",
			"Thank you!",
			"Now if you don't mind, I have some things to take care of."
		};
	}
	
	public int[] liadrinFirstEncounterChoiceHardWhoTalks()
	{
		return new int[]
		{
			1,
			0,
			1
		};
	}
	
	public String[] liadrinFirstEncounterChoiceEasy()
	{
		return new String[]
		{
			"Glad to hear it! \nUnfortunately I can't stay, keep up the good work and we'll meet again soon."
		};
	}
	
	public int[] liadrinFirstEncounterChoiceEasyWhoTalks()
	{
		return new int[]
		{
			1
		};
	}
	
}
