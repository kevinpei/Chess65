package chess;

//import statements
import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Knight extends ChessPiece{

	/**
	 * Knight constructor
	 * <p>
	 * The constructor for Knight calls the ChessPiece super constructor.
	 * 
	 * @param color Description: The color of the Knight. Can be w or b for white and black.
	 * @param board Description: The chessboard the Knight is located in.
	 * @param row Description: The row the Knight starts on in the chessboard.
	 * @param column Description: The column the Knight starts on in the chessboard.
	 */
	public Knight(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	/**
	 * Checks to see if the given square is valid to move onto.
	 * <p>
	 *This function checks to see whether it is a valid move to move onto the square with
	 *the given row and column. It is valid if it is empty or if there is a piece of the opposite color
	 *there, which the Knight can capture.
	 *<p>
	 *The move is invalid if the move takes the Knight outside the board (row or column greater than 8 or
	 *less than 0) and if there is another piece of the same color on that square.
	 *
	 *@param row Description: The row of the square being checked for whether it is valid to move onto.
	 *@param column Description: The column of the square being checked for whether it is valid to move onto.
	 *@return Description: Returns true if the Knight can move onto the given square and false otherwise.
	 */
	public boolean isValidSquare(int row, int column) {
		if (row >= 0 && row < 8 && column >= 0 && column < 8) {
			if (this.board.getSquare(row,  column).getPiece() == null || 
					!(this.board.getSquare(row,  column).getPiece().color.equals(this.color))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets all available moves for the Knight.
	 * <p>
	 * Calls the isValidSquare function for the squares that a knight can move to: 2 rows up and 1 column to
	 * the left, 1 row up and 2 columns to the left, 1 row down and 2 columns to the left, 2 rows down and
	 * 1 column to the left, 2 rows down and 1 column to the right, 1 row down and 2 columns to the right,
	 * 1 row up and 2 columns to the right, and 2 rows up and 1 column to the right.
	 * <p>
	 * If the isValidSquare function returns true for a given position, then that position's letter-number
	 * combination will be added to an ArrayList of all possible moves for the Knight.
	 * 
	 * 
	 * @return Description: Returns the ArrayList of all possible moves the Knight can take.
	 */
	public ArrayList<String> getAvailableMoves() {
		
		/*
		 * An ArrayList of Strings that holds the string representations of all possible moves that 
		 * can be taken by the Knight.
		 */
		ArrayList<String> possibleMoves = new ArrayList<String>();
		
		/*
		 * A 2-dimensional array of integers that holds all the positions a knight can move to. Each of 
		 * these positions will be checked by isValidSquare to see if they are valid moves for the knight.
		 */
		int[][] knightPositions = {{this.row + 2, this.column + 1}, {this.row + 1, this.column + 2},
				{this.row - 1, this.column + 2}, {this.row - 2, this.column + 1}, {this.row - 2, this.column - 1},
				{this.row - 1, this.column - 2}, {this.row + 1, this.column - 2}, {this.row + 2, this.column - 1}};
		
		for (int i = 0; i < 8; i++) {
			if (isValidSquare(knightPositions[i][0], knightPositions[i][1])) {
				possibleMoves.add(this.board.getSquare(knightPositions[i][0], knightPositions[i][1]).getPosition());
			}
		}
		return possibleMoves;
	}

	/**
	 * Returns the String representation of this piece.
	 * <p>
	 * Returns a String representation of this piece, which is its one-letter color appended by "N",
	 * representing its status as a knight.
	 * 
	 * @return Description: Returns the string representation of this piece.
	 */
	public String toString() {
		return color + "N";
	}
}
