package entity;

import main.GamePanel;

public class Proyectile extends Entity{

    Entity user;

    public Proyectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean isAlive, Entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.isAlive = isAlive;
        this.user = user;
        this.life = maxLife;
    }

    public void update(){

        if (user == gp.player){
            int mosterIndex = gp.checker.checkEntity(this, gp.monster);
            if (mosterIndex != 999){
                gp.player.damageMonster(mosterIndex, attack);
                isAlive = false;
            }
        }
        if (user != gp.player){

        }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if (life <= 0){
            isAlive = false;
        }

        spriteCounter++;
        if (spriteCounter > 12){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
}
