package io.pulseds.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {

    public ControlPanel() {
        setSpacing(15);

        Button start = new Button("▶ START");
        Button stop = new Button("■ STOP");

        getChildren().addAll(start, stop);
    }
}
