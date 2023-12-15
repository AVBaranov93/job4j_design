package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int deleted = 0;
        int modified = 0;
        Map<Integer, String> previousMap = new HashMap();
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        Map<Integer, String> currentMap = new HashMap();
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (Map.Entry<Integer, String> entry : previousMap.entrySet()) {
            if (!entry.getValue().equals(currentMap.get(entry.getKey())) && currentMap.get(entry.getKey()) != null) {
                modified++;
            }
        }
        for (Map.Entry<Integer, String> entry : currentMap.entrySet()) {
            if (previousMap.get(entry.getKey()) == null) {
                added++;
            }
        }
        for (Map.Entry<Integer, String> entry : previousMap.entrySet()) {
            if (currentMap.get(entry.getKey()) == null) {
                deleted++;
            }
        }
        return new Info(added, modified, deleted);
    }
}
