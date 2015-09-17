package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Audio.JukeBox;
import Entity.Doodad.Activatable.ActivatableShrineMysteriousDungeon;
import Entity.Enemies.Succubus;
import TileMap.Background;
import TileMap.GameOver;

public class MysteriousDungeon extends MainMap
{
	protected boolean pathBlocked;
	
	protected ActivatableShrineMysteriousDungeon activatableShrine;
	
	protected Succubus temporaryBoss;
	
	protected boolean bossEngaged;
	protected boolean bossDefeated;
	
	
	public MysteriousDungeon(GameStateManager gameStatemanager) 
	{
		super(gameStatemanager);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/MysteriousDungeonA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Backgrounds/RockCave.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnTorch(109, 200);
		
		spawnTorch(109, 780);
		
		spawnTorch(500, 780);
		
		player.setPosition(109, 200);
		player.setSpawnPoint(109, 200);
		player.setSpawning(true);
		
		spawnSuccubus(1600, 780, false);
		spawnSuccubus(1883, 240, false);
		
		spawnSlug(2551, 780, false);
		
		temporaryBoss = createNewSuccubus("Fiona", player.getx() + 200, player.gety() - 10, false);
		
		activatableShrine = new ActivatableShrineMysteriousDungeon(tileMap, gameStatemanager, this, 3840, 690, temporaryBoss);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		
		temporaryBoss.inControl(false);
		
		doneInitializing = true;
		bossEngaged = false;
		bossDefeated = false;
	}
	
	public void update()
	{
		super.update();
		
		if(bossEngaged)
		{
			if(temporaryBoss.isDead())
			{
				bossEngaged = false;
				bossDefeated = true;
				JukeBox.stop("MysteriousBattle");
				JukeBox.loop("Dungeon1");
				
				
			}
		}
		
		if(bossEngaged || bossDefeated) return;
		if(!activatableShrine.getActive() && activatableShrine.getStartConversation())
		{
			bossEngaged = true;
			JukeBox.stop("MysteriousConversation");
			JukeBox.loop("MysteriousBattle");
			
			temporaryBoss.inControl(true);
		}
		
		
	}


}
