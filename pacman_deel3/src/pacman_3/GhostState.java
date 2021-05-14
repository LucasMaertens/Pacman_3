package pacman;

import java.util.Random;

public abstract class GhostState {
	
	public abstract boolean isVulnerable();

	public abstract GhostState move(Ghost ghost, Random random);

	public abstract GhostState hitBy(Ghost ghost, PacMan pacMan);

}
