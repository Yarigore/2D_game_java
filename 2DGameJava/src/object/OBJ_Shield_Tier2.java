package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Tier2 extends Entity {
    public OBJ_Shield_Tier2(GamePanel gp) {
        super(gp);

        type = type_shield;
        name = "Shield Tier 2";
        down1 = setUp("/objects/escudoTier2.png", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name +"]\nIt's just a simple\nshield";
    }
}
