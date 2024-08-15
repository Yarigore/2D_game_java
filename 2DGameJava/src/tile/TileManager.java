package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[50];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){

        setUp(0, "/tiles/prado.png", false);
        setUp(1, "/tiles/arena.png", false);
        setUp(2, "/tiles/barro.png", false);
        setUp(3, "/tiles/pradoFlor.png", false);
        setUp(4, "/tiles/arenaLisa.png", false);
        setUp(5, "/tiles/arenaRocas.png", false);
        setUp(6, "/tiles/CasaA/puertaSimple.png", false);
        setUp(7, "/tiles/CasaA/ladoDerechoCasa.png", true);
        setUp(8, "/tiles/CasaA/ladoCentralCasa.png", true);
        setUp(9, "/tiles/CasaA/ladoIzquierdoCasa.png", true);
        setUp(10, "/tiles/CasaA/tejaCasa.png", true);
        setUp(11, "/tiles/CasaA/tejadoAbajoDerecho.png", true);
        setUp(12, "/tiles/CasaA/tejadoAbajoCentral.png", true);
        setUp(13, "/tiles/CasaA/tejadoAbajoIzquierda.png", true);
        setUp(14, "/tiles/CasaA/chimenea.png", true);
        setUp(15, "/tiles/CasaA/tejadoArribaDerecho.png", true);
        setUp(16, "/tiles/CasaA/tejadoArribaCentral.png", true);
        setUp(17, "/tiles/CasaA/tejadoArribaIzquierdo.png", true);
    }

    public void setUp(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ){
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
