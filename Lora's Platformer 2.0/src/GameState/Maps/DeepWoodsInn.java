package GameState.Maps;

import javax.imageio.ImageIO;

import Entity.Player.Player;
import GameState.GameStateManager;
import GameState.Conversation.ConversationState;
import GameState.MainMap.MainMap;
import TileMap.TileMap;

public class DeepWoodsInn extends MainMap
{

	public DeepWoodsInn
		(
			GameStateManager gameStatemanager,
			TileMap tileMap,
			Player player,
			ConversationState conversationState
		) 
	{
		super
			(
				gameStatemanager, 
				tileMap, 
				player, 
				conversationState, 
				"DeepWoods"
			);
		
		try
		{
			tileMap.loadTiles(ImageIO.read(getClass().getResource("/Art/Tilesets/LorasTilesetIndoorVillage.png")));
			tileMap.loadMap("/Maps/HouseA.map");
			tileMap.setPosition(0, 0);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		player.setTileMap(tileMap);
		
		player.setSpawnLocationX(200);
		player.setSpawnLocationY(800);
		
		doneInitializing = true;
	}


}
