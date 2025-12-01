package Entity;

/**
 * CLUE INTEGRATION TEMPLATE FOR NPCs
 * 
 * This template shows how to add clue tracking to any NPC dialogue.
 * Copy this pattern to your NPC classes (Npc_lady, etc.)
 * 
 * ===== STEP 1: ADD CLUES ARRAY TO ENTITY.JAVA =====
 * 
 * Already done! The Entity base class now has:
 *     String clues[] = new String[30];  // clues associated with each dialogue
 * 
 * ===== STEP 2: SET UP CLUES IN YOUR NPC'S setDialogue() METHOD =====
 * 
 * Example in Npc_lady.java:
 * 
 *     public void setDialogue() {
 *         // Dialogue 0
 *         dialogues[0] = "I saw someone near the weapon storage";
 *         clues[0] = "Lady: Witnessed someone at weapon storage";
 *         
 *         // Dialogue 1 (no clue)
 *         dialogues[1] = "How are you today?";
 *         clues[1] = null;  // or just leave it null by default
 *         
 *         // Dialogue 2
 *         dialogues[2] = "The suspect left around midnight";
 *         clues[2] = "Lady: Suspect was active at midnight";
 *     }
 * 
 * ===== STEP 3: HOW IT WORKS =====
 * 
 * When the player talks to an NPC:
 * 1. Entity.speak() is called
 * 2. It displays dialogues[dialogueIndex]
 * 3. If clues[dialogueIndex] is not null, it automatically adds it to ClueTracker
 * 4. The clue appears in the Clue Tracker popup (press J)
 * 
 * ===== EXAMPLE NPCS =====
 * 
 * === Doctor NPC ===
 * public void setDialogue() {
 *     dialogues[0] = "I examined the victim - they showed signs of poison";
 *     clues[0] = "Doctor: Victim poisoned";
 *     
 *     dialogues[1] = "The toxin used was rare and expensive";
 *     clues[1] = "Doctor: Rare toxin used - suggests premeditation";
 * }
 * 
 * === Guard NPC ===
 * public void setDialogue() {
 *     dialogues[0] = "I saw three people enter that night";
 *     clues[0] = "Guard: Three people entered the area";
 *     
 *     dialogues[1] = "One of them was carrying a briefcase";
 *     clues[1] = "Guard: One person carried a briefcase";
 * }
 * 
 * === Nurse NPC ===
 * public void setDialogue() {
 *     dialogues[0] = "The patient's time of death was between 10 PM and midnight";
 *     clues[0] = "Nurse: Time of death 10 PM - midnight";
 *     
 *     dialogues[1] = "I was on duty the whole night";
 *     clues[1] = "Nurse: Claims to have been on duty all night";
 * }
 * 
 * ===== TIPS =====
 * 
 * 1. Keep clues SHORT and SPECIFIC
 *    ❌ BAD: "They said stuff about things"
 *    ✅ GOOD: "Guard: Saw suspect leaving at 11 PM"
 * 
 * 2. Include NPC NAME in clue for clarity
 *    ✅ "Lady: Heard strange noise at midnight"
 *    (Makes it clear which NPC gave which clue)
 * 
 * 3. Set clue to null for small talk
 *    dialogues[0] = "How's the weather?";
 *    clues[0] = null;  // No clue for casual dialogue
 * 
 * 4. One dialogue = one clue maximum
 *    If one dialogue reveals multiple important facts, make separate dialogues
 * 
 * ===== AUTOMATIC FEATURES =====
 * 
 * ✓ Clues are automatically numbered (Clue 1, Clue 2, etc.)
 * ✓ Duplicate clues are NOT added (system checks for duplicates)
 * ✓ No compilation changes needed - just set the clues[] array
 * ✓ Works with all NPCs that extend Entity
 */
public class ClueIntegrationTemplate {
    // This is documentation only
}
