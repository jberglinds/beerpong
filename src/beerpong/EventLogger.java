package beerpong;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * Created by jonathan on 2015-05-19.
 */
public class EventLogger {

    private LinkedList<Text> messages;

    public EventLogger() {
        messages = new LinkedList<Text>();
    }

    public void newHit(Player player, int cup){
        String string = player.getName() + " hit cup nr. " + cup + "!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    public void newHitWithBounce(Player player, int cup){
        String string = player.getName() + " hit cup nr. " + cup + " with a sweet bounce!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    public void newMiss(Player player){
        String string = player.getName() + " missed as usual...";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

    public void newMissWithBounce(Player player){
        String string = player.getName() + " missed, but it was a nice bounce attempt!";
        Text text = makeTextObject(string);
        messages.addLast(text);
    }

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

    public boolean containsMessages(){
        return !messages.isEmpty();
    }

    public Text getMessage(){
        return messages.removeFirst();
    }
}
