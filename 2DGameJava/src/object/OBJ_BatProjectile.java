package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_BatProjectile extends Projectile {

    GamePanel gp;

    public OBJ_BatProjectile(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Poison";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        up2 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        down1 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        down2 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        left1 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        left2 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        right1 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
        right2 = setUp("/objects/batProyectile.png", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if (user.mana >= useCost){
            haveResource = true;
        }
        return haveResource;
    }

    public void subtractResource(Entity user){
        user.mana -= useCost;
    }

    public Color getParticleColor(){
        Color color = new Color(227, 3, 252);
        return color;
    }
    public int getParticleSize(){
        int size = 6; // 6 PIXELS
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
