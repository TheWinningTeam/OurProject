import java.util.Scanner;


public class kunal_e {
	
	private class square {
		public int[] possible;
		public int row_clue = -1;
		public int col_clue = -1;
		public int block = 0;
		public int n_possible = 0;
		
		public square() {
			possible = new int[46];
			n_possible = 45;
		}
		
		public void disable(int start, int end) {
			for(int i=start; i < end; i++) {
				if(possible[i] == 0)
					n_possible -= 1;
				
				possible[i] = 1;
			}
		}
		
		public boolean isset(int x) {
			return possible[x] == 0;
		}
		
	}
	
	
	public kunal_e() {
		Scanner in = new Scanner(System.in);
		
		int size = in.nextInt();
		
		square board[][] = new square[size][size];
		
		int col = 0;
		
		for(int i=0; i < size; i++) {
			
			for(int j=0; j < size; j++) {
				int first = in.nextInt();
				int second = in.nextInt();
				
				if(first == -1 && second == -1) {
					board[j][i] = null;
				} else if(first == 0 && second == 0) {
					continue;
				} else if(first == -1) {
					board[j][i].row_clue = second;
					board[j][i].n_possible = 1;
				} else if(second == -1) {
					board[j][i].col_clue = first;
					board[j][i].n_possible = 1;
				} else {
					board[j][i].row_clue = first;
					board[j][i].col_clue = second;
					board[j][i].n_possible = 1;
				}
			}
		}
		
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				if(board[j][i] == null) {
					continue;
				}
				
				if(board[j][i].col_clue != -1) {
					int n_blocks = 0;
					while(board[j][i+n_blocks] != null &&
							board[j][i+n_blocks].row_clue != -1 &&
							board[j][i+n_blocks].col_clue != -2) {
						n_blocks += 1;
					}
					
					for(int k=0; i < n_blocks; k++) {
						board[j][i+k].disable(board[j][i].col_clue - n_blocks + 1, 46);
					}
					
				}
				
				if(board[j][i].row_clue != -1) {
					int n_blocks = 0;
					while(board[j+n_blocks][i] != null &&
							board[j+n_blocks][i].row_clue != -1 &&
							board[j+n_blocks][i].col_clue != -2) {
						n_blocks += 1;
					}
					
					for(int k=0; i < n_blocks; k++) {
						board[j+k][i].disable(board[j][i].row_clue - n_blocks + 1, 46);
					}
					
				}
				
			}
		}
		
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				System.out.println(board[j][i].n_possible);
			}
		}

		
	}

	public static void main(String[] args) {
		new kunal_e();

	}

}
