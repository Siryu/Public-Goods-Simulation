package publicGoods;

import actors.Contributor;

public class Driver {

	public static void main(String[] args) {
		// Phase 1 ... all permutations
		float bigSpender = 1f;
		float middleMan = 0.5f;
		float miser = 0.0f;
		int pot = 500;
		
		GameController gc = new GameController(1, 2, false, false, false);
		gc.addContributor(new Contributor(bigSpender, pot));
		gc.addContributor(new Contributor(miser, pot));
		gc.addContributor(new Contributor(bigSpender, pot));
		gc.run();
	}
}