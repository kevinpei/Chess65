package chess;

import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */

public class Queen extends ChessPiece{

    /**
     * Queen constructor
     * <p>
     * The constructor for Queen calls the ChessPiece super constructor.
     *
     * @param color Description: The color of the Queen. Can be w or b for white and black.
     * @param board Description: The chessboard the Queen is located in.
     * @param row Description: The row the Queen starts on in the chessboard.
     * @param column Description: The column the Queen starts on in the chessboard.
     */
	public Queen(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}


    /**
     * Gets all available moves for the Queen.
     * <p>
     * determines the legal squares that this Queen can move to from its current location
     * Queen can move: as far as the player would like in a unblocked horizontal, vertical, or diagonal line
     *
     * @return Description: Returns the ArrayList of all possible moves the Queen can take from its current location.
     */
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		/*
		 * This following code block tests all squares above the queen.
		 * It tests each square above the queen one at a time. If it is empty,
		 * then the square is a possible move. If it is occupied, that means
		 * that both it and every square above it is not a valid move, thus
		 * why i is set to -1.
		 */
		for (int i = this.column - 1; i >= 0; i--) {
			if (this.board.getSquare(this.row, i).getPiece() != null) {
				/*
				 * If the space is occupied by a piece of the opposite color, then it is possible to
				 * move there by capturing that piece. However, further movement beyond that piece is still
				 * impossible.
				 */
				if (!(this.board.getSquare(this.row, i).getPiece().color.equals
						(this.board.getSquare(this.row, this.column).getPiece().color))) {
					possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
				}
				i = -1;
			} else {
				possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
			}
		}
		/*
		 * It is the same idea for this one, except moving downward instead of upward.
		 */
		for (int i = this.column + 1; i < 8; i++) {
			if (this.board.getSquare(this.row, i).getPiece() != null) {
				if (!(this.board.getSquare(this.row, i).getPiece().color.equals
						(this.board.getSquare(this.row, this.column).getPiece().color))) {
					possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
				}
				i = 8;
			} else {
				possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
			}
		}
		/*
		 * Likewise, this checks all moves to the left of the queen.
		 */
		for (int i = this.row - 1; i >= 0; i--) {
			if (this.board.getSquare(i, this.column).getPiece() != null) {
				if (!(this.board.getSquare(i, this.column).getPiece().color.equals
						(this.board.getSquare(this.row, this.column).getPiece().color))) {
					possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
				}
				i = -1;
			} else {
				possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
			}
		}
		/*
		 * Likewise, this checks all moves to the right of the queen.
		 */
		for (int i = this.row + 1; i < 8; i++) {
			if (this.board.getSquare(i, this.column).getPiece() != null) {
				if (!(this.board.getSquare(i, this.column).getPiece().color.equals
						(this.board.getSquare(this.row, this.column).getPiece().color))) {
					possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
				}
				i = 8;
			} else {
				possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
			}
		}
		// j will keep track of whatever i isn't keeping track of to ensure diagonal movement.
		int j = 0;
		/*
		 * This following code block tests all directions diagonally upwards and to the left
		 * of the queen. If there is no piece, then it continues testing along that path. If
		 * there is a piece, that means that each space beyond that is invalid, hence why i
		 * is set to -1 to terminate the loop.
		 */
		for (int i = this.column - 1; i >= 0; i--) {
			/*
			 * j is used to keep track of the row in this case. Because the queen is moving
			 * up and to the left, i keeps track of the leftward movement, while j keeps track
			 * of the upward movement. Whenever i increases, j also has to increase to indicate
			 * diagonal movement. j in this case is being subtracted from row to indicate upward
			 * movement.
			 */
			j++;
			if (this.row - j >= 0) {
				//System.out.println("Testing " + (this.row - j) + ", " + i );
				if (this.board.getSquare(this.row - j, i).getPiece() != null) {
					/*
					 * If the space is occupied by a piece of the opposite color, then it is possible to
					 * move there by capturing that piece. However, further movement beyond that piece is still
					 * impossible.
					 */
					if (!(this.board.getSquare(this.row - j, i).getPiece().color.equals
							(this.board.getSquare(this.row, this.column).getPiece().color))) {
						possibleMoves.add(this.board.getSquare(this.row - j, i).getPosition());
					}
					i = -1;
				} else {
					possibleMoves.add(this.board.getSquare(this.row - j, i).getPosition());
				}
			}
		}
		// We reset j after every code block.
		j = 0;
		/*
		 * This code block does the same above for diagonally upwards and to the right.
		 */
		for (int i = this.column + 1; i < 8; i++) {
			j++;
			if (this.row - j >= 0) {
				if (this.board.getSquare(this.row - j, i).getPiece() != null) {
					if (!(this.board.getSquare(this.row - j, i).getPiece().color.equals
							(this.board.getSquare(this.row, this.column).getPiece().color))) {
						possibleMoves.add(this.board.getSquare(this.row - j, i).getPosition());
					}
					i = 8;
				} else {
					possibleMoves.add(this.board.getSquare(this.row - j, i).getPosition());
				}
			}
		}
		j = 0;
		/*
		 * This code block does the same above for diagonally downwards and to the right.
		 */
		for (int i = this.column + 1; i < 8; i++) {
			j++;
			if (this.row + j < 8) {
				if (this.board.getSquare(this.row + j, i).getPiece() != null) {
					if (!(this.board.getSquare(this.row + j, i).getPiece().color.equals
							(this.board.getSquare(this.row, this.column).getPiece().color))) {
						possibleMoves.add(this.board.getSquare(this.row + j, i).getPosition());
					}
					i = 8;
				} else {
					possibleMoves.add(this.board.getSquare(this.row + j, i).getPosition());
				}
			}
		}
		j = 0;
		/*
		 * This code block does the same above for diagonally downwards and to the left.
		 */
		for (int i = this.column - 1; i >= 0; i--) {
			j++;
			if (this.row + j < 8) {
				if (this.board.getSquare(this.row + j, i).getPiece() != null) {
					if (!(this.board.getSquare(this.row + j, i).getPiece().color.equals
							(this.board.getSquare(this.row, this.column).getPiece().color))) {
						possibleMoves.add(this.board.getSquare(this.row + j, i).getPosition());
					}
					i = -1;
				} else {
					possibleMoves.add(this.board.getSquare(this.row + j, i).getPosition());
				}
			}
		}
		return possibleMoves;
	}

    /**
     * Returns the String representation of this piece.
     * <p>
     * Returns a String representation of this piece, which is its one-letter color appended by "Q",
     * representing its status as a Queen.
     *
     *
     * @return Description: Returns the string representation of this piece.
     */
	public String toString() {
		return color + "Q";
	}
	
}
