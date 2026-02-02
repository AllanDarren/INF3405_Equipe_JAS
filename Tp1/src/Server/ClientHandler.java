package Server;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Cette classe hérite de Thread pour traiter chaque client de manière indépendante.
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        System.out.println("New connection with client#" + clientNumber + " at " + socket);
    }

    @Override
    public void run() {
        try {
            // Création du canal d'envoi vers le client
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Envoi du message de bienvenue (format UTF)
            out.writeUTF("Hello from server - you are client#" + clientNumber);

        } catch (IOException e) {
            System.err.println("Error handling client#" + clientNumber + ": " + e.getMessage());
        } finally {
            try {
                // Fermeture propre du socket pour libérer les ressources
                socket.close();
            } catch (IOException e) {
                System.err.println("Couldn't close a socket, what's going on?");
            }
            System.out.println("Connection with client# " + clientNumber + " closed");
        }
    }
}