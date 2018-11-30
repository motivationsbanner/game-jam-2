package ch.motivationsbanner.gamejam2.game;


import ch.motivationsbanner.gamejam2.gamescreen.GameScreen;
import ch.motivationsbanner.gamejam2.menu.StartMenu;
import ch.motivationsbanner.gamejam2.settings.Settings;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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

        Settings.stage = gameStage;
        gameStage.setTitle("game-jam-2");

        // let's load the startMenu first
        StartMenu startMenu = new StartMenu();
        startMenu.prefWidthProperty().bind(gameStage.widthProperty());
        startMenu.prefHeightProperty().bind(gameStage.heightProperty());
        changePane(startMenu);

        startMenu.setOnGameStart(() -> {
            changePane(GameScreen.getInstance());
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
     * change the parent which is currently visible
     * @param parent the parent element to show
     */
    private void changePane(Parent parent) {
        root.getChildren().clear();
        root.getChildren().add(parent);
    }
}
