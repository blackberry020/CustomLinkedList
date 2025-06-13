package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory extends Thread{
    private final Storage storage;
    private final Random rand = new Random();

    public Factory(Storage storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int day = 1; day <= 100; day++){
            List<Part> newParts = new ArrayList<>();
            int count = rand.nextInt(11);
            for (int i = 0; i < count; i++)
                newParts.add(Part.values()[rand.nextInt(4)]);
            try{
                storage.produce(newParts);
                System.out.printf("Day %d: Factory produced %d parts.\n", day, count);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
