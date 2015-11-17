package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Lucas MARTINEZ
 * @version 04/11/15
 */
public class ServerThread extends Thread{
    Socket client;

    public ServerThread(Socket client) {
        super("ServerThread");
        this.client = client;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream()));
        ) {
            String inputLine, outputLine;
            Protocol protocol = new Protocol();
            outputLine = protocol.processInput("WELCOME");
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = protocol.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("STOP OK"))
                    break;
            }

            client.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
