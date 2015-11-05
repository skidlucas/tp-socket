package client;

import java.io.*;
import java.net.*;

public class Client {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 6669;
    private Socket clientSocket;

    public Client(){
        try {
            clientSocket = new Socket(HOSTNAME, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        //tuto oracle
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("STOP OK"))
                    break;

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + HOSTNAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    HOSTNAME);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.connect();
        } catch (Exception e) {
            System.err.println("Client:Echec du lancement du client");
        }
    }
}