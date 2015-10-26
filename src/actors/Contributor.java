package actors;

import java.util.List;

import contributions.Contribution;

public class Contributor {
	
	private Contribution contribution;
	private float bank;
	private double netGain = 0;
	private double totalGain = 0;
	
	public Contributor(float contribution, float bank) {
		this.contribution = new Contribution(contribution, 0);
		this.bank = bank;
	}
	
	public Contributor(float contribution, float bank, float adjustRate){
		this.contribution = new Contribution(contribution, adjustRate);
		this.bank = bank;
	}
	
	public Contributor(float contribution, float bank, float adjustRate, double minimumBet) {
		this(contribution, bank, adjustRate);
		this.contribution.setMinimumBet(bank, minimumBet);
	}
	
	public Contribution getContribution() {
		return this.contribution;
	}
	
	public float getBank(){
		return bank;
	}
	
	public void receiveReward(float reward, List<Contribution> otherContributions){
		this.contribution.adjustBetPercent(reward, bank, otherContributions);
		this.netGain = reward;
		this.totalGain += reward;
		this.bank -= this.contribution.getBet(bank);
		this.bank += reward;
	}
	
	public void setMinimumBet(float amount) {
		this.contribution.setMinimumBet(bank, amount);
	}
		
	@Override
	public String toString() {
		return String.format("Contribution: %-10.0fBalance: %-15.2fGain: %-15.2fTotal Gain: %-10.2f", this.contribution.getBetPercent() * 100, bank, netGain, totalGain);
	}
}
