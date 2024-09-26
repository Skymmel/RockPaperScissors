package eu.skymmel.rpsgame.multiplayer;

import eu.skymmel.rpsgame.Game;
import eu.skymmel.rpsgame.Main;

import java.io.*;
import java.net.*;

public class Server {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void server(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            InetAddress ipAddress = InetAddress.getLocalHost();
            System.out.println("[!] Server is running on IP: " + ipAddress.getHostAddress() + " and port: " + port);
            System.out.println("[!] Waiting for connection...");

            try (Socket socket = serverSocket.accept()) {
                System.out.println("[+] A client has connected from IP: " + socket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                Main.opponent = in.readLine();
                System.out.println("[+] " + Main.opponent + " connected.");
                out.println(Main.nickname);

                System.out.println("[!] Game started between " + Main.nickname + " and " + Main.opponent + ".");

                byte player;
                byte opponent;

                for (int i = 0; i < 5; i++) {
                    System.out.println("\n---------------------------\n");
                    System.out.println("[!] Round #" + (i + 1));
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
