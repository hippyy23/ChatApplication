package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muteanu Adrian
 */
public class ThreadClient extends Thread {

    private Socket socket = null;
    private InputStreamReader isr;
    private BufferedReader in;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("started");
        try {

            isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);

            while (true) {
                try {
                System.out.println(in.readLine());
                } catch (SocketException ex) {
                    System.out.println("Connection lost!");
                    break;
                }
            }

        } catch (IOException ex) {
            try {
                isr.close();
                in.close();
            } catch (IOException ex1) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
