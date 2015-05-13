package beerpong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
        primaryStage.setTitle("Beer Pong LIVE");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
