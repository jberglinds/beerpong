package beerpong;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class Controller {

    public AnchorPane bg;

    public void strobe(Event event) {
        FadeTransition ft = new FadeTransition(Duration.millis(10), bg);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(100);
        ft.setAutoReverse(true);
        ft.play();
    }
}
