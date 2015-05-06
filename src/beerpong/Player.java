package beerpong;

/**
 * Class representing a player in a beerpong-game.
 * For keeping track of individual statistics like hit/miss ratio.
 * Created by jonathan on 2015-05-06.
 */
public class Player {

    private final String name;
    private int noOfThrows;
    private int noOfHits;

    /**
     * New player with given name.
     * @param name Name of player.
     */
    public Player(String name) {
        this.name = name;
        this.noOfThrows = 0;
        this.noOfHits = 0;
    }

    /**
     * Returns the name of the player
     * @return Name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the players hit ratio.
     * @return 1.00 if no throws, otherwise hits/throws.
     */
    public double getHitRatio() {
        if (noOfHits == 0)
            return 1.00;
        else
            return noOfHits/noOfThrows;
    }

    /**
     * Increment hit-count.
     */
    public void newHit(){
        noOfHits++;
        noOfThrows++;
    }

    /**
     * Increment miss-count.
     */
    public void newMiss(){
        noOfThrows++;
    }
}
