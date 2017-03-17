package chess;

import java.util.ArrayList;

public class King extends ChessPiece{

	public King(String color, Chessboard board, int row, int column) {
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
		ArrayList<String> possibleEnemyMoves = new ArrayList<String>();
		if (this.color.equals("b")) {
			possibleEnemyMoves = this.board.getColorAvailablemoves("w");
		} else {
			possibleEnemyMoves = this.board.getColorAvailablemoves("b");
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValidSquare(this.row + i, this.column + j)) {
						if (!(possibleEnemyMoves.contains(this.board.
								getSquare(this.row + i, this.column + j).getPosition()))) {
							possibleMoves.add(this.board.
									getSquare(this.row + i,  this.column + j).getPosition());
						}
					}
				}
			}
		}
		return possibleMoves;
	}
	
	public String toString() {
		return color + "K";
	}
	
}
