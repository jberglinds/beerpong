package beerpong;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by jonathan on 2015-05-20.
 */
public class StatisticsController {

    public Label playerName, throwsMade, bounceAttempts, hitRatio, bounceHitRatio;

    public void updateLabels(String playerName, int throwsMade, int bounceAttempts, double hitRatio, double bounceHitRatio){
        this.playerName.setText(playerName);
        this.throwsMade.setText(Integer.toString(throwsMade));
        this.bounceAttempts.setText(Integer.toString(bounceAttempts));

        NumberFormat percentFormatter = NumberFormat.getPercentInstance(Locale.US);
        this.hitRatio.setText(percentFormatter.format(hitRatio));
        this.bounceHitRatio.setText(percentFormatter.format(bounceHitRatio));

        if (throwsMade == 0){
            this.hitRatio.setText("-");
        }
        if (bounceAttempts == 0){
            this.bounceHitRatio.setText("-");
        }

        System.out.println(playerName + throwsMade + bounceAttempts + hitRatio + bounceHitRatio);
    }

    public void close(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.close();
    }
}
