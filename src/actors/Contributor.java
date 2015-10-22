package actors;

public class Contributor {
	
	private float contribution;
	private float bank;
	private byte change = 1;
	private float netGain = 0;
	private float adjustRate;
	private float minimumAmount = 0.0f;
	private float initialContribution;
	
	public Contributor(float contribution, float bank){
		this.initialContribution = contribution;
		this.bank = bank;
	}
	
	public float getContribution() {
		float baseAmount;
		if(this.bank > this.minimumAmount) {
			float base = initialContribution * bank;
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
	private void adjustContribution(float reward){
		float previousGain = netGain;
		netGain = reward - getContribution();
		bank -= getContribution();
		float gainChange = netGain - previousGain;
		change = (byte)(gainChange/Math.abs(gainChange));
		this.contribution += change * adjustRate;
	}
	
	private void normalizeContribution() {
		if(this.contribution > 100) {
			this.contribution = 100;
		}
		else if(this.contribution < 0) {
			this.contribution = 0;
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
		return String.format("Contribution: %-10.0fBalance: %-15.2fGain: %-10.2f", contribution * 100, bank, netGain);
	}
}
