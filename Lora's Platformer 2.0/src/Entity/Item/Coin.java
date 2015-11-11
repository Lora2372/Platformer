package Entity.Item;

import Audio.JukeBox;
import Entity.MapObject;
import Entity.Player.Player;
import Main.Content;
import TileMap.TileMap;

public class Coin extends Item
{

	public Coin(
			TileMap tileMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			String coinType
			) 
	{
		super(
				tileMap, 
				inWorld, 
				60, 
				60, 
				60, 
				60,
				0.3,
				0.3, 
				8, 
				locationX, 
				locationY,
				owner, 
				true, 
				stacks, 
				true, 
				coinType,
				CreateItem.getDescriptionName(coinType.toString()),
				CreateItem.getDescription(coinType.toString())
				
				);

	}
	
	public void setItem()
	{
		if(itemType.equals(CreateItem.Coins.Gold.toString()))
		{
			sprites = Content.CoinGold[0];
		}
		else if(itemType.equals(CreateItem.Coins.Silver.toString()))
		{
			sprites = Content.CoinSilver[0];
		}
	}
	
	public void interact(Player player)
	{
		JukeBox.play("Coin");
		if(itemType.equals(CreateItem.Coins.Silver.toString()))
		{
			player.addSilver(stacks);
		}
		else if(itemType.equals(CreateItem.Coins.Gold.toString()))
		{
			player.addGold(stacks);
		}
		
		removeMe = true;
	}
}