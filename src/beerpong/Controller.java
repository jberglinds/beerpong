package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public Game game;

    public AnchorPane bg;
    public Label gameName;
    public Label team1score, team2score;

    public Circle bounceButton;

    public GridPane leftCups, rightCups;
    public Circle oneRight, twoRight, threeRight, fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight;
    public Circle oneLeft, twoLeft, threeLeft, fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft;
    public Label team1name, team2name;
    public Label player11, player12, player13;
    public Label player21, player22, player23;

    public HBox player11box, player12box, player13box, player21box, player22box, player23box;
    public ToggleGroup team1players, team2players;
    public RadioButton p11, p12, p13, p21, p22, p23;

    public ScrollPane scrollPane;
    public TextFlow eventFlow;
    public Text statusfield;

    private boolean finishedGame;
    private boolean bounce;

    public Controller() {
        finishedGame = false;
    }


    private void strobe() {
        FadeTransition ft = new FadeTransition(Duration.millis(10), bg);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(100);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void easterEgg(Event event) {
        strobe();
    }

    /**
     * Changes the state of boolean bounce.
     * @param event a click on the radio button
     */
    public void bounceAdded(Event event) {
        bounce = !bounce;
        if (bounce){
            bounceButton.setFill(Paint.valueOf("black"));
        } else {
            bounceButton.setFill(Paint.valueOf("white"));
        }
    }

    private void resetBounce(){
        bounce = false;
        bounceButton.setFill(Paint.valueOf("white"));
    }

    /**
     * If a miss is registered on the graphic board a call will be made to
     * the current team representing a miss. If hitCup() returns false the
     * team has used all their throws and it will be the other teams turn.
     * @param event a click on the miss button.
     */
    public void miss(Event event) {

        if (!finishedGame) {

            int answerCode = game.miss(bounce);
            if (answerCode == -1) {
                int index = game.getCurrentTeamIndex();
                highlightTeam(index);
                if (index == 0) {
                    removeHits(1);
                } else {
                    removeHits(0);
                }
            }
            updateEventWall();
            resetBounce();
        }
    }


    /**
     * If a hit is registered on the graphic board a call will be made to
     * the current team representing a hit. If hitCup() returns false the
     * team has used all their throws and it will be the other teams turn.
     * Only the enemies cups are available for play at any time.
     * @param event A click on any cup in play at that particular time.
     */
    public void hit(Event event) {

        if (!finishedGame) {

            Circle c = (Circle) event.getSource();
            int answerCode = 0;

            if (c.getOpacity() == 0) {
                return;
            }

            if (c == oneLeft) {
                answerCode = game.hit(0, bounce, "Left");
            } else if (c == twoLeft) {
                answerCode = game.hit(1, bounce, "Left");
            } else if (c == threeLeft) {
                answerCode = game.hit(2, bounce, "Left");
            } else if (c == fourLeft) {
                answerCode = game.hit(3, bounce, "Left");
            } else if (c == fiveLeft) {
                answerCode = game.hit(4, bounce, "Left");
            } else if (c == sixLeft) {
                answerCode = game.hit(5, bounce, "Left");
            } else if (c == sevenLeft) {
                answerCode = game.hit(6, bounce, "Left");
            } else if (c == eightLeft) {
                answerCode = game.hit(7, bounce, "Left");
            } else if (c == nineLeft) {
                answerCode = game.hit(8, bounce, "Left");
            } else if (c == tenLeft) {
                answerCode = game.hit(9, bounce, "Left");
            } else if (c == oneRight) {
                answerCode = game.hit(0, bounce, "Right");
            } else if (c == twoRight) {
                answerCode = game.hit(1, bounce, "Right");
            } else if (c == threeRight) {
                answerCode = game.hit(2, bounce, "Right");
            } else if (c == fourRight) {
                answerCode = game.hit(3, bounce, "Right");
            } else if (c == fiveRight) {
                answerCode = game.hit(4, bounce, "Right");
            } else if (c == sixRight) {
                answerCode = game.hit(5, bounce, "Right");
            } else if (c == sevenRight) {
                answerCode = game.hit(6, bounce, "Right");
            } else if (c == eightRight) {
                answerCode = game.hit(7, bounce, "Right");
            } else if (c == nineRight) {
                answerCode = game.hit(8, bounce, "Right");
            } else if (c == tenRight) {
                answerCode = game.hit(9, bounce, "Right");
            }

            if (answerCode == 1) {
                c.setOpacity(0.5);
            } else if (answerCode == -1) {
                c.setOpacity(0.5);
                int index = game.getCurrentTeamIndex();
                highlightTeam(index);
                if (index == 0) {
                    removeHits(1);
                } else {
                    removeHits(0);
                }
            }
            updateScores();
            updateEventWall();
            resetBounce();
        }
    }

    /**
     * Sets the hit enemyCups of a team to opacity 0.
     * @param teamIndex the team that was playing
     */
    private void removeHits(int teamIndex) {
        ArrayList<Integer> hits = game.getCupsHit(teamIndex);

        if (teamIndex == 0) {
            for (Integer n : hits) {
                if (n == 0) {
                    oneRight.setOpacity(0);
                } else if (n == 1) {
                    twoRight.setOpacity(0);
                } else if (n == 2) {
                    threeRight.setOpacity(0);
                } else if (n == 3) {
                    fourRight.setOpacity(0);
                } else if (n == 4) {
                    fiveRight.setOpacity(0);
                } else if (n == 5) {
                    sixRight.setOpacity(0);
                } else if (n == 6) {
                    sevenRight.setOpacity(0);
                } else if (n == 7) {
                    eightRight.setOpacity(0);
                } else if (n == 8) {
                    nineRight.setOpacity(0);
                } else if (n == 9) {
                    tenRight.setOpacity(0);
                }
            }
        } else if (teamIndex == 1){
            for (Integer n : hits) {
                if (n == 0) {
                    oneLeft.setOpacity(0);
                } else if (n == 1) {
                    twoLeft.setOpacity(0);
                } else if (n == 2) {
                    threeLeft.setOpacity(0);
                } else if (n == 3) {
                    fourLeft.setOpacity(0);
                } else if (n == 4) {
                    fiveLeft.setOpacity(0);
                } else if (n == 5) {
                    sixLeft.setOpacity(0);
                } else if (n == 6) {
                    sevenLeft.setOpacity(0);
                } else if (n == 7) {
                    eightLeft.setOpacity(0);
                } else if (n == 8) {
                    nineLeft.setOpacity(0);
                } else if (n == 9) {
                    tenLeft.setOpacity(0);
                }
            }
        }
    }

    /**
     * Highlights the team that is currently playing.
     * @param teamIndex the team to highlight
     */
    private void highlightTeam(int teamIndex) {
        if (teamIndex == 0) {
            team2name.setTextFill(Paint.valueOf("white"));
            team1name.setTextFill(Paint.valueOf("yellow"));

            for (Toggle t : team1players.getToggles()) {
                if (t instanceof RadioButton) {
                    ((RadioButton) t).setDisable(false);
                }
            }
            p11.setSelected(true);
            for (Toggle t : team2players.getToggles()) {
                if (t instanceof RadioButton) {
                    ((RadioButton) t).setDisable(true);
                }
            }

        } else {
            team1name.setTextFill(Paint.valueOf("white"));
            team2name.setTextFill(Paint.valueOf("yellow"));

            for (Toggle t : team1players.getToggles()) {
                if (t instanceof RadioButton) {
                    ((RadioButton) t).setDisable(true);
                }
            }
            p21.setSelected(true);
            for (Toggle t : team2players.getToggles()) {
                if (t instanceof RadioButton) {
                    ((RadioButton) t).setDisable(false);
                }
            }
        }
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
        } else if (event.getSource() == player23){
            player = game.getTeams()[1].getPlayers()[2];
        } else if (event.getSource() == team1name){
            if (game.getTeams()[0].getPlayers().length == 1){
                player = game.getTeams()[0].getPlayers()[0];
            } else {
                return;
            }
        } else if (event.getSource() == team2name){
            if (game.getTeams()[1].getPlayers().length == 1){
                player = game.getTeams()[1].getPlayers()[0];
            } else {
                return;
            }
        } else {
            player = game.getTeams()[0].getPlayers()[0];
        }

        Stage popupStage = new Stage();
        popupStage.setTitle("Player Statistics");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "playerStatistics.fxml"));

        Parent popup = loader.load();
        popupStage.setScene(new Scene(popup, 400, 250));

        StatisticsController controller = loader.getController();
        controller.updateLabels(player.getName(), player.getNoOfThrows(), player.getNoOfBounceAttempts(), player.getHitRatio(), player.getBounceHitRatio());

        popupStage.show();
    }

    private void updateScores(){
        int[] scores = game.getScores();
        if (game.getCupCount() == 3) {
            if (scores[0] >= 3) {
                gameName.setText(team1name.getText() + " won!");
                finishedGame = true;
            }
            if (scores[1] >= 3) {
                gameName.setText(team2name.getText() + " won!");
                finishedGame = true;
            }
        } else if (game.getCupCount() == 6) {
            if (scores[0] >= 6) {
                gameName.setText(team1name.getText() + " won!");
                finishedGame = true;
            }
            if (scores[1] >= 6) {
                gameName.setText(team2name.getText() + " won!");
                finishedGame = true;
            }
        } else {
            if (scores[0] >= 10) {
                gameName.setText(team1name.getText() + " won!");
                finishedGame = true;
            }
            if (scores[1] >= 10) {
                gameName.setText(team2name.getText() + " won!");
                finishedGame = true;
            }
        }
        if (finishedGame) {
            strobe();
            gameName.setFont(Font.font("System", 50));
            gameName.setTextFill(Paint.valueOf("red"));
        }
        team1score.setText(Integer.toString(scores[0]));
        team2score.setText(Integer.toString(scores[1]));
        com.apple.eawt.Application.getApplication().setDockIconBadge(Integer.toString(scores[0]) + " - " + Integer.toString(scores[1]));
    }

    /**
     * Retrieves all available undisplayed messages from the event logger and displays them.
     */
    private void updateEventWall(){
        EventLogger events = game.getEventLogger();

        String text = events.getStatusMessage();
        if (text.contains("...")) {
            statusfield.setFill(Paint.valueOf("red"));
        } else {
            statusfield.setFill(Paint.valueOf("blue"));
        }
        statusfield.setText(text);
        statusfield.setVisible(true);

        while (events.containsMessages()){
            eventFlow.getChildren().add(0, events.getMessage());
            eventFlow.getChildren().get(0).setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
            if (eventFlow.getChildren().size() > 1){
                eventFlow.getChildren().get(1).setStyle("-fx-font-size: 13px; -fx-font-weight: none");
            }
        }
    }

    public void setPlayerIndex(ActionEvent actionEvent) {
        if (actionEvent.getSource() == p11 || actionEvent.getSource() == p12 || actionEvent.getSource() ==p13){
            Toggle selectedToggle = team1players.getSelectedToggle();
            int selectedToggleIndex = team1players.getToggles().indexOf(selectedToggle);
            game.setPlayerIndex(selectedToggleIndex);
        } else {
            Toggle selectedToggle = team2players.getSelectedToggle();
            int selectedToggleIndex = team2players.getToggles().indexOf(selectedToggle);
            game.setPlayerIndex(selectedToggleIndex);
        }
    }

    /**
     * Update view from variables set to game from initController.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();

        gameName.setText(game.getGameName());
        statusfield.setVisible(false);

        Team[] teams = game.getTeams();
        team1name.setText(teams[0].getTeamName());
        team1name.setTextFill(Paint.valueOf("yellow"));
        team2name.setText(teams[1].getTeamName());
        if(teams[0].getPlayers().length == 1){
            player11box.setVisible(false);
            player12box.setVisible(false);
            player13box.setVisible(false);
            player21box.setVisible(false);
            player22box.setVisible(false);
            player23box.setVisible(false);
        } else if (teams[0].getPlayers().length == 2){
            Player[] players = teams[0].getPlayers();
            player11.setText(players[0].getName());
            player12.setText(players[1].getName());

            players = teams[1].getPlayers();
            player21.setText(players[0].getName());
            player22.setText(players[1].getName());
            player13box.setVisible(false);
            player23box.setVisible(false);
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

        for (Toggle t : team2players.getToggles()) {
            if (t instanceof RadioButton) {
                ((RadioButton) t).setDisable(true);
            }
        }
        setupCups(game.getNoOfCups());

    }

    /**
     * Hides the cups that wont be used in this game.
     * @param cups amount of players in a team
     */
    private void setupCups(int cups){
        if (cups == 3){
            Group group1 = new Group();
            group1.getChildren().addAll(fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft);
            group1.setVisible(false);

            Group group2 = new Group();
            group1.getChildren().addAll(fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight);
            group2.setVisible(false);

            team1name.setCursor(Cursor.HAND);
            team2name.setCursor(Cursor.HAND);

            leftCups.setTranslateX(-60);
            rightCups.setTranslateX(60);

        } else if (cups == 6){
            Group group1 = new Group();
            group1.getChildren().addAll(sevenLeft, eightLeft, nineLeft, tenLeft);
            group1.setVisible(false);

            Group group2 = new Group();
            group1.getChildren().addAll(sevenRight, eightRight, nineRight, tenRight);
            group2.setVisible(false);

            leftCups.setTranslateX(-30);
            rightCups.setTranslateX(30);
        }
    }

}
