package chess;

import java.util.ArrayList;
import java.util.Collection;

public class BishopMoves extends MoveManager{
    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol){
        Collection<ChessMove> pMoves=new ArrayList<>();
        pMoves.addAll(getURight(board,myPos,tCol,null,8));
        pMoves.addAll(getLRight(board,myPos,tCol,null,8));
        pMoves.addAll(getULeft(board,myPos,tCol,null,8));
        pMoves.addAll(getLLeft(board,myPos,tCol,null,8));
        return pMoves;
    }
}
