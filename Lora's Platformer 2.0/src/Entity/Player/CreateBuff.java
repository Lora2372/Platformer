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
		buffDescriptionName.put(Buffs.Soaked.toString(), "Soaked");
		buffDescriptionName.put(Buffs.Hot.toString(), "Hot");
		buffDescriptionName.put(Buffs.Warm.toString(), "Warm");
		buffDescriptionName.put(Buffs.Cold.toString(), "Cold");
		buffDescriptionName.put(Buffs.Freezing.toString(), "Freezing");

		buffDescriptionName.put(Buffs.CampFire.toString(), "Campfire");
		
		
		buffDescription = new HashMap<String, String>();
		buffDescription.put(Buffs.RestoreHealth.toString(), "Restoring health over time.");
		buffDescription.put(Buffs.RestoreMana.toString(), "Restoring mana over time.");
		buffDescription.put(Buffs.RestoreStamina.toString(), "Restoring stamina over time.");
		
		buffDescription.put(Buffs.Dry.toString(), "Resistant to cold.");
		buffDescription.put(Buffs.Wet.toString(), "Susceptible to cold.");
		buffDescription.put(Buffs.Soaked.toString(), "Very susceptible to cold!");
		buffDescription.put(Buffs.Hot.toString(), "Regeneration slightly increased.");
		buffDescription.put(Buffs.Warm.toString(), "Normal regeneration.");
		buffDescription.put(Buffs.Cold.toString(), "Regeneration greatly reduced.");
		buffDescription.put(Buffs.Freezing.toString(), "Unable to regenerate!");

		buffDescription.put(Buffs.CampFire.toString(), "Keeps you warm and dry.");

	}
	
	
	public static enum Buffs
	{
		RestoreHealth,
		RestoreMana,
		RestoreStamina,
		Dry,
		Wet,
		Soaked,
		Hot,
		Warm,
		Cold,
		Freezing,
		CampFire
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
