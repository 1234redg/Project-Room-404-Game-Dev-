package Entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import Main.ClueTracker;
import Main.GamePanel;
import object.SuperObject;

public class Kyle extends Entity {

    private boolean[] clueGiven = new boolean[10];
    private boolean initialConversationCompleted = false;

    // Named constants to avoid index confusion
    private static final int KNIFE_DIALOGUE_INDEX = 5; // dialogue line that asks for the knife
    private static final int CLUE_INDEX = 0; // index in clues[] used when adding the discovered clue

    public Kyle(GamePanel gp) {
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
            down1 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc3IDLE1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/NPC/npc3IDLE2Stretched.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {

        dialogues[0] = "Charles: Hi, Sir. I’m Charles. I heard a strange noise in this room last night.";
        dialogues[1] = "Mark: Oh, hi Mr. Charles. I’m a cleaner in this apartment, and from what I know, there was a party in Room 404 last night. I was cleaning at that time when I heard a weird noise, but I didn’t dare go inside because I was a bit afraid.";
        dialogues[2] = "Charles: Is that so? Do you know who was in the room during the party last night?";
        dialogues[3] = "Mark: Based on what I can remember, there was a jail guard, a nurse, a teacher, a doctor, and my friend, who works in maintenance. But what really puzzles me is that I haven’t seen my friend at all since last night.";
        dialogues[4] = "Charles: Can you tell me more?";
        dialogues[5] = "Mark: Please, find my friend first or any thing that will led me to him and I will tell you more.";
        dialogues[6] = "Charles: I'll try to find your friend but I am really worried on what really happened that night in room 404.";

        // Changed to a template using the ClueTracker placeholder syntax.
        // {prof:A} and {prof:B} will be replaced with professions at runtime.
        clues[0] = "{name:A} arrives at their room after the {prof:B}, but before the {prof:C}.";
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

        // FIRST conversation (line-by-line)
        if (!initialConversationCompleted) {
            if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
                gp.ui.showMessage(dialogues[dialogueIndex]);
            } else {
                // Finished first-time dialogue
                initialConversationCompleted = true;
                gp.ui.showMessage("You should look for the knife.");
            }
            return;
        }

        // SECOND conversation + beyond

        // If knife was already handed over
        if (clueGiven[CLUE_INDEX]) {
            gp.ui.showMessage("Click the Investigate button below to see your first clue.");
            return;
        }

        // If player has the knife right now
        if (playerHasItem("Knife")) {
            gp.ui.showMessage("I found a dead body inside the room. Maybe, its your friend. I picked up this knife for proof. Press ENTER to hand over the knife and check your clue tracker below.");
        } else {
            gp.ui.showMessage("You should look for the knife.");
        }
    }

    @Override
    public void onEnterPressed() {
        // Already completed first conversation: handle giving the knife or thanking.
        if (initialConversationCompleted) {

            // Knife already handed over
            if (clueGiven[CLUE_INDEX]) {
                gp.ui.showMessage("Thank you again. Click the Investigate button below to see your first clue.");
                gp.gameState = gp.playState;
                gp.currentNPC = -1;
                return;
            }

            // Prompt knife dialogue
            gp.ui.showMessage(dialogues[KNIFE_DIALOGUE_INDEX]);

            if (playerHasItem("Knife")) {
                boolean removed = removeItemFromPlayer("Knife");

                if (removed) {
                    // Add the clue template (not a resolved string). Generate resolved clues immediately so the UI can show it.
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();

                    clueGiven[CLUE_INDEX] = true;

                    gp.ui.showMessage("Thank you. This really helps.");
                    gp.ui.showMessage("Click the Investigate button below to see your first clue.");
                    gp.ui.showMessage("Press ENTER to exit.");

                    // Auto–exit after handing over the knife
                    gp.gameState = gp.playState;
                    gp.currentNPC = -1;
                    return;
                } else {
                    gp.ui.showMessage("You no longer have the Knife.");
                }
            } else {
                gp.ui.showMessage("You should look for the knife.");
            }

            gp.gameState = gp.playState;
            gp.currentNPC = -1;
            return;
        }

        // FIRST-TIME line-by-line conversation
        if (dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null) {
            gp.ui.showMessage(dialogues[dialogueIndex]);

            // Check Knife step at KNIFE_DIALOGUE_INDEX
            if (dialogueIndex == KNIFE_DIALOGUE_INDEX && playerHasItem("Knife") && !clueGiven[CLUE_INDEX]) {
                boolean removed = removeItemFromPlayer("Knife");
                if (removed) {
                    // Add the clue template (not a resolved string). Generate resolved clues immediately so the UI can show it.
                    ClueTracker.getInstance().addClueTemplate(clues[CLUE_INDEX]);
                    ClueTracker.getInstance().generateResolvedClues();

                    clueGiven[CLUE_INDEX] = true;

                    gp.ui.showMessage("Thank you. This really helps.");
                    gp.ui.showMessage("Click the Investigate button below to see your first clue.");
                    gp.ui.showMessage("Press ENTER to exit.");

                    gp.gameState = gp.playState;
                    gp.currentNPC = -1;
                    return;
                } else {
                    gp.ui.showMessage("You no longer have the Knife.");
                }
            }

            dialogueIndex++;

            // Check if finished all dialogue lines
            boolean anyRemaining = false;
            for (int i = dialogueIndex; i < dialogues.length; i++) {
                if (dialogues[i] != null) {
                    anyRemaining = true;
                    break;
                }
            }
            if (!anyRemaining) initialConversationCompleted = true;

            return;
        }

        // Safety exit
        gp.gameState = gp.playState;
        gp.currentNPC = -1;
    }
}