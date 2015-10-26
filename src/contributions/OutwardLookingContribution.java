package contributions;

import java.util.List;

public class OutwardLookingContribution extends Contribution{

	public OutwardLookingContribution(float betPercent, float adjustRate) {
		super(betPercent, adjustRate);
	}

	@Override
	public void adjustBetPercent(double reward, double bank, double totalReward, List<Contribution> otherContributions) {		
		double originalContribution = this.getBet(bank);
		float currentGainChangePercent = (float) (reward / originalContribution);
		if(currentGainChangePercent < this.previousChangePercent) {
			for(Contribution oc : otherContributions) {				
				this.directionIncreaseBet = oc.getBetPercent() > this.getBetPercent();	
			}
		}
		this.previousChangePercent = currentGainChangePercent;
		
		float directionValue = directionIncreaseBet ? 1 : -1;	
		this.betPercent += (adjustRate * directionValue);
		this.adjustForMinimumBet(bank);
	}
}
