package Entity.Doodad.Activatable;

import java.util.HashMap;

import TileMap.TileMap;

public class CreateDoodad 
{

	protected TileMap tileMap;
	
	public CreateDoodad(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		
		doodadName = new HashMap<String, String>();
		doodadName.put(Doors.Boss.toString(), "Boss Door");
		
		doodadName.put(Chests.Common.toString(), "Common Chest");
		doodadName.put(Chests.Uncommon.toString(), "Uncommon Chest");
		doodadName.put(Chests.Rare.toString(), "Rare Chest");
		
		
	}
	
	public static enum Doors
	{
		Boss
	}
	
	public static enum Chests
	{
		Common,
		Uncommon,
		Rare
	}
	
	
	public static HashMap<String, String> doodadName = new HashMap<String, String>();
}
