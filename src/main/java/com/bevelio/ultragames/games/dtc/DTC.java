package com.bevelio.ultragames.games.dtc;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFromToEvent;

import com.bevelio.ultragames.commons.utils.WorldUtils;
import com.bevelio.ultragames.core.Match;
import com.bevelio.ultragames.core.Objective;
import com.bevelio.ultragames.team.Team;

/**
 * Destroy The Core Minigame
 * 
 * @author Bevelio
 */
public class DTC extends Match
{
	
	public DTC()
	{
		super("DTC", new String[] {});
	}
	
	@EventHandler
	public void onLavaFlow(BlockFromToEvent e)
	{
		Block block = e.getBlock();
		if(!this.isLive())
		{
			return;
		}
		
		if(!block.getType().isSolid())
		{
			for(Objective obj : this.getObjectives())
			{
				if(obj.isWithin(block.getLocation()))
				{
					obj.active = false;
					if(this.getRemainingTeams().size() <= 1)
					{
						this.end(this.getRemainingTeams().get(0));
					}
				}
			}
		}
	}
	
	@Override
	public void onStart()
	{	
	}
	
	@Override
	protected void generateObjective(Objective objective)
	{
		int radius = objective.radius;
		boolean hollow = true;
		   
		int bx = objective.location.getBlockX();
		int by = objective.location.getBlockY();
		int bz = objective.location.getBlockZ();
	     
		for(int x = bx - radius; x <= bx + radius; x++) 
		{
			for(int y = by - radius; y <= by + radius; y++) 
			{
				for(int z = bz - radius; z <= bz + radius; z++) 
				{
					double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y))) + 2.9;
					if(distance < radius * radius)
					{
						if(!(hollow && distance < ((radius - 1) * (radius - 1)))) 
						{
							new Location(objective.location.getWorld(), x, y, z).getBlock().setType(objective.material);
						}
						else 
						{
							new Location(objective.location.getWorld(), x, y, z).getBlock().setType(Material.STATIONARY_LAVA);
						}
					}
	                 
				}
			}
		}
	}
}
