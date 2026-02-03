package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMoves {
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol) {
        Collection<ChessMove> pMoves = new ArrayList<>();
        pMoves.addAll(checkPos(board,myPos,tCol,2,1));
        pMoves.addAll(checkPos(board,myPos,tCol,2,-1));

        pMoves.addAll(checkPos(board,myPos,tCol,1,2));
        pMoves.addAll(checkPos(board,myPos,tCol,1,-2));

        pMoves.addAll(checkPos(board,myPos,tCol,-2,1));
        pMoves.addAll(checkPos(board,myPos,tCol,-2,-1));

        pMoves.addAll(checkPos(board,myPos,tCol,-1,2));
        pMoves.addAll(checkPos(board,myPos,tCol,-1,-2));

        return  pMoves;
    }

    private Collection<ChessMove> checkPos(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol,int y,int x){
        Collection<ChessMove> pMoves = new ArrayList<>();
        ChessPosition curPos = new ChessPosition(myPos.getRow()+y, myPos.getColumn()+x);
        if(curPos.getRow()<9&&curPos.getRow()>0&&curPos.getColumn()<9&&curPos.getColumn()>0){
            ChessPiece p = board.getPiece(curPos);
            if(p!=null){
                if(p.getTeamColor()!=tCol){
                    pMoves.add(new ChessMove(myPos,curPos,null));
                }
            }
            else{
                pMoves.add(new ChessMove(myPos,curPos,null));
            }
        }
        return  pMoves;
    }

}
