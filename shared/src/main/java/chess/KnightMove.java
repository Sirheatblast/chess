package chess;

import java.util.ArrayList;
import java.util.Collection;

public class KnightMove extends PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        ChessGame.TeamColor ourColor = board.getPiece(myPosition).getTeamColor();
        Collection<ChessMove> pMoves = new ArrayList<>();

        ChessMove cMove = calcPos(ourColor,board,myPosition,2,1);
        if(cMove!=null){
            pMoves.add(cMove);
        }
        cMove = calcPos(ourColor,board,myPosition,2,-1);
        if(cMove!=null){
            pMoves.add(cMove);
        }

        cMove = calcPos(ourColor,board,myPosition,-2,1);
        if(cMove!=null){
            pMoves.add(cMove);
        }
        cMove = calcPos(ourColor,board,myPosition,-2,-1);
        if(cMove!=null){
            pMoves.add(cMove);
        }

        cMove = calcPos(ourColor,board,myPosition,1,2);
        if(cMove!=null){
            pMoves.add(cMove);
        }
        cMove = calcPos(ourColor,board,myPosition,1,-2);
        if(cMove!=null){
            pMoves.add(cMove);
        }

        cMove = calcPos(ourColor,board,myPosition,-1,2);
        if(cMove!=null){
            pMoves.add(cMove);
        }
        cMove = calcPos(ourColor,board,myPosition,-1,-2);
        if(cMove!=null){
            pMoves.add(cMove);
        }

        return pMoves;
    }

    private ChessMove calcPos(ChessGame.TeamColor ourColor,ChessBoard board, ChessPosition myPosition,int row,int col){
        if(myPosition.getRow()+row<=8&&myPosition.getRow()+row>=1 && myPosition.getColumn()+col>=1&&myPosition.getColumn()+col<=8){
            ChessPiece p = board.getPiece(new ChessPosition(myPosition.getRow()+row,myPosition.getColumn()+col));
            if(p!=null){
                if(p.getTeamColor()!=ourColor){
                    return new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+row,myPosition.getColumn()+col), null);
                }
            }
            else{
                return new ChessMove(myPosition,new ChessPosition(myPosition.getRow()+row,myPosition.getColumn()+col), null);
            }
        }
        return null;
    }
}
