import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ricket_ResistorsNetwork {
	public static void main(String[] args) {
		doStuff();
	}

	public static void doStuff() {
		try {
			if (InetAddress.getLocalHost().getHostName()
					.equals("nom26779d.nomadic.ncsu.edu")) {
				try {
					System.setIn(new FileInputStream(
							new File(
									"/Users/ricky/Documents/IEEEXtreme/problemJ/input.txt")));
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

		int numProblems = 0;
		try {
			numProblems = Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
		} catch (IOException e) {
		}

		for (int i = 0; i < numProblems; i++) {
			try {
				solveProblem(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void solveProblem(String line) {
		String[] strResistors = line.split(" ");
		Node rootNode = null;
		Map<Character, Node> allNodes = new HashMap<Character, Node>();
		List<Resistor> allResistors = new ArrayList<Resistor>();
		for (String s : strResistors) {
			char c0 = s.charAt(0);
			char c1 = s.charAt(1);
			
			Node n1 = allNodes.get(Character.valueOf(c1));
			if(n1 == null) {
				n1 = new Node(c1);
				allNodes.put(Character.valueOf(c1), n1);
			}
			
			Node n0 = allNodes.get(Character.valueOf(c0));
			if(n0 == null) {
				n0 = new Node(c0);
				allNodes.put(Character.valueOf(c0), n0);
				if(rootNode == null) {
					rootNode = n0;
				}
			}
			
			Resistor res = new Resistor(n0, n1, 1.0);
			n0.rightResistors.add(res);
			n1.leftResistors.add(res);
			allResistors.add(res);
		}
		
		// now solve it
		while(rootNode.rightResistors.size() > 1 || !rootNode.rightResistors.get(0).right.rightResistors.isEmpty() || rootNode.rightResistors.get(0).right.leftResistors.size() > 1) {
			// System.out.println(rootNode.printGraph());
			
			// do serials
			for(Node n : allNodes.values()) {
				if(n.leftResistors.size() == 1 && n.rightResistors.size() == 1) {
					Resistor leftR = n.leftResistors.get(0);
					Resistor rightR = n.rightResistors.get(0);
					n.leftResistors.remove(leftR);
					n.rightResistors.remove(rightR);
					Node left = leftR.left;
					Node right = rightR.right;
					double resistance = leftR.value + rightR.value;
					left.rightResistors.remove(leftR);
					right.leftResistors.remove(rightR);
					allResistors.remove(leftR);
					allResistors.remove(rightR);
					Resistor newResistor = new Resistor(left, right, resistance);
					left.rightResistors.add(newResistor);
					right.leftResistors.add(newResistor);
					allResistors.add(newResistor);
				}
			}
			
			// do parallels
			for(int res0 = 0; res0 < allResistors.size(); res0++) {
				for(int res1 = res0+1; res1 < allResistors.size(); res1++) {
					Resistor one = allResistors.get(res0);
					Resistor two = allResistors.get(res1);
					if(one.left == two.left && one.right == two.right) {
						// parallel
						one.value = 1.0 / ((1.0 / one.value) + (1.0 / two.value));
						two.left.rightResistors.remove(two);
						two.right.leftResistors.remove(two);
						allResistors.remove(two);
						res1--;
					}
				}
			}
		}
		
		System.out.printf("%.4f\n", allResistors.get(0).value);
	}

	private static class Node {
		char letter;
		List<Resistor> leftResistors = new ArrayList<Resistor>(),
				rightResistors = new ArrayList<Resistor>();

		public Node(char letter) {
			this.letter = letter;
		}
		
		public String printGraph() {
			StringBuilder sb = new StringBuilder();
			for(Resistor r : rightResistors) {
				sb.append(r.toString());
				sb.append(" ");
				sb.append(r.right.printGraph());
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	private static class Resistor {
		Node left, right;
		double value;

		public Resistor(Node left, Node right, double value) {
			this.left = left;
			this.right = right;
			this.value = value;
		}

		@Override
		public String toString() {
			return ""+left.letter+right.letter;
		}
	}
}
