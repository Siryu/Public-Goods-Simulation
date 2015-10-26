package contributions;

import java.util.List;

public class Contribution {
	private float initialBetPercent;
	private float betPercent;
	private boolean directionIncreaseBet;
	private float adjustRate;
	private double minimumBet;
	private float previousChangePercent;
	
	public Contribution(float betPercent, float adjustRate) {
		directionIncreaseBet = Math.random() > 0.5d;
		this.adjustRate = adjustRate;
		this.initialBetPercent = betPercent;
		this.betPercent = betPercent;
	}
	
	public void adjustBetPercent(double reward, double bank, List<Contribution> otherContributions) {
		double originalContribution = this.getBet(bank);
		float previousGainChangePercent = this.previousChangePercent;
		float currentGainChangePercent = (float) (reward / originalContribution);
		this.previousChangePercent = currentGainChangePercent;
		
		float changeInRewardPercent = currentGainChangePercent - previousGainChangePercent;
		if(changeInRewardPercent <= 0) {
			this.directionIncreaseBet = !this.directionIncreaseBet;
		}
		
		float directionValue = directionIncreaseBet ? 1 : -1;		
		this.betPercent += (adjustRate * directionValue);
		this.adjustForMinimumBet(bank);
	}
	
	private void adjustForMinimumBet(double bank) {
		if(bank > this.minimumBet) {
			double normalReturn = bank * this.betPercent;
			double adjustedBetAmount = normalReturn >= minimumBet ? normalReturn : minimumBet;
			if(adjustedBetAmount == minimumBet) {
				this.betPercent = (float) (minimumBet / bank);
				normalizeBet();
			}
		}
	}
	
	private void normalizeBet() {
		if(this.betPercent > 1) {
			this.betPercent = 1;
		}
		else if(this.betPercent < 0.001) {
			this.betPercent = 0.001f;
		}
	}
	
	public double getBet(double bank) {
		double betAmount = bank * this.betPercent;
		if(betAmount < this.minimumBet) {
			betAmount = 0;
		}
		return betAmount;
	}
	
	public float getBetPercent() {
		return this.betPercent;
	}
	
	public void setMinimumBet(double bank, double amount) {
		this.minimumBet = amount;
		adjustForMinimumBet(bank);
	}
}
