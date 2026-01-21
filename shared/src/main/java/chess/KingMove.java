package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KingMove extends PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> pMoves = new ArrayList<>();
        pMoves.addAll(checkUp(board,myPosition, null,1,true));
        pMoves.addAll(checkDown(board,myPosition, null,1,true));
        pMoves.addAll(checkRight(board,myPosition, null,1));
        pMoves.addAll(checkLeft(board,myPosition, null,1));

        pMoves.addAll(checkULeft(board,myPosition, null,1));
        pMoves.addAll(checkURight(board,myPosition, null,1));
        pMoves.addAll(checkLRight(board,myPosition, null,1));
        pMoves.addAll(checkLLeft(board,myPosition, null,1));

        return pMoves;
    }
}
