package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        name = "Mana Crystal";
        value = 1;
        down1 = setUp("/objects/manaFull.png", gp.tileSize, gp.tileSize);
        image = setUp("/objects/manaFull.png", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/manaEmpty.png", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){

        gp.playSE(2);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
