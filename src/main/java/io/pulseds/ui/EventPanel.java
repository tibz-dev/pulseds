package io.pulseds.ui;

import javafx.animation.FadeTransition;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EventPanel extends VBox {

    private final ListView<String> events = new ListView<>();

    public EventPanel() {
        setPrefWidth(360);
        events.getItems().add("[SYSTEM] Event stream online");
        getChildren().add(events);
    }

    public void pushEvent(String msg) {
        events.getItems().add(0, msg);

        FadeTransition ft = new FadeTransition(Duration.millis(250), events);
        ft.setFromValue(0.6);
        ft.setToValue(1);
        ft.play();
    }
}
