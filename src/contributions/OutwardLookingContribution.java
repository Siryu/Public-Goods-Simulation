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
		this.previousChangePercent = currentGainChangePercent;
		
		for(Contribution oc : otherContributions) {
			if(oc.getPreviousGainPercent() > currentGainChangePercent) {
				this.directionIncreaseBet = oc.getBetPercent() > this.getBetPercent();	
			}
		}
		
		float directionValue = directionIncreaseBet ? 1 : -1;	
		this.betPercent += (adjustRate * directionValue);
		this.adjustForMinimumBet(bank);
	}
}
