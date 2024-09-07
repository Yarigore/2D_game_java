package object;

import entity.Proyectile;
import main.GamePanel;

public class OBJ_Arrow extends Proyectile {

    GamePanel gp;

    public OBJ_Arrow(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Arrow";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 0;
        isAlive = false;
        getImage();
    }

    public void getImage(){
        up1 = setUp("/objects/arrow/arrowUp.png", gp.tileSize, gp.tileSize);
        up2 = setUp("/objects/arrow/arrowUp.png", gp.tileSize, gp.tileSize);
        down1 = setUp("/objects/arrow/arrowDown.png", gp.tileSize, gp.tileSize);
        down2 = setUp("/objects/arrow/arrowDown.png", gp.tileSize, gp.tileSize);
        left1 = setUp("/objects/arrow/arrowLeft.png", gp.tileSize, gp.tileSize);
        left2 = setUp("/objects/arrow/arrowLeft.png", gp.tileSize, gp.tileSize);
        right1 = setUp("/objects/arrow/arrowRight.png", gp.tileSize, gp.tileSize);
        right2 = setUp("/objects/arrow/arrowRight.png", gp.tileSize, gp.tileSize);
    }
}
