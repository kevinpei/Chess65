package chess;

import java.util.ArrayList;
import java.util.HashMap;

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

	public void move(String command){
		char columnChar = command.charAt(0);
		int row = Character.getNumericValue(command.charAt(1));
		row--;
		int column;
		switch(columnChar) {
			case 'a': column = 0; break;
			case 'b': column = 1; break;
			case 'c': column = 2; break;
			case 'd': column = 3; break;
			case 'e': column = 4; break;
			case 'f': column = 5; break;
			case 'g': column = 6; break;
			case 'h': column = 7; break;
			default: column = -1; break;
		}
		if(column == -1){
			return;
		}
		this.board.getSquare(row,column).currentPiece = this;
		this.board.getSquare(this.row, this.column).currentPiece = null;
		this.row = row;
		this.column = column;
	}
}
