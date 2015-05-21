package beerpong;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public TextField gameName1, gameName2, gameName3, p1_1v1, p2_1v1, t1_2v2, t2_2v2, t1p1_2v2, t1p2_2v2, t2p1_2v2, t2p2_2v2, t1_3v3, t2_3v3, t1p1_3v3, t1p2_3v3, t1p3_3v3, t2p1_3v3, t2p2_3v3, t2p3_3v3;
    public ChoiceBox balls_1v1, balls_2v2, balls_3v3, cups_1v1, cups_2v2, cups_3v3;


    public void startGame(ActionEvent event) throws IOException {

        //Code to create teams
        if (event.getSource() == start1v1){
            if (p1_1v1.getText().equals("") || p2_1v1.getText().equals("")){
                showAlert();
            } else {
                if (gameName1.getText().equals("")){
                    game.setGameName("Beer Pong");
                } else {
                    game.setGameName(gameName1.getText());
                }
                int balls = 1;
                switch (balls_1v1.getSelectionModel().getSelectedIndex()){
                    case 1:
                        balls = 2;
                        break;
                    case 2:
                        balls = 3;
                        break;
                }
                int cups = 3;
                switch (cups_1v1.getSelectionModel().getSelectedIndex()){
                    case 1:
                        cups = 6;
                        break;
                    case 2:
                        cups = 10;
                        break;
                }
                game.setGameParameters(balls, cups);
                game.setTeam1(p1_1v1.getText(), new String[]{p1_1v1.getText()});
                game.setTeam2(p2_1v1.getText(), new String[]{p2_1v1.getText()});

                switchScene(event);
            }
        } else if (event.getSource() == start2v2) {
            if (t1_2v2.getText().equals("") || t2_2v2.getText().equals("") || t1p1_2v2.getText().equals("") || t1p2_2v2.getText().equals("") || t2p1_2v2.getText().equals("") || t2p2_2v2.getText().equals("")) {
                showAlert();
            } else {
                if (gameName2.getText().equals("")){
                    game.setGameName("Beer Pong");
                } else {
                    game.setGameName(gameName2.getText());
                }
                int balls = 2;
                switch (balls_2v2.getSelectionModel().getSelectedIndex()){
                    case 0:
                        balls = 1;
                        break;
                    case 2:
                        balls = 3;
                        break;
                }
                int cups = 6;
                switch (cups_2v2.getSelectionModel().getSelectedIndex()){
                    case 0:
                        cups = 3;
                        break;
                    case 2:
                        cups = 10;
                        break;
                }
                game.setGameParameters(balls, cups);

                game.setTeam1(t1_2v2.getText(), new String[]{t1p1_2v2.getText(), t1p2_2v2.getText()});
                game.setTeam2(t2_2v2.getText(), new String[]{t2p1_2v2.getText(), t2p2_2v2.getText()});

                switchScene(event);
            }
        } else if (event.getSource() == start3v3) {
            if (t1_3v3.getText().equals("") || t2_3v3.getText().equals("") || t1p1_3v3.getText().equals("") || t1p2_3v3.getText().equals("") || t1p3_3v3.getText().equals("") || t2p1_3v3.getText().equals("") || t2p2_3v3.getText().equals("") || t2p3_3v3.getText().equals("")) {
                showAlert();
            } else {
                if (gameName3.getText().equals("")){
                    game.setGameName("Beer Pong");
                } else {
                    game.setGameName(gameName3.getText());
                }
                int balls = 3;
                switch (balls_3v3.getSelectionModel().getSelectedIndex()){
                    case 1:
                        balls = 2;
                        break;
                    case 0:
                        balls = 1;
                        break;
                }
                int cups = 10;
                switch (cups_3v3.getSelectionModel().getSelectedIndex()){
                    case 1:
                        cups = 6;
                        break;
                    case 0:
                        cups = 3;
                        break;
                }
                game.setGameParameters(balls, cups);

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

        balls_1v1.getSelectionModel().select(0);
        balls_2v2.getSelectionModel().select(1);
        balls_3v3.getSelectionModel().select(2);
        cups_1v1.getSelectionModel().select(0);
        cups_2v2.getSelectionModel().select(1);
        cups_3v3.getSelectionModel().select(2);
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
        balls_1v1.getItems().addAll("1 ball/player", "2 balls/player", "3 balls/player");
        balls_1v1.getSelectionModel().select(0);
        balls_2v2.getItems().addAll("1 ball/team", "2 balls/team", "3 balls/team");
        balls_2v2.getSelectionModel().select(1);
        balls_3v3.getItems().addAll("1 ball/team", "2 balls/team", "3 balls/team");
        balls_3v3.getSelectionModel().select(2);

        cups_1v1.getItems().addAll("3 cups/player", "6 cups/player", "10 cups/player");
        cups_1v1.getSelectionModel().select(0);
        cups_2v2.getItems().addAll("3 cups/team", "6 cups/team", "10 cups/team");
        cups_2v2.getSelectionModel().select(1);
        cups_3v3.getItems().addAll("3 cups/team", "6 cups/team", "10 cups/team");
        cups_3v3.getSelectionModel().select(2);

        syncTextFields();
        game = Game.getInstance();
    }
}
