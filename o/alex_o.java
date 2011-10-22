import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class alex_o {
	
	public alex_o() {
		Scanner in = new Scanner(System.in);
		
		int min_len = in.nextInt();
		int occ = in.nextInt();
		int len = in.nextInt();
		int max_len = len - occ + 1;
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		while(in.hasNext()) {
			int num = in.nextInt();
			numbers.add(num);
		}

		HashMap<ArrayList<Integer>, Integer> patterns;
		int matches = 0;
		
		for(int j=min_len; j < max_len; j++) {
			patterns = new HashMap<ArrayList<Integer>,Integer>();
			for(int i=0; i < numbers.size(); i++) {
				if(i+j >= numbers.size()+1)
					break;
				
				ArrayList<Integer> curpattern = new ArrayList<Integer>(numbers.subList(i, i+j));

				if(patterns.containsKey(curpattern)) {
					Integer curMatches = patterns.get(curpattern) + 1;
					patterns.put(curpattern, curMatches);
					
					if(curMatches == occ)
						matches += occ;
					else if(curMatches >= occ)
						matches += 1;
					
				} else {
					patterns.put(curpattern, 1);
				}
				
			}
			
		}
		
		System.out.print(matches);
	
	
		
		
	}
	
	public static void main(String[] args) {
		new alex_o();
	}

}
