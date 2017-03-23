package chess;

import java.util.ArrayList;

public class Rook extends ChessPiece {

    /**
     * The rook also includes a hasMoved variable because a rook can only castle if it has not
     * moved yet.
     */
    public boolean hasMoved;

    /**
     * Rook constructor
     * <p>
     * The constructor for Rook calls the ChessPiece super constructor.
     *
     * @param color Description: The color of the Rook. Can be w or b for white and black.
     * @param board Description: The chessboard the Rook is located in.
     * @param row Description: The row the Rook starts on in the chessboard.
     * @param column Description: The column the Rook starts on in the chessboard.
     */
    public Rook(String color, Chessboard board, int row, int column) {
        super(color, board, row, column);
        hasMoved = false;
    }
    /**
     * Gets all available moves for the Rook.
     * <p>
     * determines the legal squares that this Rook can move to from its current location
     * Rooks can move: As far as the player would like in an unblocked horizontal or vertical line
     *
     * @return Description: Returns the ArrayList of all possible moves the Rook can take from its current location.
     */
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
    /**
     * Overwrites default move function from ChessPiece
     * <p>
     * This function returns nothing and moves the Rook piece to the designated location by calling the super class move function
     * and then updating to confirm the piece is not in its starting position
     *
     * @param command Description: the designated location to travel to for this Rook piece.
     */
    public void move(String command) {
        super.move(command);
        this.hasMoved = true;
    }

    /**
     * Returns the String representation of this piece.
     * <p>
     * Returns a String representation of this piece, which is its one-letter color appended by "R",
     * representing its status as a Rook.
     *
     *
     * @return Description: Returns the string representation of this piece.
     */
    public String toString() {
        return color + "R";
    }

}
