package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "heart";
        image = setUp("/objects/life/barRed_horizontalMid.png");
        image2 = setUp("/objects/life/barRed_horizontalMidTrans.png");
        image3 = setUp("/objects/life/barBack_horizontalMid.png");
    }
}
