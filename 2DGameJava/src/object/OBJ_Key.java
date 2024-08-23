package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "key";
        down1 = setUp("/objects/llave.png", gp.tileSize, gp.tileSize);
    }
}
