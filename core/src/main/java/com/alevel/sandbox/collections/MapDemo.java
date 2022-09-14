package com.alevel.sandbox.collections;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {

        Map<String, Integer> playerLevels = new HashMap<>();

        playerLevels.put("void", 8);
        playerLevels.put("gman", 5);
        playerLevels.put("dendi", 8);

        System.out.println(playerLevels.getOrDefault("elex", 0));
        System.out.println();

        System.out.println(playerLevels.get("void"));
        System.out.println();

        for (Map.Entry<String, Integer> entry : playerLevels.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        Map<DayOfWeek, String> events = new EnumMap<>(DayOfWeek.class);
        events.put(DayOfWeek.WEDNESDAY, "A-Level classes");
        System.out.println(events);
    }
}
