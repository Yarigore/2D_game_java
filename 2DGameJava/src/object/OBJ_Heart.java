package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "heart";
        value = 2;
        down1 = setUp("/objects/life/HearFull.png", gp.tileSize, gp.tileSize);
        image = setUp("/objects/life/HearFull.png", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/life/HearHalf.png", gp.tileSize, gp.tileSize);
        image3 = setUp("/objects/life/HearEmpty.png", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){

        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
    }
}
