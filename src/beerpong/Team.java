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
    private int currentPlayerIndex;


    public static void main(String[] args) {
        Player[] p = new Player[1];
        p[0] = new Player("jaja");
        Team t = new Team("lolol", p);

        System.out.println(t.getTeamName());
        System.out.println(t.getScore());
        System.out.println(t.getThrowCount());

    }

    /**
     * Initializes all of the classes fields.
     * @param name The name of the team.
     * @param players A list of all players in the team.
     */
    public Team(String name, Player[] players) {
        this.teamName = name;
        this.players = players;
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
     * Returns ammount of throws left.
     * @return field throwCount
     */
    public int getThrowCount() {
        return throwCount;
    }

    /**
     * Increases the score of the team by parameterized value.
     * @param n ammount to increment
     */
    public void incrementScore(int n) {
        score = score + n;
    }

    /**
     * Marks the cup in the parameter as hit.
     * @param index the index of the cup that was hit
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
        players[currentPlayerIndex].newHit();
    }

    /**
     * Resets the player index to 0.
     */
    public void resetPlayerIndex() {
        currentPlayerIndex = 0;
    }

    /**
     * Resets the throw count to ammount of players.
     */
    public void resetThrowCount() {
        throwCount = players.length;
    }

    /**
     * Throws a ball and gives the next player his/her turn. Reduces the throwcount.
     * Checks if the throwcount has reached 0, in which case the team is done.
     * Makes a call onto hitBall() if no a miss is registered.
     * @param index index of hit cup or -1 if miss.
     * @return boolean false if the team has no remaining throws, true otherwise.
     */
    public boolean throwBall(int index, boolean bounced) {
        //Fixmeh
        //Strängar?
        //Ta med index som parameter?
        //Index för miss?
        String bounceString;

        if (bounced) {
            bounceString = "but atleast it was a nice bounce attempt";
        } else {
            bounceString = "";
        }

        //Studs?
        if (index == -1) {
            System.out.println(players[currentPlayerIndex].getName() + " missed " + bounceString);
        } else {
            System.out.println(players[currentPlayerIndex].getName() + " hit cup nr: " + (index + 1));
            hitCup(index);
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
