package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private final List<Part> parts = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition partsAvailable = lock.newCondition();
    private CyclicBarrier productionBarrier;
    private boolean isDay = true;

    public void produce(List<Part> newParts) throws InterruptedException {
        lock.lock();
        try{
            parts.addAll(newParts);
            productionBarrier = new CyclicBarrier(3, () -> isDay = true);
            isDay = false;
            partsAvailable.signalAll();
        }finally {
            lock.unlock();
        }

        try {
            productionBarrier.await();
        } catch (BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Part> collectParts(int maxParts) throws InterruptedException {
        lock.lock();
        try{
            while (isDay){
                partsAvailable.await();
            }

            List<Part> collected = new ArrayList<>();
            for (int i = 0; i < maxParts && !parts.isEmpty(); i++)
                collected.add(parts.remove(0));

            return collected;
        }finally {
            lock.unlock();

            try{
                productionBarrier.await();
            }catch (BrokenBarrierException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
