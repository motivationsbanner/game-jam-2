package ch.motivationsbanner.gamejam2.entities;


import ch.motivationsbanner.gamejam2.images.ImageLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Player extends ImageView {
    private double jumpSpeed = 4.0;
    private boolean jump = false;
    private int airtime = 0;
    private final int maxAirtime = 50;
    private int animation = 0;
    private int timebetween = 5;
    private Rectangle feetHitbox;
    private Rectangle playerHitbox;


    public Player() {
        super(new Image("images/run/run0.png"));
        resetPosition();
        updateHitbox();
    }

    public void resetPosition(){
        setX(50);
        setY(300);
    }

    private void animate(){
        if(!jump&&animation%timebetween==0){
            setImage(ImageLoader.getInstance().getRunAnimation().get(animation/timebetween));
            if((animation-1)*timebetween%ImageLoader.getInstance().getRunAnimation().size()==0){
                animation = 0;
            }else{
                animation++;
            }
        }else{
            animation++;
        }
    }

    private void move(){
        //move the player up if jump == true
        if(jump&&airtime<=maxAirtime){
            setY(getY()-jumpSpeed);
            airtime++;
        }else{
            setY(getY()+jumpSpeed);
        }
        //else move him down

    }

    public void moveUp(){
        setY(getY()-jumpSpeed);
    }

    public void update(){
        animate();
        move();
        updateHitbox();
    }

    public void jump(){
        jump = true;
    }

    public void land(){
        airtime = 0;
        jump = false;
        animation = 0;
    }

    private void updateHitbox(){
        feetHitbox = new Rectangle(getX(),getY()+getImage().getHeight()-20,getImage().getWidth()-20,20);
        playerHitbox = new Rectangle(getX(),getY(),getImage().getWidth(),getImage().getHeight()-20);
    }

    public Bounds getFeetHitbox() {
        return feetHitbox.getBoundsInLocal();
    }

    public Bounds getPlayerHitbox() {
        return playerHitbox.getBoundsInLocal();
    }
}
