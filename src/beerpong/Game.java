package beerpong;

import java.util.ArrayList;

/**
 * Created by jonathan on 2015-05-18.
 */
public class Game {

    private String gameName;
    private Team[] teams = new Team[2];
    private int noOfBalls, noOfCups;

    private int currentTeamIndex;   //Index of team currently playing
    private int playerIndex;        //Index of current player, could be set by the GUI to allow same player to throw more than 1 ball.
    private int throwsLeft;         //Throws left to make
    private int removeCupCount;     //No of cups to be removed to match the score after each round.
    private int scoreThisRound;

    private EventLogger eventLogger;

    private static Game instance = new Game();

    public Game() {
        eventLogger = new EventLogger();
        currentTeamIndex = 0;
        playerIndex = 0;
        scoreThisRound = 0;
    }

    public static Game getInstance(){
        return instance;
    }

    /**
     * Sets the name of the game.
     * @param name name of game.
     */
    public void setGameName(String name){
        gameName = name;
    }

    /**
     * Returns the name of the game.
     * @return name of game.
     */
    public String getGameName(){
        return gameName;
    }

    /**
     * Sets number of balls and number of cups for the game.
     * @param balls number of balls (throws) per round.
     * @param cups number of cups per team.
     */
    public void setGameParameters(int balls, int cups){
        noOfBalls = balls;
        noOfCups = cups;
        throwsLeft = balls;
    }

    /**
     * Returns the number of cups set.
     * @return number of cups that each team will be playing with.
     */
    public int getNoOfCups(){
        return noOfCups;
    }

    /**
     * Creates the first team to participate in the game.
     * @param teamName name of team
     * @param teamplayers names of players in team
     */
    public void setTeam1(String teamName, String[] teamplayers){
        teams[0] = new Team(teamName, teamplayers, noOfCups);
    }

    /**
     * Creates the second team to participate in the game.
     * @param teamName name of team
     * @param teamplayers names of players in team
     */
    public void setTeam2(String teamName, String[] teamplayers){
        teams[1] = new Team(teamName, teamplayers, noOfCups);
    }

    /**
     * Returns an array with both teams.
     * @return array with teams that could be null.
     */
    public Team[] getTeams(){
        return teams;
    }

    public int getCupCount() {
        return teams[0].cupCount();
    }

    /**
     * Returns an array with the scores of each team
     * @return array with scores.
     */
    public int[] getScores() {
        int[] scores = new int[2];
        scores[0] = teams[0].getScore();
        scores[1] = teams[1].getScore();
        return scores;
    }

    public void setPlayerIndex(int index){
        playerIndex = index;
    }

    /**
     * Tries to hit a specific cup, called from GUI.
     * Only allows the currently playing team to try to hit cups.
     * Gives points if it is a valid hit. Allows removal of cups after round to match the teams current score.
     * @param index the index of the cup we want to try to hit.
     * @param bounced if it is a bounce-attempt or not.
     * @param side which side of cups that were clicked.
     */
    public int hit(int index, boolean bounced, String side) {
        int answerCode = 0;

        //Om det är rätt lags tur att kasta, gå vidare, annars logga ett meddelande.
        if ((side.equals("Left") && currentTeamIndex == 1) || (side.equals("Right") && currentTeamIndex == 0)) {
            answerCode = 1;
            //Om man har träffat flera i samma får man chansen att ta bort ett antal muggar extra. Dessa markeras direkt som träffade och ingen poäng ges (har redan getts).
            if (removeCupCount > 0) {
                boolean b = teams[currentTeamIndex].removeCup(index);
                if (b){
                    removeCupCount--;
                }
                //Byt lag och ge nya kast om tillräckligt många muggar har blivit borttagna
                if (removeCupCount <= 0){
                    switchTeamIndex();
                    answerCode = -1;
                }
            }

            else {
                boolean b = teams[currentTeamIndex].hitCup(index, bounced, playerIndex);

                if (b) {
                    throwsLeft--;
                    if (bounced) {
                        if (teams[currentTeamIndex].hitThisRound(index)){
                            teams[currentTeamIndex].incrementScore(3);
                            scoreThisRound += 3;
                        } else {
                            teams[currentTeamIndex].incrementScore(2);
                            scoreThisRound += 2;
                        }
                        eventLogger.newHitWithBounce(teams[currentTeamIndex].getPlayers()[playerIndex], index);
                    } else {
                        if (teams[currentTeamIndex].hitThisRound(index)){
                            teams[currentTeamIndex].incrementScore(2);
                            scoreThisRound += 2;
                        } else {
                            teams[currentTeamIndex].incrementScore(1);
                            scoreThisRound += 1;
                        }
                        eventLogger.newHit(teams[currentTeamIndex].getPlayers()[playerIndex], index);
                    }
                }
                if (throwsLeft <= 0) {
                    int cupsHit = teams[currentTeamIndex].endTurn();
                    removeCupCount = scoreThisRound-cupsHit;
                    if (removeCupCount > 0){
                        eventLogger.newRemoveCupsMessage(teams[currentTeamIndex], removeCupCount);
                    }
                    if (removeCupCount <= 0) {
                        switchTeamIndex();
                        answerCode = -1;
                    }
                }
            }
        } else {
            eventLogger.newNotFinishedMessage(teams[currentTeamIndex]);
        }
        return answerCode;
    }

    /**
     * Registers a new miss for the currently playing team.
     * @param bounced if it was a bounce-attempt or not.
     */
    public int miss(boolean bounced) {
        int answerCode = 0;
        if (throwsLeft > 0){
            teams[currentTeamIndex].newMiss(bounced, playerIndex);
            if (bounced) {
                eventLogger.newMissWithBounce(teams[currentTeamIndex].getPlayers()[playerIndex]);
            } else {
                eventLogger.newMiss(teams[currentTeamIndex].getPlayers()[playerIndex]);
            }
            throwsLeft--;
            if (throwsLeft <= 0) {
                int cupsHit = teams[currentTeamIndex].endTurn();
                removeCupCount = scoreThisRound-cupsHit;
                if (removeCupCount > 0){
                    eventLogger.newRemoveCupsMessage(teams[currentTeamIndex], removeCupCount);
                }
                if (removeCupCount <= 0) {
                    switchTeamIndex();
                    answerCode = -1;
                }
            }
        }
        return answerCode;
    }

    private void switchTeamIndex(){
        if (currentTeamIndex == 0)
            currentTeamIndex = 1;
        else
            currentTeamIndex = 0;
        eventLogger.newTimeToPlayMessage(teams[currentTeamIndex]);
        scoreThisRound = 0;
        removeCupCount = 0;
        playerIndex = 0;
        throwsLeft = noOfBalls;
    }

    public EventLogger getEventLogger(){
        return eventLogger;
    }

    public ArrayList<Integer> getCupsHit(int teamIndex) {
        return teams[teamIndex].getHitCups();
    }

    public int getCurrentTeamIndex() {
        return currentTeamIndex;
    }
}
