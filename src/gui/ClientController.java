package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private TextArea textArea;

    @FXML
    private ImageView sendMessage;

    @FXML
    private TextField messageArea;

    @FXML
    void sendMessage(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'Client.fxml'.";
        assert sendMessage != null : "fx:id=\"sendMessage\" was not injected: check your FXML file 'Client.fxml'.";
        assert messageArea != null : "fx:id=\"messageArea\" was not injected: check your FXML file 'Client.fxml'.";

    }
}
