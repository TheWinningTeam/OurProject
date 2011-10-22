import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RENAMETHIS {
	public static void main(String[] args) {
		try {
			if(InetAddress.getLocalHost().getHostName().equals("YOUR COMPUTER HOSTNAME")) {
				try {
					System.setIn(new FileInputStream(new File("/home/YOURPATH/....../input.txt")));
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

	}
}
