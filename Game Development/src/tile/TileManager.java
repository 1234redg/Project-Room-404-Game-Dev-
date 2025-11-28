package tile;

import java.awt.Graphics;
import java.awt.Rectangle; // Added for collision bounds
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {
	
	GamePanel gp;	
	public Tile[] tile;
	public int murderTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[50]; // Create 50 kinds of tiles (e.g., walls, floors, etc.)
		murderTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage(); // Renamed for Java convention
		loadMap("/MurderRoomMaps/WholeMap.txt");
	}
	
	public void getTileImage() { // Renamed to follow Java naming convention
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/00black.png")); // Fixed: Removed extra .png
			tile[0].collision = false; // Black tile: non-solid
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/1stoneWall.png"));
			tile[1].collision = true; // Wall: solid
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/2footWay.png"));
			tile[2].collision = false; // Floor: non-solid
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/3playerRoomWall.png"));
			tile[3].collision = true; // Wall: solid
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/4playerRoomWall.png"));
			tile[4].collision = true; // Wall: solid
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/5UpperLeft.png"));
			tile[5].collision = true; // Wall: solid
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/6lower.png"));
			tile[6].collision = true; // Wall: solid
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/7lowerleft.png"));
			tile[7].collision = true; // Wall: solid
			
			tile[8] = new Tile();
			tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/8UpWall.png"));
			tile[8].collision = true; // Wall: solid
			
			tile[9] = new Tile();
			tile[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/9RoomLeftWall.png"));
			tile[9].collision = true; // Wall: solid
			
			tile[10] = new Tile();
			tile[10].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/10cRup.png"));
			tile[10].collision = false; // Assuming non-wall: non-solid (adjust if needed)
			
			tile[11] = new Tile();
			tile[11].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/11cRdown.png"));
			tile[11].collision = false;
			
			tile[12] = new Tile();
			tile[12].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/12cRtile.png"));
			tile[12].collision = false;
			
			tile[13] = new Tile();
			tile[13].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/13shadowOutsideCr.png"));
			tile[13].collision = false;
			
			tile[14] = new Tile();
			tile[14].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/14outsideCr.png"));
			tile[14].collision = false;
			
			tile[15] = new Tile();
			tile[15].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/15shadow.png"));
			tile[15].collision = false;
			
			tile[16] = new Tile();
			tile[16].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/16lowerRight.png"));
			tile[16].collision = true; // Wall: solid
			
			tile[17] = new Tile();
			tile[17].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/17kitchenTile.png"));
			tile[17].collision = false;
			
			tile[18] = new Tile();
			tile[18].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/18kitchenShadow.png"));
			tile[18].collision = false;
			
			tile[19] = new Tile();
			tile[19].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/19halfShadow.png"));
			tile[19].collision = false;
			
			tile[20] = new Tile();
			tile[20].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/20halfShadow.png"));
			tile[20].collision = false;
			
			tile[21] = new Tile();
			tile[21].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/21docTileShadow.png"));
			tile[21].collision = false;
			
			tile[22] = new Tile();
			tile[22].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/22docFloor.png"));
			tile[22].collision = false;
			
			tile[23] = new Tile();
			tile[23].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/23floorShadow.png"));
			tile[23].collision = false;
			
			tile[24] = new Tile();
			tile[24].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/24tile.png"));
			tile[24].collision = false;
			
			tile[25] = new Tile();
			tile[25].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/25upper.png"));
			tile[25].collision = true; // Wall: solid
			
			tile[26] = new Tile();
			tile[26].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/26lower.png"));
			tile[26].collision = true; // Wall: solid
			
			tile[27] = new Tile();
			tile[27].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/27lowerfirst.png"));
			tile[27].collision = true; // Wall: solid
			
			tile[28] = new Tile();
			tile[28].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/28upperfirst.png"));
			tile[28].collision = true; // Wall: solid
			
			tile[29] = new Tile();
			tile[29].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/29salaWallLower.png"));
			tile[29].collision = true; // Wall: solid
			
			tile[30] = new Tile();
			tile[30].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/30salaWallUpper.png"));
			tile[30].collision = true; // Wall: solid
			
			tile[31] = new Tile();
			tile[31].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/31salaTile.png"));
			tile[31].collision = false;
			
			tile[32] = new Tile();
			tile[32].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/32upper.png"));
			tile[32].collision = true; // Wall: solid
			
			tile[33] = new Tile();
			tile[33].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/33lower.png"));
			tile[33].collision = true; // Wall: solid
			
			tile[34] = new Tile();
			tile[34].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/34teacherShadow.png"));
			tile[34].collision = false;
			
			tile[35] = new Tile();
			tile[35].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/35teacherTileNormal.png"));
			tile[35].collision = false;
			
			tile[36] = new Tile();
			tile[36].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/36policeShadow.png"));
			tile[36].collision = false;
			
			tile[37] = new Tile();
			tile[37].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/37policeNormalTile.png"));
			tile[37].collision = false;
			
			tile[38] = new Tile();
			tile[38].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/38wall.png"));
			tile[38].collision = true; // Wall: solid
			
			tile[39] = new Tile();
			tile[39].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/39wall.png"));
			tile[39].collision = true; // Wall: solid
			
			tile[40] = new Tile();
			tile[40].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/40shadow.png"));
			tile[40].collision = false;
			
			tile[41] = new Tile();
			tile[41].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/41tile.png"));
			tile[41].collision = false;
			
			tile[42] = new Tile();
			tile[42].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/42upper.png"));
			tile[42].collision = true; // Wall: solid
			
			tile[43] = new Tile();
			tile[43].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/43lower.png"));
			tile[43].collision = true; // Wall: solid
			
			tile[44] = new Tile();
			tile[44].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/44lower.png"));
			tile[44].collision = true; // Wall: solid
			
			tile[45] = new Tile();
			tile[45].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/45upper.png"));
			tile[45].collision = true; // Wall: solid
			
			tile[46] = new Tile();
			tile[46].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/46tile.png"));
			tile[46].collision = false;
			
			  		  
			
		}catch(IOException e) {
			e.printStackTrace();
		}
				
	}
	    
	    

	     

	public void loadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int row = 0;

	        while (row < gp.maxWorldRow) {
	            String line = br.readLine();
	            if (line == null) break;

	            String[] numbers = line.split(" ");
	            for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
	                int num = Integer.parseInt(numbers[col]);
	                murderTileNum[col][row] = num;
	            }

	            row++;
	        }

	        br.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	 
	public void draw(Graphics g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow  < gp.maxWorldRow) {
			
			int tileNum = murderTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image ,screenX, screenY, gp.tileSize, gp.tileSize, null);
				
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
	}
}



