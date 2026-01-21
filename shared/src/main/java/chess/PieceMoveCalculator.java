package chess;

import java.util.ArrayList;
import java.util.Collection;

public class PieceMoveCalculator {
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition){
        return new ArrayList<>();
    }

    //returns a list of available positions in the upward direction
    protected Collection<ChessMove> checkUp(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max,boolean canAttack){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosY = myPosition.getRow()+1;
        while (currentPosY <= 8) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,myPosition.getColumn()))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,myPosition.getColumn()));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(canAttack&&other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,myPosition.getColumn()), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,myPosition.getColumn()), promotionPiece));
            currentPosY++;
        }
        return validMoves;
    }
    //returns a list of available positions in the downward direction
    protected Collection<ChessMove> checkDown(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max,boolean canAttack){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosY = myPosition.getRow()-1;
        while (currentPosY >= 1) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,myPosition.getColumn()))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,myPosition.getColumn()));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(canAttack&&other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,myPosition.getColumn()), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,myPosition.getColumn()), promotionPiece));
            currentPosY--;
        }
        return validMoves;
    }
    //returns a list of available positions in the right direction
    protected Collection<ChessMove> checkRight(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()+1;
        while (currentPosX <= 8) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(myPosition.getRow(),currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(myPosition.getRow(),currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(),currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(),currentPosX), promotionPiece));
            currentPosX++;
        }
        return validMoves;
    }
    //returns a list of available positions in the left direction
    protected Collection<ChessMove> checkLeft(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()-1;
        while (currentPosX >= 1) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(myPosition.getRow(),currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(myPosition.getRow(),currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(),currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(myPosition.getRow(),currentPosX), promotionPiece));
            currentPosX--;
        }
        return validMoves;
    }
    //returns a list of available positions in the upper-right direction
    protected Collection<ChessMove> checkURight(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()+1;
        int currentPosY = myPosition.getRow()+1;
        while (currentPosX <= 8 && currentPosY <=8) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
            currentPosX++;
            currentPosY++;
        }
        return validMoves;
    }

    //returns a list of available positions in the upper-left direction
    protected Collection<ChessMove> checkULeft(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()-1;
        int currentPosY = myPosition.getRow()+1;
        while (currentPosX >= 1 && currentPosY <=8) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
            currentPosX--;
            currentPosY++;
        }
        return validMoves;
    }

    //returns a list of available positions in the lower-left direction
    protected Collection<ChessMove> checkLLeft(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()-1;
        int currentPosY = myPosition.getRow()-1;
        while (currentPosX >= 1 && currentPosY >=1) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
            currentPosX--;
            currentPosY--;
        }
        return validMoves;
    }
    //returns a list of available positions in the lower-right direction
    protected Collection<ChessMove> checkLRight(ChessBoard board, ChessPosition myPosition, ChessPiece.PieceType promotionPiece,int max){
        Collection<ChessMove> validMoves = new ArrayList<>();
        int currentPosX = myPosition.getColumn()+1;
        int currentPosY = myPosition.getRow()-1;
        while (currentPosX <=8 && currentPosY >=1) {
            if(validMoves.size()>=max){
                break;
            }
            if(board.getPiece(new ChessPosition(currentPosY,currentPosX))==null){
                ChessPiece other = board.getPiece(new ChessPosition(currentPosY,currentPosX));
                ChessPiece thisP = board.getPiece(new ChessPosition(myPosition.getRow(),myPosition.getColumn()));
                if(other.getTeamColor()!=thisP.getTeamColor()){
                    validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
                }
                break;
            }
            validMoves.add(new ChessMove(myPosition, new ChessPosition(currentPosY,currentPosX), promotionPiece));
            currentPosX++;
            currentPosY--;
        }
        return validMoves;
    }
}
