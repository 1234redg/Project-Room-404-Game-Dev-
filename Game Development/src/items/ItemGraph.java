package items;

import java.util.*;

// ItemGraph manages prerequisites and pickup rules for your items
public class ItemGraph {

    // Map of item name -> Prerequisite item names
    private Map<String, Set<String>> prereqs = new HashMap<>();
    
    // Register an item with a set of prerequisite item names
    public void addItem(String item, String... prerequisites) {
        if (!prereqs.containsKey(item)) {
            prereqs.put(item, new HashSet<>());
        }
        Collections.addAll(prereqs.get(item), prerequisites);
    }
    
    // Check if the player can pick up the item (all prereqs in inventory)
    public boolean canPickUp(String item, Set<String> inventory) {
        Set<String> required = prereqs.getOrDefault(item, Collections.emptySet());
        return inventory.containsAll(required);
    }
    
    // (Optional) For debugging: list all prerequisites for an item
    public Set<String> getPrerequisites(String item) {
        return prereqs.getOrDefault(item, Collections.emptySet());
    }
}