package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{

    GamePanel gp;

    public OBJ_Door(GamePanel gp) {

        name = "door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/botellaPequena.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
