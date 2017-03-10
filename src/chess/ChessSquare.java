package chess;

public class ChessSquare {
	public ChessPiece currentPiece;
	public int row;
	public int column;
	String appearance;
	public ChessSquare(ChessPiece piece, int row, int column) {
		this.currentPiece = piece;
		if (row % 2 == 0 && column % 2 == 1) {
			this.appearance = "##";
		} else if (row % 2 == 1 && column % 2 == 0){
			this.appearance = "##";
		} else {
			this.appearance = "  ";
		}
	}
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
	public String toString() {
		if (this.currentPiece != null) {
			return currentPiece.toString();
		} else {
			return appearance;
		}
	}
}
