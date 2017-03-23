package chess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */

/**
 *An abstract class which contains all the methods necessary for creating the other chess pieces. 
 */
public abstract class ChessPiece {
	// All ChessPieces will have a color ("b" or "w") and the board they're on
	/**
	 * A String which holds the color of the ChessPiece object. Can be either "b" or "w".
	 */
	public String color;
	/**
	 * A Chessboard object which holds the Chessboard the ChessPiece is currently on.
	 */
	public Chessboard board;
	/**
	 * The current row of the ChessPiece. Used to determine its position in the Chessboard.
	 */
	public int row;
	/**
	 * The current column of the ChessPiece. Used to determine its position in the Chessboard.
	 */
	public int column;
	/**
	 * A method to get all of a piece's currently available moves.
	 * @return Description: Returns an ArrayList containing all possible moves in chess move form (e.g. e8)
	 */
	public abstract ArrayList<String> getAvailableMoves();
	/**
	 * A function to check if a move is valid
	 * <p>
	 * This function checks whether the given move is available by checking to see if it's in its
	 * getAvailableMoves() ArrayList.
	 * @param destination Description: String representation of the chess move to check (e.g. e8).
	 * @return Description: Returns true if the move is valid and false otherwise.
	 */
	public boolean isValidMove(String destination) {
		ArrayList<String> moves = this.getAvailableMoves();
		if (moves.contains(destination)){
			return true;
		} else {
			return false;
		}
	}

	/** The constructor used for each ChessPiece.
	 *
	 * @param color Description: String of Color to be assigned to the generated ChessPiece.
	 * @param board Description: Chessboard object of the Board for the created ChessPiece to be placed on.
	 * @param row Description: Numerical value of the row for the piece to be placed.
	 * @param column Description: Numerical value of the column for the piece to be placed.
	 */
	public ChessPiece(String color, Chessboard board, int row, int column) {
		this.color = color;
		this.board = board;
		this.row = row;
		this.column = column;
	}

	/** A method to convert the piece into its string representation.
	 *
	 * @return Description: Abstract method to be implemented on implementing ChessPiece types
	 */
	public abstract String toString();

	/**
	 * Moves the ChessPiece to the given location.
	 * <p>
	 * Generic move method that takes the implicit Chess Piece and moves it to the given location
	 * It checks to make sure it is valid to move to that place
	 * 
	 * @param command Description: String form of a location to send the Chess Piece
	 */
	public void move(String command) {
		char columnChar = command.charAt(0);
		int rowChar = Character.getNumericValue(command.charAt(1));
		int row;
		switch (rowChar) {
			case 8:
				row = 0;
				break;
			case 7:
				row = 1;
				break;
			case 6:
				row = 2;
				break;
			case 5:
				row = 3;
				break;
			case 4:
				row = 4;
				break;
			case 3:
				row = 5;
				break;
			case 2:
				row = 6;
				break;
			case 1:
				row = 7;
				break;
			default:
				row = -1;
				break;
		}
		int column;
		switch (columnChar) {
			case 'a':
				column = 0;
				break;
			case 'b':
				column = 1;
				break;
			case 'c':
				column = 2;
				break;
			case 'd':
				column = 3;
				break;
			case 'e':
				column = 4;
				break;
			case 'f':
				column = 5;
				break;
			case 'g':
				column = 6;
				break;
			case 'h':
				column = 7;
				break;
			default:
				column = -1;
				break;
		}
		if (column == -1 || row == -1) {
			return;
		}
		this.board.getSquare(row, column).currentPiece = this;
		this.board.getSquare(this.row, this.column).currentPiece = null;
		this.row = row;
		this.column = column;
	}
}
