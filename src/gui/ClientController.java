package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Adrian
 */
public class ClientController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPaneUsers;

    @FXML
    private VBox vBoxUsers;

    @FXML
    private TextArea textArea;

    @FXML
    private ImageView sendMessage;

    @FXML
    private ScrollPane scrollPaneChat;

    @FXML
    private VBox vBoxChat;

    @FXML
    void sendMessage(MouseEvent event) {

    }
    
    static void writeMessage(String message) {
        
    }

    @FXML
    void initialize() {
        assert scrollPaneUsers != null : "fx:id=\"scrollPaneUsers\" was not injected: check your FXML file 'Client.fxml'.";
        assert vBoxUsers != null : "fx:id=\"vBoxUsers\" was not injected: check your FXML file 'Client.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'Client.fxml'.";
        assert sendMessage != null : "fx:id=\"sendMessage\" was not injected: check your FXML file 'Client.fxml'.";
        assert scrollPaneChat != null : "fx:id=\"scrollPaneChat\" was not injected: check your FXML file 'Client.fxml'.";
        assert vBoxChat != null : "fx:id=\"vBoxChat\" was not injected: check your FXML file 'Client.fxml'.";

    }
}
