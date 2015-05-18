package beerpong;

import java.util.ArrayList;

/**
 * Class representing a team in a beer pong game.
 * For keeping track on each teams round in the game.
 * Created by emil on 2015-05-11.
 */
public class Team {

    private final String teamName;

    private Player[] players;
    private Cup[] enemyCups;
    private int score;
    private int throwCount;
    private int currentPlayerIndex;
    private ArrayList<Integer> hitsThisRound = new ArrayList<Integer>();

    /**
     * Initializes all of the classes fields.
     * @param name The name of the team.
     * @param players A list of all players in the team.
     */
    public Team(String name, String[] players) {
        this.teamName = name;
        this.players = new Player[players.length];
        for (int i = 0; i < players.length; i++){
            this.players[i] = new Player(players[i]);
        }
        this.throwCount = players.length;
        this.score = 0;
        this.currentPlayerIndex = 0;

        // Initializes the enemyCups array depending on how many players are in the team
        switch (players.length) {
            case 1: // 1 player = 3 cups
                this.enemyCups = new Cup[3];
                for (int i = 0; i < enemyCups.length; i++) {
                    enemyCups[i] = new Cup();
                }
                break;
            case 2: // 2 players = 6 cups
                this.enemyCups = new Cup[6];
                for (int i = 0; i < enemyCups.length; i++) {
                    enemyCups[i] = new Cup();
                }
                break;
            case 3: // 3 players = 10 cups
                this.enemyCups = new Cup[10];
                for (int i = 0; i < enemyCups.length; i++) {
                    enemyCups[i] = new Cup();
                }
                break;
            default:
                System.out.println("Invalid size of team");
                this.enemyCups = null;
                break;
        }

    }

    /**
     * Returns a list of all players.
     * @return an array of Players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Returns team name.
     * @return field teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Returns the score of the team.
     * @return field score
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns amount of throws left.
     * @return field throwCount
     */
    public int getThrowCount() {
        return throwCount;
    }

    /**
     * Increases the score of the team by parameterized value.
     * @param n amount to increment
     */
    public void incrementScore(int n) {
        score = score + n;
    }

    /**
     * Marks the cup in the parameter as hit this round or increments the score of
     * 2 if it has already been hit.
     * @param index the index of the cup that was hit
     */
    public void hitCup(int index, boolean bounced) {
        if (enemyCups[index].status()) {
            return;
        }

        String bounceString;

        if (bounced) {
            bounceString = " with a sweet bounce.";
        } else {
            bounceString = "";
        }

        if(bounced) {
            incrementScore(1);
        }

        if (enemyCups[index].thisRound()) {
            System.out.println(players[currentPlayerIndex].getName() + " hit the already hit cup nr " + (index + 1) + bounceString);
            incrementScore(2);
        } else {
            System.out.println(players[currentPlayerIndex].getName() + " hit cup nr " + (index + 1) + bounceString);
            enemyCups[index].hit();
            hitsThisRound.add(index);
            incrementScore(1);
        }
    }

    /**
     * Resets the player index to 0.
     */
    public void resetPlayerIndex() {
        currentPlayerIndex = 0;
    }

    /**
     * Resets the throw count to amount of players.
     */
    public void resetThrowCount() {
        throwCount = players.length;
    }

    /**
     * Marks all cups hit the previous round as hit and therefor unavailable.
     * Resets the array of cups hit this round.
     */
    public void resetArray() {
        for (int index : hitsThisRound) {
            System.out.println(index);
            enemyCups[index].hitAfterRound();
        }
        hitsThisRound.clear();
    }

    /**
     * Throws a ball and gives the next player his/her turn. Reduces the throw count.
     * Checks if the throw count has reached 0, in which case the team is done.
     * Makes a call onto hitBall() if no a miss is registered.
     * @param index index of hit cup or -1 if miss.
     * @return boolean false if the team has no remaining throws, true otherwise.
     */
    public boolean throwBall(int index, boolean bounced) {
        if (index == -1) {
            System.out.println(players[currentPlayerIndex].getName() + " missed.");
            players[currentPlayerIndex].newMiss();
        } else {
            hitCup(index, bounced);
            players[currentPlayerIndex].newHit();
        }
        currentPlayerIndex++;
        throwCount--;
        if (throwCount == 0) {
            return false;
        } else {
            return true;
        }
    }

}
