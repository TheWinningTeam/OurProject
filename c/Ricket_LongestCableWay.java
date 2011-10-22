import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class Ricket_LongestCableWay {
	public static void main(String[] args) {
		try {
			if (InetAddress.getLocalHost().getHostName()
					.equals("nom26779d.nomadic.ncsu.edu")) {
				try {
					System.setIn(new FileInputStream(new File(
							"/Users/ricky/Documents/IEEEXtreme/problemC/input.txt")));
				} catch (FileNotFoundException e1) {
					System.out.println("Input file not found");
					System.exit(1);
				}
			}
		} catch (UnknownHostException e1) {
		}

		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		// begin your stuff here
		final int d;
		try {
			d = Integer.parseInt(in.readLine());
		} catch (IOException e) {
			System.exit(1);
			return; // stupid compiler
		}
		
		String line;
		CableInventory[] inventory = new CableInventory[20];
		
		int invidx = 0;
		try {
			while((line = in.readLine()) != null) {
				if(line.equals("0 0")) break;
				int idx = line.indexOf(' ');
				int len = Integer.parseInt(line.substring(0, idx));
				int quan = Integer.parseInt(line.substring(idx+1));
				if(quan > 0) {
					// optimize by reducing the len so that len*quan < d
					quan = Math.min(quan, d / len);
					inventory[invidx] = new CableInventory(len, quan);
					invidx++;
				}
			}
		} catch (IOException e) {
		}
		
		// Arrays.sort(inventory, 0, invidx); // this sorts inventory descending

		int min = minJoints(d, 0, inventory, -1); // recurse!
		
		if(min == Integer.MAX_VALUE) {
			System.out.println("No solution possible");
		} else {
			System.out.println(min);
		}
		System.exit(0);
		
		
	}
	
	private static int minJoints(int lengthLeft, int currListIdx, CableInventory[] inventory, int numJointsSoFar) {
		int min;
		if(lengthLeft == 0) {
			min = numJointsSoFar;
		} else {
			min = Integer.MAX_VALUE;
		}
		
		if(currListIdx >= inventory.length) return min;
		
		CableInventory thisInven = inventory[currListIdx];
		if(thisInven == null) return min;
		
		for(int i=0; i <= thisInven.num; i++) {
			if(lengthLeft - i*thisInven.length < 0) break;
			min = Math.min(min,
					minJoints(lengthLeft - i*thisInven.length, currListIdx+1, inventory, numJointsSoFar + i)
					);
		}
		
		return min;
	}
	
	private static class CableInventory implements Comparable<CableInventory> {
		public final int length;
		public final int num;
		public CableInventory(int length, int num) {
			this.length = length;
			this.num = num;
		}
		
		@Override
		public int compareTo(CableInventory o) {
			return o.length - this.length;
		}
	}

}
