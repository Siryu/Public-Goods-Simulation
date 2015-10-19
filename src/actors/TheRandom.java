package actors;

import java.util.Random;

public class TheRandom implements Actor {
	private int bank;
	private double betPercent;
	
	public TheRandom() { 
			this.betPercent = new Random().nextDouble();
	}
	
	public TheRandom(int randomPercent) {
		this.betPercent = randomPercent / 100;
	}
	
	@Override
	public int getBet() {
		int value = (int)(this.getBank() * betPercent);
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