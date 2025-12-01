package Main;

public class Quest {
    private String id;
    private String description;
    private String itemTarget; // item name to look for (e.g., "key")
    private int targetCount;
    private int currentCount;
    private boolean completed;
    private boolean oneTime; // if true, cannot be repeated

    public Quest(String id, String description, String itemTarget, int targetCount, boolean oneTime) {
        this.id = id;
        this.description = description;
        this.itemTarget = itemTarget;
        this.targetCount = targetCount;
        this.currentCount = 0;
        this.completed = false;
        this.oneTime = oneTime;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public int getTargetCount() { return targetCount; }
    public int getCurrentCount() { return currentCount; }
    public boolean isCompleted() { return completed; }
    public String getItemTarget() { return itemTarget; }

    /** Increment progress by 1 (or amount). Returns true if progress changed. */
    public boolean increment(int amount) {
        if (completed) return false;
        currentCount += amount;
        System.out.println("[QUEST] " + description + " - Progress: " + currentCount + "/" + targetCount);
        if (currentCount >= targetCount) {
            currentCount = targetCount;
            completed = true;
            System.out.println("[QUEST] " + description + " - COMPLETED!");
        }
        return true;
    }
}
