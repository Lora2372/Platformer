package GameState.MainMap;

import Entity.Item.Item;
import Entity.Unit.Bunny;
import Entity.Unit.Liadrin;
import Entity.Unit.Skeleton;
import Entity.Unit.Slug;
import Entity.Unit.Succubus;
import Entity.Unit.Unit;
import Entity.Unit.UnitData;
import Entity.Unit.Wolf;

public class SpawnUnit 
	{
		
		protected MainMap mainMap;
		
		public SpawnUnit(MainMap mainMap)
		{
			this.mainMap = mainMap;
		}
		
		public Unit spawn(UnitData unitData)
		{
			Unit unit = null;
			
			if(unitData.getUnitType().equals("LiadrinFirstEncounter"))
			{
				mainMap.liadrin = new Liadrin(mainMap.tileMap, false, true, false, true, true, 2680, 1800, mainMap);
				mainMap.characterList.add(mainMap.liadrin);
			}
			
			if(unitData.getUnitType().equals("Slug"))
			{
				unit = spawnSlug
						(
							unitData.getFacingRight(),
							unitData.getFriendly(),
							unitData.getUntouchable(), 
							unitData.getInvulnerable(), 
							unitData.getUnkillable(),
							unitData.getName(),
							unitData.getSpawnLocationX(),
							unitData.getSpawnLocationY()
						);
	
			}
			
			if(unitData.getUnitType().equals("Succubus"))
			{
				unit = spawnSuccubus
						(
							unitData.getFacingRight(),
							unitData.getFriendly(),
							unitData.getUntouchable(), 
							unitData.getInvulnerable(), 
							unitData.getUnkillable(),
							unitData.getName(),
							unitData.getSpawnLocationX(),
							unitData.getSpawnLocationY()
						);
			}
			
			if(unitData.getUnitType().equals("Wolf"))
			{
				unit = spawnWolf
						(
							unitData.getFacingRight(),
							unitData.getFriendly(),
							unitData.getUntouchable(), 
							unitData.getInvulnerable(), 
							unitData.getUnkillable(),
							unitData.getName(),
							unitData.getSpawnLocationX(),
							unitData.getSpawnLocationY()
						);
			}
			if(unit != null)
			{
				for(int i = 0; i < unitData.getItems().size(); i++)
				{
					Item item = unitData.getItems().get(i);
					unit.getInventory().addItem(item);
					mainMap.items.add(item);
				}
				return unit;
			}
			
			return null;
		}
		
		public Slug spawnSlug
		(			
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY
		)
	{	
		String[] slugNames = new String[]
		{
				"Cookie",
				"Steven",
				"Morgan",
				"Tom",
				"Carl",
				"John"			
		};
		
		if(name == null)
		{
			name = slugNames[mainMap.RNG(0, slugNames.length - 1)];
		}
		
		Slug slug = new Slug(mainMap.tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, mainMap);
		mainMap.characterList.add(slug);
		return slug;
	}
	
	public Slug spawnSlug(double spawnLocationX, double spawnLocationY, boolean facingRight, String name)
	{
		return spawnSlug(facingRight, false, false, false, false, name, spawnLocationX, spawnLocationY);
	}
	
	
	public Succubus spawnSuccubus
		(			
			boolean facingRight,
			boolean friendly,
			boolean untouchable,
			boolean invulnerable,
			boolean unkillable,
			String name,
			double spawnLocationX,
			double spawnLocationY
		)
	{
		
		String[] succubiNames = new String[]
		{
			"Rui",
			"Domwena",
			"Elerlith",
			"Synys",
			"Kallith",
			"Fierneth",
			"Catvina",
			"Bronlissa",
			"Cariel",
			"Darxia",
			"Nimnys"
		};
		
		if(name == null)
		{
			name = succubiNames[mainMap.RNG(0, succubiNames.length - 1)];
		}
				
		Succubus succubus = new Succubus(mainMap.tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, mainMap);
		mainMap.characterList.add(succubus);
		return succubus;
	}
	
	public Succubus spawnSuccubus(double spawnLocationX, double spawnLocationY, boolean facingRight)
	{
		return spawnSuccubus(facingRight, false, false, false, false, null, spawnLocationX, spawnLocationY);
	}
	
	public Wolf spawnWolf
	(			
		boolean facingRight,
		boolean friendly,
		boolean untouchable,
		boolean invulnerable,
		boolean unkillable,
		String name,
		double spawnLocationX,
		double spawnLocationY
	)
	{
	
	String[] wolfNames = new String[]
	{
		"Rhuudym",
		"Pryyfenn",
		"Phathun",
		"Jhazeem",
		"Maahzon",
		"Gzaalum",
		"Drootom"
	};
	
	if(name == null)
	{
		name = wolfNames[mainMap.RNG(0, wolfNames.length - 1)];
	}
	
	Wolf wolf = new Wolf(mainMap.tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, mainMap);
	mainMap.characterList.add(wolf);
	return wolf;
	}
	
	public Wolf spawnWolf(double spawnLocationX, double spawnLocationY, boolean facingRight)
	{
	return spawnWolf(facingRight, false, false, false, false, null, spawnLocationX, spawnLocationY);
	}
	
	public Bunny spawnBunny
	(			
		boolean facingRight,
		boolean friendly,
		boolean untouchable,
		boolean invulnerable,
		boolean unkillable,
		String name,
		double spawnLocationX,
		double spawnLocationY
	)
	{
	
	String[] bunnyNames = new String[]
	{
		"Dodger"
	};
	
	if(name == null)
	{
		name = bunnyNames[mainMap.RNG(0, bunnyNames.length - 1)];
	}
	
	Bunny bunny = new Bunny(mainMap.tileMap, facingRight, friendly, untouchable, invulnerable, unkillable, name, spawnLocationX, spawnLocationY, mainMap);
	mainMap.characterList.add(bunny);
	return bunny;
	}
	
	public Bunny spawnBunny(double spawnLocationX, double spawnLocationY, boolean facingRight)
	{
	return spawnBunny(facingRight, false, false, false, false, null, spawnLocationX, spawnLocationY);
	}
	
	public Skeleton spawnSkeleton(double locationX, double locationY, boolean facingRight, String currentMap)
	{
		Skeleton skeleton = new Skeleton(mainMap.tileMap, facingRight, false, false, false, false, "Jesse Cox", locationX, locationY, mainMap);
		mainMap.characterList.add(skeleton);
		return skeleton;
	}
}
