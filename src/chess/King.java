package chess;

import java.util.ArrayList;

public class King extends ChessPiece{

	public King(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	public ArrayList<String> getAvailableMoves() {
		return null;
	}
	
	public String toString() {
		return color + "K";
	}
	
}
