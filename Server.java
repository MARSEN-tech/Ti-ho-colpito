package ClientServer;

//Definisce il pacchetto
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        final int port = 12345; // Definisce la porta del server

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port); // Stampa un messaggio che indica che il server è avviato

            while (true) {
                Socket client = serverSocket.accept(); // Attende una connessione da un client

                new Thread(new ClientHandler(client)).start(); // Assegna il client a un nuovo thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
