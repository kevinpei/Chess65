package chess;

import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class King extends ChessPiece{

	/**
	 * The King also includes a hasMoved variable because a King can only castle if it has not
	 * moved yet.
	 */
	public boolean hasMoved;

	/**
	 * King constructor
	 * <p>
	 * The constructor for King calls the ChessPiece super constructor.
	 *
	 * @param color Description: The color of the King. Can be w or b for white and black.
	 * @param board Description: The chessboard the King is located in.
	 * @param row Description: The row the King starts on in the chessboard.
	 * @param column Description: The column the King starts on in the chessboard.
	 */

	public King(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
		hasMoved = false;
	}

	/**
	 * Checks to see if the given square is valid to move onto.
	 * <p>
	 *This function checks to see whether it is a valid move to move onto the square with
	 *the given row and column. It is valid if it is empty or if there is a piece of the opposite color
	 *there.
	 *<p>
	 *The move is invalid if the move takes the King outside the board (row or column greater than 8 or
	 *less than 0) and if there is another piece of the same color on that square.
	 *
	 *@param row Description: The row of the square being checked for whether it is valid to move onto.
	 *@param column Description: The column of the square being checked for whether it is valid to move onto.
	 *@return Description: Returns true if the King can move onto the given square and false otherwise.
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

	/**
	 * Gets all available moves for the King.
	 * <p>
	 * Calls the isValidSquare function for the squares that a King can move to: A King can move one square in any direction.

	 * <p>
	 * If the isValidSquare function returns true for a given position, then that position's letter-number
	 * combination will be added to an ArrayList of all possible moves for the King.
	 *
	 *
	 * @return Description: Returns the ArrayList of all possible moves the King can take.
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
		if (this.castling(2)) {
		    possibleMoves.add(this.board.getSquare(this.row, this.column + 2).getPosition());
        }
        if (this.castling(-2)) {
		    possibleMoves.add(this.board.getSquare(this.row, this.column - 2).getPosition());
        }
		System.out.println(possibleMoves);
		return possibleMoves;
	}

	/**
	 * Checks to see if the given move is valid to for the King piece considering the rules of check.
	 * <p>
	 *This statement checks to see if the move will put the King in check. If it will, it returns false.
	 * Otherwise, it returns true.
	 *<p>
	 *The move is invalid if the move keeps the King in check after it is completed.
	 *
	 *@param destination Description: designated move location to check for validity
	 *@return Description: Returns true if the King can move onto the given square without being in check once complete.
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
    /**
     * Checks to see if the selected King piece would castle with the given move, and if it is a valid move to do.
     * <p>
     *This statement checks to see if the move will put the King in castling. If it will, it returns true if that is possible (not blocked, rook hasn't moved, King hasn't moved),
     * Otherwise, it returns false if castling in that direction is not valid.
     *<p>
     *Castling is invalid if either the King or the selected rook has moved, or if there are still pieces between the King and rook.
     *
     *@param horizontalMovement Description: the amount (and direction: positive/negative) that the piece is being requested to move horizontally, effectively which rook to castle with.
     *@return Description: Returns true if the King can castle in that direction.
     */
	public boolean castling(int horizontalMovement) {
		if(this.hasMoved){return false;}
		else{
			if(horizontalMovement > 0){
				if(this.board.getSquare(this.row,7).getPiece() instanceof Rook){
				    Rook movementChecker = (Rook) this.board.getSquare(this.row, 7).currentPiece;
					if(this.board.getSquare(this.row,6).getPiece() == null && this.board.getSquare(this.row,5).getPiece() == null && !(movementChecker.hasMoved)){
						return true;
					}
				}
			}if(horizontalMovement <0){
				if(this.board.getSquare(this.row,0).getPiece() instanceof Rook){
                    Rook movementChecker = (Rook) this.board.getSquare(this.row, 0).currentPiece;
					if(this.board.getSquare(this.row,1).getPiece() == null && this.board.getSquare(this.row,2).getPiece() == null && this.board.getSquare(this.row,3).getPiece() == null
                            && !(movementChecker.hasMoved)){
						return true;
					}
				}
			}
		}

        return false;
    }

    /**
     * Overwrites default move function from ChessPiece
     * <p>
     * This function returns nothing and moves the King piece to the designated location by calling the super class move function
     * and then updating to confirm the piece is not in its starting position as well as telling the board where the Kings currently are for check purposes.
     *
     * @param command Description: the designated location to travel to for this King piece.
     */

	public void move(String command){
	    if(this.column == 2 && !(this.hasMoved)) {
	        this.board.getSquare(this.row, 3).currentPiece = this.board.getSquare(this.row, 0).currentPiece;
	        this.board.getSquare(this.row, 3).currentPiece.column = 3;
	        this.board.getSquare(this.row, 0).currentPiece = null;
        } else if (this.column == 6 && !(this.hasMoved)){
            this.board.getSquare(this.row, 5).currentPiece = this.board.getSquare(this.row, 7).currentPiece;
            this.board.getSquare(this.row, 5).currentPiece.column = 5;
            this.board.getSquare(this.row, 7).currentPiece = null;
        }
	    if (this.color.equals("b")){
	        this.board.blackKingLoc = this.board.getSquare(this.row,this.column);
        }else{
            this.board.whiteKingLoc = this.board.getSquare(this.row,this.column);
        }
        this.hasMoved = true;
    }

    /**
     * Returns the String representation of this piece.
     * <p>
     * Returns a String representation of this piece, which is its one-letter color appended by "K",
     * representing its status as a King.
     *
     * @return Description: Returns the string representation of this piece.
     */
	public String toString() {
		return color + "K";
	}
	
}
