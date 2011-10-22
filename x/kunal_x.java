import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;


public class kunal_x {
	
	public kunal_x() {
		try {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		
		long n_words = Integer.parseInt(reader.readLine());
		HashMap<TreeSet<Character>, HashSet<String>> dict = new HashMap<TreeSet<Character>, HashSet<String>>();

		String[] line = reader.readLine().split(" ");
		
		for(int i=0; i < n_words; i++) {
			String word = line[i];
			
			TreeSet<Character> set = new TreeSet<Character>();
			
			for(char c : word.toCharArray()) {
				set.add(c);
			}
			
			HashSet<String> bucket = dict.get(set);
			
			if(bucket == null) {
				HashSet<String> new_bucket = new HashSet<String>();
				new_bucket.add(word);
				dict.put(set, new_bucket);
			} else {
				bucket.add(word);
			}
		}
		
		int n_tests = Integer.parseInt(reader.readLine());
		for(int i=0; i < n_tests; i++) {
			String key = reader.readLine();
			
			TreeSet<Character> set = new TreeSet<Character>();
			
			for(char c : key.toCharArray()) {
				set.add(c);
			}
			
			HashSet<String> bucket = dict.get(set);
			if(bucket == null) {
				System.out.print("NONE ");
				continue;
			}
			ArrayList<String> possible_words = new ArrayList<String>();
			
			for(String word : bucket) {
				possible_words.add(word);
			}
			
			Collections.sort(possible_words);
			for(String word : possible_words) {
				System.out.print(word + " ");
			}
			
			if(i != n_tests - 1) {
				System.out.println();
			}
		}
		} catch (NumberFormatException e) {
		} catch (IOException e) {
		}		
		
	}
	
	public ArrayList<String> possible(String keys, ArrayList<String> dict) {
		ArrayList<String> result = new ArrayList<String>();
		
		for(String s : dict) {
			boolean b = false;
			
			for(char c : keys.toCharArray()) {
				if(s.indexOf(c) == -1) {
					b = true;
					break;
				}
				if(b) break;
				
				result.add(s);
			}
			
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		new kunal_x();
	}

}
