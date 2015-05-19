package beerpong;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * Class representing an eventlog to log events happening during a beerpong game such as new hit/miss.
 * Created by jonathan on 2015-05-19.
 */
public class EventLogger {

    //Queue
    private LinkedList<Text> messages;

    public EventLogger() {
        messages = new LinkedList<Text>();
    }

    /**
     * Logs a new message to the eventlogger about which cup was hit and by who.
     * @param player the player that hit the cup.
     * @param cup the index of the cup that was hit.
     */
    public void newHit(Player player, int cup){
        String string = player.getName() + " hit cup nr. " + cup + "!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    /**
     * Logs a new message to the eventlogger about which cup was hit with a bounce and by who.
     * @param player the player that hit the cup.
     * @param cup the index of the cup that was hit.
     */
    public void newHitWithBounce(Player player, int cup){
        String string = player.getName() + " hit cup nr. " + cup + " with a sweet bounce!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    /**
     * Logs a new message to the eventlogger about a missed throw from a player.
     * @param player the player that missed his throw.
     */
    public void newMiss(Player player){
        String string = player.getName() + " missed as usual...";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    /**
     * Logs a new message to the eventlogger about a missed bounce-attempt from a player.
     * @param player the player that missed the bounce-attempt (throw).
     */
    public void newMissWithBounce(Player player){
        String string = player.getName() + " missed, but it was a nice bounce attempt!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    /**
     * Logs a new message to the eventlogger saying that it's time for the given team to play.
     * @param team the team to play the coming rounds.
     */
    public void newTimeToPlayMessage(Team team){
        String string = "It is time for " + team.getTeamName() + " to play!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    private Text makeTextObject(String string){
        Text text = new Text(string + "\n");
        text.setFill(Paint.valueOf("white"));
        return text;
    }

    /**
     * Returns a boolean with the status of the eventlogger.
     * @return false if the eventlogger is empty, true otherwise.
     */
    public boolean containsMessages(){
        return !messages.isEmpty();
    }

    /**
     * Returns the oldest message in the eventlogger.
     * @return a message to be displayed on screen.
     */
    public Text getMessage(){
        return messages.removeFirst();
    }
}
