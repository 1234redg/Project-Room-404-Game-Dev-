package Entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.ClueTracker;
import Main.GamePanel;

public class Peter extends Entity {

    public Peter(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0; // NPC does NOT move

        // Initialize dialogue and clues arrays
        dialogues = new String[4];
        clues = new String[4];

        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            // Only two sprites are loaded
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc2IDLE1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc2IDLE2Stretched.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "I heard the developers of this game do not sleep ahahahahhahahahahahahahaha";
        clues[0] = "Lady: Developers don't sleep - could be involved in late-night activities";

        dialogues[1] = "This shit is frying my brain";
        clues[1] = "Lady: Stressed and overwhelmed by something";

        dialogues[2] = "Not cool";
        clues[2] = "Lady: Disapproves of recent events";

        dialogues[3] = "I love banana bread";
        clues[3] = "Lady: Has preference for banana bread";
    }

    public void setAction() {
        // NPC does not move but still animates
        spriteCounter++;
        if (spriteCounter > 100) {
            spriteNum++;
            if (spriteNum > 2) {  // Only 2 sprites loaded
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    private boolean[] clueGiven = new boolean[4];

@Override
public void speak() {
    gp.ui.showMessage(dialogues[dialogueIndex]);

    if (!clueGiven[dialogueIndex]) {
        ClueTracker.getInstance().addClue(clues[dialogueIndex]);
        clueGiven[dialogueIndex] = true;
    }

    dialogueIndex++;
    if (dialogueIndex >= dialogues.length) {
        dialogueIndex = 0;
    }
}

}
