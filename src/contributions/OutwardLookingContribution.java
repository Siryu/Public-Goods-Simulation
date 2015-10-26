package contributions;

import java.util.List;

public class OutwardLookingContribution extends Contribution{

	public OutwardLookingContribution(float betPercent, float adjustRate) {
		super(betPercent, adjustRate);
	}

	@Override
	public void adjustBetPercent(double reward, double bank, List<Contribution> otherContributions) {
		double originalContribution = this.getBet(bank);
		float currentGainChangePercent = (float) (reward / originalContribution);
		// i divide by .00001 instead of 0 so the numbers are hugely skewed when checking if 0 is better....
		if(currentGainChangePercent < this.previousChangePercent) {
			for(Contribution oc : otherContributions) {
				
				// need to check oc's previous compared to their now and see if it's a positive increase and follow it.
				
			this.directionIncreaseBet = oc.getBetPercent() > this.getBetPercent();	
			}
		}
		this.previousChangePercent = currentGainChangePercent;
		
	
		
		float directionValue = directionIncreaseBet ? 1 : -1;	
		this.betPercent += (adjustRate * directionValue);
		this.adjustForMinimumBet(bank);
	}
}
