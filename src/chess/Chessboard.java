package chess;

//import statements
import java.util.ArrayList;

/**
 * @author Kevin Pei and Andrew Dos Reis
 * @version 1.0
 * @since 1.0
 */
public class Chessboard {
	//A Chessboard is represented as an 8 x 8 array of chess squares
	/**
	 * A 2-dimensional array of ChessSquares which represents the entire chess board.
	 */
	ChessSquare[][] chessboard = new ChessSquare[8][8];
	//Tells whether white is going, in which case it is true
	/**
	 * Holds the ChessSquare which contains the white king. Used to determine check and checkmate.
	 */
	ChessSquare whiteKingLoc;
	/**
	 * Holds the ChessSquare which contains the black king. Used to determine check and checkmate.
	 */
	ChessSquare blackKingLoc;
	/**
	 * Holds the ChessSquare which contains a white pawn which just moved two spaces forwards. used to determine if
	 * an en Passant move is valid.
	 */
	ChessSquare enPassantLocationWhite;
	/**
	 * Holds the ChessSquare which contains a black pawn which just moved two spaces forwards. used to determine if
	 * an en Passant move is valid.
	 */
	ChessSquare enPassantLocationBlack;


	/**This function initializes a given side's non-pawn pieces
	 * <p>
	 * This function places a row of non-pawn pieces on the specified row of the board, all of the given color
	 *
	 * @param row Description: the row on which each of a color's non-pawn pieces will be placed
	 * @param color Description: the color of which all of the pieces will be made on the specified row
	 */
	public void initializeSide(int row, String color) {
		this.chessboard[row][0] = new ChessSquare(new Rook(color, this, row, 0), row, 0);
		this.chessboard[row][1] = new ChessSquare(new Knight(color, this, row, 1), row, 1);
		this.chessboard[row][2] = new ChessSquare(new Bishop(color, this, row, 2), row, 2);
		this.chessboard[row][3] = new ChessSquare(new Queen(color, this, row, 3), row, 3);
		this.chessboard[row][4] = new ChessSquare(new King(color, this, row, 4), row, 4);
		this.chessboard[row][5] = new ChessSquare(new Bishop(color, this, row, 5), row, 5);
		this.chessboard[row][6] = new ChessSquare(new Knight(color, this, row, 6), row, 6);
		this.chessboard[row][7] = new ChessSquare(new Rook(color, this, row, 7), row, 7);
	}

