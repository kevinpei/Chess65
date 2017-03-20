package chess;

//import statements
import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Chessboard {
	//A Chessboard is represented as an 8 x 8 array of chess squares
	ChessSquare[][] chessboard = new ChessSquare[8][8];
	//Tells whether white is going, in which case it is true
	boolean whiteIsGoing;
	
	//This function initializes a given side's non-pawn pieces
	public void initializeSide(int row, String color) {
		this.chessboard[row][0] = new ChessSquare(new Rook(color, this, row, 0), row, 0);
		this.chessboard[row][1] = new ChessSquare(new Knight(color, this, row, 1), row, 1);
		this.chessboard[row][2] = new ChessSquare(new Bishop(color, this, row, 2), row, 2);
		this.chessboard[row][3] = new ChessSquare(new Queen(color, this, row, 3), row, 3);
		this.chessboard[row][4] = new ChessSquare(new King(color, this, row, 4), row, 4);
		this.chessboard[row][5] = new ChessSquare(new Bishop(color, this, row, 5), row, 5);
		this.chessboard[row][6] = new ChessSquare(new Knight(color, this, row, 6), row, 6);
		this.chessboard[row][7] = new ChessSquare(new Rook(color, this, row, 7), row, 7);
	}
	
	//This is the constructor. It automatically populates the chessboard with ChessSquares
	public Chessboard() {
		//Initializes the black side's non-pawn pieces
		initializeSide(0, "b");
		//Initializes the black side's pawns
		for (int i = 0; i < 8; i++) {
			this.chessboard[1][i] = new ChessSquare(new Pawn("b", this, 1, i), 1, i);
		}
		//Initializes the middle unoccupied squares
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				this.chessboard[i][j] = new ChessSquare(null, i, j);
			}
		}
		//Initializes the white side's pawns
		for (int i = 0; i < 8; i++) {
			this.chessboard[6][i] = new ChessSquare(new Pawn("w", this, 6, i), 6, i);
		}
		//Initializes the white side's non-pawn pieces
		initializeSide(7, "w");
		this.chessboard[4][4] = new ChessSquare(new Queen("w", this, 4, 4), 4, 4);
		this.chessboard[4][5] = new ChessSquare(new Pawn("b", this, 4, 5), 4, 5);
		this.chessboard[4][3] = new ChessSquare(new Pawn("b", this, 4, 3), 4, 3);
		this.chessboard[3][4] = new ChessSquare(new Pawn("b", this, 3, 4), 3, 4);
		this.chessboard[3][5] = new ChessSquare(new Pawn("b", this, 3, 5), 3, 5);
		this.chessboard[3][3] = new ChessSquare(new Pawn("b", this, 3, 3), 3, 3);
		whiteIsGoing = true;
	}
	
	//Converts the chessboard into a string representation
	public String toString() {
		String output = new String();
		//Adds whatever's on each ChessSquare to the output string
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				output += this.chessboard[i][j];
				//After it reaches the 8th column, it adds a new line character
				if (j == 7) {
					output += "\n";
				}
			}
		}
		return output;
	}
	
	/*
	 * This function returns all possible moves of every piece of a given color.
	 * It is used to determine if there is a stalemate.
	 */
	public ArrayList<String> getColorAvailableMoves(String color) {
		ArrayList<String> allPieceMoves = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j].currentPiece != null) {
					if (chessboard[i][j].currentPiece.color.equals(color)) {
						if (chessboard[i][j].currentPiece.getAvailableMoves() != null) {
							allPieceMoves.addAll(chessboard[i][j].currentPiece.getAvailableMoves());
						}
					}
				}
			}
		}
		return allPieceMoves;
	}
	
	/*
	 * This function returns all possible capturing moves of every piece of a given color. This is used
	 * to determine whether a king is in checkmate and whether a given move will move it into check.
	 */
	public ArrayList<String> getColorAvailableCaptures(String color) {
		ArrayList<String> allPieceMoves = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j].currentPiece != null) {
					if (chessboard[i][j].currentPiece.color.equals(color)) {
						if (chessboard[i][j].currentPiece.getAvailableMoves() != null) {
							if (!(chessboard[i][j].currentPiece instanceof Pawn)) {
								allPieceMoves.addAll(chessboard[i][j].currentPiece.getAvailableMoves());
							} else if (chessboard[i][j].currentPiece instanceof Pawn) {
								allPieceMoves.addAll(((Pawn) chessboard[i][j].currentPiece).getAvailableCaptures());
							}
						}
					}
				}
			}
		}
		return allPieceMoves;
	}
	
	//This function returns the ChessSquare[][] chessboard
	public ChessSquare[][] getBoard() {
		return this.chessboard;
	}
	
	public ChessSquare getSquare(int i, int j) {
		return this.chessboard[i][j];
	}
	
}
