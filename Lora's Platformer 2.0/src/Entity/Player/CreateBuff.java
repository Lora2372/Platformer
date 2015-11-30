package Entity.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Unit.Unit;
import TileMap.TileMap;

public class CreateBuff 
{
	protected TileMap tileMap;
	
	public static ArrayList<ArrayList<BuffData>> buffList;
	
	
	public CreateBuff(TileMap tileMap)
	{
		this.tileMap = tileMap;
		
		
		buffDescriptionName = new HashMap<String, String>();
		buffDescriptionName.put(Buffs.RestoreHealth.toString(), "Restore health");
		buffDescriptionName.put(Buffs.RestoreMana.toString(), "Restore mana");
		buffDescriptionName.put(Buffs.RestoreStamina.toString(), "Restore stamina");
		
		
		buffDescription = new HashMap<String, String>();
		buffDescription.put(Buffs.RestoreHealth.toString(), "Restoring health over time.");
		buffDescription.put(Buffs.RestoreMana.toString(), "Restoring mana over time.");
		buffDescription.put(Buffs.RestoreStamina.toString(), "Restoring stamina over time.");
		
	}
	
	
	public static enum Buffs
	{
		RestoreHealth,
		RestoreMana,
		RestoreStamina
	}
	
	
	
	public static HashMap<String, String> buffDescriptionName = new HashMap<String, String>();
	public static HashMap<String, String> buffDescription = new HashMap<String, String>();
	public static HashMap<String, ArrayList<BuffData>> buffs = new HashMap<String, ArrayList<BuffData>>();
	
	public static String getDescriptionName(String buff)
	{		
		return buffDescriptionName.get(buff);
	}
	
	public static String getDescription(String buff)
	{
		return buffDescription.get(buff);
	}
	
	
	public static Buff createBuff(CreateBuff.Buffs buff, int duration, int restoreAmount, Unit owner, BufferedImage sprites)
	{
		return new Buff(buff, duration, restoreAmount, owner, sprites);
	}
	
}
