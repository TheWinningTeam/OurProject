import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;


public class kunal_x {
	
	public class superset implements Comparable{
		byte chars[];
		
		public superset(String word) {
			chars = new byte[26];
		
			for(char c : word.toLowerCase().toCharArray()) {
				chars[c - 'a'] = 1;
			}
		}
		
		public int hashCode() {
			int res = chars[0];
			res = res >> 5 + chars[4];
			res = res << 5 + chars[7];
			res = res << 5 + chars[14];
			res = res << 5 + chars[18];
			res = res << 5 + chars[19];
			
			return res;
		}
		
		public boolean equals(Object other) {
			superset o = (superset)other;
			for(int i=0; i < 26; i++) {
				if(chars[i] != o.chars[i])
					return false;
			}
			
			return true;
		}

		public int compareTo(Object arg0) {
			superset o = (superset)arg0;
			
			for(int i=0; i < 26; i++) {
				int res = chars[i] - o.chars[i];
				if(res != 0)
					return res;
			}
			
			return 0;
		}
	}
	
	public kunal_x() {
		try {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		
		long n_words = Long.parseLong(reader.readLine());
		TreeMap<superset, LinkedList<String>> dict = new TreeMap<superset, LinkedList<String>>();

		
		String[] line = new String[0];
		
		for(int i=0; i < n_words; i++) {
			
			if(i == line.length) {
				i -= line.length;
				n_words -= line.length;
				line = reader.readLine().split(" ");
				
			}
			
			String word = line[i];
			
			superset set = new superset(word);
			
			LinkedList<String> bucket = dict.get(set);
			
			if(bucket == null) {
				LinkedList<String> new_bucket = new LinkedList<String>();
				new_bucket.add(word);
				dict.put(set, new_bucket);
			} else {
				bucket.add(word);
			}
		}
		
		int n_tests = Integer.parseInt(reader.readLine());
		for(int i=0; i < n_tests; i++) {
			String key = reader.readLine();
			
			superset set = new superset(key);
			
			LinkedList<String> bucket = dict.get(set);
			if(bucket == null) {
				System.out.print("NONE");
			} else {
				ArrayList<String> possible_words = new ArrayList<String>();
				
				for(String word : bucket) {
					possible_words.add(word);
				}
				
				Collections.sort(possible_words);
				for(int m=0; m < possible_words.size(); m++) {
					System.out.print(possible_words.get(m));
					if(m != possible_words.size()-1) {
						System.out.print(" ");
					}
				}
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
