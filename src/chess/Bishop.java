package chess;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
	
	public Bishop(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		// j will keep track of whatever i isn't keeping track of to ensure diagonal movement.
		int j = 0;
		/*
		 * This following code block tests all directions diagonally upwards and to the left
		 * of the bishop. If there is no piece, then it continues testing along that path. If
		 * there is a piece, that means that each space beyond that is invalid, hence why i
		 * is set to -1 to terminate the loop.
		 */
		for (int i = this.column - 1; i >= 0; i--) {
			/*
			 * j is used to keep track of the row in this case. Because the bishop is moving
			 * up and to the left, i keeps track of the leftward movement, while j keeps track
			 * of the upward movement. Whenever i increases, j also has to increase to indicate
			 * diagonal movement. j in this case is being subtracted from row to indicate upward
			 * movement.
			 */
			while (this.row - j >= 0) {
				j++;
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
			while (this.row - j >= 0) {
				j++;
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
			while (this.row + j < 8) {
				j++;
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
			while (this.row + j < 8) {
				j++;
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

	public String toString() {
		return color + "B";
	}
	
}
