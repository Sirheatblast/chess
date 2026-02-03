package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMoves extends MoveManager {
    @Override
    public Collection<ChessMove> getPieceMoves(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol) {
        Collection<ChessMove> pMoves = new ArrayList<>();
        if(tCol == ChessGame.TeamColor.WHITE){
            pMoves.addAll(processWhitePawn(board,myPos,tCol));
        }
        else{
            pMoves.addAll(processBlackPawn(board,myPos,tCol));
        }
        return  pMoves;
    }

    private Collection<ChessMove> processWhitePawn(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol){
        Collection<ChessMove> pMoves = new ArrayList<>();
        if(myPos.getRow()==2){
            pMoves.addAll(getUp(board,myPos,tCol,null,2,false));
        }
        else{
            pMoves.addAll(getUp(board,myPos,tCol,null,1,false));
        }

        if(myPos.getRow()+1<9&&myPos.getColumn()+1<9&&board.getPiece(new ChessPosition(myPos.getRow()+1, myPos.getColumn()+1))!=null){
            pMoves.addAll(getURight(board,myPos,tCol,null,1));
        }
        if(myPos.getRow()+1<9&&myPos.getColumn()-1>0&&board.getPiece(new ChessPosition(myPos.getRow()+1, myPos.getColumn()-1))!=null){
            pMoves.addAll(getULeft(board,myPos,tCol,null,1));
        }

        return checkForPromotion(pMoves);
    }

    private Collection<ChessMove> processBlackPawn(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol){
        Collection<ChessMove> pMoves = new ArrayList<>();
        if(myPos.getRow()==7){
            pMoves.addAll(getDown(board,myPos,tCol,null,2,false));
        }
        else{
            pMoves.addAll(getDown(board,myPos,tCol,null,1,false));
        }

        if(myPos.getRow()-1>0&&myPos.getColumn()+1<9&&board.getPiece(new ChessPosition(myPos.getRow()-1, myPos.getColumn()+1))!=null){
            pMoves.addAll(getLRight(board,myPos,tCol,null,1));
        }
        if(myPos.getRow()-1>0&&myPos.getColumn()-1>0&&board.getPiece(new ChessPosition(myPos.getRow()-1, myPos.getColumn()-1))!=null){
            pMoves.addAll(getLLeft(board,myPos,tCol,null,1));
        }

        return checkForPromotion(pMoves);
    }

    private Collection<ChessMove> checkForPromotion(Collection<ChessMove> lMoves){
        Collection<ChessMove> pMoves = new ArrayList<>();
        for(ChessMove mv:lMoves){
            if(mv.getEndPosition().getRow()==8||mv.getEndPosition().getRow()==1){
                pMoves.add(new ChessMove(mv.getStartPosition(),mv.getEndPosition(), ChessPiece.PieceType.QUEEN));
                pMoves.add(new ChessMove(mv.getStartPosition(),mv.getEndPosition(), ChessPiece.PieceType.BISHOP));
                pMoves.add(new ChessMove(mv.getStartPosition(),mv.getEndPosition(), ChessPiece.PieceType.KNIGHT));
                pMoves.add(new ChessMove(mv.getStartPosition(),mv.getEndPosition(), ChessPiece.PieceType.ROOK));
            }
            else{
                pMoves.add(mv);
            }
        }
        return pMoves;
    }

}
