package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "heart";
        image = setUp("/objects/life/HearFull.png");
        image2 = setUp("/objects/life/HearHalf.png");
        image3 = setUp("/objects/life/HearEmpty.png");
    }
}
