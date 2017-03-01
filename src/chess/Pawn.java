package chess;

import java.util.ArrayList;

public class Pawn extends ChessPiece{

	public Pawn(String color, int row, int column) {
		this.color = color;
		this.row = row;
		this.column = column;
	}
	
	public ArrayList<String> getAvailableMoves(Chessboard chessboard) {
		ArrayList<String> availableMoves = new ArrayList<String>();
		ChessPiece[][] board = chessboard.getBoard();
		if (color == "b") {
			if (row == 1) {
			}
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
