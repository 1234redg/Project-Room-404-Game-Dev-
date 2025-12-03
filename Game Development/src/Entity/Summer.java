package Entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import Main.ClueTracker;
import Main.GamePanel;
import object.SuperObject;

public class Summer extends Entity {

    private boolean[] clueGiven = new boolean[2]; // NOW 2 CLUES
    private boolean initialConversationCompleted = false;

    private static final int KEY_DIALOGUE_INDEX = 5;
    private static final int CLUE1 = 0;
    private static final int CLUE2 = 1;
    private static final int CLUE_COUNT = 2;

    public Summer(GamePanel gp) {
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
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC4 IDLE1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/NPC4 IDLE STRETCHED.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "Charles: Hi, Sir. I’m Charles. I found a dead body inside room 404.";
        dialogues[1] = "Maybe you can tell me some important information if you have noticed something unusual happening recently.";
        dialogues[2] = "Joy: Oh, hi Mr. Charles. I’m just a visitor in this apartment. To be honest, I saw four people coming out of Room 404...";
        dialogues[3] = "I suddenly heard a strange noise, and someone came out of that room. I couldn’t see their face clearly.";
        dialogues[4] = "Charles: The killer is the one who arrived home last.";
        dialogues[5] = "Charles: Can you tell me more?";
        dialogues[6] = "Joy: Please, if you find the Flashlight and Notebook, bring them to me.";
        dialogues[7] = "Joy: They should be somewhere in the apartment.";
        dialogues[8] = "Charles: I will give them to you once I find them.";

        // CLUES
        clues[CLUE1] = "{name:D} lives next door to the {prof:A}, and the Murderer is the person who arrives last, after both {name:E} and the {prof:C}.";
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

    private boolean playerHasAllItems(String... items) {
        for (String item : items) {
            if (!playerHasItem(item)) return false;
        }
        return true;
    }

    private void removeAllItems(String... items) {
        for (String item : items) {
            removeItemFromPlayer(item);
        }
    }

    @Override
    public void speak() {

        // FIRST-TIME dialogue
        if (!initialConversationCompleted) {
            if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
                gp.ui.showMessage(dialogues[dialogueIndex]);
            } else {
                initialConversationCompleted = true;
                gp.ui.showMessage("You should find the Flashlight and Notebook to get more information.");
            }
            return;
        }

        // AFTER FIRST-TIME
        if (clueGiven[CLUE1] && clueGiven[CLUE2]) {
            gp.ui.showMessage("Click the Investigate button below to see your clues.\nPress ENTER to exit.");
            return;
        }

        if (playerHasAllItems("Flashlight", "Notebook")) {
            gp.ui.showMessage("Press ENTER to hand over the Flashlight and Notebook.");
        } else {
            gp.ui.showMessage("You should find the Flashlight and Notebook to continue.");
        }
    }

    @Override
    public void onEnterPressed() {

        // FIRST-TIME dialogue
        if (!initialConversationCompleted && dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
            gp.ui.showMessage(dialogues[dialogueIndex]);
            dialogueIndex++;

            if (dialogueIndex == KEY_DIALOGUE_INDEX && playerHasAllItems("Flashlight", "Notebook")) {
                removeAllItems("Flashlight", "Notebook");

                // GIVE BOTH CLUES
                for (int i = 0; i < CLUE_COUNT; i++) {
                    if (!clueGiven[i]) {
                        ClueTracker.getInstance().addClueTemplate(clues[i]);
                        clueGiven[i] = true;
                    }
                }

                ClueTracker.getInstance().generateResolvedClues();

                gp.ui.showMessage("You gave the Flashlight and Notebook to the lady.");
                gp.ui.showMessage("Click the Investigate button below to see your clues.");
            }

            // End conversation if no more dialogue
            boolean anyRemaining = false;
            for (int i = dialogueIndex; i < dialogues.length; i++) {
                if (dialogues[i] != null) { anyRemaining = true; break; }
            }
            if (!anyRemaining) initialConversationCompleted = true;

            return;
        }

        // AFTER FIRST-TIME
        if (initialConversationCompleted) {

            if (playerHasAllItems("Flashlight", "Notebook") && (!clueGiven[CLUE1] || !clueGiven[CLUE2])) {

                removeAllItems("Flashlight", "Notebook");

                // GIVE BOTH CLUES AGAIN IF NOT YET GIVEN
                for (int i = 0; i < CLUE_COUNT; i++) {
                    if (!clueGiven[i]) {
                        ClueTracker.getInstance().addClueTemplate(clues[i]);
                        clueGiven[i] = true;
                    }
                }

                ClueTracker.getInstance().generateResolvedClues();

                gp.ui.showMessage("You gave the Flashlight and Notebook to the lady.");
                gp.ui.showMessage("Click the Investigate button below to see your clues.");

            } else {
                gp.ui.showMessage("Click the Investigate button below to see your clues.\nPress ENTER to exit.");
            }

            gp.gameState = gp.playState;
            gp.currentNPC = -1;
        }
    }
}
