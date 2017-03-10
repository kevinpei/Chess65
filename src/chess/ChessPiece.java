package chess;

import java.util.ArrayList;

/*
 *An abstract class which contains all the methods necessary for creating the other chess pieces. 
 */
public abstract class ChessPiece {
	public String color;
	public abstract ArrayList<String> getAvailableMoves(Chessboard board);
	public abstract String move(String destination);
	public abstract String toString();
	
}
