package sweepAndPrune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        List<GameObject> objects = new ArrayList<>();
        int moves = 1;
        while (!"end".equals(line)) {

            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case "tick":
                    checkCollisions(objects, moves);
                    break;
                case "move":
                    String nameToMove = tokens[1];
                    int gameObjectIndex = getObject(objects, nameToMove);
                    if (gameObjectIndex == -1) {
                        break;
                    }
                    objects.get(gameObjectIndex).setX1(Double.parseDouble(tokens[2]));
                    objects.get(gameObjectIndex).setY1(Double.parseDouble(tokens[3]));
                    insertionSort(objects);
                    checkCollisions(objects, moves);
                    break;
                case "add":
                    String name = tokens[1];
                    double x1 = Double.parseDouble(tokens[2]);
                    double y1 = Double.parseDouble(tokens[3]);
                    GameObject object = new GameObject(x1, y1, name);
                    objects.add(object);
                    break;
                case "start":
                    insertionSort(objects);
                    break;
            }
            if ("tick".equals(tokens[0]) || "move".equals(tokens[0])) {
                moves++;
            }
            line = reader.readLine();
        }

    }

    private static void checkCollisions(List<GameObject> objects, int moves) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject currentObject = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject toCompareObj = objects.get(j);
                if (currentObject.getX2() < toCompareObj.getX1()) {
                    break;
                }
                if(currentObject.isIntersected(toCompareObj)) {
                    System.out.printf("(%d) %s collides with %s%n", moves, currentObject.getName(), toCompareObj.getName());
                }
            }
        }
    }

    private static int getObject(List<GameObject> objects, String name) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private static void insertionSort(List<GameObject> objects) {
        int index = 1;
        while (index < objects.size()) {
            GameObject gameObject = objects.get(index);
            int innerIndex = index - 1;
            while (innerIndex >= 0 &&
                    objects.get(innerIndex).getX1() > gameObject.getX1()) {
                objects.set(innerIndex + 1, objects.get(innerIndex));
                innerIndex--;
            }
            objects.set(innerIndex + 1, gameObject);
            index++;
        }
    }
}
