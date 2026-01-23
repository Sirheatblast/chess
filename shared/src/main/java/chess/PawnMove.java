package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMove extends PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        ChessGame.TeamColor ourColor = board.getPiece(myPosition).getTeamColor();
        Collection<ChessMove> pMoves = new ArrayList<>();

        if(ourColor == ChessGame.TeamColor.WHITE){
            if(myPosition.getRow()==2){
                for(var pos:checkUp(board,myPosition, null,2,false)){
                    if(pos.getEndPosition().getRow()==8){
                        pMoves.addAll(isPromoted(pos));
                    }
                    else{
                        pMoves.add(pos);
                    }
                }            }
            else {
                for(var pos:checkUp(board,myPosition, null,1,false)){
                    if(pos.getEndPosition().getRow()==8){
                        pMoves.addAll(isPromoted(pos));
                    }
                    else{
                        pMoves.add(pos);
                    }
                }
            }
            if(myPosition.getRow()+1<=8){
                ChessPosition currentPos  = new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()+1);
                if(myPosition.getColumn()+1<=8){
                    if(board.getPiece(currentPos)!=null){
                        for(var pos:checkURight(board,myPosition, null,1)){
                            if(pos.getEndPosition().getRow()==8){
                                pMoves.addAll(isPromoted(pos));
                            }
                            else{
                                pMoves.add(pos);
                            }
                        }
                    }
                }
                if(myPosition.getColumn()-1>=1){
                    currentPos  = new ChessPosition(myPosition.getRow()+1,myPosition.getColumn()-1);
                    if(board.getPiece(currentPos)!=null){
                        for(var pos:checkULeft(board,myPosition, null,1)){
                            if(pos.getEndPosition().getRow()==8){
                                pMoves.addAll(isPromoted(pos));
                            }
                            else{
                                pMoves.add(pos);
                            }
                        }
                    }
                }
            }
        }
        else{
            if(myPosition.getRow()==7){
                for(var pos:checkDown(board,myPosition, null,2,false)){
                    if(pos.getEndPosition().getRow()==0){
                        pMoves.addAll(isPromoted(pos));
                    }
                    else{
                        pMoves.add(pos);
                    }
                }
            }
            else {
                for(var pos:checkDown(board,myPosition, null,1,false)){
                    if(pos.getEndPosition().getRow()==1){
                        pMoves.addAll(isPromoted(pos));
                    }
                    else{
                        pMoves.add(pos);
                    }
                }
            }
            if(myPosition.getRow()-1>=1){
                ChessPosition currentPos  = new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()+1);
                if(myPosition.getColumn()+1<=8){
                    if(board.getPiece(currentPos)!=null){
                        for(var pos:checkLRight(board,myPosition, null,1)){
                            if(pos.getEndPosition().getRow()==1){
                                pMoves.addAll(isPromoted(pos));
                            }
                            else{
                                pMoves.add(pos);
                            }
                        }
                    }
                }
                if(myPosition.getColumn()-1>=1){
                    currentPos  = new ChessPosition(myPosition.getRow()-1,myPosition.getColumn()-1);
                    if(board.getPiece(currentPos)!=null){
                        for(var pos:checkLLeft(board,myPosition, null,1)){
                            if(pos.getEndPosition().getRow()==1){
                                pMoves.addAll(isPromoted(pos));
                            }
                            else{
                                pMoves.add(pos);
                            }
                        }
                    }
                }
            }
        }
        return pMoves;
    }

    private Collection<ChessMove> isPromoted(ChessMove move){
        Collection<ChessMove> pMoves = new ArrayList<>();
        pMoves.add(new ChessMove(move.getStartPosition(),move.getEndPosition(), ChessPiece.PieceType.QUEEN));
        pMoves.add(new ChessMove(move.getStartPosition(),move.getEndPosition(), ChessPiece.PieceType.BISHOP));
        pMoves.add(new ChessMove(move.getStartPosition(),move.getEndPosition(), ChessPiece.PieceType.ROOK));
        pMoves.add(new ChessMove(move.getStartPosition(),move.getEndPosition(), ChessPiece.PieceType.KNIGHT));
        return pMoves;
    }

}
