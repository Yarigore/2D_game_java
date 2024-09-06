package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TinyPotion extends Entity {

    GamePanel gp;
    int value = 1;

    public OBJ_TinyPotion(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Red tiny potion";
        down1 = setUp("/objects/pocionPequenaVida.png", gp.tileSize, gp.tileSize);
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
