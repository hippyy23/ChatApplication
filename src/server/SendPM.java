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
public class SendPM extends Thread {
    
    private final String userSend;
    private final Socket socketMit;
    private final Socket socketDest;
    private String message;
    private OutputStreamWriter strOut;
    private OutputStreamWriter strIn;
    private BufferedWriter buffer;
    private PrintWriter out;
    
    public SendPM(String userSend, Socket socketDest, String message) {
        this.userSend = userSend;
        this.socketDest = socketDest;
        this.message = message;
    }
    
    public SendPM(String userSend, Socket socketMit, Socket socketDest) {
        this.userSend = userSend;
        this.socketMit = socketMit;
        this.socketDest = socketDest;
    }

    public void send() {
        try {
            strOut = new OutputStreamWriter(socketDest.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new BufferedWriter(strOut);
        out = new PrintWriter(buffer, true);
        out.println(userSend + " sent a pm: " + message);
    }
    
    @Override
    public void run() {
        try {
            strIn = new OutputStreamWriter(socketMit.getOutputStream());
            strOut = new OutputStreamWriter(socketDest.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendPM.class.getName()).log(Level.SEVERE, null, ex);
        }
        buffer = new BufferedWriter(strOut);
        out = new PrintWriter(buffer, true);
        // creare la chat con il dest (input stream)
    }
    
}
