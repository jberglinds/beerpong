package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Controller {

    public AnchorPane bg;
    public Label gameName;
    public Label team1score, team2score;
    public Circle oneRight, twoRight, threeRight, fourRight, fiveRight, sixRight, sevenRight, eightRight, nineRight, tenRight;
    public Circle oneLeft, twoLeft, threeLeft, fourLeft, fiveLeft, sixLeft, sevenLeft, eightLeft, nineLeft, tenLeft;

    private boolean bounce;

    private Team[] teams;
    private int currentTeamIndex;


    public Controller() {
        teams = new Team[2];

        Player[] p = new Player[2];
        p[0] = new Player("swag");
        p[1] = new Player("swagg");
        Team teamOne = new Team("teamOne", p);

        Player[] p2 = new Player[2];
        p2[0] = new Player("yolo");
        p2[1] = new Player("yoloo");
        Team teamTwo = new Team("teamTwo", p2);

        teams[0] = teamOne;
        teams[1] = teamTwo;

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
    

}
