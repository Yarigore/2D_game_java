package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Tier1 extends Entity {
    public OBJ_Shield_Tier1(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Shield Tier 1";
        down1 = setUp("/objects/escudoTier1.png", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name +"]\nIt's just a simple\nshield";

    }
}
