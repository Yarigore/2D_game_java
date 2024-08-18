package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage(){

        up1 = setUp("/player/tile_00851.png");
        up2 = setUp("/player/tile_008512.png");
        right1 = setUp("/player/tile_00852.png");
        right2 = setUp("/player/tile_008522.png");
        left1 = setUp("/player/tile_00853.png");
        left2 = setUp("/player/tile_008532.png");
        down1 = setUp("/player/tile_00854.png");
        down2 = setUp("/player/tile_008542.png");
    }

    public void setDialogue(){

        dialogues[0] = "Hello, Stranger!";
        dialogues[1] = "So you've come to this island to \nfind the treasure?";
        dialogues[2] = "I used to be a great wizard but now... \nI,m a bit too old for taking an adventure.";
        dialogues[3] = "Well, good luck on you.";
    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter >= 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    public void speak(){
        super.speak();
    }
}
