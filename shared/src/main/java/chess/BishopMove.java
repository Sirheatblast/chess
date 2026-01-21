package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMove extends PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> pMoves = new ArrayList<>();
        pMoves.addAll(checkULeft(board,myPosition, null,8));
        pMoves.addAll(checkURight(board,myPosition, null,8));
        pMoves.addAll(checkLRight(board,myPosition, null,8));
        pMoves.addAll(checkLLeft(board,myPosition, null,8));

        return pMoves;
    }
}
