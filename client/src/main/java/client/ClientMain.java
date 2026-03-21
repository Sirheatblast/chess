package client;

import model.UserData;

import java.util.Scanner;

public class ClientMain {
    private static boolean isLoggedIn = false;
    private static UserData uData;

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

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        switch (input){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                return false;
            default:
                System.out.print("Bad input\n");
                break;
        }

        return true;
    }

    private static boolean registerUI(){

        return true;
    }

    private static boolean loggedInUI(){
        return true;
    }

}
