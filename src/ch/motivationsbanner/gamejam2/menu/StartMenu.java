package ch.motivationsbanner.gamejam2.menu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * the start-menu
 */
public class StartMenu extends VBox {

    private Runnable onExit;
    private Runnable onGameStart;

    public StartMenu() {
        Label titleLabel = new Label("game-jam-2 ;)");
        Button startButton = new Button("Start");
        Button exitButton = new Button("Exit");

        startButton.setOnAction(event -> onGameStart.run());
        exitButton.setOnAction(event ->onExit.run());

        this.getChildren().addAll(titleLabel, startButton, exitButton );
    }

    /**
     * set the onExit callback
     * @param onExit the callback to set
     */
    public void setOnExit(Runnable onExit) {
        this.onExit = onExit;
    }

    /**
     * set the onGameStart callback
     * @param onGameStart the callback to set
     */
    public void setOnGameStart(Runnable onGameStart) {
        this.onGameStart = onGameStart;
    }
}
