package HUD;

import java.awt.Graphics2D;
import Main.GamePanel;
import Main.GameUI;

public class HUDManager {

    private HUD profileHUD;
    private HUDheart heartHUD;
    private HUDBag bagHUD;             
    private HUDObjectives objectivesHUD;
    private HUDInvestigate investigateHUD;  // ✅ NEW HUD
    private GameUI gameUI; 
    public String currentDialogue;

    public HUDManager(GamePanel gp) {
        // Initialize all HUD components 
        //heartHUD = new HUDheart(gp);
        profileHUD = new HUD(gp);
        bagHUD = new HUDBag(gp);
        objectivesHUD = new HUDObjectives(gp);
        investigateHUD = new HUDInvestigate(gp);  // ✅ Initialize Investigate HUD
        gameUI = new GameUI(gp); 
    }

    // Called every frame by your game loop
    public void update() {
        //heartHUD.update();
        profileHUD.update();
        bagHUD.update();
        objectivesHUD.update();
        investigateHUD.update();  // ✅ Update Investigate HUD
    }

    // Called every frame for rendering
    @SuppressWarnings("static-access")
    public void draw(Graphics2D g2) {
        // Draw HUD layers in proper order
        //heartHUD.draw(g2);              
        profileHUD.draw(g2);            
        bagHUD.draw(g2);                
        objectivesHUD.draw(g2);
        investigateHUD.draw(g2);        // ✅ Draw Investigate HUD
        gameUI.draw(g2);                
    }

    // Detect clicks and pass to the correct HUD component
    public void handleClick(int mouseX, int mouseY) {
        // Example of click detection:
        if (objectivesHUD.isClicked(mouseX, mouseY)) {
            System.out.println("Objectives clicked!");
        }
        if (investigateHUD.isClicked(mouseX, mouseY)) {
            System.out.println("Investigate clicked!");
        }
        // Later you can add bagHUD.handleClick(mouseX, mouseY);
    }
}
