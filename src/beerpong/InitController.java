package beerpong;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    public Button start1v1, start2v2, start3v3;
    public javafx.scene.control.TextField gameName1, gameName2, gameName3, p1_1v1, p2_1v1, t1_2v2, t2_2v2, t1p1_2v2, t1p2_2v2, t2p1_2v2, t2p2_2v2, t1_3v3, t2_3v3, t1p1_3v3, t1p2_3v3, t1p3_3v3, t2p1_3v3, t2p2_3v3, t2p3_3v3;

    public void startGame(ActionEvent event) throws IOException {

        //Code to create teams and update graphics.
        if (event.getSource() == start1v1){
            if (gameName1.getText().equals("") || p1_1v1.getText().equals("") || p2_1v1.getText().equals("")){
                showAlert();
            } else {
                game.setGameName(gameName1.getText());
                game.setTeam1(p1_1v1.getText(), new String[]{p1_1v1.getText()});
                game.setTeam2(p2_1v1.getText(), new String[]{p2_1v1.getText()});

                switchScene(event);
            }
        } else if (event.getSource() == start2v2) {
            if (gameName2.getText().equals("") || t1_2v2.getText().equals("") || t2_2v2.getText().equals("") || t1p1_2v2.getText().equals("") || t1p2_2v2.getText().equals("") || t2p1_2v2.getText().equals("") || t2p2_2v2.getText().equals("")) {
                showAlert();
            } else {
                game.setGameName(gameName2.getText());
                game.setTeam1(t1_2v2.getText(), new String[]{t1p1_2v2.getText(), t1p2_2v2.getText()});
                game.setTeam2(t2_2v2.getText(), new String[]{t2p1_2v2.getText(), t2p2_2v2.getText()});

                switchScene(event);
            }
        } else if (event.getSource() == start3v3) {
            if (gameName3.getText().equals("") || t1_3v3.getText().equals("") || t2_3v3.getText().equals("") || t1p1_3v3.getText().equals("") || t1p2_3v3.getText().equals("") || t1p3_3v3.getText().equals("") || t2p1_3v3.getText().equals("") || t2p2_3v3.getText().equals("") || t2p3_3v3.getText().equals("")) {
                showAlert();
            } else {
                game.setGameName(gameName3.getText());
                game.setTeam1(t1_3v3.getText(), new String[]{t1p1_3v3.getText(), t1p2_3v3.getText(), t1p3_3v3.getText()});
                game.setTeam2(t2_3v3.getText(), new String[]{t2p1_3v3.getText(), t2p2_3v3.getText(), t2p3_3v3.getText()});

                switchScene(event);
            }
        }
    }

    private void switchScene(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
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

    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Empty fields");
        alert.setHeaderText("Empty fields!");
        alert.setContentText("Please fill in all the fields to start game!");

        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        syncTextFields();
        game = Game.getInstance();
    }
}
