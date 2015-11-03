package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Lucas MARTINEZ
 * @version 03/11/15
 */
public class Server {

    private ServerSocket serverSocket;
    private static int port = 6669;

    public Server() {
        serverSocket = null;
        /*try {
            InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.err.println("Adresse locale introuvable");
            System.exit(-1);
        }

        System.out.println("Création du serveur réussie ... "); */
    }

    private void start() {
        try {
            serverSocket = new ServerSocket(Server.port);
            System.out.println(serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine, outputLine;

            // Initiate conversation with client
            Protocol kkp = new Protocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + Server.port + " or listening for a connection");
            System.out.println(e.getMessage());
        }

        //System.out.println("Serveur lancé (" + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort() + ")");
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
