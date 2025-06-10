package org.example;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Faction extends Thread{
    private final String name;
    private final Storage storage;
    private final Map<Part, Integer> inventory = new EnumMap<>(Part.class);

    public Faction(String name, Storage storage){
        this.name = name;
        this.storage = storage;
        for(Part p : Part.values()) inventory.put(p, 0);
    }

    @Override
    public void run() {
        for(int day = 1; day <= 100; day++){
            try{
                List<Part> parts = storage.collectParts(5);
                for(Part p : parts)
                    inventory.put(p, inventory.get(p) + 1);
                System.out.printf("%s collected %d parts on night %d\n", name, parts.size(), day);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getRobotCount(){
        return Collections.min(inventory.values());
    }

    public String getFactionName(){
        return name;
    }
}
