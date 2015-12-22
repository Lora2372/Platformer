package GameState.Conversation;

import java.awt.event.KeyEvent;

import Entity.Player.Player;

public class ConversationDataTutorial 
{
	protected Player player;

	
	public ConversationDataTutorial(Player player)
	{
		this.player = player;
	}
	
	// Will contain all of the conversations within the Tutorial
	
	
	
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
/* 11 */	"Move toward the edge, jump and hold down R to glide",
/* 12 */	"Head out through the door.",
/* 13 */	"Move to the lever and press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.Interact)),
/* 14 */	"Head outside.",
/* 15 */	"Find shelter!",
/* 16 */	"Move close to the campfire.",
/* 17 */	"Move to the potion and press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.Interact)),
/* 18 */	"Press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.UseItem1)) + " to drink the potion.",

		};
	}
	
	public String[] tutorialWelcome = new String[]
	{
		"Greetings, and welcome to the tutorial! Please press E.",
		"Good! That's exactly how you progress a conversation! ",
		"During a conversation you are unable to move or act "
		+ "you must therefore end a conversation before you can "
		+ "start doing so.",
		"This is the last frame until this dialog ends, when "
		+ "it does, try moving around with the arrow keys."
	};
	
	public int[] tutorialWelcomeWhoTalks = new int[]
	{
		4, 4, 4, 4
	};
	
	public String[] tutorialMove = new String[]
	{
		"Good job, now try jumping by pressing space!" 
	};
	
	public int[] tutorialMoveWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialJump = new String[]
	{
		"Nice one! Those are the basic movements you can do. "
		+ "Why don't you try exploring the world for a bit, see what we can find." 
	};
	
	public int[] tutorialJumpWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialHoleSmall = new String[]
	{
		"Hold up, see that hole up ahead? Try jumping over it! ",
		"Shouldn't be too hard..."
	};
	
	public int[] tutorialHoleSmallWhoTalks = new int[]
	{
		4, 0
	};
	
	public String[] tutorialHoleSmallPassed = new String[]
	{
		"Good job!",
		"Hah, this is easy!"
	};
	
	public int[] tutorialHoleSmallPassedWhoTalks = new int[]
	{
		4, 0
	};
	
	public String[] tutorialMovementMastered = new String[]
	{
		"Nice, looks like you have mastered the movement."
	};
	
	public int[] tutorialMovementMasteredWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialFireBallSmall = new String[]
	{
		"Now what about attacks? Try pressing A"
	};
	
	public int[] tutorialFireBallSmallWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialFireBallLarge = new String[]
	{
		"Excellent, that's a small fireball. See how the blue bar in the upper "
				+ "left corner dropped a bit? That's your mana bar, all spells "
				+ "consume mana.",
				"Don't worry though, it will regenerate back up automatically.",
				"Hey, why don't you try holding the up arrow whilst pressing S?"
	};
	
	public int[] tutorialFireBallLargeWhoTalks = new int[]
	{
		4, 4, 4
	};

	public String[] tutorialDash = new String[]
	{
		"Nice one, not only did you cast a large fireball which is more "
				+ "powerful, you also aimed it!", 
				"You can do the same thing with the small fireball, and you can "
				+ "even aim it downwards by holding down the down arrow.",
				"Play around with it a bit if you want, when you feel ready to "
				+ "proceed, press the D button."
	};
	
	public int[] tutorialDashWhoTalks = new int[]
	{
		4, 4, 4
	};


	
	public String[] tutorialPunch = new String[]
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
	
	public int[] tutorialPunchWhoTalks = new int[]
	{
		4, 4, 4
	};


	public String[] tutorialPunchDone = new String[]
	{
		"Simple, isn't it? "
				+ "The punch is quite useful if you get up close to your enemy to beat "
				+ "the living shit out of them."
	};
	
	public int[] tutorialPunchDoneWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialEnemySuccubus = new String[]
	{
		"Watch out, it looks like an enemy has appeared right in front of you! "
				+ "Try using the attacks you just learnt to defeat it!"
	};
	
	public int[] tutorialEnemySuccubusWhoTalks = new int[]
	{
		4
	};

	public String[] tutorialTempleEnter = new String[]
	{
		"Good job!",
		"Did you hear that? It looks like the way forward just opened up. "
		+ "Why don't you enter the temple so see what awaits you inside?"
	};
	
	public int[] tutorialTempleEnterWhoTalks = new int[]
	{
		4, 4
	};
	
	public String[] tutorialHoleLarge = new String[]
	{
		"This hole is much larger than the last one...",
		"Yes, you would never be able to cross it with a normal jump.",
		"Fortunately, you also know how to glide through the air, "
		+ "whenever you are in the air, try holding down R to "
		+ "start gliding!"
	};
	
	public int[] tutorialHoleLargeWhoTalks = new int[]
	{
		0, 4, 4
	};
	
	public String[] tutorialHoleLargePassed = new String[]
	{
		"Great work! \r "
		+ "Now all you need to do is head out through the door ahead of you."
	};
	
	public int[] tutorialHoleLargePassedWhoTalks = new int[]
	{
		4
	};
	
	public String[] tutorialTempleExitClosed()
	{
		return new String[]
		{
			"Strange, this door isn't supposed to be closed...",
			"Hey, there's a lever above you, move up to it and press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.Interact)) + " to interact with it."	
		};
	}
	
	public int[] tutorialTempleExitClosedWhoTalks()
	{
		return new int[]
		{
			4, 4
		};
	}
	
	public String[] tutorialTempleExitOpen()
	{
		return new String[]
		{
			"Neat, the door is now open and you can head on outside!"	
		};
	}
	
	public int[] tutorialTempleExitOpenWhoTalks()
	{
		return new int[]
		{
			4
		};
		
	}
	
	public String[] tutorialNiceWeather()
	{
		return new String[]
		{
			"Ah, finally some fresh air, it looks like it's a beautiful day today!",
			"...",
			"Hey don't look at me, I didn't do this...",
			"That reminds me, I still haven't taught you about the importance of keeping warm!",
			"You see, staying out in rain or jumping into pools of water will make you wet.",
			"The more wet you become the more susceptible you become to cold.",
			"The more cold you become the weaker you become.",
			"Different parts of the world hold different temperatures, it can be anything from warm like here, to freezing cold.",
			"In colder areas you must be more careful not to become wet else you will freeze to death real fast!",
			"How do I warm up then?",
			"Simple, if you can find a campfire it will dry you off and warm you up in no time!",
			"Alternatively, if you stay out of rain and pools of water you will dry off eventually.",
			"For now, just try to find shelter from this rain!"
		};
	}
	
	public int[] tutorialNiceWeatherWhoTalks() 
	{
		return new int[]
		{
			4,
			0,
			4,
			4,
			4,
			4,
			4,
			4,
			4,
			0,
			4,
			4,
			4
		};
	}
	
	public String[] tutorialCampFire()
	{
		return new String[]
		{
			"What luck, not only was there a cave nearby, someone was kind enough to leave a campfire behind!",
			"Just stay close to the campfire and you will warm up in no time!"
		};
	}
	
	public int[] tutorialCampFireWhoTalks()
	{
		return new int[]
		{
			4,
			4
		};
	}
	
	public String[] tutorialWarmedUp()
	{
		return new String[]
		{
			"Ah, that feels much better.",
			"All warmed up? Good, while you were slacking off I found this thing.",
			"Wait a minute, slacking off?",
			"Nevermind that now, pick up the potion I left you, move up to it and press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.Interact)) + "."
		};
	}
	
	public int[] tutorialWarmedUpWhoTalks()
	{
		return new int[]
		{
			0,
			4,
			0,
			4
		};
	}
	
	public String[] tutorialHealingPotionTheChoice()
	{
		return new String[]
		{
			"What's this then?",
			"That is a healing potion, if you drink it, it will heal your wounds.",
			"- What does it taste like? \n " +
			"- Sounds useful."
		};
	}
	
	public int[] tutorialHealingPotionTheChoiceWhoTalks()
	{
		return new int[]
		{
			0,
			4,
			0
		};
	}
	
	public String[] tutorialHealingPotionChoiceTaste()
	{
		return new String[]
		{
			"Yes it can b- hold on, taste, why are you asking about its taste?",
			"Well, you said I had to drink it...",
			"In a situation of life and death the taste shouldn't matter, anyway...",
			"There are more kinds like it, a blue kind which restores mana and a yellow one that restores stamina.",
			"I wonder if the blue one tastes like blueberry...",
			"...",
			"What?"
		};
	}
	
	public int[] tutorialHealingPotionChoiceTasteWhoTalks()
	{
		return new int[]
		{
			4,
			0,
			4,
			4,
			0,
			4,
			0
		};
	}
	
	public String[] tutorialHealingPotionChoiceUseful()
	{
		return new String[]
		{
			"Yes it can be very useful",
			"There are more kinds like it, a blue kind which restores mana and a yellow one that restores stamina.",
			"How does one make these potions?",
			"The ingredients are fairly rare and they are quite difficult to make, perhaps I will teach you one day."
		};
	}
	
	public int[] tutorialHealingPotionChoiceUsefulWhoTalks()
	{
		return new int[]
		{
			4,
			4,
			0,
			4
		};
	}
	
	public String[] tutorialHealingPotionDamage()
	{
		return new String[]
		{
			"Anyway, let's test it.",
			"But I'm already at full health?",
			"Easily fixed.",
			"Ow! What did you do that for?",
			"Quit your whining, press " + KeyEvent.getKeyText(player.getKeyBind(Player.KeyBind.UseItem1)) + " to drink your potion."
		};
	}
	
	public int[] tutorialHealingPotionDamageWhoTalks()
	{
		return new int[]
		{
			4,
			0,
			4,
			0,
			4
		};
	}
	
	public String[] tutorialHealingPotionDrunk()
	{
		return new String[]
		{
			"Ah tastes like raspberry!",
			"Just remember only to drink them during emergencies.",
			"I know I know...",
			"At any rate, that is all I have to teach you for now. It is high time you awoken to take your test.",
			"You mean all of this has just been a dream?",
			"Yes, a dream you are about to wake up from..."
		};
	}
	
	public int[] tutorialHealingPotionDrunkWhoTalks()
	{
		return new int[]
		{
			0,
			4,
			0,
			4,
			0,
			4
		};
	}

	
}
