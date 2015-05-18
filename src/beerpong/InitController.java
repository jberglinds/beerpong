package beerpong;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jonathan on 2015-05-18.
 */
public class InitController implements Initializable{

    public Game game;

    public TabPane init;
    public javafx.scene.control.TextField gameName1, gameName2, gameName3, p1_1v1, p2_1v1, t1_2v2, t2_2v2, t1p1_2v2, t1p2_2v2, t2p1_2v2, t2p2_2v2, t1_3v3, t2_3v3, t1p1_3v3, t1p2_3v3, t1p3_3v3, t2p1_3v3, t2p2_3v3, t2p3_3v3;

    public void startGame(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        //TODO: Code to create teams and update graphics.
        game.setGameName(gameName1.getText());

        Parent initGame = FXMLLoader.load(getClass().getResource("gameView.fxml"));

        window.setScene(new Scene(initGame, 800, 400));

    }

    public void syncTextFields(){
        // Sync gamename
        gameName1.textProperty().bindBidirectional(gameName2.textProperty());
        gameName2.textProperty().bindBidirectional(gameName3.textProperty());

        // Sync teamnames
        t1_2v2.textProperty().bindBidirectional(t1_3v3.textProperty());
        t2_2v2.textProperty().bindBidirectional(t2_3v3.textProperty());

        // Sync playernames
        p1_1v1.textProperty().bindBidirectional(t1p1_2v2.textProperty());
        p1_1v1.textProperty().bindBidirectional(t1p1_3v3.textProperty());
        p2_1v1.textProperty().bindBidirectional(t2p1_2v2.textProperty());
        p2_1v1.textProperty().bindBidirectional(t2p1_3v3.textProperty());
        t1p2_2v2.textProperty().bindBidirectional(t1p2_3v3.textProperty());
        t2p2_2v2.textProperty().bindBidirectional(t2p2_3v3.textProperty());

        init.requestFocus(); // Sets the focus away from first textfield so that the user can see the placeholder text.
    }

    public void reset(ActionEvent actionEvent) {
        gameName1.setText("");
        t1_2v2.setText("");
        t2_2v2.setText("");
        t1p1_3v3.setText("");
        t1p2_3v3.setText("");
        t1p3_3v3.setText("");
        t2p1_3v3.setText("");
        t2p2_3v3.setText("");
        t2p3_3v3.setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        syncTextFields();
        game = Game.getInstance();
    }
}
