package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.TextField;

public class Controller {

    public Scene nextScene;

    public AnchorPane bg;
    public Label gameName;
    public Label team1score, team2score;
    public Circle oneRight, twoRight, threeRight, fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight;
    public Circle oneLeft, twoLeft, threeLeft, fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft;
    public Label team1name, team2name;
    public Label player11, player12, player13;
    public Label player21, player22, player23;

    private Team teamOne;
    private Team teamTwo;
    private boolean bounce;
    private Team[] teams;
    private int currentTeamIndex;

    public Controller() {
        teams = new Team[2];
        currentTeamIndex = 0;
    }

    public void setNextScene (Scene scene){
        nextScene = scene;
    }

    /***********
     initGame.fxml
     ************/

    public TabPane init;
    public javafx.scene.control.TextField gameName1, gameName2, gameName3, p1_1v1, p2_1v1, t1_2v2, t2_2v2, t1p1_2v2, t1p2_2v2, t2p1_2v2, t2p2_2v2, t1_3v3, t2_3v3, t1p1_3v3, t1p2_3v3, t1p3_3v3, t2p1_3v3, t2p2_3v3, t2p3_3v3;

    public void startGame(ActionEvent event) {
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        //TODO: Code to create teams and update graphics.

        window.setScene(nextScene);

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

    /***********
     gameView.fxml
     ************/

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
     * the current team representing a miss. If throwBall() returns false the
     * team has used all their throws and it will be the other teams turn.
     * @param event a click on the miss button.
     */
    public void miss(Event event) {
        boolean b  = teams[currentTeamIndex].throwBall(-1, bounce);

        if (currentTeamIndex == 0) {
            team1score.setText(Integer.toString(teams[currentTeamIndex].getScore()));
        } else {
            team2score.setText(Integer.toString(teams[currentTeamIndex].getScore()));
        }

        if (!b) {
            if (currentTeamIndex == 0) {
                currentTeamIndex = 1;
            } else {
                currentTeamIndex = 0;
            }
            teams[currentTeamIndex].resetPlayerIndex();
            teams[currentTeamIndex].resetThrowCount();
            System.out.println("It is time for " + teams[currentTeamIndex].getTeamName() + " to play");
        }
    }

    /**
     * Takes in 4 parameters, Team name and the names of all players. From this
     * the team is created. The team is created depending on whether team one or
     * team two is clicked on the screen.
     * @param event Clicking on the team name
     */
    public void createTeam(Event event) {
        final Stage primaryStage = new Stage();
        Pane pane = new Pane();
        Button b = new Button("Done");
        b.setTranslateX(150);
        b.setTranslateY(160);

        //Team name field
        final javafx.scene.control.TextField team = new javafx.scene.control.TextField();
        team.setText("What is your team name");
        team.setTranslateX(100);
        team.setTranslateY(10);
        //Player one field
        final javafx.scene.control.TextField player1 = new javafx.scene.control.TextField();
        player1.setText("Player 1 name");
        player1.setTranslateX(100);
        player1.setTranslateY(50);
        //Player two field
        final javafx.scene.control.TextField player2 = new javafx.scene.control.TextField();
        player2.setText("Player 2 name");
        player2.setTranslateX(100);
        player2.setTranslateY(90);
        //Player three field
        final javafx.scene.control.TextField player3 = new javafx.scene.control.TextField();
        player3.setText("Player 3 name");
        player3.setTranslateX(100);
        player3.setTranslateY(130);


        if(event.getSource() == team1name) {
            //On click of button
            b.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String teamName = team.getText();
                            Player playerOne = new Player(player1.getText());
                            Player playerTwo = new Player(player2.getText());
                            Player playerThree = new Player(player3.getText());
                            Player[] players = {playerOne, playerTwo, playerThree};
                            primaryStage.close();
                            team1name.setText(teamName);
                            player11.setText(playerOne.getName());
                            player12.setText(playerTwo.getName());
                            player13.setText(playerThree.getName());
                            teamOne = new Team(teamName, players);
                            teams[0] = teamOne;
                        }
                    });
        }

        if (event.getSource() == team2name) {
            //On click of button
            b.setOnAction(
                    new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String teamName = team.getText();
                            Player playerOne = new Player(player1.getText());
                            Player playerTwo = new Player(player2.getText());
                            Player playerThree = new Player(player3.getText());
                            Player[] players = {playerOne, playerTwo, playerThree};
                            primaryStage.close();
                            team2name.setText(teamName);
                            player21.setText(playerOne.getName());
                            player22.setText(playerTwo.getName());
                            player23.setText(playerThree.getName());
                            teamTwo = new Team(teamName, players);
                            teams[1] = teamTwo;
                        }
                    });
            }

        pane.getChildren().addAll(team, player1, player2, player3, b);
        primaryStage.setScene(new Scene(pane, 400, 200));
        primaryStage.show();
    }


    /**
     * If a hit is registered on the graphic board a call will be made to
     * the current team representing a hit. If throwBall() returns false the
     * team has used all their throws and it will be the other teams turn.
     * Only the enemies cups are available for play at any time.
     * @param event A click on any cup in play at that particular time.
     */
    public void hit(Event event) {
        //If the right team is playing we only handle left side cups
        if (currentTeamIndex == 1) {
            int index = 0;
            if (event.getSource() == oneLeft) {
                index = 1;
            }
            else if (event.getSource() == twoLeft) {
                index = 2;
            }
            else if (event.getSource() == threeLeft) {
                index = 3;
            }
            else if (event.getSource() == fourLeft) {
                index = 4;
            }
            else if (event.getSource() == fiveLeft) {
                index = 5;
            }
            else if (event.getSource() == sixLeft) {
                index = 6;
            }
            else if (event.getSource() == sevenLeft) {
                index = 7;
            }
            else if (event.getSource() == eightLeft) {
                index = 8;
            }
            else if (event.getSource() == nineLeft) {
                index = 9;
            }
            else if (event.getSource() == tenLeft) {
                index = 10;
            }

            if (index != 0) {
                boolean b = teams[currentTeamIndex].throwBall(index-1, bounce);
                team2score.setText(Integer.toString(teams[currentTeamIndex].getScore()));

                if (!b) {
                    currentTeamIndex = 0;
                    teams[currentTeamIndex].resetPlayerIndex();
                    teams[currentTeamIndex].resetThrowCount();
                    System.out.println("It is time for " + teams[currentTeamIndex].getTeamName() + " to play");
                }
            }
        } else { //If the left side is playing we only handle right side cups
            int index = 0;

            if (event.getSource() == oneRight) {
                index = 1;
            }
            else if (event.getSource() == twoRight) {
                index = 2;
            }
            else if (event.getSource() == threeRight) {
                index = 3;
            }
            else if (event.getSource() == fourRight) {
                index = 4;
            }
            else if (event.getSource() == fiveRight) {
                index = 5;
            }
            else if (event.getSource() == sixRight) {
                index = 6;
            }
            else if (event.getSource() == sevenRight) {
                index = 7;
            }
            else if (event.getSource() == eightRight) {
                index = 8;
            }
            else if (event.getSource() == nineRight) {
                index = 9;
            }
            else if (event.getSource() == tenRight) {
                index = 10;
            }

            if (index != 0) {
                boolean b = teams[currentTeamIndex].throwBall(index-1, bounce);
                team1score.setText(Integer.toString(teams[currentTeamIndex].getScore()));

                if (!b) {
                    currentTeamIndex = 1;
                    teams[currentTeamIndex].resetPlayerIndex();
                    teams[currentTeamIndex].resetThrowCount();
                    System.out.println("It is time for " + teams[currentTeamIndex].getTeamName() + " to play");
                }
            }
        }
    }



}
