package io.pulseds.ui;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TreeCanvas extends Canvas {

    public TreeCanvas() {
        super(800, 600);
        drawBoot();
    }

    private void drawBoot() {
        GraphicsContext g = getGraphicsContext2D();
        g.setFill(Color.web("#00ffcc"));
        g.fillText("AVL TREE LIVE VIEW", 20, 30);

        FadeTransition fade = new FadeTransition(Duration.millis(1200), this);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }
}
