package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author Adrian
 */
public class LoginController {
    
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea ipAddress;

    @FXML
    private TextArea portAddress;

    @FXML
    private Button connectToServer;

    @FXML
    private Button connectToLocalhost;

    @FXML
    void initialize() {
        assert ipAddress != null : "fx:id=\"ipAddress\" was not injected: check your FXML file 'Login.fxml'.";
        assert portAddress != null : "fx:id=\"portAddress\" was not injected: check your FXML file 'Login.fxml'.";
        assert connectToServer != null : "fx:id=\"connectToServer\" was not injected: check your FXML file 'Login.fxml'.";
        assert connectToLocalhost != null : "fx:id=\"connectToLocalhost\" was not injected: check your FXML file 'Login.fxml'.";

    }
}
