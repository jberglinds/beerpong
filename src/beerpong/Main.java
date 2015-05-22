package beerpong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
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
        Image ico = new Image("file:resources/images/icon.png");
        stage.getIcons().add(ico);
        stage.show();
        setIcon();

    }

    public static void main(String[] args) {
        setIcon();
        launch(args);
    }

    public static void setIcon()
    {
            com.apple.eawt.Application.getApplication().setDockIconImage(new ImageIcon("resources/images/icon.png").getImage());

    }


}
