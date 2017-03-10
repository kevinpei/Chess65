package chess;

public class Chessboard {
	ChessSquare[][] chessboard = new ChessSquare[8][8];
	boolean whiteIsGoing;
	
	public void initializeSide(int row, String color) {
		this.chessboard[row][0] = new ChessSquare(new Rook(color), row, 0);
		this.chessboard[row][1] = new ChessSquare(new Knight(color), row, 1);
		this.chessboard[row][2] = new ChessSquare(new Bishop(color), row, 2);
		this.chessboard[row][3] = new ChessSquare(new Queen(color), row, 3);
		this.chessboard[row][4] = new ChessSquare(new King(color), row, 4);
		this.chessboard[row][5] = new ChessSquare(new Bishop(color), row, 5);
		this.chessboard[row][6] = new ChessSquare(new Knight(color), row, 6);
		this.chessboard[row][7] = new ChessSquare(new Rook(color), row, 7);
	}
	
	public Chessboard() {
		initializeSide(0, "b");
		for (int i = 0; i < 8; i++) {
			this.chessboard[1][i] = new ChessSquare(new Pawn("b"), 1, i);
		}
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				this.chessboard[i][j] = new ChessSquare(null, i, j);
			}
		}
		for (int i = 0; i < 8; i++) {
			this.chessboard[6][i] = new ChessSquare(new Pawn("w"), 6, i);
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
	
	public ChessSquare[][] getBoard() {
		return this.chessboard;
	}
	
}
