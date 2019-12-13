package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muteanu Adrian
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
        String[] pm;
        String userName;
        boolean sameUser = false;
        boolean pmEx = false;

        try {

            strOut = new OutputStreamWriter(socket.getOutputStream());
            buffer = new BufferedWriter(strOut);
            out = new PrintWriter(buffer, true);
            out.println("-------------\n"
                    + "1] Insert your nickname\n"
                    + "2] Continue with your ip address\n"
                    + "-------------\n"
                    + "Insert choice: ");

            InputStreamReader strInput = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(strInput);

            String choice = in.readLine();

            switch (choice) {
                case "1":
                    while (true) {
                        out.println("-------------\n"
                                + "Insert your nickname: ");
                        userName = in.readLine();
                        for (String user : ServerMain.users.values()) {
                            if (!userName.equals(user)) {
                                sameUser = false;
                            } else if (userName.equals(user)) {
                                sameUser = true;
                                break;
                            }
                        }
                        if (sameUser != false) {
                            out.println("Username already taken");
                        } else {

                            out.println("-------------\n"
                                    + "Nickname set!"
                                    + "\nNow you can start chatting!\n"
                                    + "Type /list to view the list of all users\n"
                                    + "-------------");
                            break;
                        }
                    }
                    break;
                case "2":
                    out.println("-------------\n"
                            + "IP address set as nickname!"
                            + "\nNow you can start chatting!\n"
                            + "Type /list to view the list of all users\n"
                            + "-------------");
                    userName = socket.getInetAddress().toString().replace("/", "");
                    break;
                default:
                    out.println("-------------\n"
                            + "An error occured: IP address set as nikname!"
                            + "\nNow you can start chatting!\n"
                            + "Type /list to view the list of all users\n"
                            + "-------------");
                    userName = socket.getInetAddress().toString().replace("/", "");
                    break;
            }

            ServerMain.users.put(socket, userName);

            while (true) {
                String str = in.readLine();
                if (!str.isEmpty()) {
                    if (str.equals("/quit")) {
                        System.out.println(socket.getInetAddress() + " left");
                        ServerMain.clientThreads.remove(socket);
                    } else if (str.equals("/list")) {
                        out.println("-------------\n"
                                + "User list:");
                        ServerMain.users.values().forEach((user) -> {
                            out.println(user);
                        });
                        out.println("-------------");
                    } else if (str.contains("/pm")) {
                        pm = str.split(" ", 3);
                        for (HashMap.Entry<Socket, String> user : ServerMain.users.entrySet()) {
                            try {
                                if (pm[1].equals(user.getValue())) {
                                    pmEx = true;

                                    try {
                                        new SendPM(userName, user.getKey(), pm[2]).send();
                                    } catch (ArrayIndexOutOfBoundsException ex) {
                                        out.println("Error private message: missing message!");
                                        // creare una chat privata tra i due user /reply per il dest
                                        // new SendPM(userName, user.getKey()).start();
                                    }

                                }
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                out.println("Error private message: missing user!");
                            }
                        }
                        if (!pmEx) {
                            out.println("Username does not exists!");
                        }
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
