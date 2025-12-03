package Main;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ClueTracker {
    private static ClueTracker instance;

    private List<String> clueTemplates;
    private List<String> resolvedClues;
    private List<String> clues;

    private Map<String, Map<String, Boolean>> ruledOutMap;
    private Map<String, String> assignedProfession;
    private Map<String, String> guessedProfession;

    private final List<String> professions = Arrays.asList("Teacher", "Guard", "Doctor", "Nurse", "Murderer");

    private List<String> trackedNames;
    private Random rnd;

    private ClueTracker() {
        clueTemplates = new ArrayList<>();
        resolvedClues = new ArrayList<>();
        clues = new ArrayList<>();
        ruledOutMap = new HashMap<>();
        assignedProfession = new HashMap<>();
        guessedProfession = new HashMap<>();
        trackedNames = new ArrayList<>();
        rnd = new Random();

        setTrackedNames(Arrays.asList("Cherlie", "Kyle", "Summer", "Peter", "Red"));
    }

    public static ClueTracker getInstance() {
        if (instance == null) instance = new ClueTracker();
        return instance;
    }

    public void addClueTemplate(String template) {
        if (template == null) return;
        clueTemplates.add(template);
    }

    public void clearClueTemplates() {
        clueTemplates.clear();
        resolvedClues.clear();
        clues.clear();
    }

    public void generateResolvedClues() {
        if (trackedNames == null || trackedNames.isEmpty()) {
            setTrackedNames(Arrays.asList("Cherlie", "Kyle", "Summer", "Peter", "Red"));
        }
        resolvedClues.clear();

        List<String> namesPool = getNPCNames();
        List<String> profsPool = getProfessions();

        for (String template : clueTemplates) {
            Set<String> nameKeys = findKeys(template, "\\{name:([^}]+)\\}");
            Set<String> profKeys = findKeys(template, "\\{prof:([^}]+)\\}");

            Map<String, String> nameKeyToChosen = new HashMap<>();
            Map<String, String> profKeyToChosen = new HashMap<>();

            List<String> availableNames = new ArrayList<>(namesPool);
            Collections.shuffle(availableNames, rnd);

            List<String> availableProfs = new ArrayList<>(profsPool);
            Collections.shuffle(availableProfs, rnd);

            int nameIndex = 0;
            for (String key : nameKeys) {
                if (availableNames.isEmpty()) {
                    nameKeyToChosen.put(key, "Unknown");
                } else {
                    String chosen = (nameIndex < availableNames.size()) ?
                            availableNames.get(nameIndex++) :
                            availableNames.get(rnd.nextInt(availableNames.size()));
                    nameKeyToChosen.put(key, chosen);
                }
            }

            int profIndex = 0;
            for (String key : profKeys) {
                if (availableProfs.isEmpty()) {
                    profKeyToChosen.put(key, "Unknown");
                } else {
                    String chosen = (profIndex < availableProfs.size()) ?
                            availableProfs.get(profIndex++) :
                            availableProfs.get(rnd.nextInt(availableProfs.size()));
                    profKeyToChosen.put(key, chosen);
                }
            }

            String resolved = template;

            for (Map.Entry<String, String> e : nameKeyToChosen.entrySet()) {
                resolved = resolved.replace("{name:" + e.getKey() + "}", e.getValue());
            }
            for (Map.Entry<String, String> e : profKeyToChosen.entrySet()) {
                resolved = resolved.replace("{prof:" + e.getKey() + "}", e.getValue());
            }
            resolved = resolved.replace("{killerName}",
                    findNameByProfession("Murderer") == null ?
                            "Unknown" :
                            findNameByProfession("Murderer"));

            Pattern nameOfProfPattern = Pattern.compile("\\{nameOfProf:([^}]+)\\}");
            Matcher m = nameOfProfPattern.matcher(resolved);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                String profName = m.group(1);
                String foundName = findNameByProfession(profName);
                if (foundName == null) foundName = "Unknown";
                m.appendReplacement(sb, Matcher.quoteReplacement(foundName));
            }
            m.appendTail(sb);
            resolved = sb.toString();

            resolvedClues.add(resolved);
        }

        clues.clear();
        clues.addAll(resolvedClues);
    }

    private Set<String> findKeys(String template, String regex) {
        Set<String> keys = new HashSet<>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        while (m.find()) {
            String key = m.group(1);
            if (key != null && !key.isEmpty()) keys.add(key);
        }
        return keys;
    }

    public List<String> getClues() { return new ArrayList<>(clues); }
    public String getFormattedClue(int index) {
        if (index >= 0 && index < clues.size()) {
            return "Clue " + (index + 1) + ": " + clues.get(index);
        }
        return null;
    }

    public void registerNPC(String npcName) {
        if (!ruledOutMap.containsKey(npcName)) {
            Map<String, Boolean> m = new HashMap<>();
            for (String p : professions) m.put(p, false);
            ruledOutMap.put(npcName, m);
            assignedProfession.put(npcName, null);
        }
    }

    public void setTrackedNames(List<String> names) {
        if (names == null) names = new ArrayList<>();
        trackedNames = new ArrayList<>(names);
        ruledOutMap.clear();
        assignedProfession.clear();
        guessedProfession.clear();

        for (String name : trackedNames) {
            Map<String, Boolean> m = new HashMap<>();
            for (String p : professions) m.put(p, false);
            ruledOutMap.put(name, m);
            assignedProfession.put(name, null);
        }
    }

    public void clearTrackedNames() {
        trackedNames.clear();
        ruledOutMap.clear();
        assignedProfession.clear();
        guessedProfession.clear();
    }

    public void toggleRuledOut(String npcName, String profession) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m != null && m.containsKey(profession)) {
            m.put(profession, !m.get(profession));
        }
    }

    public void setRuledOut(String npcName, String profession, boolean ruledOut) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m != null) {
            m.put(profession, ruledOut);
        }
    }

    public boolean isRuledOut(String npcName, String profession) {
        Map<String, Boolean> m = ruledOutMap.get(npcName);
        if (m == null) return false;
        return m.getOrDefault(profession, false);
    }

    public List<String> getProfessions() {
        return new ArrayList<>(professions);
    }

    public String getAssignedProfession(String npcName) {
        return assignedProfession.get(npcName);
    }

    public void assignRandomProfessions() {
        List<String> profs = getProfessions();
        List<String> names = new ArrayList<>(trackedNames);

        if (names.isEmpty()) return;

        if (profs.size() >= names.size()) {
            List<String> shuffled = new ArrayList<>(profs);
            Collections.shuffle(shuffled, rnd);
            for (int i = 0; i < names.size(); i++) {
                assignedProfession.put(names.get(i), shuffled.get(i));
            }
            return;
        }

        List<String> pool = new ArrayList<>(profs);
        Collections.shuffle(pool, rnd);

        int i = 0;
        for (; i < pool.size() && i < names.size(); i++) {
            assignedProfession.put(names.get(i), pool.get(i));
        }
        for (; i < names.size(); i++) {
            assignedProfession.put(names.get(i), profs.get(rnd.nextInt(profs.size())));
        }
    }

    public List<String> getNPCNames() {
        return new ArrayList<>(trackedNames);
    }

    private String findNameByProfession(String profession) {
        for (Map.Entry<String, String> e : assignedProfession.entrySet()) {
            if (profession.equals(e.getValue())) return e.getKey();
        }
        return null;
    }

    public void reset() {
        clueTemplates.clear();
        resolvedClues.clear();
        clues.clear();
        ruledOutMap.clear();
        assignedProfession.clear();
        guessedProfession.clear();
        trackedNames.clear();
        setTrackedNames(Arrays.asList("Cherlie", "Kyle", "Summer", "Peter", "Red"));
    }

    public void setGuessedProfession(String npcName, String profession) {
        if (profession.equals(guessedProfession.get(npcName))) {
            guessedProfession.remove(npcName);
        } else {
            guessedProfession.put(npcName, profession);
        }
    }

    public String getGuessedProfession(String npcName) {
        return guessedProfession.get(npcName);
    }

    public int countCorrectGuesses() {
        int correct = 0;
        for (Map.Entry<String, String> entry : guessedProfession.entrySet()) {
            String npcName = entry.getKey();
            String guessed = entry.getValue();
            String actual = assignedProfession.get(npcName);
            if (guessed != null && guessed.equals(actual)) correct++;
        }
        return correct;
    }

    public boolean isVictory() {
        for (String npcName : getNPCNames()) {
            String guessed = guessedProfession.get(npcName);
            String actual = assignedProfession.get(npcName);
            if (guessed == null || !guessed.equals(actual)) return false;
        }
        return true;
    }

    // NEW: Clear guessed professions only, so user can retry
    public void clearGuesses() {
        guessedProfession.clear();
    }

    public void addClue(String clue) {
        if (clue == null || clue.isEmpty()) return;
        clues.add(clue);
    }
}