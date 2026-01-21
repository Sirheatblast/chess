package chess;


import java.util.ArrayList;
import java.util.Collection;

public class RookMove extends PieceMoveCalculator {
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> pMoves = new ArrayList<>();
        pMoves.addAll(checkUp(board,myPosition, null,8,true));
        pMoves.addAll(checkDown(board,myPosition, null,8,true));
        pMoves.addAll(checkRight(board,myPosition, null,8));
        pMoves.addAll(checkLeft(board,myPosition, null,8));

        return pMoves;
    }
}
