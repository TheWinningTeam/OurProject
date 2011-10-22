import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


public class kunal_x {
	
	public kunal_x() {
		Scanner in = new Scanner(System.in);
		
		long n_words = in.nextLong();
		HashMap<TreeSet<char[]>, HashSet<String>> dict = new HashMap<TreeSet<char[]>, HashSet<String>>();
		ArrayList<String> words = new ArrayList<String>();
		
		for(long i=0; i < n_words; i++) {
			String word = in.next();
			
			List<char[]> clist = Arrays.asList(word.toCharArray());
			TreeSet<char[]> set = new TreeSet<char[]>(clist);
			HashSet<String> bucket = dict.get(set);
			
			if(bucket == null) {
				HashSet<String> new_bucket = new HashSet<String>();
				new_bucket.add(word);
				dict.put(set, new_bucket);
			} else {
				bucket.add(word);
			}
		}
		
		int n_tests = in.nextInt();
		for(int i=0; i < n_tests; i++) {
			String key = in.next();
			
			List<char[]> clist = Arrays.asList(key.toCharArray());
			TreeSet<char[]> set = new TreeSet<char[]>(clist);
			HashSet<String> bucket = dict.get(set);
			
			ArrayList<String> possible_words = new ArrayList<String>();
			
			for(String word : bucket) {
				possible_words.add(word);
			}
			
			Collections.sort(possible_words);
			for(String word : possible_words) {
				System.out.print(word + " ");
			}
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
