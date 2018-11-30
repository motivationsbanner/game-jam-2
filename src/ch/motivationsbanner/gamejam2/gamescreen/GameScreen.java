package ch.motivationsbanner.gamejam2.gamescreen;

import ch.motivationsbanner.gamejam2.entities.Player;
import ch.motivationsbanner.gamejam2.gamesegment.BasicJumpSegment;
import ch.motivationsbanner.gamejam2.gamesegment.GameSegment;
import ch.motivationsbanner.gamejam2.gamesegment.StartSegment;
import ch.motivationsbanner.gamejam2.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Label;

import java.util.Iterator;

public class GameScreen extends Group {
    private static double movementSpeed = 4;
    private static double speedIncrement = 0.0002;
    private static Group blocks = new Group();
    private static Player player = new Player();
    private static int score = 0;
    private static int timer = 0;
    private static Label scoreLabel = new Label("Score: "+score);
    private static AnimationTimer gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if(timer % 30 == 0){
                timer = 0;
                score++;
                scoreLabel.setText("Score: "+score);
            }
            player.update();
            //move the blocks to the left for the gamespeed
            boolean addnewGameScreen = false;
            Iterator blockIterator = blocks.getChildren().iterator();
            while (blockIterator.hasNext()) {
                GameSegment block = (GameSegment) blockIterator.next();
                block.move(movementSpeed);
                //check if the player collides with a block and the player has to die
                if (block.collidePlayer(player)) {
                    die();
                }
                //if the feet frome the player hit the hitbox move the player up
                if (block.collideFeet(player)) {
                    player.moveUp();
                    player.land();
                }
                //remove the block if it is outside of the game
                if (!block.getLayoutBounds().intersects(0, 0, 2160, 720)) {
                    blockIterator.remove();
                    addnewGameScreen = true;
                }
            }
            //remove the player if hes out of the screen
            if (player.getY() >= 720) {
                die();
            }
            if(addnewGameScreen){
                createLine();
            }
            movementSpeed += speedIncrement;
            timer++;
        }
    };
    private static GameScreen instance;

    /**
     * private constructore
     */
    private GameScreen() {
        getChildren().add(player);
        getChildren().add(blocks);
        getChildren().add(scoreLabel);
        createGui();
    }

    /**
     * used to get the instance of the game
     *
     * @return GameScreen instance
     */
    public static GameScreen getInstance() {
        if (instance == null) {
            instance = new GameScreen();
            instance.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP:
                    case W:
                    case SPACE:
                        player.jump();
                        break;
                    case ESCAPE: //return to the menu
                        break;
                    case R:restart();break;
                }
            });

            Settings.stage.getScene().onKeyPressedProperty().bind(instance.onKeyPressedProperty());
        }

        return instance;
    }

    /**
     * creates the guy the first time the game starts
     */
    private void createGui() {
        restart();
    }

    /**
     * used to reset the position of the player and clear all the lines
     */
    private static void restart() {
        score = 0;
        blocks.getChildren().clear();
        blocks.getChildren().add(new StartSegment());
        blocks.getChildren().add(new BasicJumpSegment());
        player.resetPosition();
        scoreLabel.setText("Score: "+score);
        gameLoop.start();
    }

    /**
     * used to create one long line at the start of each level
     */
    private static void createLine() {
        blocks.getChildren().add(new BasicJumpSegment());
    }

    /**
     * used when the player dies
     */
    private static void die() {
        gameLoop.stop();
        //switch to the endscreen
    }
}
