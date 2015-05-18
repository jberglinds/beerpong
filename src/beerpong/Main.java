package beerpong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(final Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();

        stage.setTitle("Beer Pong!");

        URL location = getClass().getResource("initGame.fxml");
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root, 800, 400);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
