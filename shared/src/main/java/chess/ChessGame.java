package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor playingTeam;
    private ChessBoard board;

    public ChessGame() {
        board = new ChessBoard();
        board.resetBoard();
        setTeamTurn(TeamColor.WHITE);
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return playingTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return playingTeam == chessGame.playingTeam && Objects.equals(board, chessGame.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingTeam, board);
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        playingTeam = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    private ChessBoard copyBoard(ChessBoard original){
        ChessBoard copy=new ChessBoard();
        for(int y = 0;y<8;y++){
            for(int x = 0;x<8;x++ ){
                copy.get()[y][x] = original.get()[y][x];
            }
        }

        return copy;
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = board.getPiece((startPosition));
        if(piece == null)
            return null;

        Collection<ChessMove> pMoves = new ArrayList<>();
        for(var move:piece.pieceMoves(board,startPosition)){
            ChessMove originalMove = new ChessMove(move.getEndPosition(),move.getStartPosition(),null);
            ChessPiece originalEndP = board.getPiece(move.getEndPosition());

            ProcessMove(piece, move);
            if(!isInCheck(piece.getTeamColor())){
                pMoves.add(move);
            }
            ProcessMove(piece,originalMove);
            board.get()[move.getEndPosition().getRow()-1][move.getEndPosition().getColumn()-1] = originalEndP;
        }

        return pMoves;
    }

    private void ProcessMove(ChessPiece piece, ChessMove move) {
        board.get()[move.getStartPosition().getRow()-1][move.getStartPosition().getColumn()-1] = null;
        if(move.getPromotionPiece()==null){
            board.get()[move.getEndPosition().getRow()-1][move.getEndPosition().getColumn()-1] = piece;
        }
        else{
            board.get()[move.getEndPosition().getRow()-1][move.getEndPosition().getColumn()-1] = new ChessPiece(piece.getTeamColor(),move.getPromotionPiece());
        }
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece piece = board.getPiece(move.getStartPosition());

        if(!isInCheck(playingTeam)&&piece!=null&&validMoves(move.getStartPosition()).contains(move)&&piece.getTeamColor()==playingTeam){
            ProcessMove(piece, move);
            playingTeam = (playingTeam==TeamColor.WHITE)?TeamColor.BLACK:TeamColor.WHITE;
        }
        else{
            throw new InvalidMoveException();
        }
    }

    private boolean checkKingPos(ChessPosition kingPos) {
        return ProcessCheck(kingPos, playingTeam);
    }

    private boolean ProcessCheck(ChessPosition kingPos, TeamColor playingTeam) {
        Collection<ChessPosition> pPoses = new ArrayList<>();

        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                ChessPiece piece = board.get()[y][x];
                ChessPosition currentPos = new ChessPosition(y+1,x+1);
                if(piece!=null){
                    if(piece.getTeamColor() == playingTeam){
                        if(piece.getPieceType() == ChessPiece.PieceType.KING && kingPos==null){
                            kingPos = currentPos;
                        }
                    }
                    else{
                        for(var move:piece.pieceMoves(board,currentPos)) {
                            pPoses.add(move.getEndPosition());
                        }
                    }
                }
            }
        }
        return pPoses.contains(kingPos);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        return ProcessCheck(null, teamColor);
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        Collection<ChessMove>pMoves=new ArrayList<>();
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                ChessPosition currentPos = new ChessPosition(y+1,x+1);
                if(board.get()[y][x]!=null&&board.get()[y][x].getTeamColor()==teamColor){
                    pMoves.addAll(validMoves(currentPos));
                }
            }
        }

        return pMoves.isEmpty();
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves while not in check.
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        Collection<ChessMove>pMoves=new ArrayList<>();
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                ChessPosition currentPos = new ChessPosition(y+1,x+1);
                if(board.get()[y][x]!=null&&board.get()[y][x].getTeamColor()==teamColor){
                    pMoves.addAll(validMoves(currentPos));
                }
            }
        }

        return !isInCheck(teamColor)&&pMoves.isEmpty();
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }
}
