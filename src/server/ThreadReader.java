package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 17913
 */
public class ThreadReader extends Thread {

    private Socket socket = null;
    private OutputStreamWriter strOut;
    private BufferedWriter buffer;
    private PrintWriter out;

    public ThreadReader(Socket socket) {
        this.socket = socket;
        ServerMain.clientThreads.add(socket);
    }

    @Override
    public void run() {
        System.out.println(socket.getInetAddress() + " joined");
        String userName;

        try {

            strOut = new OutputStreamWriter(socket.getOutputStream());
            buffer = new BufferedWriter(strOut);
            out = new PrintWriter(buffer, true);
            out.println("1] Insert your nickname\n"
                    + "2] Continue with your ip address\n"
                    + "Insert choice: ");

            InputStreamReader strInput = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(strInput);

            String choice = in.readLine();

            switch (choice) {
                case "1":
                    out.println("Insert your nickname: ");
                    userName = in.readLine();
                    out.println("Nickname set!"
                            + "\nNow you can start chatting!");
                    break;
                case "2":
                    out.println("IP address set as nickname!"
                            + "\nNow you can start chatting!");
                    userName = socket.getInetAddress().toString();
                    break;
                default:
                    out.println("An error occured: IP address set as nikname!"
                            + "\nNow you can start chatting!");
                    userName = socket.getInetAddress().toString();
                    break;
            }

            while (true) {
                String str = in.readLine();
                if (!str.isEmpty()) {
                    if (str.equals("/quit")) {
                        System.out.println(socket.getInetAddress() + " left");
                        ServerMain.clientThreads.remove(socket);
                    } else if (str.equals("/list")) {
                        ServerMain.clientThreads.forEach((clients) -> {
                            out.println(clients.getInetAddress());
                        });
                    } else if (!str.contains("/")) {
                        synchronized (ServerMain.chat) {
                            ServerMain.chat.add(userName + ": " + str);
                        }
                    }
                }
            }
        } catch (NullPointerException ex) {
            System.out.println(socket.getInetAddress() + " disconnected");
            ServerMain.clientThreads.remove(socket);
            try {
                socket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ThreadReader.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            System.out.println(socket.getInetAddress() + " disconnected");
            ServerMain.clientThreads.remove(socket);
        }
    }
}
