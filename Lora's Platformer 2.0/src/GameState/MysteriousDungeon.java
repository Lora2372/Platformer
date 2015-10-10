package GameState;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Doodad.Activatable.ActivatableCave;
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
	
	protected boolean dungeonIntroduction;
	
	protected String[] conversation = new String[]
			{
				"Torches, someone must live here, or something..."
			};
	
	public MysteriousDungeon(GameStateManager gameStatemanager) 
	{
		super(gameStatemanager);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTileset.png")));
			tileMap.loadMap("/Maps/MysteriousDungeonA.map");
			tileMap.setPosition(0, 0);
			
			background = new Background(getClass().getResource("/Art/Backgrounds/UndergroundBackground.png"), 0.1);
			gameoverScreen = new GameOver(getClass().getResource("/Art/HUD/Foregrounds/GameOver.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		spawnTorch(109, 200);
		
		for(int i = 0; i < (3000 / (tileMap.getTileSize() * 10)); i++)
		{
			spawnTorch(109 + i * tileMap.getTileSize() * 10, 520);
		}
		
		spawnTorch(3508, 410);
		
		spawnTorch(3928, 410);
		
		player.setPosition(109, 200);
		player.setSpawnPoint(109, 200);
		player.setSpawning(true);
		
		spawnSuccubus(1600, 480, false);
		spawnSuccubus(2000, 120, false);
		spawnSuccubus(3000, 480, false);
		
		
		
		fiona = new Fiona(tileMap,false,false,false,false, true, "Fiona", player.getx() + 200, player.gety(), this, player);
		enemies.add(fiona);
		characterList.add(fiona);
		fiona.setHidden(true);
		
		activatableShrine = new ActivatableShrineMysteriousDungeon(tileMap, gameStatemanager, this, 3730, 510, fiona);
		activatables.add(activatableShrine);
		stuff.add(activatableShrine);
		
		
		fiona.inControl(false);
		
		doneInitializing = true;
		bossEngaged = false;
		bossDefeated = false;
	}
	
	
	
	public ArrayList<Entity.Unit> getCharacterList()
	{
		return characterList;
	}
	
	public ArrayList<Doodad> getStuff()
	{
		return stuff;
	}
	
	public void setDefeated(boolean b) { bossDefeated = b; }
	
	public void update()
	{
		super.update();
		
		if(!dungeonIntroduction)
		{
			if(player.getDirectionY() == 0 && player.gety() > 300)
			{
				if(!player.getConversationBox().inConversation())
				{

							int[] whoTalks = new int[]{0};
							
							player.getConversationBox().startConversation(player, null, null, conversation, whoTalks);
				}
				else
				{
					
					if(player.getConversationBox().getConversationTracker() >= conversation.length)
					{
						player.getConversationBox().endConversation();
						dungeonIntroduction = true;
						JukeBox.play("Female01EnterDungeon");
					}

				}

			}
		}
		
		if(bossEngaged)
		{
			if(bossDefeated)
			{
				bossEngaged = false;
				JukeBox.stop("MysteriousBattle");
				JukeBox.loop("MysteriousDungeon");
				
				ActivatableCave activatableCave = new ActivatableCave(tileMap, gameStateManager, 1468, 550);
				stuff.add(activatableCave);
				activatables.add(activatableCave);
				
				spawnTorch(1348, 410);
				spawnTorch(1588, 410);
				
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