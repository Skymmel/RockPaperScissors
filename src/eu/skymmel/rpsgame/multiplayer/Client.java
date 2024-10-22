package eu.skymmel.rpsgame.multiplayer;

import eu.skymmel.rpsgame.Game;
import eu.skymmel.rpsgame.Main;

import java.io.*;
import java.net.*;

import static eu.skymmel.rpsgame.assets.Icons.*;
import static eu.skymmel.rpsgame.assets.Colors.*;


public class Client {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void client(String serverHost, int port) {
        try (Socket socket = new Socket(serverHost, port)) {
            System.out.println(warn + "Connected to " + serverHost + ":" + port + ".");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(Main.nickname);
            System.out.println(join + Main.nickname + " connected.");
            Main.opponent = in.readLine();
            System.out.println(warn + "The host " + red + Main.opponent + reset + " turned on the game.\n");
            System.out.println(warn + "Game started between " + cyan + Main.nickname + reset + " and " + red + Main.opponent + reset + ".");

            byte opponent = 0;
            byte player;

            for (int i = 0; i < 5; i++) {
                System.out.println(line);
                System.out.println(warn + "Round #" + (i + 1));
                try {
                    opponent = Byte.parseByte(in.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Received invalid input from opponent.");
                    continue;
                }
                player = Game.getChoose();
                out.println(player);
                Game.matchEvaluate(player, opponent);
            }
            Game.printGameOver();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.opponent = "opponent";
    }
}
