package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public boolean hasMoved;
	
	
	public Pawn(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
	}
	
	/*
	 * This method checks to see whether the pawn can move vertically the given amount of squares.
	 * Usually it will be -1 for white pawns and 1 for black pawns, but it can also be -2 or 2 for
	 * the first move.
	 */
	public boolean isValidVerticalMovement(int verticalMovement) {
		if (this.board.getSquare(this.row + verticalMovement,  this.column).getPiece() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isValidCapture(int verticalMovement, int horizontalMovement) {
		if (this.board.getSquare(this.row + verticalMovement,  this.column + horizontalMovement)
				.getPiece() != null) {
			if (!(this.board.getSquare(this.row + verticalMovement,  this.column + horizontalMovement)
				.getPiece().color.equals(this.color))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/*
	 * This function returns an arraylist of all possible moves for the pawn. First, it tests to
	 * see whether the given pawn has moved before. If no, then it can move forward 2 spaces provided
	 * that both those spaces are blank. Pawns can also move diagonally to capture.
	 */
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		if (hasMoved == false) {
			if (this.color.equals("w")) {
				if (isValidVerticalMovement(-1) && isValidVerticalMovement(-2)) {
					possibleMoves.add(this.board.getSquare(this.row - 2, this.column).getPosition());
				}
			} else {
				if (isValidVerticalMovement(1) && isValidVerticalMovement(2)) {
					possibleMoves.add(this.board.getSquare(this.row + 2, this.column).getPosition());
				}
			}
			hasMoved = true;
		}
		if (this.color.equals("w")) {
			if (this.row > 0) {
				if (isValidVerticalMovement(-1)) {
					possibleMoves.add(this.board.getSquare(this.row - 1, this.column).getPosition());
				}
				if (isValidCapture(-1, -1)) {
					possibleMoves.add(this.board.getSquare(this.row - 1, this.column - 1).getPosition());
				}
				if (isValidCapture(-1, 1)) {
					possibleMoves.add(this.board.getSquare(this.row - 1, this.column + 1).getPosition());
				}
			}
		} else {
			if (this.row < 7) {
				if (isValidVerticalMovement(1)) {
					possibleMoves.add(this.board.getSquare(this.row + 1, this.column).getPosition());
				}
				if (isValidCapture(1, -1)) {
					possibleMoves.add(this.board.getSquare(this.row + 1, this.column - 1).getPosition());
				}
				if (isValidCapture(1, 1)) {
					possibleMoves.add(this.board.getSquare(this.row + 1, this.column + 1).getPosition());
				}
			}
		}
		return possibleMoves;
	}

	public String toString() {
		return color + "p";
	}
	
}
