package tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.GamePanel;

public class TileManager {
	
	GamePanel gp;	
	Tile[] tile;
	int murderTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[50]; //Create 10 kinds of tyles, water tile, wall tile and etc. 
		murderTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/MurderRoomMaps/WholeMap.txt");
	}
	
	public void getTileImage() {
	    String[] names = {
	        "00black.png",   //0
	        "1stoneWall.png", //1
	        "2footWay.png", //2
	        "3playerRoomWall.png", //3
	        "4playerRoomWall.png", //4
	        "5UpperLeft.png", //5
	        "6lower.png", //6
	        "7lowerleft.png", //7
	        "8UpWall.png",   //8
	        "9RoomLeftWall.png", //9
	        "10cRup.png",
	        "11cRdown.png",
	        "12cRtile.png",
	        "13shadowOutsideCr.png",
	        "14outsideCr.png",
	        "15shadow.png",
	        "16lowerRight.png",
	        "17kitchenTile.png",
	        "18kitchenShadow.png",
	        "19halfShadow.png",
	        "20halfShadow.png",
	        "21docTileShadow.png",
	        "22docFloor.png",
	        "23floorShadow.png",
	        "24tile.png",
	        "25upper.png",
	        "26lower.png",
	        "27lowerfirst.png",
	        "28upperfirst.png",
	        "29salaWallLower.png",
	        "30salaWallUpper.png",
	        "31salaTile.png",
	        "32upper.png",
	        "33lower.png",
	        "34teacherShadow.png",
	        "35teacherTileNormal..png",
	        "36policeShadow.png",
	        "37policeNormalTile.png",
	        "38wall.png",
	        "39wall.png",
	        "40shadow.png",
	        "41tile.png",
	        "42upper.png",
	        "43lower.png",
	        "44lower.png",
	        "45upper.png",
	        "46tile.png"
	    };

	    for (int i = 0; i < names.length; i++) {
	        tile[i] = new Tile();
	        String resourcePath = "/tiles/" + names[i];
	        try {
	            java.net.URL url = getClass().getResource(resourcePath);
	            if (url != null) {
	                tile[i].image = javax.imageio.ImageIO.read(url);
	            } else {
	                // Optionally show a one-time warning if a file is missing
	                System.err.println("Missing image: " + resourcePath);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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
