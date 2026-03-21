package client;

import model.UserData;

import java.util.Scanner;

public class ClientMain {
    private static boolean isLoggedIn = false;
    private static UserData uData;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true){
            if(!isLoggedIn){
                if(!preLoginUI()){
                    break;
                }
            }
            else{
                if(loggedInUI()){
                    break;
                }
            }
        }
    }

    private static boolean preLoginUI(){
        System.out.print("Chess 240\n");
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

        return false;
    }

    private static boolean loginUI(){
        String username;
        String password;

        System.out.print("\nLogin User:\n");

        System.out.print("\tUsername: ");
        username = scanner.next();

        System.out.print("\tPassword: ");
        password = scanner.next();
        return false;
    }

    private static void loggedOutHelpUI(){
        System.out.print("\nHelp:\n");
        System.out.print("\tCreate Account: Creates a new Chess Account\n");
        System.out.print("\tLogin: Allows a pre-existing user  to access their Chess Account\n");
        System.out.print("\tHelp: Helps the user navigate the menu\n");
        System.out.print("\tQuit: Quits application\n");
    }

    private static boolean loggedInUI(){
        return true;
    }

}
