package actors;

public class Contributor {
	
	private float contribution;
	private float bank;
	private float change;
	private float direction;
	private double netGain = 0;
	private double totalGain = 0;
	private float adjustRate;
	private float minimumAmount = 0.0f;
	private float initialContribution;
	
	public Contributor(float contribution, float bank){
		this.contribution = contribution;
		this.initialContribution = contribution;
		this.bank = bank;
		this.direction = (Math.random() * 2) - 1 > 0 ? 1 : -1;
	}
	
	public float getContribution() {
		float baseAmount;
		if(this.bank > this.minimumAmount) {
			//float base = initialContribution * bank;
			float base = contribution * bank;
			baseAmount = base > this.minimumAmount ? base : minimumAmount;		
			this.contribution = baseAmount / this.bank;
			normalizeContribution();
		}
		else {
			baseAmount = 0;
		}
		return baseAmount;
	}
	public float getBank(){
		return bank;
	}
	public float getAdjustRate() {
		return adjustRate;
	}
	public void setAdjustRate(float adjustRate) {
		this.adjustRate = adjustRate;
	}
	public void addToBank(float reward){
		adjustContribution(reward);
		normalizeContribution();
		bank += reward;
	}

	private void adjustContribution(float reward) {
		float originalContribution = getContribution();
		this.netGain = reward - originalContribution;
		float previousChange = this.change;
		this.totalGain += (reward - originalContribution);
		float gainChangePercent = reward / (originalContribution);
		float changeInReward = gainChangePercent - previousChange;
		//System.out.println("changeInReward: " + changeInReward);
		bank -= originalContribution;
		if(changeInReward < 0) {
			direction *= -1;
		}
		this.change = gainChangePercent;
		this.contribution += (direction * adjustRate);
	}
	
	private void normalizeContribution() {
		if(this.contribution > 1) {
			this.contribution = 1;
		}
		else if(this.contribution < 0.001) {
			this.contribution = 0.001f;
		}
	}
	
	public void removeFromBank(float amount) {
		this.bank = this.bank > amount ? this.bank - amount : 0;
	}
	
	public void setMinimumBet(float amount) {
		this.minimumAmount = amount;
	}
	
	@Override
	public String toString() {
		return String.format("Contribution: %-10.0fBalance: %-15.2fGain: %-15.2fTotal Gain: %-10.2f", contribution * 100, bank, netGain, totalGain);
	}
}
