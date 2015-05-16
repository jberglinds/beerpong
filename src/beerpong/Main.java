package beerpong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
        primaryStage.setTitle("Beer Pong LIVE");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();

        Parent initGame = FXMLLoader.load(getClass().getResource("initGame.fxml"));
        final Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(initGame, 800, 400));
        secondaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
