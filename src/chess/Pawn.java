package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public Pawn(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	public ArrayList<String> getAvailableMoves() {
		return null;
	}

	public String toString() {
		return color + "p";
	}
	
}
