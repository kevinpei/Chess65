package chess;

import java.util.ArrayList;

public class Rook implements ChessPiece{

	public String color;
	
	public Rook(String color) {
		this.color = color;
	}
	
	public ArrayList<String> getAvailableMoves(Chessboard chessboard) {
		ArrayList<String> availableMoves = new ArrayList<String>();
		ChessPiece[][] board = chessboard.getBoard();
		for (int i = 0; i < 8; i++) {
			
		}
		return null;
	}

	@Override
	public String move(String destination) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return color + "R";
	}

}
