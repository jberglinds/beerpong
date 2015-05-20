package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Game game;

    public AnchorPane bg;
    public Label gameName;
    public Label team1score, team2score;
    public Circle oneRight, twoRight, threeRight, fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight;
    public Circle oneLeft, twoLeft, threeLeft, fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft;
    public Label team1name, team2name;
    public Label player11, player12, player13;
    public Label player21, player22, player23;

    public ScrollPane scrollPane;
    public TextFlow eventFlow;

    private boolean bounce;

    public Controller() {
    }


    public void strobe(Event event) {
        FadeTransition ft = new FadeTransition(Duration.millis(10), bg);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(100);
        ft.setAutoReverse(true);
        ft.play();
    }

    /**
     * Changes the state of boolean bounce.
     * @param event a click on the radio button
     */
    public void bounceAdded(Event event) {
        bounce = !bounce;
    }

    /**
     * If a miss is registered on the graphic board a call will be made to
     * the current team representing a miss. If hitCup() returns false the
     * team has used all their throws and it will be the other teams turn.
     * @param event a click on the miss button.
     */
    public void miss(Event event) {
        game.miss(bounce);
        updateEventWall();
    }


    /**
     * If a hit is registered on the graphic board a call will be made to
     * the current team representing a hit. If hitCup() returns false the
     * team has used all their throws and it will be the other teams turn.
     * Only the enemies cups are available for play at any time.
     * @param event A click on any cup in play at that particular time.
     */
    public void hit(Event event) {
        if (event.getSource() == oneLeft) {
            game.hit(0, bounce, "Left");
        } else if (event.getSource() == twoLeft) {
            game.hit(1, bounce, "Left");
        } else if (event.getSource() == threeLeft) {
            game.hit(2, bounce, "Left");
        } else if (event.getSource() == fourLeft) {
            game.hit(3, bounce, "Left");
        } else if (event.getSource() == fiveLeft) {
            game.hit(4, bounce, "Left");
        } else if (event.getSource() == sixLeft) {
            game.hit(5, bounce, "Left");
        } else if (event.getSource() == sevenLeft) {
            game.hit(6, bounce, "Left");
        } else if (event.getSource() == eightLeft) {
            game.hit(7, bounce, "Left");
        } else if (event.getSource() == nineLeft) {
            game.hit(8, bounce, "Left");
        } else if (event.getSource() == tenLeft) {
            game.hit(9, bounce, "Left");
        } else if (event.getSource() == oneRight) {
            game.hit(0, bounce, "Right");
        } else if (event.getSource() == twoRight) {
            game.hit(1, bounce, "Right");
        } else if (event.getSource() == threeRight) {
            game.hit(2, bounce, "Right");
        } else if (event.getSource() == fourRight) {
            game.hit(3, bounce, "Right");
        } else if (event.getSource() == fiveRight) {
            game.hit(4, bounce, "Right");
        } else if (event.getSource() == sixRight) {
            game.hit(5, bounce, "Right");
        } else if (event.getSource() == sevenRight) {
            game.hit(6, bounce, "Right");
        } else if (event.getSource() == eightRight) {
            game.hit(7, bounce, "Right");
        } else if (event.getSource() == nineRight) {
            game.hit(8, bounce, "Right");
        } else if (event.getSource() == tenRight) {
            game.hit(9, bounce, "Right");
        }

        updateScores();
        updateEventWall();
    }

    public void showPlayerStatistics(Event event) throws IOException {
        Player player;
        if (event.getSource() == player11){
            player = game.getTeams()[0].getPlayers()[0];
        } else if (event.getSource() == player12) {
            player = game.getTeams()[0].getPlayers()[1];
        } else if (event.getSource() == player13) {
            player = game.getTeams()[0].getPlayers()[2];
        } else if (event.getSource() == player21) {
            player = game.getTeams()[1].getPlayers()[0];
        } else if (event.getSource() == player22) {
            player = game.getTeams()[1].getPlayers()[1];
        } else {
            player = game.getTeams()[1].getPlayers()[2];
        }

        Stage popupStage = new Stage();
        popupStage.setTitle("Player Statistics");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "playerStatistics.fxml"));

        Parent popup = loader.load();
        popupStage.setScene(new Scene(popup, 400, 250));

        StatisticsController controller = loader.getController();
        System.out.println(controller);
        controller.updateLabels(player.getName(), player.getNoOfThrows(), player.getNoOfBounceAttempts(), player.getHitRatio(), player.getBounceHitRatio());

        popupStage.show();
    }

    private void updateScores(){
        int[] scores = game.getScores();
        team1score.setText(Integer.toString(scores[0]));
        team2score.setText(Integer.toString(scores[1]));
    }

    /**
     * Retrieves all available undisplayed messages from the eventlogger and displays them.
     */
    private void updateEventWall(){
        EventLogger events = game.getEventLogger();
        while (events.containsMessages()){
            eventFlow.getChildren().add(0, events.getMessage());
        }
    }


    /**
     * Update view from variables set to game from initController.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();

        gameName.setText(game.getGameName());

        Team[] teams = game.getTeams();
        team1name.setText(teams[0].getTeamName());
        team2name.setText(teams[1].getTeamName());

        if (teams[0].getPlayers().length == 2){
            Player[] players = teams[0].getPlayers();
            player11.setText(players[0].getName());
            player12.setText(players[1].getName());

            players = teams[1].getPlayers();
            player21.setText(players[0].getName());
            player22.setText(players[1].getName());
        } else if (teams[0].getPlayers().length == 3){
            Player[] players = teams[0].getPlayers();
            player11.setText(players[0].getName());
            player12.setText(players[1].getName());
            player13.setText(players[2].getName());

            players = teams[1].getPlayers();
            player21.setText(players[0].getName());
            player22.setText(players[1].getName());
            player23.setText(players[2].getName());
        }

        setupCups(teams[0].getPlayers().length);

    }

    /**
     * Hides the cups that wont be used in this game.
     * @param players
     */
    private void setupCups(int players){
        if (players == 1){
            Group group1 = new Group();
            group1.getChildren().addAll(fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft);
            group1.setVisible(false);

            Group group2 = new Group();
            group1.getChildren().addAll(fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight);
            group2.setVisible(false);
        } else if (players == 2){
            Group group1 = new Group();
            group1.getChildren().addAll(sevenLeft, eightLeft, nineLeft, tenLeft);
            group1.setVisible(false);

            Group group2 = new Group();
            group1.getChildren().addAll(sevenRight, eightRight, nineRight, tenRight);
            group2.setVisible(false);
        }
    }

}
