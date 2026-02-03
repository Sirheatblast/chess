package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board;
    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        resetCol(0, ChessPiece.PieceType.ROOK);
        resetCol(1, ChessPiece.PieceType.KNIGHT);
        resetCol(2, ChessPiece.PieceType.BISHOP);
        resetCol(3, ChessPiece.PieceType.QUEEN);
        resetCol(4, ChessPiece.PieceType.KING);
        resetCol(5, ChessPiece.PieceType.BISHOP);
        resetCol(6, ChessPiece.PieceType.KNIGHT);
        resetCol(7, ChessPiece.PieceType.ROOK);
    }

    void resetCol(int posX,ChessPiece.PieceType pType){
        board[0][posX] = new ChessPiece(ChessGame.TeamColor.WHITE,pType);
        board[1][posX] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

        board[6][posX] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        board[7][posX] = new ChessPiece(ChessGame.TeamColor.BLACK,pType);
    }
}
