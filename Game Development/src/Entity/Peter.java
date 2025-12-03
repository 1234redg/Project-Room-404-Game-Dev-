package Entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import Main.ClueTracker;
import Main.GamePanel;
import object.SuperObject;

public class Peter extends Entity {

    private boolean[] clueGiven = new boolean[10];
    private boolean initialConversationCompleted = false;

    // Named constants
    private static final int KEY_DIALOGUE_INDEX = 5; // dialogue line that asks for the Key
    private static final int CLUE_INDEX = 0;         // index in clues[] used when adding the discovered clue

    public Peter(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0; // NPC does NOT move

        dialogues = new String[10];
        clues = new String[10];

        getImage();
        setDialogue();
    }

    public void getImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc2IDLE1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc2IDLE2Stretched.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "Charles: Hi, Sir. I’m Charles. There is a crime scene at room 404, and I just want to ask if you have any idea what happened there.";
        dialogues[1] = "John: Hi Mr. Charles. I’m a worker in this apartment, and from what I know, there was a party in Room 404 last night. I was walking at that time in the hallway when I heard a weird noise, but I didn’t dare go inside because I was a bit afraid.";
        dialogues[2] = "Charles: Is that so? Did you know any of the people who were in Room 404 during the party last night?";
        dialogues[3] = "John: Hmmmm... let me remember. From what I can recall, I saw {name:A} and the {prof:B} that time."; // you can choose to randomize these too
        dialogues[4] = "Charles: Can you tell me more?";
        dialogues[5] = "John: I will tell something more if you can find my key I lost somewhere in the rooms of this apartment.";
        dialogues[6] = "Charles: Okay!";

        // Template clue string, with random name/profession placeholders:
        // "Joy gets her room after the nurse, but she is not the last to arrive. The last person who came out of Room 404 is the killer."
        clues[CLUE_INDEX] = "The {prof:A} arrives immediately before {name:B}.";
    }

    public void setAction() {
        // NPC idle animation
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
        // FIRST-TIME conversation (line-by-line)
        if (!initialConversationCompleted) {
            if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
                gp.ui.showMessage(dialogues[dialogueIndex]);
            } else {
                // Finished first-time dialogue
                initialConversationCompleted = true;
                gp.ui.showMessage("You should look for the Key.");
            }
            return;
        }

        // SECOND conversation + beyond
        if (clueGiven[CLUE_INDEX]) {
            gp.ui.showMessage("Click the Investigate button below to see your first clue.\nPress ENTER to exit.");
            return;
        }

        // Player has the Key
        if (playerHasItem("Key")) {
            gp.ui.showMessage("Press ENTER to hand over the Key.");
        } else {
            gp.ui.showMessage("You should look for the Key.");
        }
    }

    @Override
    public void onEnterPressed() {
        // Already completed first conversation
        if (initialConversationCompleted) {
            if (clueGiven[CLUE_INDEX]) {
                gp.ui.showMessage("Click the Investigate button below to see your first clue.\nPress ENTER to exit.");
                gp.gameState = gp.playState;
                gp.currentNPC = -1;
                return;
            }

            if (playerHasItem("Key")) {
                boolean removed = removeItemFromPlayer("Key");
                if (removed) {
                    // Template clues: randomized per-game
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();
                    clueGiven[CLUE_INDEX] = true;

                    gp.ui.showMessage("Thank you for handing over the Key.");
                    gp.ui.showMessage("Click the Investigate button below to see your first clue.");
                    gp.ui.showMessage("Press ENTER to exit.");

                    gp.gameState = gp.playState;
                    gp.currentNPC = -1;
                    return;
                } else {
                    gp.ui.showMessage("You no longer have the Key.");
                    // allow the player to exit after being told they no longer have the key
                    gp.gameState = gp.playState;
                    gp.currentNPC = -1;
                    return;
                }
            } else {
                // Player doesn't have the Key: show hint and EXIT the dialogue so player can continue exploring
                gp.ui.showMessage("You should look for the Key.");
                gp.gameState = gp.playState;
                gp.currentNPC = -1;
                return;
            }
        }

        // FIRST-TIME line-by-line conversation
        if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
            gp.ui.showMessage(dialogues[dialogueIndex]);
            dialogueIndex++;

            // Check immediately if player already has Key
            if (playerHasItem("Key") && !clueGiven[CLUE_INDEX]) {
                boolean removed = removeItemFromPlayer("Key");
                if (removed) {
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();
                    clueGiven[CLUE_INDEX] = true;

                    gp.ui.showMessage("Thank you for handing over the Key.");
                    gp.ui.showMessage("Click the Investigate button below to see your first clue.");
                    gp.ui.showMessage("Press ENTER to exit.");

                    gp.gameState = gp.playState;
                    gp.currentNPC = -1;
                }
            }

            // Mark conversation complete if all lines exhausted
            boolean anyRemaining = false;
            for (int i = dialogueIndex; i < dialogues.length; i++) {
                if (dialogues[i] != null) { anyRemaining = true; break; }
            }
            if (!anyRemaining) initialConversationCompleted = true;

            return;
        }

        // Safety exit
        gp.gameState = gp.playState;
        gp.currentNPC = -1;
    }
}