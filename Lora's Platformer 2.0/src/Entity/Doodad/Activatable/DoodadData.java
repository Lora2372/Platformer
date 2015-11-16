package Entity.Doodad.Activatable;

import java.util.HashMap;

import TileMap.TileMap;

public class DoodadData 
{

	protected TileMap tileMap;
	
	public DoodadData(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		
		doodadName = new HashMap<String, String>();
		doodadName.put(Doors.Boss.toString(), "Boss Door");
		
		doodadName.put(Chests.Common.toString(), "Common Chest");
		doodadName.put(Chests.Uncommon.toString(), "Uncommon Chest");
		doodadName.put(Chests.Rare.toString(), "Rare Chest");
		
		doodadName.put(Other.CampFire.toString(), "Campfire");
		doodadName.put(Other.Lever.toString(), "Lever");
		doodadName.put(Other.Portal.toString(), "Portal");
		doodadName.put(Other.Shrine.toString(), "Shrine");
		doodadName.put(Other.Sign.toString(), "Sign");
		doodadName.put(Other.StatueSave.toString(), "Shrine of Saving");		
	}
	
	public static enum Doors
	{
		Boss,
		Village
	}
	
	public static enum Chests
	{
		Common,
		Uncommon,
		Rare
	}
	
	public static enum Other
	{
		CampFire,
		Lever,
		Portal,
		Shrine,
		Sign,
		StatueSave
	}
	
	
	public static HashMap<String, String> doodadName = new HashMap<String, String>();
}
