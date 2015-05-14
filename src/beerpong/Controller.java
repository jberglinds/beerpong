package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

public class Controller {

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
                System.out.println("Left 1");
                index = 1;
            }
            else if (event.getSource() == twoLeft) {
                System.out.println("Left 2");
                index = 2;
            }
            else if (event.getSource() == threeLeft) {
                System.out.println("Left 3");
                index = 3;
            }
            else if (event.getSource() == fourLeft) {
                System.out.println("Left 4");
                index = 4;
            }
            else if (event.getSource() == fiveLeft) {
                System.out.println("Left 5");
                index = 5;
            }
            else if (event.getSource() == sixLeft) {
                System.out.println("Left 6");
                index = 6;
            }
            else if (event.getSource() == sevenLeft) {
                System.out.println("Left 7");
                index = 7;
            }
            else if (event.getSource() == eightLeft) {
                System.out.println("Left 8");
                index = 8;
            }
            else if (event.getSource() == nineLeft) {
                System.out.println("Left 9");
                index = 9;
            }
            else if (event.getSource() == tenLeft) {
                System.out.println("Left 10");
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

        //If the left side is playing we only handle right side cups
        } else {
            int index = 0;
            if (event.getSource() == oneRight) {
                System.out.println("Right 1");
                index = 1;
            }
            else if (event.getSource() == twoRight) {
                System.out.println("Right 2");
                index = 2;
            }
            else if (event.getSource() == threeRight) {
                System.out.println("Right 3");
                index = 3;
            }
            else if (event.getSource() == fourRight) {
                System.out.println("Right 4");
                index = 4;
            }
            else if (event.getSource() == fiveRight) {
                System.out.println("Right 5");
                index = 5;
            }
            else if (event.getSource() == sixRight) {
                System.out.println("Right 6");
                index = 6;
            }
            else if (event.getSource() == sevenRight) {
                System.out.println("Right 7");
                index = 7;
            }
            else if (event.getSource() == eightRight) {
                System.out.println("Right 8");
                index = 8;
            }
            else if (event.getSource() == nineRight) {
                System.out.println("Right 9");
                index = 9;
            }
            else if (event.getSource() == tenRight) {
                System.out.println("Right 10");
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
