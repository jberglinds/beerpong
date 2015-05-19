package beerpong;

/**
 * Class representing a cup at a beerpong-table.
 * Created by jonathan on 2015-05-06.
 */
public class Cup {
    private boolean isHit;

    /**
     * New cup with false assigned to isHit.
     */
    public Cup() {
        isHit = false;
    }

    /**
     * Returns a boolean with the status of the cup.
     * @return true if hit, false otherwise.
     */
    public boolean isHit(){
        return isHit;
    }

    /**
     * Marks the cup as hit this round.
     */
    public void hit(){
        isHit = true;
    }

}
