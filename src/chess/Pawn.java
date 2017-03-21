package chess;

import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Pawn extends ChessPiece{

	/*
	 * The pawn also includes a hasMoved variable because the first move of pawn can be one or
	 * two moves forward.
	 */
	public boolean hasMoved;
	public boolean movedTwoSquares;
	
	public Pawn(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
		movedTwoSquares = false;
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
	
	/*
	 * This method checks to see whether the pawn can diagonally capture a piece. If there is a piece
	 * vertically and horizontally by the given amount of squares and it is of an opposite color, then
	 * the pawn can capture it.
	 */
	public boolean isValidCapture(int verticalMovement, int horizontalMovement) {
		if (this.row + verticalMovement> 7 || this.row + verticalMovement < 0 || this.column + horizontalMovement > 7 || this.column + horizontalMovement < 0) {
				return false;
		}
		else if (this.board.getSquare(this.row + verticalMovement,  this.column + horizontalMovement)
				.currentPiece != null) {
			if (!(this.board.getSquare(this.row + verticalMovement,  this.column + horizontalMovement)
				.currentPiece.color.equals(this.color))) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean enPassant(int verticalMovement, int horizontalMovement) {
		if (this.row + verticalMovement> 7 || this.row + verticalMovement < 0 || this.column + horizontalMovement > 7 || this.column + horizontalMovement < 0) {
			return false;
		}
		else if (this.board.getSquare(this.row, this.column + horizontalMovement).getPiece() != null) {
			if (this.board.getSquare(this.row,this.column + horizontalMovement).getPiece() instanceof Pawn) {
				Pawn adjacentPiece = (Pawn) this.board.getSquare(this.row, 
						this.column + horizontalMovement).getPiece();
				if (adjacentPiece.movedTwoSquares) {
					return true;
				}
			}
		}
		return false;
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
		}
		if (this.color.equals("w")) {
			if (this.row > 0) {
				if (isValidVerticalMovement(-1)) {
					possibleMoves.add(this.board.getSquare(this.row - 1, this.column).getPosition());
				}
			}
		} else {
			if (this.row < 7) {
				if (isValidVerticalMovement(1)) {
					possibleMoves.add(this.board.getSquare(this.row + 1, this.column).getPosition());
				}
			}
		}
		possibleMoves.addAll(this.getAvailableCaptures());
		return possibleMoves;
	}
	
	/*
	 * This function returns an arraylist of all possible capturing moves for the pawn. This is a
	 * separate function than getAvailableMoves() for determining check, because a pawn moving forward
	 * does not capture that piece.
	 */
	public ArrayList<String> getAvailableCaptures() {
		ArrayList<String> possibleCaptures = new ArrayList<String>();
		if (this.color.equals("w")) {
			if (this.row > 0) {
				if (isValidCapture(-1, -1)) {
					possibleCaptures.add(this.board.getSquare(this.row - 1, this.column - 1).getPosition());
				}
				if (isValidCapture(-1, 1)) {
					possibleCaptures.add(this.board.getSquare(this.row - 1, this.column + 1).getPosition());
				}
				if (enPassant(-1, -1)) {
					possibleCaptures.add(this.board.getSquare(this.row, this.column - 1).getPosition());
				}
				if (enPassant(-1, 1)) {
					possibleCaptures.add(this.board.getSquare(this.row, this.column + 1).getPosition());
				}
			}
		} else {
			if (this.row < 7) {
				if (isValidCapture(1, -1)) {
					possibleCaptures.add(this.board.getSquare(this.row + 1, this.column - 1).getPosition());
				}
				if (isValidCapture(1, 1)) {
					possibleCaptures.add(this.board.getSquare(this.row + 1, this.column + 1).getPosition());
				}
				if (enPassant(1, -1)) {
					possibleCaptures.add(this.board.getSquare(this.row, this.column - 1).getPosition());
				}
				if (enPassant(1, 1)) {
					possibleCaptures.add(this.board.getSquare(this.row, this.column + 1).getPosition());
				}
			}
		}
		return possibleCaptures;
	}

	public void move(String command) {
		super.move(command);
		if ((this.row == 4 && this.hasMoved == false) || (this.row == 5 && this.hasMoved == false)) {
			this.movedTwoSquares = true;
			if (this.color.equals("w")) {
				this.board.enPassantLocationWhite = this.board.getSquare(this.row, this.column);
			} else {
				this.board.enPassantLocationBlack = this.board.getSquare(this.row, this.column);
			}

		}
		this.hasMoved = true;
		if (this.color.equals("w") && this.row == 0) {

		}
	}

	public void promote(String piece) {
		switch(piece) {
			case "B": this.board.getSquare(this.row, this.column).currentPiece = new Bishop(this.color, this.board, this.row, this.column); return;
			case "R": this.board.getSquare(this.row, this.column).currentPiece = new Rook(this.color, this.board, this.row, this.column); return;
			case "N": this.board.getSquare(this.row, this.column).currentPiece = new Knight(this.color, this.board, this.row, this.column); return;
			case "Q": this.board.getSquare(this.row, this.column).currentPiece = new Queen(this.color, this.board, this.row, this.column); return;
			default: this.board.getSquare(this.row, this.column).currentPiece = new Queen(this.color, this.board, this.row, this.column); return;
		}
	}

	public String toString() {
		return color + "p";
	}
	
}
