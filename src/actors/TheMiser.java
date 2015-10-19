package actors;

public class TheMiser implements Actor {
	private int bank;	
	
	@Override
	public int getBet() {
		return 0;
	}

	@Override
	public void setBank(int value) {
		this.bank = value;
	}

	@Override
	public int getBank() {
		return this.bank;
	}

	@Override
	public void addToBank(int value) {
		this.bank += value;
	}
}