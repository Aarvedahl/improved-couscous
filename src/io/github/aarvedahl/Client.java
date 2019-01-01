package io.github.aarvedahl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements ChatClient {

    private static final String HOSTNAME = "localhost";

    void connectToServer() {
        try {
            Socket clientSocket = getSocket();
            sendMessage(clientSocket);
            displayMessagesReceived(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(Socket clientSocket) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
        }
    }

    private void displayMessagesReceived(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        while (stdIn.readLine() != null) {
            System.out.println(in.readLine());
        }
    }

    private Socket getSocket() throws IOException {
        return new Socket(HOSTNAME, PORT_NUMBER);
    }
}
