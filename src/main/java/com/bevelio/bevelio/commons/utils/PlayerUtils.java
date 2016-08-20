package com.bevelio.bevelio.commons.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerUtils
{
	public static void clear(Player player)
	{
		player.setHealth(player.getHealthScale());
		player.setFoodLevel(20);
		player.setSaturation(10f);
		player.setFireTicks(0);
		player.setFallDistance(0);
		player.setExp(0);
		player.setFlying(false);
		player.setAllowFlight(false);
		player.closeInventory();
		player.eject();
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[4]);
	}
	
	public static void reset(Player player, boolean setGameMode)
	{
		PlayerUtils.clear(player);
		
		if(setGameMode)
		{
			player.setFlying(false);
			player.setAllowFlight(false);
			player.setGameMode(GameMode.SURVIVAL);
		}
		
		for(PotionEffect effect : player.getActivePotionEffects())
		{
			player.removePotionEffect(effect.getType());
		}
	}
}
