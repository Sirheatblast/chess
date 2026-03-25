package ui;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ui.EscapeSequences.*;

public class ChessBoard {
    private static final int boardSize = 8;
    private static final String border = SET_BG_COLOR_LIGHT_GREY;
    private static final String whiteBackground = SET_BG_COLOR_CREAM;
    private static final String blackBackground = SET_BG_COLOR_DARK_GREY;

    public static void DrawBoard(chess.ChessBoard board,String pColor){
        String topBottom = EMPTY + "a  " + " b " + "  c " + "  d  " + "e  " + " f  " + " g " + " h " + EMPTY;
        ArrayList<String> sides = new ArrayList<>(List.of(" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 "));

        if(!pColor.equals("WHITE")){
            StringBuilder builder = new StringBuilder(topBottom);
            builder.reverse();
            topBottom = builder.toString();
            sides.reversed();
        }

        int start = (pColor.equals("WHITE"))?boardSize:1;
        int end = (pColor.equals("WHITE"))?1:boardSize;

        System.out.print(border);
        System.out.print(SET_TEXT_BOLD);
        System.out.print(topBottom);
        System.out.print(RESET_BG_COLOR + "\n");
        for(int y=start;checkDirection(pColor,y,end);){
            System.out.print(border);
            System.out.print(sides.get(y-1));
            for(int x = start;checkDirection(pColor,x,end);){
                System.out.print(SET_TEXT_COLOR_BLUE);
                if(x%2==y%2){
                    System.out.print(whiteBackground);
                    System.out.print(getChar(x,y,board));
                }
                else{
                    System.out.print(blackBackground);
                    System.out.print(getChar(x,y,board));
                }
                if(pColor.equals("WHITE")){
                    x--;
                }
                else{
                    x++;
                }
            }
            System.out.print(SET_TEXT_COLOR_WHITE);
            System.out.print(border);
            System.out.print(sides.get(y-1));
            System.out.print(RESET_BG_COLOR);
            System.out.print("\n");

            if(pColor.equals("WHITE")){
                y--;
            }
            else{
                y++;
            }
        }
        System.out.print(border);
        System.out.print(topBottom);
        System.out.print(RESET_BG_COLOR + "\n");
        System.out.print(RESET_TEXT_BOLD_FAINT);
    }

    private static boolean checkDirection(String pColor,int i,int end){
        if(Objects.equals(pColor, "WHITE")){
            return i>=end;
        }
        else{
            return i<=end;
        }
    }

    private static String getChar(int x, int y, chess.ChessBoard board){
        ChessPiece piece = board.getPiece(new ChessPosition(y,x));
        if(piece!=null){
            switch (piece.getPieceType()){
                case KING -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_KING:BLACK_KING;
                }
                case QUEEN -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_QUEEN:BLACK_QUEEN;
                }
                case BISHOP -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_BISHOP:BLACK_BISHOP;
                }
                case KNIGHT -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_KNIGHT:BLACK_KNIGHT;
                }
                case ROOK -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_ROOK:BLACK_ROOK;
                }
                case PAWN -> {
                    return (piece.getTeamColor()== ChessGame.TeamColor.WHITE)?WHITE_PAWN:BLACK_PAWN;
                }
            }
        }

        return EMPTY;
    }
}
