package Entity.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import Entity.Unit.Unit;

public class CreateBuff 
{
	
	public static ArrayList<ArrayList<BuffData>> buffList;
	
	
	public CreateBuff()
	{		
		
		buffDescriptionName = new HashMap<String, String>();
		buffDescriptionName.put(Buffs.RestoreHealth.toString(), "Restore health");
		buffDescriptionName.put(Buffs.RestoreMana.toString(), "Restore mana");
		buffDescriptionName.put(Buffs.RestoreStamina.toString(), "Restore stamina");
		
		buffDescriptionName.put(Buffs.Dry.toString(), "Dry");
		buffDescriptionName.put(Buffs.Wet.toString(), "Wet");
		buffDescriptionName.put(Buffs.Drenched.toString(), "Drenched");
		buffDescriptionName.put(Buffs.Hot.toString(), "Hot");
		buffDescriptionName.put(Buffs.Warm.toString(), "Warm");
		buffDescriptionName.put(Buffs.Cold.toString(), "Cold");
		buffDescriptionName.put(Buffs.Freezing.toString(), "Freezing");
		
		
		buffDescription = new HashMap<String, String>();
		buffDescription.put(Buffs.RestoreHealth.toString(), "Restoring health over time.");
		buffDescription.put(Buffs.RestoreMana.toString(), "Restoring mana over time.");
		buffDescription.put(Buffs.RestoreStamina.toString(), "Restoring stamina over time.");
		
		buffDescription.put(Buffs.Dry.toString(), "You are dry.");
		buffDescription.put(Buffs.Wet.toString(), "You are wet.");
		buffDescription.put(Buffs.Drenched.toString(), "You are drenched.");
		buffDescription.put(Buffs.Hot.toString(), "You are hot.");
		buffDescription.put(Buffs.Warm.toString(), "You are warm.");
		buffDescription.put(Buffs.Cold.toString(), "You are cold.");
		buffDescription.put(Buffs.Freezing.toString(), "You are freezing!");
		
	}
	
	
	public static enum Buffs
	{
		RestoreHealth,
		RestoreMana,
		RestoreStamina,
		Dry,
		Wet,
		Drenched,
		Hot,
		Warm,
		Cold,
		Freezing
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
