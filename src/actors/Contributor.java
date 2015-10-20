package actors;

public class Contributor {
	
	private float contribution;
	private float bank;
	
	public Contributor(float contribution){
		this.contribution = contribution;
	}
	
	public float getContribution(){
		return contribution;
	}
	public float getBank(){
		return bank;
	}
	public void setContribution(float contribution){
		this.contribution = contribution;
	}
	public void addToBank(float amount){
		bank += amount;
	}
}
