package GameState;

import java.io.IOException;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Doodad.Activatable.ActivatableShrineMysteriousDungeon;
import Entity.Enemies.Fiona;
import TileMap.Background;
import TileMap.GameOver;

public class MysteriousDungeon extends MainMap
{
	protected boolean pathBlocked;
	
	protected ActivatableShrineMysteriousDungeon activatableShrine;
	
	protected Fiona fiona;
	
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
		
		spawnTorch(109, 480);
		
		spawnTorch(500, 480);
		
		player.setPosition(109, 200);
		player.setSpawnPoint(109, 200);
		player.setSpawning(true);
		
		spawnSuccubus(1600, 480, false);
		spawnSuccubus(2000, 120, false);
		spawnSuccubus(3000, 480, false);
		
		
		fiona = new Fiona(tileMap,false,false,false,false, "Fiona", player.getx() + 200, player.gety(), this);
		enemies.add(fiona);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new ActivatableShrineMysteriousDungeon(tileMap, gameStatemanager, this, 3760, 510, fiona);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		
		fiona.inControl(false);
		
		doneInitializing = true;
		bossEngaged = false;
		bossDefeated = false;
	}
	
	public void update()
	{
		super.update();
		
		if(bossEngaged)
		{
			if(fiona.isDead())
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
			
			fiona.inControl(true);
		}
		
		
	}


}
