package org.example;

public class Skynet {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();

        Factory factory = new Factory(storage);
        Faction world = new Faction("World", storage);
        Faction wednesday = new Faction("Wednesday", storage);

        factory.start();
        world.start();
        wednesday.start();

        factory.join();
        world.join();
        wednesday.join();

        int worldRobotsCount = world.getRobotCount();
        int wednesdayRobotsCount = wednesday.getRobotCount();

        System.out.println("\n\n----RESULTS----");
        System.out.printf("World created %d robots\n", worldRobotsCount);
        System.out.printf("Wednesday created %d robots\n", wednesdayRobotsCount);
        System.out.println(worldRobotsCount > wednesdayRobotsCount ? "World wins!" :
                worldRobotsCount < wednesdayRobotsCount ? "Wednesday wins!" : "It's a draw");
    }
}