package publicGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import actors.Contributor;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
//		System.out.println("Phase 1.. all permutations of 3 different types of contributors.");
//		driver.runWithPermutations(1, 3, 1.2f, 0f, false, 0f, 0f, 0f);
//		System.out.println("Phase 2...............");
//		driver.phase2();
		driver.phase3();
	}
	
	private void runWithPermutations(int runCount, int actorCount, float potMultiplier, float flatReward, boolean divyPotByPercent, float punishment, float minEntry, float learningRate) {
		float bigSpender = 1f;
		float middleMan = 0.5f;
		float miser = 0.0f;
		int pot = 1000;
		float[] types = new float[] { bigSpender, middleMan, miser };	
		boolean reward = flatReward > 0;
		List<List<Float>> permutationList = makeSets(types, new ArrayList<Float>(), 0, actorCount);
		
		for(List<Float> list : permutationList) {
			GameController gc = new GameController(runCount, potMultiplier, divyPotByPercent, reward, flatReward, punishment);
			for(Float type : list) {
				gc.addContributor(new Contributor(type, pot, learningRate, minEntry));
			}
			gc.run();
			System.out.println();
		}
	}
	
	private void phase2() {
//		System.out.println("more actors..........................................");
//		runWithPermutations(1, 5, 2, 0, false, 0f, 0f, .01f);
//		System.out.println("Variable multiplier ..................................");
//		runWithPermutations(10, 3, 1.2f, 0, false, 0f, 0f, .1f);		// with high multiplier(3), more likely to go towards 100 if it's alreaedy high, like 100 100 50 starting or 100 100 0
																		// then all drop to 0
//		System.out.println("Flat reward added to the one given most resources.....");
//		runWithPermutations(10, 3, 2, 100, false, 0f, 0f, .1f);         // doesn't really change anything.
		System.out.println("More personalities introduced.........................");
		GameController gc = new GameController(200, 2, false, false, 0f, .1f);  // doesn't really change anything. may go up to 100, but will always head back to 0
		gc.addContributor(new Contributor(.25f, 500, .01f));
		gc.addContributor(new Contributor(.75f, 500, .01f));
		gc.addContributor(new Contributor(.1f, 500, .01f));
		gc.addContributor(new Contributor(.75f, 500, .01f));
		gc.addContributor(new Contributor(.1f, 500, .01f));
		gc.run();
//		System.out.println("percentage of pot split between actors ...............");
//		runWithPermutations(10, 3, 2, 0, true, 0f, 0f, .1f);			// trends towards everyone giving 100
//		System.out.println("place a punishment on everyone at end of round .......");
//		runWithPermutations(10, 3, 2, 0, false, 20f, 0f, .1f);			// trends toward 100 if two people are giving alot ot begin with, otherwise towards 0
//		System.out.println("Minimum entry to receive payout........................");
//		runWithPermutations(10, 3, 2, 0, false, 0f, 10f, .1f);			// all trended towards 0 
	}
	
	private void phase3() {
		HashMap<String, Double> highestAmounts = new HashMap<String, Double>();
		System.out.println("base 400 rounds, 1.2x, 1% change rate");
		GameController gc = new GameController(400, 1.2f, false, false, 0f, .05f);  
		gc.addContributor(new Contributor(.25f, 100, .01f));
		gc.addContributor(new Contributor(.75f, 100, .01f));
		gc.addContributor(new Contributor(.1f, 100, .01f));
		gc.run();
		highestAmounts.put("Base Run", getHighestTotalgain(gc.getContributors()));
		System.out.println();
		System.out.println("change multiplyer to 3x");
		gc = new GameController(400, 3f, false, false, 0f, 0f); 
		gc.addContributor(new Contributor(.25f, 100, .01f));
		gc.addContributor(new Contributor(.75f, 100, .01f));
		gc.addContributor(new Contributor(.1f, 100, .01f));
		gc.run();
		highestAmounts.put("3x multiplyer", getHighestTotalgain(gc.getContributors()));
		System.out.println();
		System.out.println("percent split by percent contributed");
		gc = new GameController(400, 1.2f, true, false, 0f, 0f);  
		gc.addContributor(new Contributor(.25f, 100, .01f));
		gc.addContributor(new Contributor(.75f, 100, .01f));
		gc.addContributor(new Contributor(.1f, 100, .01f));
		gc.run();
		highestAmounts.put("percent split by percent contributed", getHighestTotalgain(gc.getContributors()));
		System.out.println();
		System.out.println("include a minumum buy in of 20");
		gc = new GameController(400, 1.2f, false, true, 0f, 0f);  
		gc.addContributor(new Contributor(.25f, 100, .01f, 20));
		gc.addContributor(new Contributor(.75f, 100, .01f, 20));
		gc.addContributor(new Contributor(.1f, 100, .01f, 20));
		gc.run();
		highestAmounts.put("Minimum buy in of 20", getHighestTotalgain(gc.getContributors()));
		System.out.println();
		System.out.println("give a bonus to the best contributors");
		gc = new GameController(400, 1.2f, false, false, 5f, 0f);  
		gc.addContributor(new Contributor(.25f, 100, .01f));
		gc.addContributor(new Contributor(.75f, 100, .01f));
		gc.addContributor(new Contributor(.1f, 100, .01f));
		gc.run();
		highestAmounts.put("best contributors bonus", getHighestTotalgain(gc.getContributors()));
		System.out.println();
		System.out.println("have a penalty of 10 each round");
		gc = new GameController(400, 1.2f, false, false, 0, 5f);  
		gc.addContributor(new Contributor(.25f, 100, .01f));
		gc.addContributor(new Contributor(.75f, 100, .01f));
		gc.addContributor(new Contributor(.1f, 100, .01f));
		gc.run();
		highestAmounts.put("penalty each round", getHighestTotalgain(gc.getContributors()));
		
		Entry<String, Double> winner = null;
		for(Entry<String, Double> h : highestAmounts.entrySet()) {
			if(winner == null || winner.getValue() < h.getValue()) {
				winner = h;
			}
		}
		
		System.out.println("\n\n the winner is " + winner.getKey() + " with " + winner.getValue() + " total gain.");
		
		
	}
	
	private static List<List<Float>> makeSets(float[] types, List<Float> beginning, int location, int numberPlayers) {
		ArrayList<List<Float>> toReturn = new ArrayList<>();
		for(int i = location; i < types.length; i++) {
			List<Float> nextOnes = new ArrayList<Float>(beginning);
			while(nextOnes.size() < numberPlayers - 1) {
				nextOnes.add(types[i]);
				toReturn.addAll(makeSets(types, nextOnes, i + 1, numberPlayers));
			}
			nextOnes.add(types[i]);
			toReturn.add(new ArrayList<Float>(nextOnes));
		}
		return toReturn;
	}
	
	private double getHighestTotalgain(List<Contributor> contributors) {
		double highestAmount = 0;
		for(Contributor c : contributors) {
			if(c.getTotalGain() > highestAmount) {
				highestAmount = c.getTotalGain();
			}
		}
		return highestAmount;
	}
}