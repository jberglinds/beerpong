package beerpong;

/**
 * Created by jonathan on 2015-05-18.
 */
public class Game {

    private String gameName;
    private Team[] teams = new Team[2];
    private int currentTeamIndex;
    private boolean bounce;

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
}
