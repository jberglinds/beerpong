package beerpong;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        final Stage secondaryStage = new Stage();
        VBox vbox = new VBox();
        Button b = new Button("Done");
        final ChoiceBox choice = new ChoiceBox();
        choice.getItems().addAll("1v1", "2v2", "3v3");

        b.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String s = choice.getValue().toString();
                        System.out.println(s);
                        secondaryStage.close();
                    }
                });

        Text t = new Text("Choose the type of game you are playing");
        t.setTranslateX(80);
        t.setTranslateY(-40);
        t.setScaleX(1.3);
        t.setScaleY(1.3);
        b.setTranslateX(171);
        b.setTranslateY(100);

        choice.setTranslateX(165);
        choice.setTranslateY(50);
        vbox.getChildren().addAll(choice, b, t);
        secondaryStage.setScene(new Scene(vbox, 400, 200));
        secondaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
