package eu.skymmel.rpsgame;

import java.util.Scanner;

import static eu.skymmel.rpsgame.assets.Colors.*;
import static eu.skymmel.rpsgame.assets.Icons.*;

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
            System.out.println(warn + "IT'S A DRAW!");
        }
        else if (
                (option[player].equals(option[0]) && option[opponent].equals(option[2]))
                        ||
                        (option[player].equals(option[2]) && option[opponent].equals(option[1]))
                        ||
                        (option[player].equals(option[1]) && option[opponent].equals(option[0]))
        ) {
            win++;
            System.out.println(warn + cyan + Main.nickname + reset + " won.");
            System.out.println(warn + red + Main.opponent + reset + " lost.");
        }
        else {
            loss++;
            System.out.println(warn + red + Main.opponent + reset + " won.");
            System.out.println(warn + cyan + Main.nickname + reset + " lost.");
        }
    }
    public static byte getChoose() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat are you choose?");
        System.out.println(green + " 1 " + reset + "Rock");
        System.out.println(green + " 2 " + reset + "Paper");
        System.out.println(green + " 3 " + reset + "Scissors");
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
                System.out.println(warn + "Please enter a valid number");
            }
        }
        return chosen;
    }
    public static void printGameOver(){
        System.out.println(blue + "\n---[ " + reset + "G A M E   O V E R" + blue + " ]---\n" + reset);
        System.out.println(warn + cyan + Main.nickname + reset + " won " + Game.win + " game(s).");
        System.out.println(warn + red + Main.opponent + reset + " won " + Game.loss + " game(s).");
        System.out.println(line);
        loss = 0;
        win = 0;
    }
}