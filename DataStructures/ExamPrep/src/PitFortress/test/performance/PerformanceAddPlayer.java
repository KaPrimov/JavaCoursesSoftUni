package PitFortress.test.performance;


import PitFortress.test.BaseTestClass;
import PitFortress.test.categories.Performance;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PerformanceAddPlayer extends BaseTestClass {

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts1() throws FileNotFoundException {
        File file = new File("src/PitFortress/Tests/AddPlayer/addPlayer.0.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts2() throws FileNotFoundException {
        File file = new File("src/PitFortress/Tests/AddPlayer/addPlayer.1.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts3() throws FileNotFoundException {
        File file = new File("src/PitFortress/Tests/AddPlayer/addPlayer.2.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts4() throws FileNotFoundException {
        File file = new File("src/PitFortress/Tests/AddPlayer/addPlayer.3.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }

    @Category(Performance.class)
    @Test
    public void PerformanceAddPlayer_WithRandomAmounts5() throws FileNotFoundException {
        File file = new File("src/PitFortress/Tests/AddPlayer/addPlayer.4.txt");
        Scanner scanner = new Scanner(file);
        ArrayList<String[]> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            commands.add(scanner.nextLine().split(" "));
        }

        long start = System.currentTimeMillis();

        for (int i = 0; i < commands.size(); i++) {
            this.PitFortressCollection.addPlayer(commands.get(i)[1], Integer.parseInt(commands.get(i)[2]));
        }

        long end = System.currentTimeMillis();
        Assert.assertTrue(end - start < 350);

        Assert.assertEquals("Players Count did not match!", commands.size(), this.PitFortressCollection.getPlayerCount());
    }
}

