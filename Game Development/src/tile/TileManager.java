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
		
		tile = new Tile[15]; //Create 10 kinds of tyles, water tile, wall tile and etc. 
		murderTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadRoom();
	}
	
	public void getTileImage() {
	    String[] names = {
	        "wall.png",   //0
	        "Up.png", //1
	        "1.3.png", //2
	        "2.1.png", //3
	        "Floor4.png", //4
	        "2.4.png", //5
	        "3.1.png", //6
	        "Up.png", //7
	        "Floor3.png",   //8
	        "sLeft.png", //9
	        "4.3.png", //10
	        "4.png",   //11
	        "Floor4.png",//12
	        "left.png", //13
	        "Up.png"    //14
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

	public void loadRoom() {
		try {
			InputStream is = getClass().getResourceAsStream("/MurderRoomMap/MurderRoom.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {//Change this later on for room size adjustment
				
				String line = br.readLine(); //Read text file
				
				while(col < gp.maxScreenCol) {
					
					String numbers[] = line.split(" ");
				 	
					int num = Integer.parseInt(numbers[col]);
					
					murderTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row  < gp.maxScreenRow) {
			
			int tileNum = murderTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image ,x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
	}
}
