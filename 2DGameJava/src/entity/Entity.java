package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage atackUp1, atackUp2, atackDown1, atackDown2, atackLeft1, atackLeft2, atackRight1, atackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];

    // STATE
    public int worldX, worldY;
    public  String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean isInvincible = false;
    boolean isAttacking = false;
    public boolean isAlive = true;
    public boolean isDying = false;
    boolean isHpBarOn = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // CHARACTER ATRIBUTES
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    // ITEM ATTRIBUTES
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;

    // TYPE
    public int type; // 0 = PLAYER, 1 = NPC, 2 = MONSTER
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){}
    public void damageReaction(){

    }
    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction){
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }
    public void use(Entity entity){}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for (int i = 0; i < gp.obj.length; i++){
            if (gp.obj[i] == null){
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX; // THE DEAD MONSTER'S WORLDX
                gp.obj[i].worldY = worldY; // THE DEAD MONSTER'S WORLDY
                break;
            }
        }
    }
    public Color getParticleColor(){
        Color color = null;
        return color;
    }
    public int getParticleSize(){
        int size = 0; // 6 PIXELS
        return size;
    }
    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target){

        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle particle1 = new Particle(gp, generator, color, size, speed, maxLife, -2, -1);
        Particle particle2 = new Particle(gp, generator, color, size, speed, maxLife, 2, -1);
        Particle particle3 = new Particle(gp, generator, color, size, speed, maxLife, -2, 1);
        Particle particle4 = new Particle(gp, generator, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(particle1);
        gp.particleList.add(particle2);
        gp.particleList.add(particle3);
        gp.particleList.add(particle4);
    }
    public void update(){
        setAction();

        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkEntity(this, gp.npc);
        gp.checker.checkEntity(this, gp.monster);
        gp.checker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.checker.checkPlayer(this);

        if (this.type == type_monster && contactPlayer == true){
            damagePlayer(attack);
        }

        // IF COLLISION IS FALSE, ENTITY CAN MOVE
        if(!collisionOn){
            switch (direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        spriteCounter++;

        if(spriteCounter > 10){
            if (spriteNum == 1){
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (isInvincible){
            invincibleCounter++;
            if (invincibleCounter > 60){
                isInvincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
    }
    public void damagePlayer(int attack){

        if (!gp.player.isInvincible){
            gp.playSE(4);
            int damage = attack - gp.player.defense;
            if (damage < 0) damage = 0;
            gp.player.life -= damage;
            gp.player.isInvincible = true;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ){

            switch (direction){
                case "up":
                    if(spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                    break;
                case "down":
                    if(spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                    break;
                case "left":
                    if(spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                    break;
                case "right":
                    if(spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                    break;
            }

            // MONSTER HP BAR
            if (type == 2 && isHpBarOn){
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter > 600){
                    hpBarCounter = 0;
                    isHpBarOn = false;
                }
            }

            if (isInvincible){
                isHpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (isDying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }
    public void dyingAnimation(Graphics2D g2){

        int i = 5;
        dyingCounter++;

        if (dyingCounter <= i) changeAlpha(g2, 0);
        if (dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1);
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0);
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1);
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0);
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1);
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 0);
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) changeAlpha(g2, 1);
        if (dyingCounter > i * 8){
            isAlive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
    public BufferedImage setUp(String imagePath, int width, int height){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, width, height);
        }catch (IOException e){
            e.printStackTrace();
        }
        return  image;
    }
}
