package actors;

public class Contributor {
	
	private float contribution;
	private float bank;
	private byte change = 1;
	private float netGain = 0;
	private float adjustRate;
	
	public Contributor(float contribution, float bank){
		this.contribution = contribution;
		this.bank = bank;
	}
	
	public float getContribution() {
		return contribution * bank;
	}
	public float getBank(){
		return bank;
	}
	public float getAdjustRate(){
		return adjustRate;
	}
	public void setAdjustRate(float adjustRate){
		this.adjustRate = adjustRate;
	}
	public void addToBank(float reward){
		adjustContribution(reward);
		bank += reward;
	}
	private void adjustContribution(float reward){
		float previousGain = netGain;
		netGain = reward - getContribution();
		bank -= getContribution();
		float gainChange = netGain - previousGain;
		change = (byte)(gainChange/gainChange);
		contribution += change * adjustRate;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contribution: ");
		sb.append(Math.round(this.contribution * 100) + "%");
		sb.append("\tTotal balance: ");
		sb.append(bank);
		sb.append("\tNet Amount Earned: ");
		sb.append(netGain);
		return sb.toString();
	}
}
