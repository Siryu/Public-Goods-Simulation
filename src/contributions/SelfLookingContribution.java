package contributions;

import java.util.List;

public class SelfLookingContribution extends Contribution {

	public SelfLookingContribution(float betPercent, float adjustRate) {
		super(betPercent, adjustRate);
	}
	
	@Override
	public void adjustBetPercent(double reward, double bank, List<Contribution> otherContributions) {
		double originalContribution = this.getBet(bank);
		float previousGainChangePercent = this.previousChangePercent;
		float currentGainChangePercent = (float) (reward / originalContribution);
		this.previousChangePercent = currentGainChangePercent;
		
		float changeInRewardPercent = currentGainChangePercent - previousGainChangePercent;
		if(changeInRewardPercent <= 0) {
			this.directionIncreaseBet = !this.directionIncreaseBet;
		}
		
		float directionValue = directionIncreaseBet ? 1 : -1;		
		this.betPercent += (adjustRate * directionValue);
		this.adjustForMinimumBet(bank);
	}
}