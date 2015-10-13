package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Enemies.Succubus;
import Entity.Player.Player;
import TileMap.Background;
import TileMap.GameOver;
import TileMap.TileMap;

public class Tutorial extends MainMap
{

	protected int tutorialProgress = 0;
	
	protected Succubus succubus;
	
	public Tutorial(GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player
			) 
	{
		super(gameStatemanager, 
				tileMap,
				player);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/TutorialA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/DeepWoods01.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		player.setTileMap(tileMap);
		player.setCurrentMap("DeepWoods");
		
		spawnTorch(3870, 400);
		
		if(!player.getLoaded())
		{
			player.setPosition(400, 200);
			player.setSpawnPoint(400, 200);
		}
		else
		{
			player.setLoaded(false);
			player.setPosition(player.getSpawnX(), player.getSpawnY());
		}
		player.setSpawning(true);
				
		doneInitializing = true;
	}
	
	public void update()
	{
		super.update();
		try
		{
			if(player.getInConversation())
			{
				if(player.getConversationBox().getConversationTracker() >= player.getConversationBox().getConversationLength())
				{
					player.getConversationBox().endConversation();
					tutorialProgress++;
				}
			}

			if(!player.getFalling())
				player.setSpawnPoint(player.getx() - 100, player.gety() - 50);
				
			if(tutorialProgress == 0 && !player.getFalling())
				if(!player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[]{
							"Greetings, and welcome to the tutorial!\nPlease press E.",
							"Good! That's exactly how you progress a conversation,\nnow how about you try moving with the arrow keys?"
					}, new int[] { 3, 3 });
			
			if(tutorialProgress == 1)
				if(player.getLeft() || player.getRight() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] { 
							"Good job, now try jumping by pressing space!" 
							}, new int[] { 3 });
			
			if(tutorialProgress == 2)
				if(player.getJumping() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] { 
							"Nice one!\nThose are the basic movements you can do.\n"
							+ "Why don't you try exploring the map for a bit, see what we can find." 
									}, new int[] { 3 });
			
			if(tutorialProgress == 3)
				if(player.getx() > 1420 && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] { 
							"Hold up, see that hole up ahead? Try jumping over it!\n",
							"Shouldn't be too hard..."
							}, new int[] { 3, 0 });
					
			if(tutorialProgress == 4)
				if(player.getx() > 1820 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, new String[] { 
							"Good job!\n"
							}, new int[] { 3 });
			
			if(tutorialProgress == 5)
				if(player.getx() > 2600 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Nice, looks like you have mastered the movement."
					}, new int[] { 3 });
			
			if(tutorialProgress == 6)
				if(player.getx() > 2950 && !player.getInConversation() && !player.getFalling())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Now what about attacks? Try pressing A"
					}, new int[] { 3 });
			
			if(tutorialProgress == 7)
				if(player.getFireBallSmallDoneCasting() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Excellent, that's a small fireball. See how the blue bar in the upper\n"
							+ "left corner dropped a bit? That's your mana bar, all spells\n"
							+ "consume mana.",
							"Don't worry though, it will regenerate back up automatically.",
							"Hey, why don't you try holding the up arrow whilst pressing S?"
					}, new int[] { 3, 3, 3 });
			
			if(tutorialProgress == 8)
				if(player.getFireBallLargeDoneCasting() && player.getUp() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Nice one, not only did you cast a large fireball which is more\n"
							+ "powerful, you also aimed it!", 
							"You can do the same thing with the small fireball, and you can\n"
							+ "even aim it downwards by holding down the down arrow.",
							"Play around with it a bit if you want, when you feel ready to\n"
							+ "proceed, press the D button."
					}, new int[] { 3, 3, 3 });
			
			if(tutorialProgress == 9)
				if(player.getDashing() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"That's a dash attack, as you could see it will allow you to dash\n"
							+ "forward, dealing damage to all enemies that you pass along the\n"
							+ "way!",
							"You might also have noticed that the yellow bar decreased as you\n"
							+ "used dash, that's the stamina bar, all your physical attacks\n"
							+ "drain your stamina.",
							"Don't worry though, just as with your mana, your stamina will\n"
							+ "regenerate automatically. For our final attack, try pressing F."
					}, new int[] { 3, 3, 3 });
			
			if(tutorialProgress == 10)
				if(player.getPunching() && !player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Simple, isn't it?\n"
							+ "The punch is quite useful if you get up close to your enemy to beat\n"
							+ "the living shit out of them."
					}, new int[] { 3});
			
			
			if(tutorialProgress == 11)
				if(!player.getInConversation())
				{
					succubus = new Succubus(tileMap, false, false, false, false, false, "Rui", 3500, player.gety(), this);
					enemies.add(succubus);
					characterList.add(succubus);
					
					
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Watch out, it looks like an enemy has appeared right in front of you!\n"
							+ "Try using the attacks you just learnt to defeat it!"
					}, new int[] { 3 });
				}
			
			if(tutorialProgress == 12)
				if(succubus.isDead())
					if(!player.getInConversation())
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"Good job!",
							"Did you hear that? It looks like the way forward jus opened up.\n"
							+ "Why don't you enter the temple so see what awaits you inside?"
					}, new int[] { 3, 3 });
					else
						if(player.getConversationBox().getConversationTracker() == 1)
						{
							JukeBox.play("Close");
							tileMap.loadMap("/Maps/TutorialB.map");
						}


			if(tutorialProgress == 13)
			{
				if(!player.getInConversation() && player.getx() > 3800)
					player.getConversationBox().startConversation(player, null, null, new String[] {
							"This hole is much larger than the last one...",
							"Yes, you would never be able to cross it with a normal jump.",
							"Fortunately, you also know how to glide through the air,\n"
							+ "whenever you are in the air, try holding down E to\n"
							+ "start gliding!"
					}, new int[] { 0, 3, 3, 3 });
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}