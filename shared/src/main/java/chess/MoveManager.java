package chess;

import java.util.ArrayList;
import java.util.Collection;

public class MoveManager {
    private ChessPiece CheckIfOcupied(ChessBoard board,ChessPosition curPos){
        if(board.getPiece(curPos)!=null)
            return board.getPiece(curPos);
        return null;
    }

    public  Collection<ChessMove>getPieceMoves(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol){
        return null;
    }


    protected Collection<ChessMove> getUp(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max, boolean canAttack){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            rowVal++;
            if(rowVal<9){
                ChessPosition curPos = new ChessPosition(rowVal, myPos.getColumn());
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,canAttack)) {
                    break;
                }
            }
        }
        return pMoves;
    }
    protected Collection<ChessMove> getDown(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max, boolean canAttack){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            rowVal--;
            if(rowVal>0){
                ChessPosition curPos = new ChessPosition(rowVal, myPos.getColumn());
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,canAttack)) {
                    break;
                }
            }
        }
        return pMoves;
    }
    protected Collection<ChessMove> getRight(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        for(int i=0;i<max;i++){
            colVal++;
            if(colVal<9){
                ChessPosition curPos = new ChessPosition(myPos.getRow(), colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    protected Collection<ChessMove> getLeft(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        for(int i=0;i<max;i++){
            colVal--;
            if(colVal>0){
                ChessPosition curPos = new ChessPosition(myPos.getRow(), colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    protected Collection<ChessMove> getURight(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            colVal++;
            rowVal++;
            if(colVal<9&&rowVal<9){
                ChessPosition curPos = new ChessPosition(rowVal, colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    protected Collection<ChessMove> getULeft(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            colVal--;
            rowVal++;
            if(colVal>0&&rowVal<9){
                ChessPosition curPos = new ChessPosition(rowVal, colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    protected Collection<ChessMove> getLRight(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            colVal++;
            rowVal--;
            if(colVal<9&&rowVal>0){
                ChessPosition curPos = new ChessPosition(rowVal, colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    protected Collection<ChessMove> getLLeft(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, int max){
        Collection<ChessMove> pMoves = new ArrayList<>();
        int colVal = myPos.getColumn();
        int rowVal = myPos.getRow();
        for(int i=0;i<max;i++){
            colVal--;
            rowVal--;
            if(colVal>0&&rowVal>0){
                ChessPosition curPos = new ChessPosition(rowVal, colVal);
                if (AddMove(board, myPos, tCol, promotionP, pMoves, curPos,true)) {
                    break;
                }
            }
        }
        return pMoves;
    }

    private boolean AddMove(ChessBoard board, ChessPosition myPos, ChessGame.TeamColor tCol, ChessPiece.PieceType promotionP, Collection<ChessMove> pMoves, ChessPosition curPos,boolean canAttack) {
        ChessPiece curP = CheckIfOcupied(board,curPos);
        if(curP!=null){
            if(tCol!=curP.getTeamColor()&&canAttack){
                pMoves.add(new ChessMove(myPos,curPos,promotionP));
            }
            return true;
        }
        else{
            pMoves.add(new ChessMove(myPos,curPos,promotionP));
        }
        return false;
    }
}
