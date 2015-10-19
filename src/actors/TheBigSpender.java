package actors;

public class TheBigSpender implements Actor {
	private int bank;
	
	@Override
	public int getBet() {
		int value = this.getBank();
		this.bank -= value;
		return value;
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