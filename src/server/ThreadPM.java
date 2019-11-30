package server;

import java.net.Socket;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrian
 */
public class ThreadPM extends Thread {
    
    private final String userSend;
    private final Socket socketDest;
    private String message;
    private OutputStreamWriter strOut;
    private BufferedWriter buffer;
    private PrintWriter out;
    
    public ThreadPM(String userSend, Socket socketDest, String message) {
        this.userSend = userSend;
        this.socketDest = socketDest;
        this.message = message;
    }
    
    @Override
    public void run() {
        try {
            strOut = new OutputStreamWriter(socketDest.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new BufferedWriter(strOut);
        out = new PrintWriter(buffer, true);
        out.println(userSend + " send a pm: " + message);
    }
    
}
