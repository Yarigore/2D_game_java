package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_NormalPotion extends Entity {

    GamePanel gp;
    public OBJ_NormalPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red normal potion";
        value = 2;
        down1 = setUp("/objects/pocionGrandeVida.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your life has been recovered by " + value + ".";
        entity.life += value;
        gp.playSE(2);
    }
}
