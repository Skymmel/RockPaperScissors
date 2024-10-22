package eu.skymmel.rpsgame;

import eu.skymmel.rpsgame.multiplayer.Client;
import eu.skymmel.rpsgame.multiplayer.Server;

import java.util.Random;
import java.util.Scanner;

import static eu.skymmel.rpsgame.Game.getChoose;
import static eu.skymmel.rpsgame.assets.Colors.*;
import static eu.skymmel.rpsgame.assets.Icons.*;

public class Menu {
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(blue + "\n---[ " + reset + "G A M E   M E N U" + blue + " ]---" + reset);
            System.out.println("1" + yellow + " = " + reset + "Single player");
            System.out.println("2" + yellow + " = " + reset + "Host server");
            System.out.println("3" + yellow + " = " + reset + "Join server");
            System.out.println("4" + yellow + " = " + reset + "Change nickname");
            System.out.println("5" + yellow + " = " + reset + "Quit");
            System.out.println(line);
            System.out.print(question + "Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    singleplayer();
                    break;
                case 2:
                    Server.server(getPort());
                    break;
                case 3:
                    Client.client(getAddress(), getPort());
                    break;
                case 4:
                    getLocalPlayerName();
                    break;
                case 5:
                    System.out.println(warn + "Goodbye");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    private static String getAddress() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(question + "Enter server address: ");
        return scanner.nextLine();
    }
    private static int getPort() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + "Enter port number: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public static void singleplayer () {
        for (int i = 0; i < 5; i++) {
            System.out.println(line);
            System.out.println(warn + "Round #" + (i + 1));
            Random random = new Random();
            Game.matchEvaluate(getChoose(), (byte) random.nextInt(3));
        }
        Game.printGameOver();
    }
    public static void getLocalPlayerName (){
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + "Enter your name: ");
        Main.nickname = scanner.nextLine();
        System.out.println("Hello, " + cyan + Main.nickname + reset + "!");
        System.out.println(warn + "Welcome to RockPaperScissors Game!");
    }
    public static void setLocalPlayerName (String name){
        Main.nickname = name;
        System.out.println("Hello, " + Main.nickname + "!");
    }
}
