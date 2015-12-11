package Entity.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import GameState.GameStateManager;

public class CreateUnit 
{

	static ArrayList<ArrayList<TemporaryUnitData>> characterList;
	
	public CreateUnit()
	{
		units = new HashMap<String, ArrayList<TemporaryUnitData>>();
		characterList = new ArrayList<ArrayList<TemporaryUnitData>>(GameStateManager.GameMaps.values().length - 1);
		
		for(int i = 0; i < GameStateManager.GameMaps.values().length - 1; i++)
		{
			ArrayList<TemporaryUnitData> tempList = new ArrayList<TemporaryUnitData>();
			characterList.add(tempList);
			units.put(GameStateManager.GameMaps.values()[i + 1].toString(), characterList.get(i));
			
		}		
		
	}
	
	public static enum Units
	{
		Bunny,
		Fiona,
		Liadrin,
		Skeleton,
		Slug,
		Succubus,
		Wolf
	};
	
	public static int getCharacterListSize() { return characterList.size(); }
	
	public static void addUnit(String currentMap, TemporaryUnitData unitData)
	{
		units.get(currentMap).add(unitData);
	}
	
	public static void resetUnitList(String map)
	{
		units.get(map).clear();
	}
	
	public static ArrayList<TemporaryUnitData> getUnitDataList(String map)
	{
		return units.get(map);
	}
	
	
	public static HashMap<String, ArrayList<TemporaryUnitData>> units = new HashMap<String, ArrayList<TemporaryUnitData>>();
	
}
