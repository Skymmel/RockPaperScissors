package eu.skymmel.rpsgame;

import eu.skymmel.rpsgame.multiplayer.Client;
import eu.skymmel.rpsgame.multiplayer.Server;

import java.util.Random;
import java.util.Scanner;

import static eu.skymmel.rpsgame.Game.getChoose;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n---[ G A M E   M E N U ]---\n");
            System.out.println("1 = Single player");
            System.out.println("2 = Host server");
            System.out.println("3 = Join server");
            System.out.println("4 = Change nickname");
            System.out.println("5 = Quit");
            System.out.println("\n---------------------------\n");
            System.out.println("[?] Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    singleplayer();
                    break;
                case 2:
                    Server.server(3000);
                    break;
                case 3:
                    Client.client("127.0.0.1", 3000);
                    break;
                case 4:
                    getLocalPlayerName();
                    break;
                case 5:
                    System.out.println("Goodbye");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    public static void singleplayer () {
        for (int i = 0; i < 5; i++) {
            System.out.println("\n---------------------------\n");
            System.out.println("[!] Round #" + (i + 1));
            Random random = new Random();
            Game.matchEvaluate(getChoose(), (byte) random.nextInt(3));
        }
        Game.printGameOver();
    }
    public static void getLocalPlayerName (){
        Scanner scanner = new Scanner(System.in);
        System.out.print("[?] Enter your name: ");
        Main.nickname = scanner.nextLine();
        System.out.println("Hello, " + Main.nickname + "!");
        System.out.println("Welcome to RockPaperScissors Game!");
    }
    public static void setLocalPlayerName (String name){
        Main.nickname = name;
        System.out.println("Hello, " + Main.nickname + "!");
    }
}
