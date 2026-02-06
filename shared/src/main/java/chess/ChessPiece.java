package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    private final ChessGame.TeamColor mCol;
    private final PieceType mPType;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        mCol = pieceColor;
        mPType = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return mCol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return mCol == that.mCol && mPType == that.mPType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCol, mPType);
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return mPType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> pMoves = new ArrayList<>();
        switch (mPType){
            case PAWN:
                PawnMoves pm = new PawnMoves();
                pMoves.addAll(pm.getPieceMoves(board,myPosition,mCol));
                break;
            case KNIGHT:
                KnightMoves km = new KnightMoves();
                pMoves.addAll(km.getPieceMoves(board,myPosition,mCol));
                break;
            case KING:
                KingMoves kim = new KingMoves();
                pMoves.addAll(kim.getPieceMoves(board,myPosition,mCol));
                break;
            case QUEEN:
                QueenMoves qm = new QueenMoves();
                pMoves.addAll(qm.getPieceMoves(board,myPosition,mCol));
                break;
            case ROOK:
                RookMoves rm = new RookMoves();
                pMoves.addAll(rm.getPieceMoves(board,myPosition,mCol));
                break;
            case BISHOP:
                BishopMoves bm = new BishopMoves();
                pMoves.addAll(bm.getPieceMoves(board,myPosition,mCol));
                break;
        }
        return pMoves;
    }
}
