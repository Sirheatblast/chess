package chess;

import java.util.ArrayList;
import java.util.Collection;

public class RookMoves extends MoveManager{
    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol){
        Collection<ChessMove> pMoves=new ArrayList<>();

        pMoves.addAll(getUp(board,myPos,tCol,null,8,true));
        pMoves.addAll(getDown(board,myPos,tCol,null,8,true));
        pMoves.addAll(getRight(board,myPos,tCol,null,8));
        pMoves.addAll(getLeft(board,myPos,tCol,null,8));

        return pMoves;
    }
}
