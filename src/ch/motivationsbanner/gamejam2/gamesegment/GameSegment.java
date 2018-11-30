package ch.motivationsbanner.gamejam2.gamesegment;

import ch.motivationsbanner.gamejam2.blocks.Block;
import ch.motivationsbanner.gamejam2.entities.Player;
import javafx.scene.Group;
import javafx.scene.Node;

public class GameSegment extends Group {
    public GameSegment(){
    }

    public void move(double speed){
        for(Node blocknode: getChildren()){
            ((Block)blocknode).move(speed);
        }
    }

    public boolean collideFeet(Player player){
       for(Node blocknode: getChildren()){
           if(((Block)blocknode).getLayoutBounds().intersects(player.getFeetHitbox())){
               return true;
           }
       }
       return false;
    }

    public boolean collidePlayer(Player player){
        for(Node blocknode: getChildren()){
            if(((Block)blocknode).getLayoutBounds().intersects(player.getPlayerHitbox())){
                return true;
            }
        }
        return false;
    }
}
