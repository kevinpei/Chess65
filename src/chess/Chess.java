package chess;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Chess {


	public static void main(String[] args) {
		
		//Initializes scanner object and the chessboard
		Scanner sc = new Scanner(System.in);
		Chessboard board = new Chessboard();
		System.out.println(board);
		ArrayList<String> test = board.getSquare(4, 4).getPiece().getAvailableMoves();
		
		for (int i = 0; i< test.size(); i++) {
			System.out.println(test.get(i));
		}
	}
	
}
