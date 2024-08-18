package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {

        name = "heart";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/life/barRed_horizontalMid.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/life/barRed_horizontalMidTrans.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/life/barBack_horizontalMid.png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
