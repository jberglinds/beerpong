package beerpong;

/**
 * Class representing a cup at a beerpong-table.
 * Created by jonathan on 2015-05-06.
 */
public class Cup {
    private boolean isHit;
    private boolean hitThisRound;

    /**
     * New cup with false assigned to isHit.
     */
    public Cup() {
        isHit = false;
        hitThisRound = false;
    }

    /**
     * Returns a boolean with the status of the cup.
     * @return true if hit, false otherwise.
     */
    public boolean status(){
        return isHit;
    }

    /**
     * Marks the cup as hit this round.
     */
    public void hit(){
        hitThisRound = true;
    }

    /**
     * Tells you whether the cup has been hit this round.
     * @return true if hit this round, false otherwise
     */
    public boolean thisRound() { return hitThisRound; }

    /**
     * Marks the cup as hit
     */
    public void hitAfterRound() {
        isHit = true;
    }
}
