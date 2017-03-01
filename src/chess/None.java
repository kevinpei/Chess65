package chess;

import java.util.ArrayList;

public class None extends ChessPiece{
	
	String appearance;
	
	public None(String appearance) {
		this.appearance = appearance;
	}
	
	public ArrayList<String> getAvailableMoves(Chessboard chessboard) {
		return null;
	}

	@Override
	public String move(String destination) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return appearance;
	}
	
}
