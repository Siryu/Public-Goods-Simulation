package publicGoods;

import java.util.ArrayList;

import actors.Contributor;

public class GameController {
	private int numberOfRounds;
	private int potMultiplyer;
	private boolean percentageReward;
	private float bestContributorBonus;
	private float flatPunishment;
	private float minimumEntry;
	private ArrayList<Contributor> contributors;
	
	public GameController(int numberOfRounds, int potMultiplyer, boolean percentageReward, float bestContributorBonus, float flatPunishment, float minimumEntry) {
		this.numberOfRounds = numberOfRounds;
		this.potMultiplyer = potMultiplyer;
		this.percentageReward = percentageReward;
		this.bestContributorBonus = bestContributorBonus;
		this.flatPunishment = flatPunishment;
		this.minimumEntry = minimumEntry;
		this.contributors = new ArrayList<Contributor>();
	}
	
	public void run() {
		for(int i = 0; i < numberOfRounds; i++) {
			float pot = 0;
			float newPot = 0;
			for(Contributor c : this.contributors) {
				pot += c.getContribution();
			}
			newPot = pot * potMultiplyer;
			float individualPayout;
			Contributor highestContributor = null;
		
			for(Contributor c : this.contributors) {
				if(highestContributor == null || highestContributor.getContribution() < c.getContribution()) {
					highestContributor = c;
				}
			}
			
			if(percentageReward) {
				for(Contributor c : this.contributors) {
					float percentOfPot = c.getContribution() / pot;
					individualPayout = percentOfPot * newPot;
					if(c == highestContributor) {
						individualPayout += this.bestContributorBonus;
					}
					c.addToBank(individualPayout);
				}
			}
			else {
				individualPayout = newPot / contributors.size();
				for(Contributor c : this.contributors) {
					c.addToBank(individualPayout);
				}
			}
			for(Contributor c : this.contributors) {
				c.removeFromBank(this.flatPunishment);
			}
		}
		
		for(Contributor c : this.contributors) {
			System.out.println(c.toString());
		}
	}
	
	public void addContributor(Contributor contributor) {
		contributor.setMinimumBet(this.minimumEntry);
		this.contributors.add(contributor);
	}
	
}