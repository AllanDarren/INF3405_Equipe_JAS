package Server;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class Server {
    private static ServerSocket listener;

    // Application Serveur
    public static void main(String[] args) throws Exception {
        // Compteur incrémenté à chaque connexion d'un client au serveur
        int clientNumber = 0;

        // Adresse et port du serveur
        String serverAddress = "127.0.0.1";
        int serverPort = 5000;

        // Création de la connexion pour communiquer avec les clients
        listener = new ServerSocket();
        listener.setReuseAddress(true);

        InetAddress serverIP = InetAddress.getByName(serverAddress);

        // Association de l'adresse et du port à la connexion
        listener.bind(new InetSocketAddress(serverIP, serverPort));
        System.out.format("The server is running on %s:%d%n", serverAddress, serverPort);

        try {
            while (true) {
                // Important : la fonction accept() est bloquante
                // On attend qu'un prochain client se connecte
                new ClientHandler(listener.accept(), clientNumber++).start();
            }
        } finally {
            // Fermeture de la connexion
            if (listener != null && !listener.isClosed()) {
                listener.close();
            }
        }
    }
}