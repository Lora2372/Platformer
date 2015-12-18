package Entity.Item;

import Entity.MapObject;
import Entity.Unit.Unit;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Bomb extends Item
{

	protected ItemData.Bombs BombType;
	
	public Bomb
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			ItemData.Bombs BombType
		)
	{
		super
			(
				tileMap,
				mainMap,
				inWorld, 
				32, 
				64, 
				32, 
				64,
				0.3,
				0.3, 
				8, 
				locationX, 
				locationY,
				owner, 
				true, 
				stacks, 
				true,
				true,
				BombType.toString(),
				ItemData.getDescriptionName(BombType.toString()),
				ItemData.getDescription(BombType.toString())
			);
		
		this.BombType = BombType;

	}
	
	public void setItem()
	{
		sprites = Content.Bomb[0];
		portrait = Content.PortraitBomb[0];

	}
	
	public void use(Unit user)
	{
		super.use(user);
		
		Entity.Doodad.Bomb bomb = mainMap.spawnDoodad.spawnBomb(user.getLocationX(), user.getLocationY());
		bomb.activateBomb();
	}
	
	public void playSound()
	{
		// Pick up sound
	}
}