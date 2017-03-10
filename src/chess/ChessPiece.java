package chess;

import java.util.ArrayList;

public abstract class ChessPiece {
	public String color;
	
	
	public abstract ArrayList<String> getAvailableMoves(Chessboard board);
	public abstract String move(String destination);
	public abstract String toString();
	
}
