package ch.motivationsbanner.gamejam2.game;


import ch.motivationsbanner.gamejam2.menu.StartMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {

    /**
     * the container for everything
     */
    private AnchorPane root = new AnchorPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage gameStage) {

        gameStage.setTitle("game-jam-2");

        // let's load the startMenu first
        StartMenu startMenu = new StartMenu();
        startMenu.prefWidthProperty().bind(gameStage.widthProperty());
        startMenu.prefHeightProperty().bind(gameStage.heightProperty());
        changePane(startMenu);

        startMenu.setOnGameStart(() -> {
            // TODO: load game pane
        });

        startMenu.setOnExit(() -> {
            System.exit(0);
        });

        //create new scene
        Scene scene = new Scene(root, 1080, 720);
        scene.getStylesheets().add("style.css");

        gameStage.setScene(scene);
        gameStage.show();
        gameStage.toFront();
    }

    /**
     * change the pane which is currently visible
     * @param pane the pane to show
     */
    private void changePane(Pane pane) {
        root.getChildren().clear();
        root.getChildren().add(pane);
    }
}
