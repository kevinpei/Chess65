package chess;

import java.util.ArrayList;

public class Rook extends ChessPiece{

	public Rook(String color, Chessboard board, int row, int column) {
		super(color, board, row, column);
	}
	
	public ArrayList<String> getAvailableMoves() {
		ArrayList<String> possibleMoves = new ArrayList<String>();
		System.out.println("FUCK");
		/*
		 * This following code blocks tests all squares above the rook.
		 * It tests each square above the rook one at a time. If it is empty,
		 * then the square is a possible move. If it is occupied, that means
		 * that both it and every square above it is not a valid move, thus
		 * why i is set to 0.
		 */
		for (int i = this.column - 1; i >= 0; i--) {
			System.out.println(i);
			System.out.println("testing " + this.board.getSquare(this.row, i).getPosition());
			if (this.board.getSquare(this.row, i).getPiece() != null) {
				System.out.println("Oopsie");
				i = 0;
			} else {
				System.out.println("Working");
				possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
			}
		}
		/*
		 * It is the same idea for this one, except moving downward instead of upward.
		 */
		for (int i = this.column + 1; i < 8; i++) {
			System.out.println(i);
			System.out.println("testing " + this.board.getSquare(this.row, i).getPosition());
			if (this.board.getSquare(this.row, i).getPiece() != null) {
				System.out.println("Oopsie");
				i = 8;
			} else {
				System.out.println("Working");
				possibleMoves.add(this.board.getSquare(this.row, i).getPosition());
			}
		}
		/*
		 * Likewise, this checks all moves to the left of the rook.
		 */
		for (int i = this.row - 1; i >= 0; i--) {
			System.out.println("testing " + this.board.getSquare(i, this.column).getPosition());
			if (this.board.getSquare(i, this.column).getPiece() != null) {
				System.out.println("Oopsie");
				i = 0;
			} else {
				System.out.println("Working");
				possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
			}
		}
		/*
		 * Likewise, this checks all moves to the right of the rook.
		 */
		for (int i = this.row + 1; i < 8; i++) {
			System.out.println("testing " + this.board.getSquare(i, this.column).getPosition());
			if (this.board.getSquare(i, this.column).getPiece() != null) {
				System.out.println("Oopsie");
				i = 8;
			} else {
				System.out.println("Working");
				possibleMoves.add(this.board.getSquare(i, this.column).getPosition());
			}
		}
		return possibleMoves;
	}
	
	public String toString() {
		return color + "R";
	}

}
