package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/tile_00851.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/tile_008512.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/tile_00852.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/tile_008522.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/tile_00853.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/tile_008532.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/tile_00854.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/tile_008542.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if(keyH.upPressed){
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.checker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.checker.checkObject(this, true);

            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;

            if(spriteCounter > 10){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){
        if(index != 999){

            String objectName = gp.obj[index].name;

            switch (objectName){
                case "key":
                    hasKey++;
                    gp.obj[index] = null;
                    gp.ui.showMessage("You got a key!");
                    gp.playSE(2);
                    break;
                case "door":
                    if(hasKey > 0){
                        gp.obj[index] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                        gp.playSE(1);
                    }
                    else{
                        gp.ui.showMessage("You need a key!");
                    }
                    break;
                case "chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    break;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
