package Main;

/**
 * CLUE TRACKER USAGE GUIDE
 * 
 * The Clue Tracker system allows you to track clues discovered during NPC dialogue
 * and manage suspect status (ruling out NPCs based on gathered evidence).
 * 
 * ===== BASIC USAGE =====
 * 
 * 1. ADD A CLUE (in dialogue code):
 *    ClueTracker tracker = ClueTracker.getInstance();
 *    tracker.addClue("Suspect A has a weapon");
 *    // Clue automatically gets numbered: "Clue 1: Suspect A has a weapon"
 * 
 * 2. TOGGLE NPC SUSPECT STATUS (when you rule out an NPC):
 *    ClueTracker tracker = ClueTracker.getInstance();
 *    tracker.toggleNPCStatus("Npc_lady");  // true = ruled out, false = suspect
 * 
 * 3. OPEN CLUE TRACKER UI:
 *    Press J key to open/close the Clue Tracker popup
 *    - Top section shows all discovered clues (numbered)
 *    - Bottom section shows NPCs with checkboxes
 *    - ☐ = suspect, ☑ = ruled out
 *    - Click checkbox to toggle suspect status
 * 
 * ===== EXAMPLE INTEGRATION IN NPC DIALOGUE =====
 * 
 * In your NPC class (e.g., Npc_lady.java):
 * 
 *     public void setDialogue() {
 *         dialogues[0] = "I saw someone near the weapon!";
 *         // When this dialogue plays, add the clue:
 *     }
 *     
 *     public void speakDialogue(int index) {
 *         // Play dialogue
 *         System.out.println(dialogues[index]);
 *         
 *         // Add clue after dialogue
 *         if (index == 0) {
 *             ClueTracker.getInstance().addClue("Someone near the weapon");
 *         }
 *     }
 * 
 * ===== CUSTOMIZING COLORS =====
 * 
 * In GamePanel or where you have access to clueTrackerUI:
 * 
 *     ClueTrackerUI ui = gp.clueTrackerUI;
 *     ui.bgColor = new Color(30, 30, 30);           // Dark background
 *     ui.textColor = new Color(255, 255, 255);      // White text
 *     ui.checkboxCheckedColor = new Color(0, 200, 100); // Custom checked color
 * 
 * ===== AVAILABLE CUSTOMIZABLE COLORS =====
 * 
 *     ui.bgColor                    - Popup background
 *     ui.textColor                  - Text color
 *     ui.headerColor                - Section headers (Clues, Suspects)
 *     ui.borderColor                - Border and checkbox outline
 *     ui.checkboxUncheckedColor     - Unchecked checkbox fill
 *     ui.checkboxCheckedColor       - Checked checkbox fill + checkmark
 * 
 * ===== METHODS =====
 * 
 * ClueTracker (Singleton):
 *   - getInstance()                   → Get singleton instance
 *   - addClue(String clueText)        → Add new clue
 *   - getClues()                      → Get list of clues
 *   - getFormattedClue(int index)     → Get numbered clue (e.g., "Clue 1: ...")
 *   - registerNPC(String npcName)     → Register NPC for tracking
 *   - toggleNPCStatus(String npcName) → Toggle ruled out status
 *   - setNPCRuledOut(String, boolean) → Set ruled out explicitly
 *   - isRuledOut(String npcName)      → Check if NPC is ruled out
 *   - getNPCNames()                   → Get list of all registered NPCs
 *   - reset()                         → Clear all data
 * 
 * ClueTrackerUI:
 *   - toggleVisibility()              → Show/hide popup
 *   - setVisible(boolean v)           → Explicitly set visibility
 *   - isVisible()                     → Check if popup is visible
 *   - draw(Graphics2D g2)             → Draw the popup (called automatically)
 * 
 */
public class ClueTrackerUsageGuide {
    // This is just documentation, not executable code
}
