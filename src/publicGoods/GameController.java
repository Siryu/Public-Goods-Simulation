package publicGoods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import actors.Contributor;
import contributions.Contribution;

public class GameController {
	private int numberOfRounds;
	private float potMultiplyer;
	private boolean percentageReward;
	private boolean minimumBet;
	private float bestContributorBonus;
	private float flatPunishment;
	
	private ArrayList<Contributor> contributors;
	
	public GameController(int numberOfRounds, float potMultiplyer, boolean percentageReward, boolean minimumBet, float bestContributorBonus, float flatPunishment) {
		this.numberOfRounds = numberOfRounds;
		this.potMultiplyer = potMultiplyer;
		this.percentageReward = percentageReward;
		this.bestContributorBonus = bestContributorBonus;
		this.flatPunishment = flatPunishment;
		this.minimumBet = minimumBet;
		this.contributors = new ArrayList<Contributor>();
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		for(int i = 0; i < numberOfRounds; i++) {
			float pot = 0;
			float newPot = 0;
			for(Contributor c : this.contributors) {
				pot += c.getContribution().getBet(c.getBank());
			}
			newPot = pot * potMultiplyer;
			float individualPayout;
			ArrayList<Contributor> highestContributors = getHighestContributors();
			
			if(percentageReward) {
				if(pot > 0) {
					for(Contributor c : this.contributors) {
						float percentOfPot = (float) (c.getContribution().getBet(c.getBank()) / pot);
						individualPayout = percentOfPot * newPot;
						float finalPayout = individualPayout;
						double highestContributorReward = this.bestContributorBonus / highestContributors.size();
						if(highestContributors.contains(c)) {
							finalPayout += highestContributorReward;
						}
						c.receiveReward(finalPayout - this.flatPunishment, newPot, this.getOtherContributions(c));
					}
				}
			}
			else {
				int contributorCount = 0;
				ArrayList<Contributor> minBetSuccess = new ArrayList<Contributor>();
				if(this.minimumBet) {
					for(Contributor contributor : this.contributors) {
						if(contributor.getContribution().getBet(contributor.getBank()) > 0) {
							contributorCount++;
							minBetSuccess.add(contributor);
						}
					}
				}
				else {
					contributorCount = this.contributors.size();
					minBetSuccess = this.contributors;
				}
				
				individualPayout = newPot / contributorCount;
				for(Contributor c : minBetSuccess) {
					float finalPayout = individualPayout;
					if(highestContributors.contains(c)) {
						finalPayout += this.bestContributorBonus / highestContributors.size();
					}
					c.receiveReward(finalPayout - this.flatPunishment, newPot, this.getOtherContributions(c));
				}
			}
			for(Contributor c : this.contributors) {
				System.out.println(c.toString());
			}
			System.out.println();
			scanner.nextLine();
			
	
		}
		
	}
	
	public void addContributor(Contributor contributor) {
		this.contributors.add(contributor);
	}
	
	private ArrayList<Contributor> getHighestContributors() {
		ArrayList<Contributor> contributors = new ArrayList<Contributor>();
		
		for(Contributor c : this.contributors) {
			if(contributors.isEmpty()) {
				contributors.add(c);
			}
			else {
				for(int i = 0; i < contributors.size(); i++) {
					if(c.getContribution().getBet(c.getBank()) > contributors.get(i).getContribution().getBet(contributors.get(i).getBank())) {
						contributors.remove(contributors.get(i));
						if(!contributors.contains(c)) {
							contributors.add(c);
						}
					}
					else if(c.getContribution().getBet(c.getBank()) == contributors.get(i).getContribution().getBet(contributors.get(i).getBank())) {
						if(!contributors.contains(c)) {
							contributors.add(c);
						}
					}
				}
			}
		}
		return contributors;
	}
	
	private List<Contribution> getOtherContributions(Contributor contributor) {
		ArrayList<Contribution> contributions = new ArrayList<Contribution>();
		for(Contributor c : this.contributors) {
			if(c != contributor) {
				contributions.add(c.getContribution());
			}
		}
		return contributions;
	}
}