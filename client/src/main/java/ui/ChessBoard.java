package ui;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.List;

import static ui.EscapeSequences.*;

public class ChessBoard {
    private static final int boardSize = 7;
    private static final String border = SET_BG_COLOR_CHOCOLATE;
    private static final String whiteBackground = SET_BG_COLOR_CREAM;
    private static final String blackBackground = SET_BG_COLOR_DARK_GREY;

    public static void DrawBoard(chess.ChessBoard board){
        String topBottom = EMPTY+" a "+ " b " +" c "+" d "+" e "+" f "+ " g " + " h "+EMPTY+RESET_BG_COLOR+"\n";
        ArrayList<String> sides = new ArrayList<>(List.of(" 8 "," 7 "," 6 "," 5 "," 4 ", " 3 ", " 2 "," 1 "));

        System.out.print(border);
        System.out.print(topBottom);
        for(int y=0;y<boardSize+1;y++){
            System.out.print(border);
            System.out.print(sides.get(y));
            for(int x = 0;x<boardSize;x++){
                if(x%2==y%2){
                    System.out.print(whiteBackground);
                    System.out.print(getChar(x+1,y+1,board));
                }
                else{
                    System.out.print(blackBackground);
                    System.out.print(getChar(x+1,y+1,board));
                }
            }
            System.out.print(border);
            System.out.print(sides.get(y));
            System.out.print(RESET_BG_COLOR);
            System.out.print("\n");
        }
        System.out.print(border);
        System.out.print(topBottom);
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
