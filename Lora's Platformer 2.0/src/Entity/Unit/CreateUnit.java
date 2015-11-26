package Entity.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import TileMap.TileMap;

public class CreateUnit 
{
	
	protected TileMap tileMap;
	
	static ArrayList<ArrayList<UnitData>> characterList;
//	ArrayList<ArrayList<Individual>> group = new ArrayList<ArrayList<Individual>>(4);
	
	public CreateUnit(TileMap tileMap)
	{
		this.tileMap = tileMap;
		units = new HashMap<String, ArrayList<UnitData>>();
		characterList = new ArrayList<ArrayList<UnitData>>(4);
		
		for(int i = 0; i < 4; i++)
		{
			ArrayList<UnitData> tempList = new ArrayList<UnitData>();
			characterList.add(tempList);
		}
		
		units.put("LorasCavern", characterList.get(0));
		units.put("MysteriousDungeon", characterList.get(1));
		units.put("FionasSanctum", characterList.get(2));
		units.put("DeepWoods", characterList.get(3));
		
		
		
	}
	
	public static int getCharacterListSize() { return characterList.size(); }
	
	public static void addUnit(String map, UnitData unitData)
	{
		units.get(unitData.getCurrentMap()).add(unitData);
	}
	
	public static void resetUnitList(String map)
	{
		units.get(map).clear();
	}
	
	public static ArrayList<UnitData> getUnitDataList(String map)
	{
		return units.get(map);
	}
	
	public static ArrayList<UnitData> getUnitDataList(int index)
	{
		try
		{
			if(index == 0)
			{
				if(units.get("LorasCavern") != null)
				{
					return units.get("LorasCavern");
				}
				
			}
			else if(index == 1)
			{
				if(units.get("LorasCavern") != null)
				{
					return units.get("MysteriousDungeon");
				}
			}
			else if(index == 2)
			{
				return units.get("FionasSanctum");
			}
			else if(index == 3)
			{
				return units.get("DeepWoods");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	
		return null;
	}
	
	
	public static HashMap<String, ArrayList<UnitData>> units = new HashMap<String, ArrayList<UnitData>>();
	
}
