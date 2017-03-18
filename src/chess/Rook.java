package chess;

import java.util.ArrayList;

public class Rook extends ChessPiece{

	/*
	 * The rook also includes a hasMoved variable because a rook can only castle if it has not
	 * moved yet.
	 */
	public boolean hasMoved;
	
	public Rook(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
	}
	
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		/*
		 * This following code block tests all squares above the rook.
		 * It tests each square above the rook one at a time. If it is empty,
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
		 * Likewise, this checks all moves to the left of the rook.
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
		 * Likewise, this checks all moves to the right of the rook.
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
		return possibleMoves;
	}
	
	public String toString() {
		return color + "R";
	}

}
