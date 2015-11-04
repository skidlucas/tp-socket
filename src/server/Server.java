package server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public class Server {

    private ServerSocket serverSocket;
    private static int port = 6669;

    public Server() {
        try {
            serverSocket = new ServerSocket(Server.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server successfully created...");
    }

    private void start() {
        boolean listening = true;

        try {
            while (listening) {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + Server.port);
            System.exit(-1);
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
