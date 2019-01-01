package io.github.aarvedahl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ChatClient {

    void startConnection() {
        try {
            Socket clientSocket = etablishConnection();
            displayMessagesReceived(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void displayMessagesReceived(Socket clientSocket) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        while ((inputLine = inputStream.readLine()) != null) {
            System.out.println(inputLine);
        }
    }


    private Socket etablishConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        return serverSocket.accept();
    }

    private void acceptMultipleCLients(ServerSocket serverSocket) throws IOException {
        while (true) {
            SocketThread socketThread = new SocketThread();
            Socket clientSocket = serverSocket.accept();
        }
    }
}