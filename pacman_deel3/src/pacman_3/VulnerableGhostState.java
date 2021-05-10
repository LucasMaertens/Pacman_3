package pacman_3;

import java.util.Random;

public class VulnerableGhostState extends GhostState {
	
	public static final int VULNERABLE_TIME = 5;
	public static final int MOVE_DELAY = 1;
	
	private int vulnerableTimeLeft = VULNERABLE_TIME;
	private int moveDelayLeft = MOVE_DELAY;

	@Override
	public boolean isVulnerable() {
		return true;
	}
	
	@Override
	public GhostState move(Ghost ghost, Random random) {
		if (moveDelayLeft > 0) {
			moveDelayLeft--;
			return this;
		}
		ghost.reallyMove(random);
		moveDelayLeft = MOVE_DELAY;
		if (vulnerableTimeLeft > 0) {
			vulnerableTimeLeft--;
			return this;
		} else
			return new RegularGhostState();
	}
	
	@Override
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		ghost.setSquare(ghost.getOriginalSquare());
		return new RegularGhostState();
	}
	
}
