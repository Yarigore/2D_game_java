package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_NormalPotion extends Entity {

    GamePanel gp;
    int value = 2;

    public OBJ_NormalPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red normal potion";
        down1 = setUp("/objects/pocionGrandeVida.png", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nHeals your life by " + value + ".";
    }

    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
                "Your life has been recovered by " + value + ".";
        entity.life += value;
        if (gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSE(2);
    }
}
