package ch.motivationsbanner.gamejam2.game;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {
        //set root
        AnchorPane root = new AnchorPane();
        //create new scene
        Scene scene = new Scene(root, 1080, 720);
        gameStage.setScene(scene);
        gameStage.show();
        gameStage.toFront();
    }
}
