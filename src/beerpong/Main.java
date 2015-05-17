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

        URL location = getClass().getResource("initGame.fxml");
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root, 800, 400);

        stage.setScene(scene);
        stage.show();

        Controller controller = fxmlLoader.getController();

        Parent initGame = fxmlLoader.load(getClass().getResource("gameView.fxml"));
        controller.setNextScene(new Scene(initGame, 800, 400));
        controller.syncTextFields();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
