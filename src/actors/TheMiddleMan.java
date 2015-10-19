package actors;

public class TheMiddleMan implements Actor {
	private int bank;
	
	@Override
	public int getBet() {
		int value = this.getBank() / 2;
		this.bank -= value;
		return value;
	}

	@Override
	public void setBank(int value) {
		this.bank = value;
	}

	@Override
	public void addToBank(int value) {
		this.bank += value;
	}

	@Override
	public int getBank() {
		return this.bank;
	}
}