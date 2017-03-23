package chess;

import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Pawn extends ChessPiece{

	/**
	 * The pawn also includes a hasMoved variable because the first move of pawn can be one or
	 * two moves forward.
	 */
	public boolean hasMoved;
	/**
	 * The pawn also includes a hasMoved variable because the first move of pawn can be one or
	 * two moves forward.
	 */
	public boolean movedTwoSquares;

	/**
	 * Pawn constructor
	 * <p>
	 * The constructor for Pawn calls the ChessPiece super constructor.
	 *
	 * @param color Description: The color of the Pawn. Can be w or b for white and black.
	 * @param board Description: The chessboard the Pawn is located in.
	 * @param row Description: The row the Pawn starts on in the chessboard.
	 * @param column Description: The column the Pawn starts on in the chessboard.
	 */
	public Pawn(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
		movedTwoSquares = false;
	}
	/**
	 * Determines whether a vertical movement is valid for the selected Pawn piece
	 *
	 * This method checks to see whether the pawn can move vertically the given amount of squares.
	 * Usually it will be -1 for white pawns and 1 for black pawns, but it can also be -2 or 2 for
	 * the first move. If the piece is still at start, the both 1 and 2 (absolute value) space movements will return true, after being moved only 1 space movements will return true for Pawns.
	 *
	 * @return Description: boolean describing whether a vertical movement is a valid option from the selected pieces current position
	 */
	public boolean isValidVerticalMovement(int verticalMovement) {
		if (this.board.getSquare(this.row + verticalMovement,  this.column).getPiece() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Determines whether a move, specifically a capture, is valid for the selected Pawn piece
	 *
	 * This method checks to see whether the pawn can diagonally capture a piece. If there is a piece
	 * vertically and horizontally by the given amount of squares and it is of an opposite color, then
	 * the pawn can capture it.
	 *
	 * @return Description: boolean describing whether a capture is a valid option from the selected pieces current position
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
	/**
	 * Determines whether a enpassant rule is in effect and usable
	 *
	 * This method checks to see whether the pawn is in the position to use enpassant rule to capture another pawn piece from the enemy
	 *
	 * @return Description: boolean describing whether the enpassant rule is valid for the current piece
	 */
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

	/**
	 * Gets all available moves for the Pawn.
	 * <p>
	 * Calls the isValidVerticalMovement and getAvailableCaptures functions for the squares that a Pawn
	 * can move to: on the first turn they can move twice vertical, otherwise they may move vertical one space forward. They can also move diagonally one space to capture another piece
	 *
	 * @return Description: Returns the ArrayList of all possible moves the Pawn can take from its current location.
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

	/**
	 * Gets all available moves for the Pawn.
	 * <p>
	 * This function returns an arraylist of all possible capturing moves for the pawn. This is a
	 * separate function than getAvailableMoves() for determining check, because a pawn moving forward
	 * does not capture that piece.
	 *
	 * @return Description: Returns the ArrayList of all possible attacks the Pawn can take from its current location.
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
	/**
	 * Overwrites default move function from ChessPiece
	 * <p>
	 * This function returns nothing and moves the Pawn piece to the designated location by calling the super class move function
	 * and then considering enpassant and other special rules of the pawn piece.
	 *
	 * @param command Description: the designated location to travel to for this Pawn piece.
	 */
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
	/**
	 * Implements promotion rule of chess
	 * <p>
	 * This function returns nothing and promotes the current Pawn piece to a specified new piece type upon arrival at the other side of the chess board
	 * depending on input: Bishop, Rook, Knight, or Queen; with Queen being the default.
	 *
	 * @param piece Description: the requested piece type to transform the Pawn into.
	 */
	public void promote(String piece) {
		switch(piece) {
			case "B": this.board.getSquare(this.row, this.column).currentPiece = new Bishop(this.color, this.board, this.row, this.column); return;
			case "R": this.board.getSquare(this.row, this.column).currentPiece = new Rook(this.color, this.board, this.row, this.column); return;
			case "N": this.board.getSquare(this.row, this.column).currentPiece = new Knight(this.color, this.board, this.row, this.column); return;
			case "Q": this.board.getSquare(this.row, this.column).currentPiece = new Queen(this.color, this.board, this.row, this.column); return;
			default: this.board.getSquare(this.row, this.column).currentPiece = new Queen(this.color, this.board, this.row, this.column); return;
		}
	}
	/**
	 * Returns the String representation of this piece.
	 * <p>
	 * Returns a String representation of this piece, which is its one-letter color appended by "p",
	 * representing its status as a Pawn.
	 *
	 *
	 * @return Description: Returns the string representation of this piece.
	 */
	public String toString() {
		return color + "p";
	}
	
}
