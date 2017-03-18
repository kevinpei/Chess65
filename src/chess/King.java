package chess;

import java.util.ArrayList;

public class King extends ChessPiece{

	/*
	 * The king also includes a hasMoved variable because a king can only castle if it has not
	 * moved yet.
	 */
	public boolean hasMoved;
	
	public King(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
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
	
	/*
	 * A king can move one square in any direction. 
	 */
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValidSquare(this.row + i, this.column + j)) {
						possibleMoves.add(this.board.getSquare(this.row + i,  this.column + j).getPosition());
					}
				}
			}
		}
		return possibleMoves;
	}
	
	/*
	 * This statement checks to see if the move will put the king in check. If it will, it returns false.
	 * Otherwise, it returns true.
	 */
	public boolean isValidMove(String destination) {
		if (super.isValidMove(destination)) {
			ArrayList<String> possibleEnemyMoves = new ArrayList<String>();
			if (this.color.equals("b")) {
				possibleEnemyMoves = this.board.getColorAvailableCaptures("w");
			} else {
				possibleEnemyMoves = this.board.getColorAvailableCaptures("b");
			}
			for (String move: possibleEnemyMoves) {
				if (this.getAvailableMoves().contains(move)){
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return color + "K";
	}
	
}
