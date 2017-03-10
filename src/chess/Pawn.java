package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public Pawn(String color) {
		this.color = color;
	}
	
	public ArrayList<String> getAvailableMoves(Chessboard chessboard) {
		ArrayList<String> availableMoves = new ArrayList<String>();
		ChessSquare[][] board = chessboard.getBoard();
		if (color == "b") {
		}
		return null;
	}

	@Override
	public String move(String destination) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return color + "p";
	}
	
}
