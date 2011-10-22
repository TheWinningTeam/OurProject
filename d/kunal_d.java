import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class kunal_d {
	
	static int n_students = 0;

	
	public kunal_d() {
		HashMap<coord, Integer> coord_to_pos = new HashMap<coord, Integer>();
		HashMap<Integer, Integer> pos_to_prime = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> prime_to_pos = new HashMap<Integer, Integer>();
		
		Scanner in = new Scanner(System.in);
		
		n_students = in.nextInt();
		
		/*
		ArrayList<coord> students = new ArrayList<coord>();
		
		while(in.hasNext()) {
			students.add(new coord(in.nextInt(), in.nextInt()));
		}
		
		*/
		
		int dirx = 1;
		int diry = 0;
		
		coord start = new coord(0,0);
		for(int i=0; i < n_students; i++) {
			System.out.println(start);
			start.x += dirx;
			start.y += diry;
			
			if(start.x == start.y+1 && start.x > 0) {
				dirx = 0;
				diry = 1;
			} else if(start.x == start.y && start.x > 0){
				dirx = -1;
				diry = 0;
			} else if(start.x == -start.y && start.x < 0 && start.y > 0) {
				dirx = 0;
				diry = -1;
			} else if(start.x == start.y && start.y < 0) {
				dirx = 1;
				diry = 0;
			}
		}
		
	}
	
	public static void main(String[] args) {
		new kunal_d();
	}
	
	public class coord {
		int x, y;
		
		public coord(int x, int y) {
			this.x = x; this.y = y;
		}
		
		public boolean equals(Object other) {
			coord o = (coord)other;
			return x == o.x && y == o.y;
		}
		
		public int hashCode() {
			return x*121 + y;
		}
		
		public String toString() {
			return "(" + this.x + "," + this.y + ")";
		}
	}
}
