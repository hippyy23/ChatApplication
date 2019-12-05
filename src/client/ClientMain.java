package client;

/**
 *
 * @author 17913
 */
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        String conn;
        String indirizzo = "localhost";
        int port = 5000;
        String userInput;
        String[] splitConn;
        Socket socket;

        while (true) {

            while (true) {
                synchronized (System.out) {
                    System.out.println("Type /connect {[IP] [PORT]} to connect to a server"
                            + "\nType /quit to exit from the application");
                }

                conn = input.nextLine().toLowerCase();
                splitConn = conn.split("\\s+");

                if (conn.contains("/connect")) {
                    try {
                        indirizzo = splitConn[1];
                        port = Integer.parseInt(splitConn[2]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Connecting to localhost...");
                    }
                    break;
                } else if (conn.equals("/quit")) {
                    System.out.println("Quiting...");
                    System.exit(0);
                }

            }

            try {

                socket = new Socket(indirizzo, port);
                System.out.println("EchoClient: avviato");
                System.out.println("Socket del client: " + socket);

                new ThreadClient(socket).start();

                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
                BufferedWriter bw = new BufferedWriter(osw);
                PrintWriter out = new PrintWriter(bw, true);

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    userInput = stdIn.readLine();
                    if (userInput.equals("/quit")) {
                        socket.close();
                        out.close();
                        bw.close();
                        osw.close();
                        System.out.println("Disconnected from server");
                        break;
                    }
                    out.println(userInput);
                }

            } catch (UnknownHostException e) {
                synchronized (System.out) {
                    System.out.println("Host non riconosciuto... " + indirizzo);
                    input.nextLine();
                }
            } catch (IOException e) {
                synchronized (System.out) {
                    System.out.println("Non riesco ad avere I/O per la connessione a: " + indirizzo);
                    input.nextLine();
                }
            }
        }
    }
}
