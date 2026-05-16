package com.cosmeticcannon;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PluginDescriptor(
	name = "Cosmetic Cannon",
	description = "Replaces cannonball projectiles with custom visuals, configurable per firing direction",
	tags = {"cannon", "cosmetic", "projectile", "cannonball", "visual", "swap"}
)
public class CosmeticCannonPlugin extends Plugin
{
	private static final Logger log = LoggerFactory.getLogger(CosmeticCannonPlugin.class);

	// Projectile IDs for cannonball types fired by the dwarf multicannon.
	private static final int CANNONBALL         = 53;   // standard cannonball
	private static final int GRANITE_CANNONBALL = 1443; // granite cannonball
	private static final int RESKIN_CANNONBALL  = 2018; // cannon reskin variant (confirmed in-game)

	// Shared rainbow counter - advances once per swapped shot across all directions.
	private int rainbowIndex = 0;

	@Inject
	private Client client;

	@Inject
	private CosmeticCannonConfig config;

	@Subscribe
	public void onProjectileMoved(ProjectileMoved event)
	{
		if (!config.swapEnabled())
		{
			return;
		}

		Projectile projectile = event.getProjectile();
		int id = projectile.getId();

		if (id != CANNONBALL && id != GRANITE_CANNONBALL && id != RESKIN_CANNONBALL)
		{
			return;
		}

		// onProjectileMoved fires every tick for each active projectile, not just
		// on spawn. Guard against processing a projectile we already suppressed.
		if (projectile.getRemainingCycles() <= 0)
		{
			return;
		}

		WorldPoint src = projectile.getSourcePoint();
		WorldPoint tgt = projectile.getTargetPoint();

		if (src == null || tgt == null)
		{
			return;
		}

		int dx = tgt.getX() - src.getX();
		int dy = tgt.getY() - src.getY();

		if (dx == 0 && dy == 0)
		{
			log.debug("Cannonball projectile {} has identical source and target; skipping swap", id);
			return;
		}

		int replacementId = resolveReplacement(dx, dy);
		if (replacementId <= 0)
		{
			return;
		}

		// Chinbompa technique: spawn a replacement projectile copying all timing
		// and trajectory parameters, then suppress the original by zeroing its end cycle.
		client.createProjectile(
			replacementId,
			src, projectile.getStartHeight(), projectile.getSourceActor(),
			tgt, projectile.getEndHeight(),   projectile.getTargetActor(),
			client.getGameCycle(), projectile.getEndCycle(),
			projectile.getSlope(), 0
		);
		projectile.setEndCycle(0);

		log.debug("Swapped cannonball {} -> {} (mode={}, dx={}, dy={})",
			id, replacementId, config.swapMode(), dx, dy);
	}

	/**
	 * Resolves the replacement projectile ID based on the current swap mode.
	 * Returns <= 0 if no swap should occur.
	 */
	private int resolveReplacement(int dx, int dy)
	{
		switch (config.swapMode())
		{
			case RANDOM:
				return ProjectileSwap.random().getProjectileId();

			case RAINBOW:
			{
				int rainbowId = ProjectileSwap.RAINBOW_SEQUENCE[rainbowIndex % ProjectileSwap.RAINBOW_SEQUENCE.length];
				rainbowIndex++;
				return rainbowId;
			}

			case CUSTOM:
			{
				int dirIndex = directionIndex(dx, dy);
				return swapForDirection(dirIndex).getProjectileId();
			}

			default:
				return -1;
		}
	}

	/**
	 * Maps a source->target delta to one of 8 compass direction indices.
	 * 0=E, 1=NE, 2=N, 3=NW, 4=W, 5=SW, 6=S, 7=SE
	 * OSRS world coords: +X = east, +Y = north.
	 */
	private int directionIndex(int dx, int dy)
	{
		return (int) Math.round(Math.atan2(dy, dx) / (Math.PI / 4)) & 7;
	}

	/**
	 * Returns the configured ProjectileSwap preset for the given direction index.
	 * Used in Custom mode.
	 */
	private ProjectileSwap swapForDirection(int dirIndex)
	{
		switch (dirIndex)
		{
			case 0: return config.projectileEast();
			case 1: return config.projectileNorthEast();
			case 2: return config.projectileNorth();
			case 3: return config.projectileNorthWest();
			case 4: return config.projectileWest();
			case 5: return config.projectileSouthWest();
			case 6: return config.projectileSouth();
			case 7: return config.projectileSouthEast();
			default: return ProjectileSwap.NONE;
		}
	}

	@Provides
	CosmeticCannonConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CosmeticCannonConfig.class);
	}
}
