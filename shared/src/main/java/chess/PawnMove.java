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
                pMoves.addAll(checkUp(board,myPosition, ChessPiece.PieceType.QUEEN,2,false));
            }
            else{
                pMoves.addAll(checkUp(board,myPosition, ChessPiece.PieceType.QUEEN,1,false));
            }
            pMoves.addAll((checkULeft(board,myPosition, ChessPiece.PieceType.QUEEN,1)));
            pMoves.addAll((checkURight(board,myPosition, ChessPiece.PieceType.QUEEN,1)));
        }
        else{
            if(myPosition.getRow()==7){
                pMoves.addAll(checkDown(board,myPosition, ChessPiece.PieceType.QUEEN,2,false));
            }
            else{
                pMoves.addAll(checkDown(board,myPosition, ChessPiece.PieceType.QUEEN,1,false));
            }
            pMoves.addAll((checkLLeft(board,myPosition, ChessPiece.PieceType.QUEEN,1)));
            pMoves.addAll((checkLRight(board,myPosition, ChessPiece.PieceType.QUEEN,1)));
        }


        return pMoves;
    }

}
