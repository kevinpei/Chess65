package chess;

public class ChessSquare {
	/*
	 * A ChessSquare represents each square on the chessboard. Each ChessSquare can contain
	 * a piece and is identified by its row and column. It has a default appearance if there
	 * is no piece currently on the board, which alternates "  " and "##".
	 */
	/**
	 * Holds the current ChessPiece on this ChessSquare. Is null if there is no piece on it.
	 */
	public ChessPiece currentPiece = null;
	/**
	 * Holds the row of this ChessSquare.
	 */
	public int row;
	/**
	 * Holds the column of this ChessSquare.
	 */
	public int column;
	/**
	 * ChessSquares alternate appearance as "  " and "##" if empty. This variable holds which appearance 
	 * it should have if it has no ChessPiece on it.
	 */
	String appearance;

	/**
	 * Chess Square constructor
	 * <p>
	 * The constructor for a square that makes up the board.
	 * Makes every other ChessSquare different in appearance.
	 *
	 * @param piece Description: The color of the King. Can be w or b for white and black.
	 * @param row Description: The row the square goes on the chessboard.
	 * @param column Description: The column the square goes on the chessboard.
	 */
	public ChessSquare(ChessPiece piece, int row, int column) {
		this.currentPiece = piece;
		this.row = row;
		this.column = column;
		if (row % 2 == 0 && column % 2 == 1) {
			this.appearance = "##";
		} else if (row % 2 == 1 && column % 2 == 0){
			this.appearance = "##";
		} else {
			this.appearance = "  ";
		}
	}
	/**
	 * Returns the letter value of the numerical column of a square
	 * <p>
	 * Because columns will be by letter, this method can convert numbers into their
	 * equivalent characters.
	 *
	 * @return Description: String of the selected square's column.
	 */

	public String getColumn() {
		switch(column) {
		case 0: return "a";
		case 1: return "b";
		case 2: return "c";
		case 3: return "d";
		case 4: return "e";
		case 5: return "f";
		case 6: return "g";
		case 7: return "h";
		default: return null;
		}
	}
	/**
	 * Returns the numerical value of the row of a square
	 * <p>
	 * Because row will be by number from 0-7, this method can convert numbers into their 1-8 value that the player would understand
	 *
	 * @return Description: number value of the selected square's row.
	 */
	public int getRow() {
		switch(row) {
			case 0: return 8;
			case 1: return 7;
			case 2: return 6;
			case 3: return 5;
			case 4: return 4;
			case 5: return 3;
			case 6: return 2;
			case 7: return 1;
			default: return 0;
		}
	}
	/**
	 * Returns the String representation of this square.
	 * <p>
	 * This method gives the String representation of each ChessSquare, which is its
	 * piece or its appearance if there is no piece on it.
	 *
	 * @return Description: Returns the string representation of this square.
	 */
	public String toString() {
		if (this.currentPiece != null) {
			return currentPiece.toString();
		} else {
			return appearance;
		}
	}
	/**
	 * Returns the String representation of this square.
	 * <p>
	 * This method gives the ChessPiece object currently on this square, if it is empty it returns null.
	 *
	 * @return Description: Returns the ChessPiece object on this current square.
	 */
	public ChessPiece getPiece() {
		return currentPiece;
	}

	/**
	 * Returns the String representation of this squares coordinates.
	 * <p>
	 * This method gives the String representation of the location of this specific square
	 *
	 * @return Description: Returns the string representation of this square's location.
	 */
	public String getPosition() {
		return this.getColumn() + (this.getRow());
	}
}
