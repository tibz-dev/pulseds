package io.pulseds.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PulseApp extends Application 
{

    @Override
    public void start(Stage stage) 
    
    {
        
        BorderPane root = new BorderPane();

        root.setCenter(new TreeCanvas());
        root.setRight(new EventPanel());
        root.setBottom(new ControlPanel());

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("/io/pulseds/resources/ui/cyber.css").toExternalForm());
        stage.setTitle("PulseDS — Real-Time Data Structure Engine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
