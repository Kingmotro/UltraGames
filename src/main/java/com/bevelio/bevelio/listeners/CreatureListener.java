package com.bevelio.bevelio.listeners;

import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.bevelio.bevelio.core.Match;
import com.bevelio.bevelio.core.MatchManager;
import com.bevelio.bevelio.plugin.BevelioPlugin;

public class CreatureListener implements Listener
{
	private MatchManager mm;
	
	public CreatureListener()
	{
		mm = BevelioPlugin.getMatchManager();
	}
	
	@EventHandler
	public void onMonsterTarget(EntityTargetLivingEntityEvent e) {
		Match game = mm.getMatch();
		if(game == null) return;
		if(e.isCancelled()) return;
		if(!(e.getTarget() instanceof Player)) return;
		Player player = (Player) e.getTarget();
		e.setCancelled(!mm.isPlaying(player));
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void onWorldCreature(CreatureSpawnEvent event) {
		Match game = mm.getMatch();
		if(game == null) return;

	    if ((!game.creatureAllow) && (!game.creatureAllowForce))
	    {
	    	event.setCancelled(true);
	    }
	}
}
