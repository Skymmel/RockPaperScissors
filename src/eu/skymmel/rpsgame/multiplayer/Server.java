package eu.skymmel.rpsgame.multiplayer;

import eu.skymmel.rpsgame.Game;
import eu.skymmel.rpsgame.Main;

import java.io.*;
import java.net.*;

import static eu.skymmel.rpsgame.assets.Colors.*;
import static eu.skymmel.rpsgame.assets.Icons.*;

public class Server {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            InetAddress ipAddress = InetAddress.getLocalHost();
            System.out.println(warn + "Server is running on IP: " + green + ipAddress.getHostAddress() + ":" + port + reset);
            System.out.println(warn + "Waiting for connection...");

            try (Socket socket = serverSocket.accept()) {
                System.out.println(join + "A client has connected from IP: " + green + socket.getInetAddress().getHostAddress() + reset);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                Main.opponent = in.readLine();
                System.out.println(join + Main.opponent + " connected.");
                out.println(Main.nickname);

                System.out.println(warn + "Game started between " + cyan + Main.nickname + reset + " and " + red + Main.opponent + reset + ".");

                byte player;
                byte opponent;

                for (int i = 0; i < 5; i++) {
                    System.out.println(line);
                    System.out.println(warn + "Round #" + (i + 1));
                    player = Game.getChoose();
                    out.println(player);
                    opponent = Byte.parseByte(in.readLine());
                    Game.matchEvaluate(player, opponent);
                }
                Game.printGameOver();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.opponent = "opponent";
    }
}
