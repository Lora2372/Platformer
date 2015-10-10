package Entity.Doodad.Activatable;

import Main.Content;
import TileMap.TileMap;
import Audio.JukeBox;
import Entity.Doodad.Doodad;
import Entity.Player.Player;

public class ActivatableChest extends Doodad
{


	public ActivatableChest(
			TileMap tileMap, 
			double spawnX,
			double spawnY,
			String chestType
			) 
	{
		super(tileMap, 
				spawnX, 
				spawnY, 
				60, 
				60,
				60,
				60,
				false, 
				true, 
				false,
				true,
				false,
				chestType
				);
	}
	
	public void setDoodad(int currentAction)
	{
		if(doodadType.equals("Common"))
		{
			if(currentAction == 0)
				sprites = Content.ChestCommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestCommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestCommonOpened[0];
		}
		else if(doodadType.equals("Uncommon"))
		{
			if(currentAction == 0)
				sprites = Content.ChestUncommonClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestUncommonOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestUncommonOpened[0];
		}
		else if(doodadType.equals("Rare"))
		{
			if(currentAction == 0)
				sprites = Content.ChestRareClosed[0];
			else if(currentAction == 1)
				sprites = Content.ChestRareOpening[0];
			else if(currentAction == 2)
				sprites = Content.ChestRareOpened[0];
		}
	}
	
	public void interact(Player player)
	{
		active = true;
	}
	
	public void activateSound() 
	{ 
		JukeBox.play("OpenChest" + doodadType);
	}
	
}
