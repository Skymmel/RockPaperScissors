package eu.skymmel.rpsgame;

import java.util.Scanner;

public class Game {
    private static final String[] option = {"rock", "paper", "scissors"};
    // 0 - Rock
    // 1 - Paper
    // 2 - Scissors
    public static byte win = 0;
    public static byte loss = 0;

    public static void matchEvaluate(byte player, byte opponent) {
        System.out.println(Main.nickname + " chose " + option[player]);
        System.out.println(Main.opponent + " chose " + option[opponent]);
        if (option[player].equals(option[opponent])) {
            System.out.println("[!] IT'S A DRAW!");
        }
        else if (
            (option[player].equals(option[0]) && option[opponent].equals(option[2]))
            ||
            (option[player].equals(option[2]) && option[opponent].equals(option[1]))
            ||
            (option[player].equals(option[1]) && option[opponent].equals(option[0]))
        ) {
            win++;
            System.out.println("[!] " + Main.nickname + " won.");
            System.out.println("[!] " + Main.opponent + " lost.");

        }
        else {
            loss++;
            System.out.println("[!] " + Main.nickname + " lost.");
            System.out.println("[!] " + Main.opponent + " won.");
        }
    }
    public static byte getChoose() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat are you choose?");
        System.out.println(" 1 Rock");
        System.out.println(" 2 Paper");
        System.out.println(" 3 Scissors");
        System.out.print("I choose ");

        byte chosen = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                chosen = (byte) (Byte.parseByte(scanner.nextLine())-1);
                if (chosen < 3 && chosen >= 0) {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
        return chosen;
    }
    public static void printGameOver(){
        System.out.println("\n---[ G A M E   O V E R ]---\n");
        System.out.println("[!] " + Main.nickname + " won " + Game.win + " game(s).");
        System.out.println("[!] " + Main.opponent + " won " + Game.loss + " game(s).");
        System.out.println("\n---------------------------\n");
        loss = 0;
        win = 0;
    }
}