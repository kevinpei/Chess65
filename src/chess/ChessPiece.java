package chess;

import java.util.ArrayList;

public abstract class ChessPiece {
	public String color;
	public int row;
	public int column;
	
	public abstract ArrayList<String> getAvailableMoves(Chessboard board);
	public abstract String move(String destination);
	public abstract String toString();
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
}
