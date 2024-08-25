package main;

import entity.NPC_OldMan;
import monster.MON_Bat;
import object.OBJ_Door;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Door(gp);
        gp.obj[0].worldX = gp.tileSize * 15;
        gp.obj[0].worldY = gp.tileSize * 23;

    }

    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 22;
        gp.npc[0].worldY = gp.tileSize * 21;
    }

    public void setMonster(){

        int i = 0;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 25;
        gp.monster[i].worldY = gp.tileSize * 25;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 26;
        gp.monster[i].worldY = gp.tileSize * 26;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 27;
        gp.monster[i].worldY = gp.tileSize * 27;
        i++;
        gp.monster[i] = new MON_Bat(gp);
        gp.monster[i].worldX = gp.tileSize * 28;
        gp.monster[i].worldY = gp.tileSize * 28;

    }
}
