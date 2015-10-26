package actors;

import java.util.List;

import contributions.Contribution;
import contributions.OutwardLookingContribution;

public class Contributor {
	private Contribution contribution;
	private float bank;
	private double netGain = 0;
	private double totalGain = 0;
	
	public Contributor(float contribution, float bank) {
		this.contribution = new OutwardLookingContribution(contribution, 0f);
		this.contribution.setMinimumBet(this.bank, 0);
		this.bank = bank;
	}
	
	public Contributor(float contribution, float bank, float learningRate){
		this(contribution, bank);
		this.contribution.setLearningRate(learningRate);
	}
	
	public Contributor(float contribution, float bank, float learningRate, double minimumBet) {
		this(contribution, bank, learningRate);
		this.contribution.setMinimumBet(bank, minimumBet);
	}
	
	public Contribution getContribution() {
		return this.contribution;
	}
	
	public float getBank(){
		return bank;
	}
	
	public void receiveReward(float reward, List<Contribution> otherContributions){
		double previousBet = this.contribution.getBet(this.bank);
		try {
			this.contribution.adjustBetPercent(reward, bank, otherContributions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.netGain = reward - previousBet;
		this.totalGain += reward - previousBet;
		this.bank -= previousBet;
		this.bank += reward;
	}
	
	public void setMinimumBet(float amount) {
		this.contribution.setMinimumBet(bank, amount);
	}
		
	@Override
	public String toString() {
		return String.format("Contribution: %-10.0fBalance: %-15.2fGain: %-15.2fTotal Gain: %-10.2f", this.contribution.getBetPercent() * 100, this.bank, this.netGain, this.totalGain);
	}
}
