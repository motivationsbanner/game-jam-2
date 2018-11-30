package ch.motivationsbanner.gamejam2.gamescreen;

import ch.motivationsbanner.gamejam2.blocks.Block;
import ch.motivationsbanner.gamejam2.entities.Player;
import ch.motivationsbanner.gamejam2.settings.Settings;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

import java.util.Iterator;

public class GameScreen extends Group {
    private double movementSpeed = 4;
    private double speedIncrement = 0.0002;
    private Group blocks = new Group();
    private static Player player = new Player();
    private int score = 0;
    private AnimationTimer gameLoop= new AnimationTimer(){
        @Override
        public void handle(long now) {
            player.update();
            //move the blocks to the left for the gamespeed
            Iterator blockIterator = blocks.getChildren().iterator();
            while(blockIterator.hasNext()){
                Block block = (Block)blockIterator.next();
                block.move(movementSpeed);
                //check if the player collides with a block and the player has to die
                if(block.getLayoutBounds().intersects(player.getPlayerHitbox())){
                    die();
                }
                //if the feet frome the player hit the hitbox move the player up
                if(block.getLayoutBounds().intersects(player.getFeetHitbox())){
                    player.moveUp();
                    player.land();
                }
                //remove the block if it is outside of the game
                if(!block.getLayoutBounds().intersects(0,0,2160,720)) {
                    blockIterator.remove();
                }
            }
            //remove the player if hes out of the screen
            if(player.getY()>=720){
                die();
            }
            movementSpeed+=speedIncrement;
            //increase the score by one
            score++;
        }
    };
    private static GameScreen instance;

    /**
     * private constructore
     */
    private GameScreen(){
        getChildren().add(player);
        getChildren().add(blocks);
        createGui();
    }

    /**
     * used to get the instance of the game
     * @return GameScreen instance
     */
    public static GameScreen getInstance(){
        if(instance == null){
            instance = new GameScreen();
            instance.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP: case W: case SPACE:  player.jump();break;
                    case ESCAPE: //return to the menu
                         break;

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
    private void restart(){
        score = 0;
        blocks.getChildren().clear();
        createLine();
        player.resetPosition();
        gameLoop.start();
    }

    /**
     * used to create one long line at the start of each level
     */
    private void createLine(){
        blocks.getChildren().add(new Block(0,600,2160,50));
    }

    /**
     * used when the player dies
     */
    private void die(){
        gameLoop.stop();
        //switch to the endscreen
    }
}
