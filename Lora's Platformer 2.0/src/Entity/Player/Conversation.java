package Entity.Player;


public class Conversation 
{
	public static String[] fionaDefeated = new String[]
	{
		"Ugh...",
		"Ready to talk about this?",
		"I'll kill you for this...",
		"...",
		"Damn it, she escaped!",
		"...",
		"At least the door opened..."
	};
	
	public static int[] fionaDefeatedWhoTalks = new int[]
	{
		1,
		0,
		1,
		3,
		0,
		3,
		0
	};
	
	
	public static String[] tutorialWelcomeMessage = new String[]
	{
		"Greetings, and welcome to the tutorial!\nPlease press E.",
		"Good! That's exactly how you progress a conversation!\n",
		"During a conversation you are unable to move or act\n"
		+ "you must therefor end a conversation before you can\n"
		+ "start doing so.",
		"This is the last frame until this dialog ends, when\n"
		+ "it does, try moving around with the arrow keys."
	};
	
	public static int[] tutorialWelcomeMessageWhoTalks = new int[]
	{
		4, 4, 4, 4
	};
	
	public static String[] tutorialMoveMessage = new String[]
	{
		"Good job, now try jumping by pressing space!" 
	};
	
	public static int[] tutorialMoveMessageWhoTalks = new int[]
	{
		4
	};
	
	public static String[] tutorialJumpMessage = new String[]
	{
		"Nice one!\nThose are the basic movements you can do.\n"
		+ "Why don't you try exploring the map for a bit, see what we can find." 
	};
	
	public static int[] tutorialJumpMessageWhoTalks = new int[]
	{
		4
	};
	
	public static String[] tutorialHoleSmallMessage = new String[]
	{
		"Hold up, see that hole up ahead? Try jumping over it!\n",
		"Shouldn't be too hard..."
	};
	
	public static int[] tutorialHoleSmallMessageWhoTalks = new int[]
	{
		4, 0
	};
	
	public static String[] tutorialHoleSmallPassedMessage = new String[]
	{
		"Good job!",
		"Hah, this is easy!"
	};
	
	public static int[] tutorialHoleSmallPassedMessageWhoTalks = new int[]
	{
		4, 0
	};
	
	public static String[] tutorialMovementMasteredMessage = new String[]
	{
		"Nice, looks like you have mastered the movement."
	};
	
	public static int[] tutorialMovementMasteredMessageWhoTalks = new int[]
	{
		4
	};
	
	public static String[] tutorialFireBallSmallMessage = new String[]
	{
		"Now what about attacks? Try pressing A"
	};
	
	public static int[] tutorialFireBallSmallMessageWhoTalks = new int[]
	{
		4
	};
	
	public static String[] tutorialFireBallLargeMessage = new String[]
	{
		"Excellent, that's a small fireball. See how the blue bar in the upper\n"
				+ "left corner dropped a bit? That's your mana bar, all spells\n"
				+ "consume mana.",
				"Don't worry though, it will regenerate back up automatically.",
				"Hey, why don't you try holding the up arrow whilst pressing S?"
	};
	
	public static int[] tutorialFireBallLargeMessageWhoTalks = new int[]
	{
		4, 4, 4
	};

	public static String[] tutorialDashMessage = new String[]
	{
		"Nice one, not only did you cast a large fireball which is more\n"
				+ "powerful, you also aimed it!", 
				"You can do the same thing with the small fireball, and you can\n"
				+ "even aim it downwards by holding down the down arrow.",
				"Play around with it a bit if you want, when you feel ready to\n"
				+ "proceed, press the D button."
	};
	
	public static int[] tutorialDashMessageWhoTalks = new int[]
	{
		4, 4, 4
	};


	
	public static String[] tutorialPunchMessage = new String[]
	{
		"That's a dash attack, as you could see it will allow you to dash\n"
				+ "forward, dealing damage to all enemies that you pass along the\n"
				+ "way!",
				"You might also have noticed that the yellow bar decreased as you\n"
				+ "used dash, that's the stamina bar, all your physical attacks\n"
				+ "drain your stamina.",
				"Don't worry though, just as with your mana, your stamina will\n"
				+ "regenerate automatically. For our final attack, try pressing F."
	};
	
	public static int[] tutorialPunchMessageWhoTalks = new int[]
	{
		4, 4, 4
	};


	public static String[] tutorialPunchDoneMessage = new String[]
	{
		"Simple, isn't it?\n"
				+ "The punch is quite useful if you get up close to your enemy to beat\n"
				+ "the living shit out of them."
	};
	
	public static int[] tutorialPunchDoneMessageWhoTalks = new int[]
	{
		4
	};
	
	public static String[] tutorialEnemySuccubusMessage = new String[]
	{
		"Watch out, it looks like an enemy has appeared right in front of you!\n"
				+ "Try using the attacks you just learnt to defeat it!"
	};
	
	public static int[] tutorialEnemySuccubusMessageWhoTalks = new int[]
	{
		4
	};

	public static String[] tutorialTempleEnterMessage = new String[]
	{
		"Good job!",
		"Did you hear that? It looks like the way forward jus opened up.\n"
		+ "Why don't you enter the temple so see what awaits you inside?"
	};
	
	public static int[] tutorialTempleEnterMessageWhoTalks = new int[]
	{
		4, 4
	};
	
	public static String[] tutorialHoleLargeMessage = new String[]
	{
		"This hole is much larger than the last one...",
		"Yes, you would never be able to cross it with a normal jump.",
		"Fortunately, you also know how to glide through the air,\n"
		+ "whenever you are in the air, try holding down E to\n"
		+ "start gliding!"
	};
	
	public static int[] tutorialHoleLargeMessageWhoTalks = new int[]
	{
		0, 4, 4
	};
	
	public static String[] tutorialHoleLargePassedMessage = new String[]
	{
		"Great work!"
	};
	
	public static int[] tutorialHoleLargePassedMessageWhoTalks = new int[]
	{
		4
	};

	
	
	public static void loadConversation()
	{

	}
}
