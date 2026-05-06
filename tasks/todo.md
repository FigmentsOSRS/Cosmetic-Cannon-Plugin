# Cosmetic Cannon Plugin

## Status: Working ✓

---

## Spec

**Goal:** Replace cannonball projectiles (steel ID 53, granite ID 1443) with custom
projectiles on a per-firing-direction basis. The cannon rotates through 8 compass
directions (N, NE, E, SE, S, SW, W, NW); each direction gets its own configurable
replacement projectile ID.

**Technique:** Chinbompa swap — on `onProjectileMoved`, create a replacement
projectile mirroring all original parameters, then call `setEndCycle(0)` on the
original to suppress it.

**Direction detection:** `atan2(dy, dx)` on the source→target WorldPoint delta,
rounded to the nearest 45° and masked to [0,7].
  - 0=E, 1=NE, 2=N, 3=NW, 4=W, 5=SW, 6=S, 7=SE

**Confirmed IDs:**
  - Steel cannonball: 53 (confirmed in-game)
  - Granite cannonball: 1443 (unverified, needs in-game test)
  - Cannon rotation animation projectiles: 1-8 (NOT cannonballs, correctly ignored)

---

## Tasks

- [x] Research cannonball projectile IDs (53 confirmed, 1443 unverified)
- [x] Determine direction detection approach (atan2 vector math)
- [x] Identify `createProjectile` API signature (WorldPoint-based, current master)
- [x] Write plugin scaffold (build.gradle, Config, Plugin)
- [x] Fix build infrastructure (standalone build.gradle, gradlew.bat, settings.gradle)
- [x] Load in RuneLite and verify it compiles
- [x] Confirm `onProjectileMoved` fires for ID 53
- [x] Confirm direction bucketing is correct across all 8 directions
- [x] Test swap with known replacement ID (366 ice barrage)
- [ ] Test granite cannonball (ID 1443) swap separately
- [ ] Verify no double-swap (same projectile processed twice)
- [ ] Curate a preset ID list for v2 config dropdown
- [ ] Submit to Plugin Hub when stable

---

## Lessons

- Directory creation requires one level at a time with the Filesystem MCP tool.
- `createProjectile` API uses WorldPoint (not LocalPoint) in current RuneLite master.
  Old writeups of the Chinbompa technique use the legacy int-coordinate overload —
  do not follow those.
- ProjectileID.java is deprecated/deleted from RuneLite master. Use raw int constants.
- `apply from: "https://raw.githubusercontent.com/.../build.gradle"` is the Plugin Hub
  CI build script — uses `subprojects {}` so it does nothing for a standalone project.
  Use a full standalone `plugins { id 'java' }` build.gradle for local dev.
- Cannon rotation animation projectiles are IDs 1-8 — NOT cannonballs. They appear
  alongside ID 53 in the DevTools overlay but are correctly ignored by the ID filter.
- All 8 cannon firing directions use the same projectile ID (53). Direction detection
  via atan2 source→target vector is the correct approach.
- On Windows PowerShell, gradlew requires `.\gradlew.bat` prefix — bare `gradlew.bat`
  is not recognised without it.
- The Gradle panel in IntelliJ does not accept shell commands — use the Terminal tab
  for command-line Gradle invocations.
- Exit code -1073741819 (0xC0000005) = Windows GPU/LWJGL crash. Add `--mode=CPU`
  to run task args to bypass.
