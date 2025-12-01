package HUD;

import java.awt.Graphics2D;

import Main.GamePanel;

public class HUDManager {

    private HUD profileHUD;
    private HUDheart heartHUD;
	public String currentDialogue;


    public HUDManager(GamePanel gp) {
    	
        // Initialize all HUD components 
        heartHUD = new HUDheart(gp);
        profileHUD = new HUD(gp);
    }

    // Called every frame by your game loop
    public void update() {
        heartHUD.update();
        profileHUD.update();;
    }

    // Called every frame for rendering
    public void draw(Graphics2D g2) {
        heartHUD.draw(g2);
        profileHUD.draw(g2);
    }

    // Detect clicks and pass to the correct HUD component
    public void handleClick(int mouseX, int mouseY) {
       // inventoryHUD.handleClick(mouseX, mouseY);
    }
}
