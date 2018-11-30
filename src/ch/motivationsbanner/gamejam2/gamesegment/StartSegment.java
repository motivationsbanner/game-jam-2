package ch.motivationsbanner.gamejam2.gamesegment;

import ch.motivationsbanner.gamejam2.blocks.Block;
import javafx.scene.Group;

public class StartSegment extends GameSegment {
    public StartSegment(){
        getChildren().add(new Block(0, 600, 2160, 50));
    }
}
