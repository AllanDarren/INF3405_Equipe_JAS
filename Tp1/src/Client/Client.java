package Client;
import java.io.DataInputStream;
import java.net.Socket;

/**
 * Application Client pour se connecter au serveur de test.
 */
public class Client {
    private static Socket socket;

    public static void main(String[] args) throws Exception {
        // Adresse et port du serveur
        String serverAddress = "127.0.0.1";
        int port = 5000;

        // Création d'une nouvelle connexion avec le serveur
        socket = new Socket(serverAddress, port);
        System.out.format("Connecté au serveur sur [%s:%d]%n", serverAddress, port);

        try {
            // Création d'un canal entrant pour recevoir les messages envoyés par le serveur
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Attente de la réception d'un message envoyé par le serveur sur le canal
            // Note: Le serveur doit utiliser out.writeUTF("...") pour que cela fonctionne
            String helloMessageFromServer = in.readUTF();
            System.out.println("Message reçu du serveur : " + helloMessageFromServer);

        } finally {
            // Fermeture de la connexion avec le serveur dans un bloc finally pour plus de sécurité
            socket.close();
            System.out.println("Connexion fermée.");
        }
    }
}