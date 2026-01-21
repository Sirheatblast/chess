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

    private final PieceType pType;
    private final ChessGame.TeamColor pColor;



    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        pColor = pieceColor;
        pType = type;
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
        return pColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return pType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pType == that.pType && pColor == that.pColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pType, pColor);
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        return switch (pType) {
            case PAWN -> {
                PawnMove pm = new PawnMove();
                yield pm.pieceMoves(board, myPosition);
            }
            case ROOK -> {
                RookMove rm = new RookMove();
                yield rm.pieceMoves(board, myPosition);
            }
            case KNIGHT -> {
                KnightMove km = new KnightMove();
                yield km.pieceMoves(board, myPosition);
            }
            case BISHOP -> {
                BishopMove bm = new BishopMove();
                yield bm.pieceMoves(board, myPosition);
            }
            case QUEEN -> {
                QueenMove qm = new QueenMove();
                yield qm.pieceMoves(board, myPosition);
            }
            case KING -> {
                KingMove kim = new KingMove();
                yield kim.pieceMoves(board, myPosition);
            }
        };
    }

}
