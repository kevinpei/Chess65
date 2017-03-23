package chess;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */

/**
 * Main class to run the chess game
 */
public class Chess {


	public static void main(String[] args) {
		boolean checkMate = false;
		boolean stalemate = false;
		boolean whiteIsGoing = true;
        boolean isPromoting = false;

		//Initializes scanner object and the chessboard
		Scanner sc = new Scanner(System.in);
		Chessboard board = new Chessboard();

		while (!(checkMate) && !(stalemate)) {
            if (whiteIsGoing && board.enPassantLocationWhite != null) {
               Pawn noMoreEnPassant = (Pawn) board.enPassantLocationWhite.getPiece();
               noMoreEnPassant.movedTwoSquares = false;
               board.enPassantLocationWhite = null;
            } else if (board.enPassantLocationBlack != null){
                Pawn noMoreEnPassant = (Pawn) board.enPassantLocationBlack.getPiece();
                noMoreEnPassant.movedTwoSquares = false;
                board.enPassantLocationBlack = null;
            }
            System.out.println(board);
            String position = null;
            String destination = null;
            String promotion = null;
		    boolean validMove = false;
		    ArrayList<String> whiteKingMoves= new ArrayList<String>();
            ArrayList<String> blackKingMoves= new ArrayList<String>();
		    while(!validMove) {
                String move = sc.nextLine();
                //System.out.println(move);
                position = move.substring(0, 2);
                destination = move.substring(3, 5);
                if (move.length() == 7) {
                    promotion = move.substring(6);
                }
                //System.out.println(position);
                //System.out.println(destination);
                String color;
                if(whiteIsGoing){
                    color = "w";
                }else{
                    color = "b";
                }if(board.getSquare(position).currentPiece != null){
                    if(board.getSquare(position).currentPiece.color.equals(color)){
                        if(board.getSquare(position).currentPiece.isValidMove(destination)) {
                            if (board.getSquare(position).currentPiece instanceof Pawn && (board.getSquare(destination).row == 0 || board.getSquare(destination).row == 7)) {
                                isPromoting = true;
                            }
                            if (promotion != null) {
                                if (board.getSquare(position).currentPiece instanceof Pawn && (board.getSquare(destination).row == 0 || board.getSquare(destination).row == 7)) {
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid move");
                                }
                            } else {
                                validMove = true;
                            }
                        }else{
                            System.out.println("Invalid move");
                        }
                    }
                    else{
                        System.out.println("Invalid move");
                    }
                }else{
                    System.out.println("Invalid move");
                }

            }
			board.getSquare(position).currentPiece.move(destination);
		    if (isPromoting) {
		        Pawn promotingPawn = (Pawn) board.getSquare(destination).currentPiece;
		        if (promotion == null)  {
		            promotingPawn.promote("Q");
                } else {
                    promotingPawn.promote(promotion);
                }
		        isPromoting = false;
            }
			if (!whiteIsGoing) {
		        whiteKingMoves = board.whiteKingLoc.currentPiece.getAvailableMoves();
                if (board.getColorAvailableMoves("w").isEmpty()) {
					System.out.println("Stalemate");
					stalemate = true;
				} else if (board.getColorAvailableCaptures("b").contains(board.whiteKingLoc.getPosition())) {
				    for (String move : board.whiteKingLoc.currentPiece.getAvailableMoves()) {
				        if (!board.whiteKingLoc.currentPiece.isValidMove(move)){
				            whiteKingMoves.remove(move);
                        }

                    }
                    if(whiteKingMoves.isEmpty()) {
				        checkMate = true;
				        System.out.println("Checkmate");
                    }
                    else {
                        System.out.println("Check");
                    }
				}
			} else {
                blackKingMoves = board.blackKingLoc.currentPiece.getAvailableMoves();
                if (board.getColorAvailableMoves("b").isEmpty()) {
                    System.out.println("Stalemate");
                    stalemate = true;
                } else if (board.getColorAvailableCaptures("w").contains(board.blackKingLoc.getPosition())) {
                    for (String move : board.blackKingLoc.currentPiece.getAvailableMoves()) {
                        if (!board.blackKingLoc.currentPiece.isValidMove(move)){
                            blackKingMoves.remove(move);
                        }

                    }
                    if(blackKingMoves.isEmpty()) {
                        checkMate = true;
                        System.out.println("Checkmate");
                    }
                    else {
                        System.out.println("Check");
                    }

                }
            }
                whiteIsGoing = !whiteIsGoing;
		}
        System.out.println("Game Over");
	}
	
}
