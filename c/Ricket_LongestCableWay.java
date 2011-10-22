import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
		int d;
		try {
			d = Integer.parseInt(in.readLine());
		} catch (IOException e) {
		}
		
		String line;
		List<CableInventory> inventory = new ArrayList<CableInventory>();
		
		try {
			while((line = in.readLine()) != null) {
				int idx = line.indexOf(' ');
				int len = Integer.parseInt(line.substring(0, idx));
				int quan = Integer.parseInt(line.substring(idx+1));
				if(quan > 0) {
					inventory.add(new CableInventory(len, quan));
				}
			}
		} catch (IOException e) {
		}
		
		
		
	}
	
	private static class CableInventory {
		public final int length;
		public final int max;
		public int num;
		public CableInventory(int length, int num) {
			this.length = length;
			this.max = num;
		}
	}

}
