package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "bat";
        speed = 2;
        maxLife = 4;
        life = maxLife;
        attack = 2;
        defense = 0;
        exp = 2;

        solidArea.x = 10;
        solidArea.y = 20;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setUp("/monster/bat1.png", gp.tileSize, gp.tileSize);
        up2 = setUp("/monster/bat2.png", gp.tileSize, gp.tileSize);
        down1 = setUp("/monster/bat1.png", gp.tileSize, gp.tileSize);
        down2 = setUp("/monster/bat2.png", gp.tileSize, gp.tileSize);
        left1 = setUp("/monster/bat1.png", gp.tileSize, gp.tileSize);
        left2 = setUp("/monster/bat2.png", gp.tileSize, gp.tileSize);
        right1 = setUp("/monster/bat1.png", gp.tileSize, gp.tileSize);
        right2 = setUp("/monster/bat2.png", gp.tileSize, gp.tileSize);
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

    public void damageReaction(){
        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}
