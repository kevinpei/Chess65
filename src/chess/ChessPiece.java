package chess;

import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
/*
 *An abstract class which contains all the methods necessary for creating the other chess pieces. 
 */
public abstract class ChessPiece {
	// All ChessPieces will have a color ("b" or "w") and the board they're on
	public String color;
	public Chessboard board;
	public int row, column;
	// A method to get all of a piece's currently available moves.
	public abstract ArrayList<String> getAvailableMoves();
	// All ChessPieces will have the same move function, checking whether the given move is available.
	public boolean isValidMove(String destination) {
		ArrayList<String> moves = this.getAvailableMoves();
		if (moves.contains(destination)){
			return true;
		} else {
			return false;
		}
	}
	// The constructor used for each ChessPiece.
	public ChessPiece(String color, Chessboard board, int row, int column) {
		this.color = color;
		this.board = board;
		this.row = row;
		this.column = column;
	}
	// A method to convert the piece into its string representation.
	public abstract String toString();
	
}
