package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

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


    //Update view from variables from game.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = Game.getInstance();

        gameName.setText(game.getGameName());
    }
}
