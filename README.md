# Cosmetic Cannon

A RuneLite plugin that replaces dwarf multicannon projectiles with custom visuals, configurable per firing direction.

## Features

- Swap cannonball projectiles with any of 70+ preset visual effects sourced from boss projectiles and other in-game graphics
- Four swap modes: Random, Rainbow Cycle, Custom (per direction), and Experimental (raw ID entry)
- Independent control over each of the 8 cannon firing directions (N, NE, E, SE, S, SW, W, NW)
- Shared rainbow counter cycles through a full colour spectrum shot by shot
- Supports all dwarf multicannon variants including the standard cannon and reskin

## Swap Modes

| Mode | Behaviour |
|---|---|
| Random | Each shot picks a random projectile from the full preset list |
| Rainbow Cycle | Cycles through a spectrum of colours with each shot fired |
| Custom | Choose a specific replacement preset per firing direction |
| Experimental | Enter a raw projectile ID per firing direction for unlisted effects |

## Supported Projectile Types

| Type | Projectile ID |
|---|---|
| Steel cannonball | 53 |
| Granite cannonball | 1443 |
| Cannon reskin variant | 2018 |

## Notes

- Projectile IDs in the preset list were sourced from the [Projectile Override plugin by Loze-Put](https://github.com/Loze-Put/projectile-override) and the [RuneMonk entity viewer](https://runemonk.com/tools/entityviewer-beta)
- The swap technique is adapted from the Chinbompa plugin by sigterm
- In Experimental mode, use the RuneLite DevTools projectile inspector to identify projectile IDs in-game
- Custom Direction Overrides are ignored when mode is set to Random or Rainbow Cycle
- Experimental Direction Overrides are ignored when mode is not set to Experimental
