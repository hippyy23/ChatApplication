package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Adrian
 */
public class MainController extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Chat");
        stage.getIcons().add(new Image("file:mainico.png"));
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
