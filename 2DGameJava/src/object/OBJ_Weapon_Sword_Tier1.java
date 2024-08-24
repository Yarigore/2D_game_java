package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Weapon_Sword_Tier1 extends Entity {
    public OBJ_Weapon_Sword_Tier1(GamePanel gp) {
        super(gp);

        name = "Sword Tier 1";
        down1 = setUp("/objects/espadaTier1.png", gp.tileSize, gp.tileSize);
        attackValue = 1;
    }
}
