package Entity.Item;

import Audio.JukeBox;
import Entity.MapObject;
import Entity.Player.Player;
import GameState.MainMap.MainMap;
import Main.Content;
import TileMap.TileMap;

public class Coin extends Item
{

	public Coin
		(
			TileMap tileMap,
			MainMap mainMap,
			boolean inWorld, 
			double locationX, 
			double locationY, 
			MapObject owner, 
			int stacks,
			String coinType
		) 
	{
		super
			(
				tileMap,
				mainMap,
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
				ItemData.getDescriptionName(coinType.toString()),
				ItemData.getDescription(coinType.toString())
			);

	}
	
	public void setItem()
	{
		if(itemType.equals(ItemData.Coins.Gold.toString()))
		{
			sprites = Content.CoinGold[0];
		}
		else if(itemType.equals(ItemData.Coins.Silver.toString()))
		{
			sprites = Content.CoinSilver[0];
		}
	}
	
	public void interact(Player player)
	{
		JukeBox.play("Coin");
		if(itemType.equals(ItemData.Coins.Silver.toString()))
		{
			player.addSilver(stacks);
		}
		else if(itemType.equals(ItemData.Coins.Gold.toString()))
		{
			player.addGold(stacks);
		}
		
		removeMe = true;
	}
}