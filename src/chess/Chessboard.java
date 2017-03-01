package chess;

public class Chessboard {
	String[][] chessboard = new String[8][8];
	boolean whiteIsGoing;
	
	public void initializeSide(int row, String color) {
		this.chessboard[row][0] = color + "R";
		this.chessboard[row][1] = color + "N";
		this.chessboard[row][2] = color + "B";
		this.chessboard[row][3] = color + "Q";
		this.chessboard[row][4] = color + "K";
		this.chessboard[row][5] = color + "B";
		this.chessboard[row][6] = color + "N";
		this.chessboard[row][7] = color + "R";
	}
	
	public Chessboard() {
		initializeSide(0, "b");
		for (int i = 0; i < 8; i++) {
			this.chessboard[1][i] = "bp";
		}
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0 && j % 2 == 1) {
					this.chessboard[i][j] = "##";
				} else if (i % 2 == 1 && j % 2 == 0){
					this.chessboard[i][j] = "##";
				} else {
					this.chessboard[i][j] = "  ";
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			this.chessboard[6][i] = "wp";
		}
		initializeSide(7, "w");
		whiteIsGoing = true;
	}
	
	public String toString() {
		String output = new String();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				output += this.chessboard[i][j];
				if (j == 7) {
					output += "\n";
				}
			}
		}
		return output;
	}
	
}
