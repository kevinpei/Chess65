package chess;
import java.util.ArrayList;
import java.util.Scanner;


public class Chess {


	public static void main(String[] args) {
		
		//Initializes scanner object and the chessboard
		Scanner sc = new Scanner(System.in);
		Chessboard board = new Chessboard();
		System.out.println(board);
		String move = sc.next();
	}
	
}
