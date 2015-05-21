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
    private int[] hitCups; //For keeping track on number of hits on a cup in a round.
    private int score;

    /**
     * Initializes all of the classes fields.
     * @param name The name of the team.
     * @param players A list of all players in the team.
     * @param cups Number of cups to be initialized.
     */
    public Team(String name, String[] players, int cups) {
        this.teamName = name;
        this.players = new Player[players.length];
        for (int i = 0; i < players.length; i++){
            this.players[i] = new Player(players[i]);
        }
        this.score = 0;

        this.enemyCups = new Cup[cups];
        for (int i = 0; i < enemyCups.length; i++) {
            enemyCups[i] = new Cup();
        }
        hitCups = new int[enemyCups.length];
    }

    public int cupCount() {
        return enemyCups.length;
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
     * Increases the score of the team by parameterized value.
     * @param n amount to increment
     */
    public void incrementScore(int n) {
        score = score + n;
    }


    /**
     * Marks the cups as hit for real after a round and returns the number of individual cups that was hit.
     * @return number of different cups that were hit.
     */
    public int endTurn() {
        //Markera de träffade muggarna som faktiskt träffade efter en rundas slut och returnera antalet olika träffade muggar.
        int noOfDifferentCups = 0;
        for (int i=0; i< hitCups.length; i++){
            if (hitCups[i] > 0){
                enemyCups[i].hit();
                noOfDifferentCups++;
            }
        }
        hitCups = new int[enemyCups.length];
        return noOfDifferentCups;
    }

    /**
     * Tries to hit one of the enemycups and adds a hit to the players statistics if it wasn't already hit.
     * Returns a boolean with the status of the hit-attempt.
     * @param index the index of the cup we want to try to hit.
     * @param bounced bounce-attempt or not? (for player statistics)
     * @param playerIndex the index of the player in the team that tries to hit the cup.
     * @return true if the cup hasn't been hit in an previous round. false otherwise and nothing happens.
     */
    public boolean hitCup(int index, boolean bounced, int playerIndex) {

        //Om den inte redan är träffad från en tidigare runda, gå vidare, annars returnera false.
        if (!enemyCups[index].isHit()){

            //Registrera träff på en spelare för statistik.
            if (bounced){
                players[playerIndex].newHitWithBounce();
            } else {
                players[playerIndex].newHit();
            }

            //Lägg till en träff på muggens index i en array.
            hitCups[index]++;
            return true;

        } else {
            return false;
        }
    }

    /**
     * Marks a cup as hit without giving a player credit for it. Used when more points are given than different cups hit to remove cups.
     * @param index the index of the cup that we want to remove
     * @return true if the cup wasn't already marked as hit from before, false otherwise.
     */
    public boolean removeCup(int index) {

        //Om den inte redan är träffad sen en tidigare runda, gå vidare, annars returnera false.
        if (!enemyCups[index].isHit()){
            enemyCups[index].hit(); //Markera som träffad direkt och returnera true.
            return true;
        } else {
            return false;
        }
    }

    /**
     * Registers a new miss to the player statistics.
     * @param bounced if it was a bounce-attempt or not.
     * @param playerIndex the player that missed his throw.
     */
    public void newMiss(boolean bounced, int playerIndex){
        if (bounced){
            players[playerIndex].newMissWithBounce();
        } else {
            players[playerIndex].newMiss();
        }
    }

    /**
     * Returns a boolean with the status for the cup with given index.
     * Used for checking how many points a specific hit should give in Game.java
     * @param index the index of the cup we want to check on
     * @return true if the cup has been hit before in this round, false otherwise.
     */
    public boolean hitThisRound(int index){
        return hitCups[index] > 1;
    }

    public ArrayList<Integer> getHitCups() {
        ArrayList<Integer> cupsHit = new ArrayList<Integer>();

        for (int i = 0; i < enemyCups.length; i++) {
            if (enemyCups[i].isHit()) {
                cupsHit.add(i);
            }
        }
        return cupsHit;
    }

}
