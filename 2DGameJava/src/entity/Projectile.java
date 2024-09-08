package entity;

import main.GamePanel;

public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel gp) {
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
                generateParticle(user.projectile, gp.monster[mosterIndex]);
                isAlive = false;
            }
        }
        if (user != gp.player){
            boolean contactPlayer = gp.checker.checkPlayer(this);
            if (!gp.player.isInvincible && contactPlayer == true){
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                isAlive = false;
            }
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

    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user){}
}
