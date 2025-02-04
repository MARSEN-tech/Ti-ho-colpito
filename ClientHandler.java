ackage ClientServer;

//Definisce il pacchetto

import java.net.Socket;
import java.io.*;
import java.util.Random; 
//Importa la classe Random per generare risposte casuali

public class ClientHandler implements Runnable {
 private Socket client;
 private static final Random random = new Random(); // Crea un oggetto Random per generare risposte casuali
 
// final: non mi permette la modifica della vartiabile
 
 public ClientHandler(Socket client) {
     this.client = client; // Assegna la socket del client
     // this: si riferisce all'istanza di se stesso()
 }

 @Override
 public void run() {
     try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          PrintWriter out = new PrintWriter(client.getOutputStream(), true)) { 
    	 
    	 // Imposta i flussi di input e output
         String inputFromClient;
         
         // Legge l'input dal client
         while ((inputFromClient = in.readLine()) != null) { 

        	// Se il client invia "FINE-INVIO", esce dal ciclo
             if (inputFromClient.trim().equalsIgnoreCase("FINE-INVIO")) {
                 break;
             }

             System.out.println(Thread.currentThread().getName() + " received: " + inputFromClient); // Stampa l'input ricevuto sulla console del server

             if (isValidGuess(inputFromClient)) {
                 String response = (random.nextBoolean()) ? "Hit!" : "Missed, try again!"; // Genera una risposta casuale
                out.println(response); // Invia la risposta al client
                
             } else {
                 out.println(" "); // Invia uno spazio vuoto per input non valido
             }
         }

         client.close(); // Chiude la connessione con il client
         System.out.println("Connection closed");

     } catch (IOException e) {
         e.printStackTrace(); //stampa l'errore e tutto quello che è successo 
     }
 }
 // Valida il formato dell'input: una lettera (A-Z) seguita da uno spazio e un numero (1-9)
 private boolean isValidGuess(String input) {
     return input.matches("^[A-Z] [1-9]$");
 }
}
