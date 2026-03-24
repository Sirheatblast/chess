package client;

import chess.ChessBoard;
import model.UserData;

import java.util.Scanner;

public class ClientMain {
    private static boolean isLoggedIn = false;
    private static UserData currentUser;
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServerFacade facade = new ServerFacade();

    private static final ChessBoard board = new ChessBoard();

    public static void main(String[] args) {
        board.resetBoard();
        ui.ChessBoard.DrawBoard(board);
//        while(true){
//            if(!isLoggedIn){
//                if(!preLoginUI()){
//                    break;
//                }
//            }
//            else{
//                if(!loggedInUI()){
//                    break;
//                }
//            }
//        }
    }

    private static boolean preLoginUI(){
        System.out.print("♚ Chess 240 ♔\n");
        System.out.print("\n");
        System.out.print("[Logged out]\n");

        System.out.print("1: Create Account\n");
        System.out.print("2: Login\n");
        System.out.print("3: Help\n");
        System.out.print("4: Quit\n");

        String input = scanner.next();
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
        username = scanner.next();

        System.out.print("\tPassword: ");
        password = scanner.next();

        System.out.print("\tEmail: ");
        email = scanner.next();

        try{
            currentUser = facade.createUser(username,password,email);
            return true;
        }
        catch (Exception e){
            System.out.print("Failed to create User");
            return false;
        }
    }

    private static boolean loginUI(){
        String username;
        String password;

        System.out.print("\nLogin User:\n");

        System.out.print("\tUsername: ");
        username = scanner.next();

        System.out.print("\tPassword: ");
        password = scanner.next();

        currentUser = new UserData(username,password,null); //temp
        return true; //temp
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

        String input = scanner.next();
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
                currentUser = null;
                isLoggedIn = false;
                break;
            case "6":
                loginHelpUI();
                break;
            case "7":
                return false;
            default:
                System.out.print("Bad input\n");
                break;
        }

        return true;
    }

    private static void loginHelpUI() {
        System.out.print("\nHelp:\n");
        System.out.print("\tPlay Game: Allows the user to pick and join a game\n");
        System.out.print("\tList Games: Shows all games on the server\n");
        System.out.print("\tCreate Game: Allows the user to Create and join a new game\n");
        System.out.print("\tObserve Game: Allows the user to view an ongoing match\n");
        System.out.print("\tLogout: Logs out the current player and returns to start menu\n");
        System.out.print("\tHelp: Helps the user navigate the menu\n");
        System.out.print("\tQuit: Quits application\n");
    }

    private static void playGame() {
        String gameName;
        String dColor;

        System.out.print("Enter Game Name: ");
        gameName = scanner.next();
        System.out.print("Enter Desired Color: ");
        dColor = scanner.next();
    }

    private static void listGames(){

    }

    private static void createGame(){
        String gameName;
        gameName = scanner.next();
    }

    private static void observeGame(){

    }
}
