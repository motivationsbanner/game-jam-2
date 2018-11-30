package ch.motivationsbanner.gamejam2.gamesegment;

import ch.motivationsbanner.gamejam2.blocks.Block;

public class BasicJumpSegment extends GameSegment {
    public BasicJumpSegment(){
        getChildren().add(new Block(2160,600,150,50));
        getChildren().add(new Block(2460,600,150,50));
        getChildren().add(new Block(2760,600,150,50));
        getChildren().add(new Block(3060,600,150,50));
        getChildren().add(new Block(3360,600,960,50));
    }
}
