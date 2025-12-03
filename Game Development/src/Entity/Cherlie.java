package Entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import Main.ClueTracker;
import Main.GamePanel;
import object.SuperObject;

public class Cherlie extends Entity {

    private boolean[] clueGiven = new boolean[1]; // only 1 clue for Cherlie
    private boolean initialConversationCompleted = false;

    private static final int KEY_DIALOGUE_INDEX = 5; // dialogue line asking for Paper
    private static final int CLUE_INDEX = 0;         // index of clue to give

    public Cherlie(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;

        dialogues = new String[10];
        clues = new String[10];

        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc1IDLE1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc1IDLE2stretched.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "Charles: Hi, Ms. I am Charles, an investigator. Do you have any idea who went out of Room 404 before the strange noise happened?";
        dialogues[1] = "Mae: Oh, hi Mr. Charles. I’m the admin of this apartment, and there was a party in Room 404 last night. Later, I just saw 2 person.";
        dialogues[2] = "Charles: Interesting... Did you know who came out last from that room?";
        dialogues[3] = "Mae: I don’t know because I was busy at that time. I only saw two people I knew then.";
        dialogues[4] = "Charles: Can you tell me more?";
        dialogues[5] = "Mae: Maybe. If you can find my paper, I will share, that’s what kept me busy that night. I kept looking for it, but I couldn’t find it.";
        dialogues[6] = "Charles: I'll try my best to find it.";

        // Template clue string using random placeholders for both names and professions
        // Original: "John is not the teacher. He arrives home after Mark but before the doctor."
        clues[CLUE_INDEX] = "{name:C} is not the {prof:D} and arrives earlier than the {prof:E}.";
    }

    public void setAction() {
        spriteCounter++;
        if (spriteCounter > 100) {
            spriteNum++;
            if (spriteNum > 2) spriteNum = 1;
            spriteCounter = 0;
        }
    }

    private boolean playerHasItem(String itemName) {
        if (gp == null || gp.player == null || gp.player.inventory == null) return false;
        for (SuperObject s : gp.player.inventory) {
            if (s != null && itemName.equals(s.name)) return true;
        }
        return false;
    }

    private boolean removeItemFromPlayer(String itemName) {
        if (gp == null || gp.player == null || gp.player.inventory == null) return false;
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            SuperObject s = gp.player.inventory.get(i);
            if (s != null && itemName.equals(s.name)) {
                gp.player.inventory.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void speak() {
        // FIRST-TIME conversation
        if (!initialConversationCompleted) {
            if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
                gp.ui.showMessage(dialogues[dialogueIndex]);
            } else {
                initialConversationCompleted = true;
                gp.ui.showMessage("You should find the Paper to get more information.");
            }
            return;
        }

        // AFTER FIRST-TIME
        if (clueGiven[CLUE_INDEX]) {
            gp.ui.showMessage("Click the Investigate button below to see your clue.\nPress ENTER to exit.");
            return;
        }

        if (playerHasItem("Paper")) {
            gp.ui.showMessage("Press ENTER to hand over the Paper.");
        } else {
            gp.ui.showMessage("You should find the Paper to continue.");
        }
    }

    @Override
    public void onEnterPressed() {
        // FIRST-TIME dialogue sequence
        if (!initialConversationCompleted && dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
            gp.ui.showMessage(dialogues[dialogueIndex]);
            dialogueIndex++;
            
            if (dialogueIndex == KEY_DIALOGUE_INDEX && playerHasItem("Paper") && !clueGiven[CLUE_INDEX]) {
                boolean removed = removeItemFromPlayer("Paper");
                if (removed) {
                    // Randomized clue: use template
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();
                    clueGiven[CLUE_INDEX] = true;
                    gp.ui.showMessage("You gave the Paper to the lady.");
                    gp.ui.showMessage("Click the Investigate button below to see your clue.");
                }
            }

            // Mark conversation complete if done
            boolean anyRemaining = false;
            for (int i = dialogueIndex; i < dialogues.length; i++) {
                if (dialogues[i] != null) { anyRemaining = true; break; }
            }
            if (!anyRemaining) initialConversationCompleted = true;

            return;
        }

        // AFTER FIRST-TIME
        if (initialConversationCompleted) {
            if (clueGiven[CLUE_INDEX]) {
                gp.ui.showMessage("Click the Investigate button below to see your clue.\nPress ENTER to exit.");
            } else if (playerHasItem("Paper")) {
                boolean removed = removeItemFromPlayer("Paper");
                if (removed) {
                    // Randomized clue: use template
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();
                    clueGiven[CLUE_INDEX] = true;
                    gp.ui.showMessage("You gave the Paper to the lady.");
                    gp.ui.showMessage("Click the Investigate button below to see your clue.");
                } else {
                    gp.ui.showMessage("You no longer have the Paper.");
                }
            } else {
                gp.ui.showMessage("You should find the Paper to continue.");
            }

            gp.gameState = gp.playState;
            gp.currentNPC = -1;
        }
    }
}