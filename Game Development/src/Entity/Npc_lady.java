package Entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Npc_lady extends Entity {
	
	
	public Npc_lady(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
		
	}
	
	 public void getImage() {
	        try {
	            // Down sprites
	            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/down1.png"));
	            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/down2.png"));
	            down3 = ImageIO.read(getClass().getResourceAsStream("/NPC/down3.png"));
	            down4 = ImageIO.read(getClass().getResourceAsStream("/NPC/down4.png"));
	            down5 = ImageIO.read(getClass().getResourceAsStream("/NPC/down5.png"));
	            down6 = ImageIO.read(getClass().getResourceAsStream("/NPC/down6.png"));
	            down7 = ImageIO.read(getClass().getResourceAsStream("/NPC/down7.png"));
	            down8 = ImageIO.read(getClass().getResourceAsStream("/NPC/down8.png"));
	            
	            right1 = ImageIO.read(getClass().getResourceAsStream("/NPC/right0.png"));
	            right2 = ImageIO.read(getClass().getResourceAsStream("/NPC/right1.png"));
	            right3 = ImageIO.read(getClass().getResourceAsStream("/NPC/right2.png"));
	            right4 = ImageIO.read(getClass().getResourceAsStream("/NPC/right3.png"));
	            right5 = ImageIO.read(getClass().getResourceAsStream("/NPC/right4.png"));
	            right6 = ImageIO.read(getClass().getResourceAsStream("/NPC/right5.png"));
	            right7 = ImageIO.read(getClass().getResourceAsStream("/NPC/right6.png"));
	            right8 = ImageIO.read(getClass().getResourceAsStream("/NPC/right7.png"));

	            // Up sprites
	            up1 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile000.png"));
	            up2 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile001.png"));
	            up3 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile002.png"));
	            up4 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile003.png"));
	            up5 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile004.png"));
	            up6 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile005.png"));
	            up7 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile006.png"));
	            up8 = ImageIO.read(getClass().getResourceAsStream("/NPC/tile007.png"));

	            // Left sprites
	            left1 = ImageIO.read(getClass().getResourceAsStream("/NPC/left1.png"));
	            left2 = ImageIO.read(getClass().getResourceAsStream("/NPC/left2.png"));
	            left3 = ImageIO.read(getClass().getResourceAsStream("/NPC/left3.png"));
	            left4 = ImageIO.read(getClass().getResourceAsStream("/NPC/left4.png"));
	            left5 = ImageIO.read(getClass().getResourceAsStream("/NPC/left5.png"));
	            left6 = ImageIO.read(getClass().getResourceAsStream("/NPC/left6.png"));
	            left7 = ImageIO.read(getClass().getResourceAsStream("/NPC/left7.png"));
	            left8 = ImageIO.read(getClass().getResourceAsStream("/NPC/left8.png"));



	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void setDialogue() {
		 dialogues[0] = "i heard the developers of this game does not sleep ahahahahhahahahahahahahaha";
		 dialogues[1] = "this shit is frying my brain";
		 dialogues[2] = "not cool";
		 dialogues[3] = "i love banana bread";
	 }
	 
	 public void setAction() {
		 
		 actionLockCounter++;
		 
		 if(actionLockCounter == 120) {
			 Random random = new Random();
			 int i = random.nextInt(100)+1;
			 if(i < 25) {
				 direction = "up";
			 }
			 if(i> 25 && i <= 50) {
				 direction = "down";
			 }
			 if(i > 50 && i <= 75) {
				 direction = "left";
			 }
			 if(i > 75 && i <= 100 ) {
				 direction = "right";
			 }
			 actionLockCounter = 0;
		 }
		
	 }
	 
	 public void speak() {
		super.speak();

	 }
}
