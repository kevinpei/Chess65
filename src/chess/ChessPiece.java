package chess;

import java.util.ArrayList;

public interface ChessPiece {
	public ArrayList<String> getAvailableMoves(Chessboard board);
	public String move(String destination);
	public String toString();
}
