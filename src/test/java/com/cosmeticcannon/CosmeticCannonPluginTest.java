package com.cosmeticcannon;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CosmeticCannonPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CosmeticCannonPlugin.class);
		RuneLite.main(args);
	}
}
