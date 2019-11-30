package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author 17913
 */
public class ThreadWriter extends Thread {

    private int size;
    private OutputStreamWriter strOut;
    private BufferedWriter buffer;
    private PrintWriter out;

    public ThreadWriter() {
        this.size = ServerMain.chat.size();
    }

    @Override
    public void run() {
        System.out.println("started");

        while (true) {
            synchronized (ServerMain.chat) {
                if (ServerMain.chat.size() > size) {
                    for (int i = 0; i < ServerMain.clientThreads.size(); i++) {
                        try {
                            strOut = new OutputStreamWriter(ServerMain.clientThreads.get(i).getOutputStream());
                        } catch (IOException ex) {
                            System.out.print("something went wrong");
                        }
                        buffer = new BufferedWriter(strOut);
                        out = new PrintWriter(buffer, true);
                        out.println(ServerMain.chat.get(ServerMain.chat.size() - 1));

                    }
                    incSize();
                }
            }
        }
    }

    public void incSize() {
        size++;
    }

}
