package ch.motivationsbanner.gamejam2.images;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ImageLoader {
    static ImageLoader instance;
    private ArrayList<Image> runAnimation = new ArrayList<>();
    private ArrayList<Image> jumpUpAnimation = new ArrayList<>();
    private ArrayList<Image> jumpDownAnimation = new ArrayList<>();
    private Image jumpstandstill;
    private ImageLoader(){
        //add all the run animation images
        for(int i = 0; i<8;i++){
            runAnimation.add(new Image("images/run/run"+i+".png"));
        }
        for(int i = 0; i<4;i++){
            jumpUpAnimation.add(new Image("images/jump/jump"+i+".png"));
        }
        for(int i = 0; i<4;i++){
            jumpDownAnimation.add(new Image("images/landing/landing"+i+".png"));
        }
        jumpstandstill = new Image("images/jump/jump4.png");
    }

    public static ImageLoader getInstance(){
        if(instance==null){
            instance = new ImageLoader();
        }
        return instance;
    }

    public Image getJumpstandstill() {
        return jumpstandstill;
    }

    public ArrayList<Image> getJumpDownAnimation() {
        return jumpDownAnimation;
    }

    public ArrayList<Image> getJumpUpAnimation() {
        return jumpUpAnimation;
    }

    public ArrayList<Image> getRunAnimation() {
        return runAnimation;
    }
}
