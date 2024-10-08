package main;

import entity.NPC_OldMan;
import monster.MON_Bat;
import object.*;
import tile_interactive.IT_DryTree;
import tile_interactive.InteractiveTile;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        int i = 0;
        gp.obj[i] = new OBJ_Door(gp);
        gp.obj[i].worldX = gp.tileSize * 15;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 24;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize * 25;
        gp.obj[i].worldY = gp.tileSize * 25;
        i++;
        gp.obj[i] = new OBJ_Axe(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 26;
        i++;
        gp.obj[i] = new OBJ_Shield_Tier2(gp);
        gp.obj[i].worldX = gp.tileSize * 26;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
        gp.obj[i] = new OBJ_TinyPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 27;
        gp.obj[i].worldY = gp.tileSize * 27;
        i++;
        gp.obj[i] = new OBJ_NormalPotion(gp);
        gp.obj[i].worldX = gp.tileSize * 28;
        gp.obj[i].worldY = gp.tileSize * 28;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 29;
        gp.obj[i].worldY = gp.tileSize * 28;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 29;
        gp.obj[i].worldY = gp.tileSize * 29;
        i++;
        gp.obj[i] = new OBJ_Coin(gp);
        gp.obj[i].worldX = gp.tileSize * 29;
        gp.obj[i].worldY = gp.tileSize * 30;
        i++;
        gp.obj[i] = new OBJ_Heart(gp);
        gp.obj[i].worldX = gp.tileSize * 30;
        gp.obj[i].worldY = gp.tileSize * 30;
        i++;
        gp.obj[i] = new OBJ_ManaCrystal(gp);
        gp.obj[i].worldX = gp.tileSize * 30;
        gp.obj[i].worldY = gp.tileSize * 31;
    }

    public void setNPC(){

        int i = 0;
        gp.npc[i] = new NPC_OldMan(gp);
        gp.npc[i].worldX = gp.tileSize * 22;
        gp.npc[i].worldY = gp.tileSize * 21;
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

    public void setInteractiveTile(){
        int i = 0;
        gp.iTile[i] = new IT_DryTree(gp, 10, 10); i++;
        gp.iTile[i] = new IT_DryTree(gp, 11, 10); i++;
        gp.iTile[i] = new IT_DryTree(gp, 10, 11); i++;
    }
}
