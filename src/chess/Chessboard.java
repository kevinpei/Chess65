package chess;

public class Chessboard {
	ChessPiece[][] chessboard = new ChessPiece[8][8];
	boolean whiteIsGoing;
	
	public void initializeSide(int row, String color) {
		this.chessboard[row][0] = new Rook(color);
		this.chessboard[row][1] = new Knight(color);
		this.chessboard[row][2] = new Bishop(color);
		this.chessboard[row][3] = new Queen(color);
		this.chessboard[row][4] = new King(color);
		this.chessboard[row][5] = new Bishop(color);
		this.chessboard[row][6] = new Knight(color);
		this.chessboard[row][7] = new Rook(color);
	}
	
	public Chessboard() {
		initializeSide(0, "b");
		for (int i = 0; i < 8; i++) {
			this.chessboard[1][i] = new Pawn("b");
		}
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				if (i % 2 == 0 && j % 2 == 1) {
					this.chessboard[i][j] = new None("##");
				} else if (i % 2 == 1 && j % 2 == 0){
					this.chessboard[i][j] = new None("##");
				} else {
					this.chessboard[i][j] = new None("  ");
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			this.chessboard[6][i] = new Pawn("w");
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
	
	public ChessPiece[][] getBoard() {
		return this.chessboard;
	}
	
}
