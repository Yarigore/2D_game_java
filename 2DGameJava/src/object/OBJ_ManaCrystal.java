package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Mana Crystal";
        image = setUp("/objects/manaFull.png", gp.tileSize, gp.tileSize);
        image2 = setUp("/objects/manaEmpty.png", gp.tileSize, gp.tileSize);
    }
}
