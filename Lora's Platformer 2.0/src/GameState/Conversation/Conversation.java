package GameState.Conversation;

import Entity.Player.Player;

public class Conversation 
{
	
	protected Player player;
	
	public Conversation(Player player)
	{
		this.player = player;
	}
	
	
	public String[] unlockObject(String object)
	{
		return new String[]
		{
			"The " + object + " is locked.",
			"Do you wish to unlock it? \n " +
			"- Yes \n " +
			"- No"
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
		
	public String[] tutorialInstructions()
	{
		return new String[]
		{
/* 0 */		"Press E",
/* 1 */		"Use the arrow keys to move",
/* 2 */		"Press space to jump",
/* 3 */		"Explore the world",
/* 4 */		"Jump over the hole using the arrow keys to move and space to jump",
/* 5 */		"Press A to fire a small fireball",
/* 6 */		"Hold up or down to aim, then press S to fire a large fireball",
/* 7 */		"Press D to dash forward",
/* 8 */		"Press F to punch",
/* 9 */		"Defeat the enemy using A/S/D/F",
/* 10 */	"Enter the temple",
/* 11 */	"Move toward the edge, jump and hold down R to glide"
		};
	}
	
	public String[] tutorialWelcomeMessage = new String[]
	{
		"Greetings, and welcome to the tutorial! Please press E.",
		"Good! That's exactly how you progress a conversation! ",
		"During a conversation you are unable to move or act "
		+ "you must therefore end a conversation before you can "
		+ "start doing so.",
		"This is the last frame until this dialog ends, when "
		+ "it does, try moving around with the arrow keys."
	};
	
	public int[] tutorialWelcomeMessageWhoTalks = new int[]
	{
		4, 4, 4, 4
	};
	
	public String[] tutorialMoveMessage = new String[]
	{
		"Good job, now try jumping by pressing space!" 
	};
	
	public int[] tutorialMoveMessageWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialJumpMessage = new String[]
	{
		"Nice one! Those are the basic movements you can do. "
		+ "Why don't you try exploring the world for a bit, see what we can find." 
	};
	
	public int[] tutorialJumpMessageWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialHoleSmallMessage = new String[]
	{
		"Hold up, see that hole up ahead? Try jumping over it! ",
		"Shouldn't be too hard..."
	};
	
	public int[] tutorialHoleSmallMessageWhoTalks = new int[]
	{
		4, 0
	};
	
	public String[] tutorialHoleSmallPassedMessage = new String[]
	{
		"Good job!",
		"Hah, this is easy!"
	};
	
	public int[] tutorialHoleSmallPassedMessageWhoTalks = new int[]
	{
		4, 0
	};
	
	public String[] tutorialMovementMasteredMessage = new String[]
	{
		"Nice, looks like you have mastered the movement."
	};
	
	public int[] tutorialMovementMasteredMessageWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialFireBallSmallMessage = new String[]
	{
		"Now what about attacks? Try pressing A"
	};
	
	public int[] tutorialFireBallSmallMessageWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialFireBallLargeMessage = new String[]
	{
		"Excellent, that's a small fireball. See how the blue bar in the upper "
				+ "left corner dropped a bit? That's your mana bar, all spells "
				+ "consume mana.",
				"Don't worry though, it will regenerate back up automatically.",
				"Hey, why don't you try holding the up arrow whilst pressing S?"
	};
	
	public int[] tutorialFireBallLargeMessageWhoTalks = new int[]
	{
		4, 4, 4
	};

	public String[] tutorialDashMessage = new String[]
	{
		"Nice one, not only did you cast a large fireball which is more "
				+ "powerful, you also aimed it!", 
				"You can do the same thing with the small fireball, and you can "
				+ "even aim it downwards by holding down the down arrow.",
				"Play around with it a bit if you want, when you feel ready to "
				+ "proceed, press the D button."
	};
	
	public int[] tutorialDashMessageWhoTalks = new int[]
	{
		4, 4, 4
	};


	
	public String[] tutorialPunchMessage = new String[]
	{
		"That's a dash attack, as you could see it will allow you to dash "
				+ "forward, dealing damage to all enemies that you pass along the "
				+ "way!",
				"You might also have noticed that the yellow bar decreased as you "
				+ "used dash, that's the stamina bar, all your physical attacks "
				+ "drain your stamina.",
				"Don't worry though, just as with your mana, your stamina will "
				+ "regenerate automatically. For our final attack, try pressing F."
	};
	
	public int[] tutorialPunchMessageWhoTalks = new int[]
	{
		4, 4, 4
	};


	public String[] tutorialPunchDoneMessage = new String[]
	{
		"Simple, isn't it? "
				+ "The punch is quite useful if you get up close to your enemy to beat "
				+ "the living shit out of them."
	};
	
	public int[] tutorialPunchDoneMessageWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialEnemySuccubusMessage = new String[]
	{
		"Watch out, it looks like an enemy has appeared right in front of you! "
				+ "Try using the attacks you just learnt to defeat it!"
	};
	
	public int[] tutorialEnemySuccubusMessageWhoTalks = new int[]
	{
		4
	};

	public String[] tutorialTempleEnterMessage = new String[]
	{
		"Good job!",
		"Did you hear that? It looks like the way forward just opened up. "
		+ "Why don't you enter the temple so see what awaits you inside?"
	};
	
	public int[] tutorialTempleEnterMessageWhoTalks = new int[]
	{
		4, 4
	};
	
	public String[] tutorialHoleLargeMessage = new String[]
	{
		"This hole is much larger than the last one...",
		"Yes, you would never be able to cross it with a normal jump.",
		"Fortunately, you also know how to glide through the air, "
		+ "whenever you are in the air, try holding down R to "
		+ "start gliding!"
	};
	
	public int[] tutorialHoleLargeMessageWhoTalks = new int[]
	{
		0, 4, 4
	};
	
	public String[] tutorialHoleLargePassedMessage = new String[]
	{
		"Great work!",
		"This is sadly the end of the tutorial at the moment."
	};
	
	public int[] tutorialHoleLargePassedMessageWhoTalks = new int[]
	{
		4, 4
	};
	
	public String[] lorasCavernWelcomeMessage = new String[]
	{
		"Ah, so you have finally awakened.",
		"I feel dizzy... Where am I?",
		"In the realm of mortals, this is the final test .",
		"In order to succeed, you must make you way to the keeper's sanctum and prove your worth to her.",
		"Sound simple enough... How do I get out of here?",
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
	

	public String[] mysteriousDungeonTorchMessage = new String[]
	{
			"Torches, someone must live here, or something..."
	};
	

	public String[] mysteriousDungeonDirectionMessage = new String[]
	{
		"Up: Guarded treasure \n "
		+ "Down: No treasure",
		"Well, I guess I need to make a choice..."
	};
	
	public int[] mysteriousDungeonDirectionMessageWhoTalks = new int[]
	{
		2, 0
	};
	

	
	public String[] interactWithFionasShrine()
	{
		return new String[]
		{
			"The shrine glows faintly, there appears to be some pulsing orb at its core.",
			"Touch the orb? \n - Yes \n - No"
		};
	};
	
	public int[] interactWithFinonasShrineWhoTalks()
	{
		return new int[]
		{
			3,
			3
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
			"Another apprentice thrown to the wolves I see...",										// 1
			"I won't be an apprentice for much longer!",											// 0
			"No? \n We'll see...",																	// 1
			"I'm Fiona, by the way, keeper of this sanctum.",										// 1
			"If you manage to defeat me, you've earned your freedom.",								// 1
			"If you lose however, well... let's just say you will never see the light of day.",		// 1
			"I lose to no one!",																	// 0
			"Defend yourself if you must, it will make my victory all the sweeter..."				// 1
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
		"As keeper of this sanctum I hearby release you from duty, you're free to go.",			// 1
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

	
	
	public void loadConversation()
	{

	}
}
