package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ClueTracker stores clues discovered during dialogue and tracks suspect status for NPCs.
 * Singleton pattern to ensure only one instance exists.
 */
public class ClueTracker {
    
    private static ClueTracker instance;
    
    private List<String> clues; // List of discovered clues, numbered as "Clue 1", "Clue 2", etc.

    // New model: for each NPC store a map of profession -> ruledOut(boolean).
    // true = ruled out ("this person is NOT this profession")
    private Map<String, Map<String, Boolean>> ruledOutMap;

    // Assigned profession per NPC (randomized each game start)
    private Map<String, String> assignedProfession;

    // Default professions used by the tracker/UI
    private final List<String> professions = Arrays.asList("Teacher", " Guard", "Doctor", "Nurse");
    
    private ClueTracker() {
        clues = new ArrayList<>();
        ruledOutMap = new HashMap<>();
        assignedProfession = new HashMap<>();
    }
    
    /**
     * Get singleton instance
     */
    public static ClueTracker getInstance() {
        if (instance == null) {
            instance = new ClueTracker();
        }
        return instance;
    }
    
    /**
     * Add a clue. If it's new, it's automatically numbered.
     */
    public void addClue(String clueText) {
        if (!clues.contains(clueText)) {
            clues.add(clueText);
            System.out.println("Clue added: " + clueText);
        }
    }
    
    /**
     * Get all clues as a numbered list
     */
    public List<String> getClues() {
        return new ArrayList<>(clues);
    }
    
    /**
     * Get a clue by index (0-based), formatted as "Clue #: text"
     */
    public String getFormattedClue(int index) {
        if (index >= 0 && index < clues.size()) {
            return "Clue " + (index + 1) + ": " + clues.get(index);
        }
        return null;
    }
    /**
     * Register an NPC for tracking (initialize profession ruled-out map)
     */
    public void registerNPC(String npcName) {
        if (!ruledOutMap.containsKey(npcName)) {
            Map<String, Boolean> m = new HashMap<>();
            for (String p : professions) m.put(p, false);
            ruledOutMap.put(npcName, m);
            assignedProfession.put(npcName, null);
        }
    }

    /**
     * Toggle ruled-out status for a given NPC + profession.
     * true means "this person is NOT this profession".
     */
    public void toggleRuledOut(String npcName, String profession) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m != null && m.containsKey(profession)) {
            m.put(profession, !m.get(profession));
        }
    }

    /**
     * Explicitly set ruled-out state for an NPC/profession
     */
    public void setRuledOut(String npcName, String profession, boolean ruledOut) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m != null && m.containsKey(profession)) {
            m.put(profession, ruledOut);
        }
    }

    /**
     * Check if NPC is ruled out for a given profession
     */
    public boolean isRuledOut(String npcName, String profession) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m == null) return false;
        return m.getOrDefault(profession, false);
    }

    /**
     * Get the list of professions (columns)
     */
    public List<String> getProfessions() {
        return new ArrayList<>(professions);
    }

    /**
     * Get assigned profession for an NPC (may be null until assigned)
     */
    public String getAssignedProfession(String npcName) {
        return assignedProfession.get(npcName);
    }

    /**
     * Assign random professions to all registered NPCs. Called at game start.
     * Professions may repeat across NPCs.
     */
    public void assignRandomProfessions() {
        Random rnd = new Random();
        List<String> profs = getProfessions();
        List<String> npcs = new ArrayList<>(ruledOutMap.keySet());

        // If we have at least as many professions as NPCs, assign uniquely by shuffling professions
        if (profs.size() >= npcs.size()) {
            List<String> shuffled = new ArrayList<>(profs);
            java.util.Collections.shuffle(shuffled, rnd);
            for (int i = 0; i < npcs.size(); i++) {
                assignedProfession.put(npcs.get(i), shuffled.get(i));
            }
            return;
        }

        // Otherwise, try to assign each profession once, then assign remaining NPCs randomly
        List<String> pool = new ArrayList<>(profs);
        java.util.Collections.shuffle(pool, rnd);
        int i = 0;
        for (; i < pool.size() && i < npcs.size(); i++) {
            assignedProfession.put(npcs.get(i), pool.get(i));
        }

        // Remaining NPCs: assign random professions (duplicates allowed)
        for (; i < npcs.size(); i++) {
            assignedProfession.put(npcs.get(i), profs.get(rnd.nextInt(profs.size())));
        }
    }
    
    /**
     * Get all registered NPCs
     */
    public List<String> getNPCNames() {
        return new ArrayList<>(ruledOutMap.keySet());
    }
    
    /**
     * Clear all data (for testing or game reset)
     */
    public void reset() {
        clues.clear();
        ruledOutMap.clear();
        assignedProfession.clear();
    }
}
