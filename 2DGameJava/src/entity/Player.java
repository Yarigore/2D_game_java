package entity;

import main.GamePanel;
import main.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 30, 30);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttack();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){

        up1 = setUp("/player/walk/tile_00851.png", gp.tileSize, gp.tileSize);
        up2 = setUp("/player/walk/tile_008512.png", gp.tileSize, gp.tileSize);
        right1 = setUp("/player/walk/tile_00852.png", gp.tileSize, gp.tileSize);
        right2 = setUp("/player/walk/tile_008522.png", gp.tileSize, gp.tileSize);
        left1 = setUp("/player/walk/tile_00853.png", gp.tileSize, gp.tileSize);
        left2 = setUp("/player/walk/tile_008532.png", gp.tileSize, gp.tileSize);
        down1 = setUp("/player/walk/tile_00854.png", gp.tileSize, gp.tileSize);
        down2 = setUp("/player/walk/tile_008542.png", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttack(){
        atackUp1 = setUp("/player/attack/upSword1.png", gp.tileSize, gp.tileSize * 2);
        atackUp2 = setUp("/player/attack/upSword2.png", gp.tileSize, gp.tileSize * 2);
        atackDown1 = setUp("/player/attack/downSword1.png", gp.tileSize, gp.tileSize * 2);
        atackDown2 = setUp("/player/attack/downSword2.png", gp.tileSize, gp.tileSize * 2);
        atackLeft1 = setUp("/player/attack/leftSword1.png", gp.tileSize * 2, gp.tileSize);
        atackLeft2 = setUp("/player/attack/leftSword2.png", gp.tileSize * 2, gp.tileSize);
        atackRight1 = setUp("/player/attack/rightSword1.png", gp.tileSize * 2, gp.tileSize);
        atackRight2 = setUp("/player/attack/rightSword2.png", gp.tileSize * 2, gp.tileSize);
    }

    public void update(){

        if (isAttacking){
            attacking();
        }
        else if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed){

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

            // CHECK OBJECT COLLISION
            int objIndex = gp.checker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION
            int npcIndex = gp.checker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.checker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.eHandler.checkEvent();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn && !keyH.enterPressed){
                switch (direction){
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            gp.keyH.enterPressed = false;

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

        // THIS NEEDS TO BE OUTSIDE OF KEY IF STATEMENT!
        if (isInvincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                isInvincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void attacking(){
        spriteCounter++;

        if (spriteCounter <= 5){
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            // SAVE THE CURRENT WORLDX, WORLDY, SOLIDAREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // ADJUST PLAYER'S WORLDX/Y FOR THE ATTACKAREA
            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            // ATTACKAREA BECOMES SOLIDAREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // CHECK MONSTER COLLISION WITH THE UPDATE WORLDX/Y AND SOLIDAREA
            int monsterIndex = gp.checker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // AFTER CHECKING COLLISION, RESTORE THE ORIGINAL DATA
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }

    public void pickUpObject(int index){
        if(index != 999){

        }
    }

    public void interactNPC(int index){

        if (gp.keyH.enterPressed){
            if(index != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
            else {
                isAttacking = true;
            }
        }
    }

    public void contactMonster(int index){

        if (index != 999){

            if (isInvincible == false){
                life -= 1;
                isInvincible = true;
            }
        }
    }

    public void damageMonster(int index){
        if (index != 999){
            if (!gp.monster[index].isInvincible){
                gp.monster[index].life -= 1;
                gp.monster[index].isInvincible = true;

                if (gp.monster[index].life <= 0){
                    gp.monster[index] = null;
                }
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int temScreenX = screenX;
        int temScreenY = screenY;

        switch (direction){
            case "up":
                if (!isAttacking){
                    if(spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if (isAttacking){
                    temScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) image = atackUp1;
                    if (spriteNum == 2) image = atackUp2;
                }
                break;
            case "down":
                if (!isAttacking){
                    if(spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if (isAttacking){
                    if(spriteNum == 1) image = atackDown1;
                    if (spriteNum == 2) image = atackDown2;
                }
                break;
            case "left":
                if (!isAttacking){
                    if(spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if (isAttacking){
                    temScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) image = atackLeft1;
                    if (spriteNum == 2) image = atackLeft2;
                }
                break;
            case "right":
                if (!isAttacking){
                    if(spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                if (isAttacking){
                    if(spriteNum == 1) image = atackRight1;
                    if (spriteNum == 2) image = atackRight2;
                }
                break;
        }

        if (isInvincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, temScreenX, temScreenY, null);

        // RESET ALPHA
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // DEBUG
        //g2.setFont(new Font("Arial", Font.PLAIN, 26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible: " + invincibleCounter, 10, 400);
    }
}
