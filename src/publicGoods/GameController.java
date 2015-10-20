package publicGoods;

import java.util.ArrayList;

import actors.Contributor;

public class GameController {
	private int numberOfRounds;
	private int potMultiplyer;
	private boolean percentageReward;
	private boolean flatPunishment;
	private boolean minimumEntry;
	private ArrayList<Contributor> contributors;
	
	public GameController(int numberOfRounds, int potMultiplyer, boolean percentageReward, boolean flatPunishment, boolean minimumEntry) {
		this.numberOfRounds = numberOfRounds;
		this.potMultiplyer = potMultiplyer;
		this.percentageReward = percentageReward;
		this.flatPunishment = flatPunishment;
		this.minimumEntry = minimumEntry;
		this.contributors = new ArrayList<Contributor>();
	}
	
	public void run() {
		// weee a game loop for numberofRounds
	}
	
	public void addContributor(Contributor contributor) {
		this.contributors.add(contributor);
	}
}