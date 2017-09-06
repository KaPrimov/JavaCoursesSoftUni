import java.util.*;

public class AStar {

    private char[][] map;
    private PriorityQueue<Node> priorityQueue;


    public AStar(char[][] map) {
        this.map = map;
        this.priorityQueue = new PriorityQueue<>();
    }

    public static int getH(Node current, Node goal) {
        int deltaX = Math.abs(current.getCol() - goal.getCol());
        int deltaY = Math.abs(current.getRow() - goal.getRow());

        return deltaX + deltaY;
    }

    public Iterable<Node> getPath(Node start, Node goal) {
        this.priorityQueue.enqueue(start);
        Map<Node, Node> parent = new LinkedHashMap<>();
        Map<Node, Integer> cost = new LinkedHashMap<>();
        parent.put(start, null);
        cost.put(start, 0);
        start.setF(getH(start, goal));

        while (this.priorityQueue.size() != 0) {
            Node current = priorityQueue.dequeue();
            if(current.equals(goal)) {
                break;
            }

            for (int row = -1; row <= 1; row++) {
                for (int col = -1; col <= 1 ; col++) {
                    if(Math.abs(col) != Math.abs(row)) {
                        if(isInsideTheMatrix(current.getRow() + row, current.getCol() + col) &&
                                this.map[current.getRow() + row][current.getCol() + col] != 'W') {
                            Node neighbour = new Node(current.getRow() + row, current.getCol() + col);
                            int newCost = cost.get(current) + 1;
                            if(!cost.containsKey(neighbour) || cost.get(current) > newCost) {
                                cost.put(neighbour, newCost);
                                neighbour.setF(newCost + getH(neighbour, goal));
                                priorityQueue.enqueue(neighbour);
                                parent.put(neighbour, current);
                            }
                        }
                    }
                }
            }
        }
        List<Node> path = new ArrayList<>();
        while (parent.get(goal) != null) {
            path.add(0, goal);
            goal = parent.get(goal);
        }

        if(path.isEmpty()) {
            path.add(0, null);
            return path;
        }

        path.add(0, start);
        return path;
    }

    private boolean isInsideTheMatrix(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }
}
