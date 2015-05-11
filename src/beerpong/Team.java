package beerpong;

/**
 * Class representing a team in a beerpong-game.
 * For keeping track on each teams round in the game.
 * Created by emil on 2015-05-11.
 */
public class Team {

    private final String teamName;

    private Player[] players;
    private Cup[] enemyCups;
    private int score;
    private int throwCount;


    public static void main(String[] args) {
        Player[] p = new Player[1];
        p[0] = new Player("jaja");
        Team t = new Team("lolol", p);

        System.out.println(t.getTeamName());
        System.out.println(t.getScore());
        System.out.println(t.getThrowCount());

    }


    public Team(String name, Player[] players) {
        this.teamName = name;
        this.players = players;
        this.throwCount = players.length;
        this.score = 0;

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
     * 
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     *
     * @return
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return
     */
    public int getThrowCount() {
        return throwCount;
    }

    /**
     *
     * @param n
     */
    public void incrementScore(int n) {
        score = score + n;
    }

    /**
     *
     * @param index
     */
    public void hitCup(int index) {
        if (enemyCups[index] == null) {
            System.out.println("enemyCups[index] == null; nullpointer exception here");
            return;
        }

        if (enemyCups[index].status()) {
            System.out.println("This cup is already hit");
            return;
        }

        if (enemyCups[index].thisRound()) {
            incrementScore(2);
        } // Lägg till studs
        else {
            incrementScore(1);
        }
    }

    /**
     * Throws a ball and does other calls depending on what the ball hit.
     * If the ball hits a cup hitCup() i called upon.
     * If not, then nothing more is done
     */
    public void throwBall() {
        // if (Miss)
        // return;

        // if(Träff)
        // hitCup(int träffadMugg)

        throwCount--;
    }

}
