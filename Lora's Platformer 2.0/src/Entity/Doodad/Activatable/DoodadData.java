package Entity.Doodad.Activatable;

import java.util.ArrayList;
import java.util.HashMap;

import GameState.GameStateManager;

public class DoodadData 
{
	
	static ArrayList<ArrayList<TemporaryDoodadData>> doodadList;
	
	public DoodadData()
	{
		
		doodads = new HashMap<String, ArrayList<TemporaryDoodadData>>();
		doodadList = new ArrayList<ArrayList<TemporaryDoodadData>>(GameStateManager.GameMaps.values().length - 1);
		
		for(int i = 0; i < GameStateManager.GameMaps.values().length - 1; i++)
		{
			ArrayList<TemporaryDoodadData> tempList = new ArrayList<TemporaryDoodadData>();
			doodadList.add(tempList);
			doodads.put(GameStateManager.GameMaps.values()[i + 1].toString(), doodadList.get(i));
		}
		
		
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
	
	public static int getDoodadListSize() { return doodadList.size(); }
	
	public static void addDoodad(String currentMap, TemporaryDoodadData doodadData)
	{
		doodads.get(currentMap).add(doodadData);
	}
	
	public static void resetDoodadList(String map)
	{
		doodads.get(map).clear();
	}
	
	public static ArrayList<TemporaryDoodadData> getDoodadDataList(String map)
	{
		return doodads.get(map);
	}
	
	
	public static HashMap<String, String> doodadName = new HashMap<String, String>();
	
	public static HashMap<String, ArrayList<TemporaryDoodadData>> doodads  = new HashMap<String, ArrayList<TemporaryDoodadData>>();
}
