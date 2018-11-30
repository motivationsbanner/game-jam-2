package ch.motivationsbanner.gamejam2.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
    public Block(double x, double y, double width, double height){
        super(x,y,width,height);
        setFill(Color.BLACK);
    }

    public void move(double movementSpeed) {
        setX(getX()-movementSpeed);
    }
}
