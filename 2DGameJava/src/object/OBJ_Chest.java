package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends Entity {

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = "chest";
        down1 = setUp("/objects/cofre.png", gp.tileSize, gp.tileSize);
    }
}
