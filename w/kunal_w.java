import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;


public class kunal_w {
	
	ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	static int carbs, fats, prots;

	public kunal_w() {
		Scanner in = new Scanner(System.in);
		int tc, tf, tp;
		tc = tf = tp = 0;
		
		carbs = in.nextInt();
		fats = in.nextInt();
		prots = in.nextInt();
		
		int n_fruits = in.nextInt();
		
		for(int i=0; i < n_fruits; i++) {
			int c, f, p;
			String name = in.next();
			c = in.nextInt();
			f = in.nextInt();
			p = in.nextInt();
			
			tf += f;
			tc += c;
			tp += p;
			
			fruits.add(new Fruit(name, c, f, p, in.nextInt()));
		}
		
		if(tc == 0 && carbs != 0) {
			System.out.println("None");
			System.exit(0);
		}
		
		if(tf == 0 && fats != 0) {
			System.out.println("None");
			System.exit(0);
		}
		
		if(tp == 0 && prots != 0) {
			System.out.println("None");
			System.exit(0);
		}
		
		Count c = new Count(n_fruits);
		
		c = minimize_other(c, fruits);
		
		for(int i=0; i < fruits.size()-1; i++) {
			System.out.println(fruits.get(i).name + " " + c.counts[i]);
		}
		
		System.out.print(fruits.get(fruits.size()-1).name + " " + c.counts[fruits.size()-1]);
		
	}
	
	public Count minimize_other(Count c, ArrayList<Fruit> fruits) {
		
		PriorityQueue<Count> unchecked = new PriorityQueue<Count>();
		HashSet<Count> checked = new HashSet<Count>();
		
		unchecked.add(c);
		
		while(!unchecked.isEmpty()) {
			c = unchecked.remove();
			checked.add(c);
			
			if(c.sumc > carbs && c.sumf > fats && c.sump > prots) {
				Count min_o = c;
				
				for(Count x : unchecked) {
					if(x.sumc >= carbs && x.sumf >= fats && x.sump >= prots) {
						if(x.sumo < min_o.sumo)
							min_o = x;
					}
				}
				
				for(Count x : checked) {
					if(x.sumc >= carbs && x.sumf >= fats && x.sump >= prots) {
						if(x.sumo < min_o.sumo)
							min_o = x;
					}
				}
				
				return min_o;
			}
			
			for(int i=0; i < fruits.size(); i++) {
				Count next = c.clone();
				Fruit f = fruits.get(i);
				next.counts[i] += 1;
				next.sumc += f.carbs;
				next.sumf += f.fats;
				next.sump += f.prots;
				next.sumo += f.other;
				
				if(!checked.contains(next) && !unchecked.contains(next)) {
					unchecked.add(next);
				}
					
			}
			
		}
		System.out.println("ret null");
		
		return null;
	}
	
	private class Fruit {
		public String name;
		public int carbs, fats, prots, other;
		
		public Fruit(String name, int carbs, int fats, int prots, int other) {
			this.name = name;
			this.carbs = carbs;
			this.fats = fats;
			this.prots = prots;
			this.other = other;
		}
	}
	
	private class Count implements Comparable {
		int[] counts;
		int sumc, sumf, sump, sumo;
		
		public Count(int nFruits) {
			counts = new int[nFruits];
			sumc = sumf = sump = sumo = 0;
		}
		
		public boolean equals(Object other) {
			Count o = (Count)other;
			
			for(int i=0; i < counts.length; i++) {
				if(counts[i] != o.counts[i]) {
					return false;
				}
			}
			
			return true;
		}
		
		public int hashCode() {
			int result = 0;
			for(int i=0; i < counts.length; i++) {
				result ^= counts[i];
				result = result << 1 + result;
			}
			
			return result;
		}
		
		public Count clone() {
			Count res = new Count(counts.length);
			
			for(int i=0; i < counts.length; i++)
				res.counts[i] = counts[i];
			
			res.sumc = sumc;
			res.sumf = sumf;
			res.sump = sump;
			res.sumo = sumo;
			
			return res;
		}
		
		public int compareTo(Object o) {
			Count other = (Count)o;
			return sumo - other.sumo;
		}
		
		public int undone() {
			int result = 0;
			
			if(sumc < carbs) result += 1;
			if(sumf < fats) result += 1;
			if(sump < prots) result += 1;
			
			return result;
		}
		
		public String toString() {
			String re = "";
			for(int i : counts) {
				re += i + ",";
			}
			
			return re;
		}

	}
	
	public static void main(String[] args) {
		new kunal_w();
	}
}
