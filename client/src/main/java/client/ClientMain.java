package client;

import model.AuthData;
import model.result.*;

import java.util.Scanner;

public class ClientMain {
    private static boolean isLoggedIn = false;
    private static AuthData currentUser;
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ServerFacade FACADE = new ServerFacade("http://localhost:8080/");

    public static void main(String[] args) {
        while(true){
            if(!isLoggedIn){
                if(!preLoginUI()){
                    break;
                }
            }
            else{
                if(!loggedInUI()){
                    break;
                }
            }
        }
    }

    private static boolean preLoginUI(){
        System.out.print("♚ Chess 240 ♔\n");
        System.out.print("\n");
        System.out.print("[Logged out]\n");

        System.out.print("1: Create Account\n");
        System.out.print("2: Login\n");
        System.out.print("3: Help\n");
        System.out.print("4: Quit\n");

        String input = SCANNER.next();
        switch (input){
            case "1":
                isLoggedIn = registerUI();
                break;
            case "2":
                isLoggedIn = loginUI();
                break;
            case "3":
                loggedOutHelpUI();
                break;
            case "4":
                return false;
            default:
                System.out.print("Bad input\n");
                break;
        }

        return true;
    }

    private static boolean registerUI(){
        String username;
        String password;
        String email;

        System.out.print("\nRegister User:\n");

        System.out.print("\tUsername: ");
        username = SCANNER.next();

        System.out.print("\tPassword: ");
        password = SCANNER.next();

        System.out.print("\tEmail: ");
        email = SCANNER.next();

        try{
            UserResult result = FACADE.createUser(username,password,email);
            if(result.getStatus()!= 200){
                throw new Exception(result.getMessage());
            }
            currentUser = new AuthData(result.getAuthToken(),result.getUsername());
            return true;
        }
        catch (Exception e){
            System.out.printf("\nFailed to create User: %s\n",e.getMessage());
            return false;
        }
    }

    private static boolean loginUI(){
        String username;
        String password;

        System.out.print("\nLogin User:\n");

        System.out.print("\tUsername: ");
        username = SCANNER.next();

        System.out.print("\tPassword: ");
        password = SCANNER.next();

        try{
            UserResult result = FACADE.loginUser(username,password);
            if(result.getStatus()!= 200){
                throw new Exception(result.getMessage());
            }
            currentUser = new AuthData(result.getAuthToken(),result.getUsername());
            return true;
        }
        catch (Exception e){
            System.out.printf("\nFailed to login User: %s\n",e.getMessage());
            return false;
        }
    }

    private static void loggedOutHelpUI(){
        System.out.print("\nHelp:\n");
        System.out.print("\tCreate Account: Creates a new Chess Account\n");
        System.out.print("\tLogin: Allows a pre-existing user  to access their Chess Account\n");
        System.out.print("\tHelp: Helps the user navigate the menu\n");
        System.out.print("\tQuit: Quits application\n");
    }

    private static boolean loggedInUI(){
        System.out.print("♚ Chess 240 ♔\n");
        System.out.print("\n");

        StringBuilder sb;
        sb = new StringBuilder("[");
        sb.append(currentUser.getUsername());
        sb.append("]\n");

        System.out.print(sb.toString());

        System.out.print("1: Play Game\n");
        System.out.print("2: List Games\n");
        System.out.print("3: Create Game\n");
        System.out.print("4: Observe Game\n");
        System.out.print("5: Logout\n");
        System.out.print("6: Help\n");
        System.out.print("7: Quit\n");

        String input = SCANNER.next();
        switch (input){
            case "1":
                playGame();
                break;
            case "2":
                listGames();
                break;
            case "3":
                createGame();
                break;
            case "4":
                observeGame();
                break;
            case "5":
                logoutUser();
                break;
            case "6":
                loginHelpUI();
                break;
            case "7":
                logoutUser();
                return false;
            default:
                System.out.print("Bad input\n");
                break;
        }

        return true;
    }

    private static void logoutUser() {
        try{
            FACADE.logoutUser(currentUser);
        }
        catch (Exception e){
            System.out.print("\nFailed to delete auth token\n");
        }
        currentUser = null;
        isLoggedIn = false;
    }

    private static void loginHelpUI() {
        System.out.print("\nHelp:\n");
        System.out.print("\tPlay Game: Allows the user to pick and join a game\n");
        System.out.print("\tList Games: Shows all games on the server\n");
        System.out.print("\tCreate Game: Allows the user to Create a new game\n");
        System.out.print("\tObserve Game: Allows the user to view an ongoing match\n");
        System.out.print("\tLogout: Logs out the current player and returns to start menu\n");
        System.out.print("\tHelp: Helps the user navigate the menu\n");
        System.out.print("\tQuit: Quits application\n");
    }

    private static void playGame() {
        String gameName;
        String dColor;

        System.out.print("Enter ID of game: ");
        int id = SCANNER.nextInt();
        System.out.print("Enter Desired Color: ");
        dColor = SCANNER.next();
        dColor = dColor.toUpperCase();

        try{
            int gameID = getGameID(id);

            GameResult result = FACADE.joinGame(currentUser,gameID,dColor);
            if(result.getStatus()!=200){
                throw new Exception(result.getMessage());
            }

            ui.ChessBoard.drawBoard(result.getGameData().getGame().getBoard(),dColor);
        }
        catch (Exception e){
            System.out.printf("%s",e.getMessage());
        }
    }

    private static void listGames(){
        try{
            GameListResult result = FACADE.getGames(currentUser);
            if(result.getStatus()!= 200){
                throw new Exception(result.getMessage());
            }

            int counter = 1;
            System.out.print("Current Games:\n");
            for(var gameData:result.getGames()){
                System.out.printf("\t %d: %s white player: %s black player: %s\n",
                        counter,gameData.getGameName(),gameData.getWhiteUsername(),
                        gameData.getBlackUsername());
                counter++;
            }

        } catch (Exception e) {
            System.out.print("\nFailed to retrieve games\n");
        }

    }

    private static void createGame(){
        System.out.print("Enter game name: ");
        String gameName;
        gameName = SCANNER.next();
        try{
            GameRequest gameReq = new GameRequest(gameName,null,0);
            GameResult result = FACADE.createGame(currentUser,gameReq);
            if(result.getStatus()!=200){
                throw new Exception(result.getMessage());
            }
        }
        catch (Exception e){
            System.out.printf("\nFailed to create new game: %s\n",e.getMessage());
        }
    }

    private static void observeGame(){
        System.out.print("Enter ID of game to observe: ");
        int id = SCANNER.nextInt();

        try{
            int gameID = getGameID(id);

            GameResult result = FACADE.observeGame(currentUser,gameID);
            if(result.getStatus()!=200){
                throw new Exception(result.getMessage());
            }

            ui.ChessBoard.drawBoard(result.getGameData().getGame().getBoard(),"WHITE");
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
    }

    private static int getGameID(int id) throws Exception {
        GameListResult listResult = FACADE.getGames(currentUser);
        if(listResult.getStatus()!= 200){
            throw new Exception(listResult.getMessage());
        }

        if(id >listResult.getGames().size()+1){
            throw new Exception("ID doesn't exist\n");
        }
        return listResult.getGames().get(id -1).getGameID();
    }
}
