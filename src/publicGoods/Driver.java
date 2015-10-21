package publicGoods;

import java.util.ArrayList;
import java.util.List;

import actors.Contributor;

public class Driver {

	public static void main(String[] args) {
		Driver driver = new Driver();
		// Phase 1 ... all permutations
		float bigSpender = 1f;
		float middleMan = 0.5f;
		float miser = 0.0f;
		
		float[] types = new float[] { bigSpender, middleMan, miser };
		
		List<List<Float>> list = makeSets(types, new ArrayList<Float>(), 0, 3);
		
		System.out.println(list);
		
		int pot = 500;
		
		
		
		// a a b
		// a a a
		// a a c
		// a b b
		// a b c
		// b b b
		// c c c
		// b c c
		// b b c
		// a c c
		

		// Phase 2 ... more options
		
	}

	private void moreActors() {
		// TODO Auto-generated method stub
		
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
}