package chess;

public class ChessSquare {
	/*
	 * A ChessSquare represents each square on the chessboard. Each ChessSquare can contain
	 * a piece and is identified by its row and column. It has a default appearance if there
	 * is no piece currently on the board, which alternates "  " and "##".
	 */
	public ChessPiece currentPiece = null;
	public int row;
	public int column;
	String appearance;
	// This is the constructor, which makes every other ChessSquare different in appearance.
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
	/*
	 * Because columns will be by letter, this method can convert numbers into their
	 * equivalent characters.
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
	/*
	 * This method gives the String representation of each ChessSquare, which is its
	 * piece or its appearance if there is no piece on it.
	 */
	public String toString() {
		if (this.currentPiece != null) {
			return currentPiece.toString();
		} else {
			return appearance;
		}
	}
	
	public ChessPiece getPiece() {
		return currentPiece;
	}
	
	public String getPosition() {
		return this.getColumn() + (this.row + 1);
	}
}
