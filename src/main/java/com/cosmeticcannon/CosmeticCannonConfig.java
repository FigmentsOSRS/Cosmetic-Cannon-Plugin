package com.cosmeticcannon;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("cosmeticcannon")
public interface CosmeticCannonConfig extends Config
{
	@ConfigSection(
		name = "General",
		description = "Global settings",
		position = 0
	)
	String generalSection = "general";

	@ConfigItem(
		keyName = "swapEnabled",
		name = "Enable swap",
		description = "Master toggle. When off, all cannonball projectiles are unchanged.",
		section = generalSection,
		position = 0
	)
	default boolean swapEnabled()
	{
		return true;
	}

	@ConfigItem(
		keyName = "swapMode",
		name = "Mode",
		description = "<html>Random: each shot picks a random projectile from the preset list.<br>" +
			"Rainbow: cycles through a spectrum of colours shot by shot.<br>" +
			"Custom: choose a replacement per firing direction from the preset list.<br>" +
			"Experimental: manually enter a projectile ID per firing direction.</html>",
		section = generalSection,
		position = 1
	)
	default SwapMode swapMode()
	{
		return SwapMode.CUSTOM;
	}

	// -------------------------------------------------------------------------
	// Custom direction overrides — preset dropdown per direction.
	// Active when Mode is set to Custom.
	// -------------------------------------------------------------------------

	@ConfigSection(
		name = "Custom Direction Overrides",
		description = "Only used when Mode is set to Custom. " +
			"Choose a replacement projectile for each of the 8 firing directions. " +
			"None leaves that direction unchanged.",
		position = 2
	)
	String directionSection = "directions";

	@ConfigItem(
		keyName = "projectileNorth",
		name = "North",
		description = "Replacement projectile when the cannon fires North.",
		section = directionSection,
		position = 0
	)
	default ProjectileSwap projectileNorth()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileNorthEast",
		name = "North-East",
		description = "Replacement projectile when the cannon fires North-East.",
		section = directionSection,
		position = 1
	)
	default ProjectileSwap projectileNorthEast()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileEast",
		name = "East",
		description = "Replacement projectile when the cannon fires East.",
		section = directionSection,
		position = 2
	)
	default ProjectileSwap projectileEast()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileSouthEast",
		name = "South-East",
		description = "Replacement projectile when the cannon fires South-East.",
		section = directionSection,
		position = 3
	)
	default ProjectileSwap projectileSouthEast()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileSouth",
		name = "South",
		description = "Replacement projectile when the cannon fires South.",
		section = directionSection,
		position = 4
	)
	default ProjectileSwap projectileSouth()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileSouthWest",
		name = "South-West",
		description = "Replacement projectile when the cannon fires South-West.",
		section = directionSection,
		position = 5
	)
	default ProjectileSwap projectileSouthWest()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileWest",
		name = "West",
		description = "Replacement projectile when the cannon fires West.",
		section = directionSection,
		position = 6
	)
	default ProjectileSwap projectileWest()
	{
		return ProjectileSwap.NONE;
	}

	@ConfigItem(
		keyName = "projectileNorthWest",
		name = "North-West",
		description = "Replacement projectile when the cannon fires North-West.",
		section = directionSection,
		position = 7
	)
	default ProjectileSwap projectileNorthWest()
	{
		return ProjectileSwap.NONE;
	}

	// -------------------------------------------------------------------------
	// Experimental direction overrides — raw projectile ID entry per direction.
	// Active when Mode is set to Experimental. 0 = no swap for that direction.
	// -------------------------------------------------------------------------

	@ConfigSection(
		name = "Experimental Direction Overrides",
		description = "Only used when Mode is set to Experimental. " +
			"Enter a raw projectile ID for each of the 8 firing directions. " +
			"0 leaves that direction unchanged. " +
			"Use the RuneLite DevTools projectile inspector to find IDs.",
		position = 3
	)
	String experimentalSection = "experimental";

	@ConfigItem(
		keyName = "experimentalNorth",
		name = "North",
		description = "Raw projectile ID when the cannon fires North. 0 = no swap.",
		section = experimentalSection,
		position = 0
	)
	default int experimentalNorth()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalNorthEast",
		name = "North-East",
		description = "Raw projectile ID when the cannon fires North-East. 0 = no swap.",
		section = experimentalSection,
		position = 1
	)
	default int experimentalNorthEast()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalEast",
		name = "East",
		description = "Raw projectile ID when the cannon fires East. 0 = no swap.",
		section = experimentalSection,
		position = 2
	)
	default int experimentalEast()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalSouthEast",
		name = "South-East",
		description = "Raw projectile ID when the cannon fires South-East. 0 = no swap.",
		section = experimentalSection,
		position = 3
	)
	default int experimentalSouthEast()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalSouth",
		name = "South",
		description = "Raw projectile ID when the cannon fires South. 0 = no swap.",
		section = experimentalSection,
		position = 4
	)
	default int experimentalSouth()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalSouthWest",
		name = "South-West",
		description = "Raw projectile ID when the cannon fires South-West. 0 = no swap.",
		section = experimentalSection,
		position = 5
	)
	default int experimentalSouthWest()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalWest",
		name = "West",
		description = "Raw projectile ID when the cannon fires West. 0 = no swap.",
		section = experimentalSection,
		position = 6
	)
	default int experimentalWest()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "experimentalNorthWest",
		name = "North-West",
		description = "Raw projectile ID when the cannon fires North-West. 0 = no swap.",
		section = experimentalSection,
		position = 7
	)
	default int experimentalNorthWest()
	{
		return 0;
	}
}
