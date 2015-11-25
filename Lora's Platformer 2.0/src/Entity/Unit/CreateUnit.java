package Entity.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import TileMap.TileMap;

public class CreateUnit 
{
	
	protected TileMap tileMap;
	
	static ArrayList<UnitData> characterList;
//	ArrayList<ArrayList<Individual>> group = new ArrayList<ArrayList<Individual>>(4);
	
	public CreateUnit(TileMap tileMap)
	{
		this.tileMap = tileMap;
		units = new HashMap<String, ArrayList<UnitData>>();
		characterList = new ArrayList<UnitData>();
		
		units.put("LorasCavern", characterList);
		
		
		
	}
	
	public static void loadUnitList(String map, UnitData unitData)
	{
		characterList.add(unitData);
	}
	
	public static void resetUnitList(String map)
	{
		units.get(map).clear();
	}
	
	public static ArrayList<UnitData> getUnits(String map)
	{
		return units.get(map);
	}
	
	
	public static HashMap<String, ArrayList<UnitData>> units = new HashMap<String, ArrayList<UnitData>>();
	
}
