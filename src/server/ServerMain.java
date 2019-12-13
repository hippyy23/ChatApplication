package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Muteanu Adrian
 */
public class ServerMain {

    static final int PORT = 5000;
    static HashMap<Socket, String> users = new HashMap<>();
    static ArrayList<Socket> clientThreads = new ArrayList<>();
    static ArrayList<String> chat = new ArrayList<>();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        ServerSocket chatSock = new ServerSocket(PORT);
        System.out.println("EchoServer: avviato ");
        new ThreadWriter().start();

        while (true) {
            new ThreadReader(chatSock.accept()).start();
        }
    }

}
