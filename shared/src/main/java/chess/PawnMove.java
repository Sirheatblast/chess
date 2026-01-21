package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PawnMove extends PieceMoveCalculator{
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        Collection<ChessMove> pMoves = new ArrayList<>();

        if(myPosition.getRow()==1||myPosition.getRow()==6){
            pMoves.addAll(checkUp(board,myPosition, ChessPiece.PieceType.QUEEN,2));
        }
        else{
            pMoves.addAll(checkUp(board,myPosition, ChessPiece.PieceType.QUEEN,1));
        }

        return pMoves;
    }

}
