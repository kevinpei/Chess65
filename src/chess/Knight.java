package chess;

import java.util.ArrayList;

public class Knight extends ChessPiece{

	public Knight(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	/*
	 *This function checks to see whether it is a valid move to move onto the square with
	 *the given row and column. It is valid if it is empty or if there is a piece of the opposite color
	 *there.
	 */
	public boolean isValidSquare(int row, int column) {
		// The move is automatically invalid if it takes a piece outside the board.
		if (row >= 0 && row < 8 && column >= 0 && column < 8) {
			if (this.board.getSquare(row,  column).getPiece() == null || 
					!(this.board.getSquare(row,  column).getPiece().color.equals(this.color))) {
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		/*
		 * knightPositions represents the possible ways the knight can move, being two down and one to the right,
		 * one down and two the right, one up and two to the right, two up and one to the right, two up
		 * and one to the left, one up and two to the left, one down and two to the left, and two down and one
		 * to the left respectively.
		 */
		int[][] knightPositions = {{this.row + 2, this.column + 1}, {this.row + 1, this.column + 2},
				{this.row - 1, this.column + 2}, {this.row - 2, this.column + 1}, {this.row - 2, this.column - 1},
				{this.row - 1, this.column - 2}, {this.row + 1, this.column - 2}, {this.row + 2, this.column - 1}};
		/*
		 * For each of the knightPositions, check to see if that move is valid and add it to the list of possible
		 * moves if it is. 
		 */
		for (int i = 0; i < 8; i++) {
			if (isValidSquare(knightPositions[i][0], knightPositions[i][1])) {
				possibleMoves.add(this.board.getSquare(knightPositions[i][0], knightPositions[i][1]).getPosition());
			}
		}
		return possibleMoves;
	}

	public String toString() {
		return color + "N";
	}
	
}
