package contributions;

import java.util.List;

public abstract class Contribution {
	protected float betPercent;
	protected boolean directionIncreaseBet;
	protected float adjustRate;
	protected double minimumBet;
	protected float previousChangePercent;
	protected double lastBet;
	
	public Contribution(float betPercent, float adjustRate) {
		directionIncreaseBet = Math.random() > 0.5d;
		this.adjustRate = adjustRate;
		this.betPercent = betPercent;
	}
	
	public abstract void adjustBetPercent(double reward, double bank, double totalReward, List<Contribution> otherContributions);
		
	protected void adjustForMinimumBet(double bank) {
		if(bank > this.minimumBet) {
			double normalReturn = bank * this.betPercent;
			double adjustedBetAmount = normalReturn >= minimumBet ? normalReturn : minimumBet;
			if(adjustedBetAmount == minimumBet) {
				this.betPercent = (float) (minimumBet / bank);
			}
		}
		normalizeBet();
	}
	
	private void normalizeBet() {
		if(this.betPercent > 1) {
			this.betPercent = 1;
		}
		else if(this.betPercent < 0.00001) {
			this.betPercent = 0.00001f;
		}
	}
	
	public double getBet(double bank) {
		double betAmount = bank * this.betPercent;
		if(betAmount < this.minimumBet) {
			betAmount = 0;
		}
		this.lastBet = betAmount;
		return betAmount;
	}
	
	public double getLastBet() {
		return this.lastBet;
	}
	
	public float getBetPercent() {
		return this.betPercent;
	}
	
	public double getPreviousGainPercent() {
		return this.previousChangePercent;
	}
	
	public boolean getPositiveDirecetion() {
		return this.directionIncreaseBet;
	}
	
	public void setLearningRate(float learningRate) {
		this.adjustRate = learningRate;
	}
	
	public void setMinimumBet(double bank, double amount) {
		this.minimumBet = amount;
		adjustForMinimumBet(bank);
	}
}
