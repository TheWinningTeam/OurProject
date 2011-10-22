import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class kunal_h {
	
	@SuppressWarnings("unchecked")
	public kunal_h() {
		Scanner in = new Scanner(System.in);
		int n_problems = in.nextInt();
		for(int k=0; k < n_problems; k++) {
			int n_balls = in.nextInt();
			ball balls[] = new ball[n_balls];
			
			for(int i=0; i < n_balls; i++) {
				balls[i] = new ball(in.nextInt(), in.nextInt());
			}
			
			ArrayList<ball> aballs = new ArrayList<ball>(Arrays.asList(balls));
			
			
			int target = in.nextInt();
			double time = in.nextInt();
			
			while(time > 0) {
				double next_event = time;
				for(int i=0; i < aballs.size(); i++) {
					double collision_time = aballs.get(i).get_collision_time(aballs, time);
					
					if(collision_time < next_event)
						next_event = collision_time;
				}
				
				int j=0;
				for(int i=0; i < aballs.size(); i++)
					if(aballs.get(i-j).update_dist(next_event)) {
						aballs.remove(i-j);
						j += 1;
					}
				
				for(ball b : aballs)
					b.update_vel(aballs);
				
				for(ball b : aballs)
					b.updated = false;
				
				time -= next_event;
			}
			
			if(k == n_problems-1) {
				System.out.printf("%d", (int)balls[target-1].dist);
			} else 
				System.out.printf("%d\n", (int)balls[target-1].dist);
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	private class ball implements Comparator {
		double dist, speed;
		boolean updated = false;
		public ball(int dist, int speed) {
			this.dist = dist;
			this.speed = speed;
			
		}
		
		public double get_collision_time(ArrayList<ball> aballs, double max) {
			if(speed == 0)
				return max;
			
			double cap = (100 - dist) / speed;
			
			if(cap <= 0) {
				cap = dist / -speed;
			}
			
			double min = cap;
			
			for(ball b : aballs) {
				double rel_vel = speed - b.speed;
				double dist = this.dist - b.dist;
				int sign = rel_vel > 0 ? 1 : -1;
				
				if(rel_vel == 0) {
					continue;
				}
				
				double time = dist * sign / rel_vel;
				if(time > 0 && time < min) {
					min = time; 
				}
				
			}
			
			return min;
			
		}
		
		public boolean update_dist(double nextEvent) {
			if(dist == 100 || dist == 0)
				return true;
			
			this.dist += this.speed * nextEvent;
			return false;
		}
		
		public void update_vel(ArrayList<ball> aballs) {
			
			if(dist == 0 || dist == 100) {
				speed = -speed;
				return;
			}
			
			if(!updated)
			for(ball b : aballs) {
				if(b.dist == dist) {
					double temp = b.speed;
					b.speed = speed;
					speed = temp;
					updated = true;
					b.updated = true;
				}
			}
		}
		
		public int compare(Object arg0, Object arg1) {
			ball one = (ball)arg0;
			ball two = (ball)arg1;
			double res = one.dist - two.dist;
			if(res < 0) return -1;
			if(res > 0) return 1;
			return 0;
		}
		
	}
	
	public static void main(String[] args) {
		new kunal_h();
	}

}
