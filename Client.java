package ClientServer;

//Definisce il pacchetto

import java.net.Socket;
import java.io.*;

public class Client {

 public static void main(String[] args) {
     String host = "localhost"; // Imposta l'indirizzo del server
     int port = 12345; // Imposta la porta

     try (Socket socket = new Socket(host, port);
    		 
          BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
         // Inizializza i flussi di input e output

         System.out.println("Connected to " + host + ":" + port);
         System.out.println("Enter your guess (e.g., 'C 4') or type 'FINE-INVIO' to exit:");

         String str;
         while (true) {
             System.out.print("> "); 
             str = userIn.readLine(); 
             out.println(str); // Invia l'input al server
             
             // Esce se l'utente digita "FINE-INVIO"
             if ("FINE-INVIO".equalsIgnoreCase(str.trim())) {
                 break;
             }

             String response = in.readLine(); // Legge la risposta dal server
             System.out.println("Server: '" + response + "'");
         }

     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
