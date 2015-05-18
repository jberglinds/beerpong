package beerpong;

/**
 * Created by jonathan on 2015-05-18.
 */
public class Game {

    private String gameName;
    private Team[] teams = new Team[2];
    private int currentTeamIndex;

    private static Game instance = new Game();

    public Game() {
    }

    public static Game getInstance(){
        return instance;
    }

    
    public void setGameName(String name){
        gameName = name;
    }

    public String getGameName(){
        return gameName;
    }

    public void setTeam1(String teamName, String[] teamplayers){
        teams[0] = new Team(teamName, teamplayers);
    }

    public void setTeam2(String teamName, String[] teamplayers){
        teams[1] = new Team(teamName, teamplayers);
    }

    public Team[] getTeams(){
        return teams;
    }

    public int[] getScores() {
        int[] scores = new int[2];
        scores[0] = teams[0].getScore();
        scores[1] = teams[1].getScore();
        return scores;
    }

    public void hit(int index, boolean bounced, String side) {

        if ((side.equals("Left") && currentTeamIndex == 1) || (side.equals("Right") && currentTeamIndex == 0)) {
            boolean b = teams[currentTeamIndex].throwBall(index, bounced);

            if (!b) {
                teams[currentTeamIndex].resetArray();
                if (currentTeamIndex == 1) {
                    currentTeamIndex = 0;
                } else {
                    currentTeamIndex = 1;
                }
                teams[currentTeamIndex].resetPlayerIndex();
                teams[currentTeamIndex].resetThrowCount();
                System.out.println("It is time for " + teams[currentTeamIndex].getTeamName() + " to play");
            }
        }
    }

    public void miss() {
        boolean b = teams[currentTeamIndex].throwBall(-1, false);

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
