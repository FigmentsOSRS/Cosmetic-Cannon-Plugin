package com.cosmeticcannon;

/**
 * Top-level swap mode. Controls how the replacement projectile is chosen.
 */
public enum SwapMode
{
	RANDOM  ("Random"),
	RAINBOW ("Rainbow Cycle"),
	CUSTOM  ("Custom (per direction)");

	private final String displayName;

	SwapMode(String displayName)
	{
		this.displayName = displayName;
	}

	@Override
	public String toString()
	{
		return displayName;
	}
}