	/**
	 * This is the constructor. It automatically populates the chessboard with ChessSquares for either side, and prepares the chess game to be played
	 */
	public Chessboard() {
		//Initializes the black side's non-pawn pieces
		initializeSide(0, "b");
		this.blackKingLoc = this.getSquare(0,4);
		//Initializes the black side's pawns
		for (int i = 0; i < 8; i++) {
			this.chessboard[1][i] = new ChessSquare(new Pawn("b", this, 1, i), 1, i);
		}
		//Initializes the middle unoccupied squares
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				this.chessboard[i][j] = new ChessSquare(null, i, j);
			}
		}
		//Initializes the white side's pawns
		for (int i = 0; i < 8; i++) {
			this.chessboard[6][i] = new ChessSquare(new Pawn("w", this, 6, i), 6, i);
		}
		//Initializes the white side's non-pawn pieces
		initializeSide(7, "w");
		this.whiteKingLoc = this.getSquare(7,4);
		/*this.chessboard[4][4] = new ChessSquare(new Queen("w", this, 4, 4), 4, 4);
		this.chessboard[4][5] = new ChessSquare(new Pawn("b", this, 4, 5), 4, 5);
		this.chessboard[4][3] = new ChessSquare(new Pawn("b", this, 4, 3), 4, 3);
		this.chessboard[3][4] = new ChessSquare(new Pawn("b", this, 3, 4), 3, 4);
		this.chessboard[3][5] = new ChessSquare(new Pawn("b", this, 3, 5), 3, 5);
		this.chessboard[3][3] = new ChessSquare(new Pawn("b", this, 3, 3), 3, 3);
		*/
	}

	/**Converts the chessboard into a string representation
	 *
	 * @return Description: a String representation of the entire Chess board of the current game, formatted to act as the visual aid to play the game
	 */
	public String toString() {
		String output = new String();
		//Adds whatever's on each ChessSquare to the output string
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				output += this.chessboard[i][j];
				//After it reaches the 8th column, it adds a new line character
				if (j == 7) {
					output += "\n";
				}
			}
		}
		return output;
	}
	
	/**
	 * This function returns all possible moves of every piece of a given color.
	 * It is used to determine if there is a stalemate.
     *
     * @param color Description: Color of the team that a list of Available moves should be generated for ("b":black or "w":white).
     * @return Description: Returns an ArrayList of the available moves of the specified team given the current positions of the pieces on the board
	 */
	public ArrayList<String> getColorAvailableMoves(String color) {
		ArrayList<String> allPieceMoves = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j].currentPiece != null) {
					if (chessboard[i][j].currentPiece.color.equals(color)) {
						if (chessboard[i][j].currentPiece.getAvailableMoves() != null) {
							allPieceMoves.addAll(chessboard[i][j].currentPiece.getAvailableMoves());
						}
					}
				}
			}
		}
		return allPieceMoves;
	}
	
	/**
	 * This function returns all possible capturing moves of every piece of a given color. This is used
	 * to determine whether a king is in checkmate and whether a given move will move it into check.
     *
     * @param color Description: Color of the team that a list of Available captures should be generated for ("b":black or "w":white).
     * @return Description: Returns an ArrayList of the available captures that can be performed by the specified team given the current positions of the pieces on the board
	 */
	public ArrayList<String> getColorAvailableCaptures(String color) {
		ArrayList<String> allPieceMoves = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j].currentPiece != null) {
					if (chessboard[i][j].currentPiece.color.equals(color)) {
						if (chessboard[i][j].currentPiece.getAvailableMoves() != null) {
							if (!(chessboard[i][j].currentPiece instanceof Pawn)) {
								allPieceMoves.addAll(chessboard[i][j].currentPiece.getAvailableMoves());
							} else if (chessboard[i][j].currentPiece instanceof Pawn) {
								allPieceMoves.addAll(((Pawn) chessboard[i][j].currentPiece).getAvailableCaptures());
							}
						}
					}
				}
			}
		}
		return allPieceMoves;
	}

    /**This function returns the ChessSquare[][] chessboard
     *
     * The board of play for chess is made up of a two-dimensional array
     * @return Description: Returns two-dimensional array containing all of the squares on the board, squares subsequently can have pieces.
     * Therefore this method returns the current state of the running game of chess
     */
	public ChessSquare[][] getBoard() {
		return this.chessboard;
	}
	
	/**
	 * This function returns a ChessSquare.
	 * <p>
	 * It returns the ChessSquare with row i and column j in the two dimensional array of the chessboard.
	 * 
	 * @param i Description: The row of the ChessSquare being requested.
	 * @param j Description: The column of the ChessSquare being requested.
	 * @return Description: Returns the ChessSquare with row i and column j.
	 */
	public ChessSquare getSquare(int i, int j) {
		return this.chessboard[i][j];
	}

	/**
	 * This function returns a ChessSquare.
	 * <p>
	 * Because ChessSquares are stored in a two-dimensional array, there must be a way to translate a chess
	 * move (e.g. e8) into a ChessSquare. This function can take a chess move as an input and return the
	 * appropriate ChessSquare.
	 * 
	 * @param position Description: The chess move to be translated.
	 * @return Description: Returns the ChessSquare corresponding to that chess move.
	 */
	public ChessSquare getSquare(String position) {
		char columnChar = position.charAt(0);
		int rowChar = Character.getNumericValue(position.charAt(1));
		int row;
		switch(rowChar) {
			case 8: row = 0; break;
			case 7: row = 1; break;
			case 6: row = 2; break;
			case 5: row = 3; break;
			case 4: row = 4; break;
			case 3: row = 5; break;
			case 2: row = 6; break;
			case 1: row = 7; break;
			default: row = -1; break;
		}
		int column;
		switch(columnChar) {
			case 'a': column = 0; break;
			case 'b': column = 1; break;
			case 'c': column = 2; break;
			case 'd': column = 3; break;
			case 'e': column = 4; break;
			case 'f': column = 5; break;
			case 'g': column = 6; break;
			case 'h': column = 7; break;
			default: column = -1; break;
		}
		if(column == -1 || row == -1){
			return null;
		}
		return this.chessboard[row][column];
	}
}
