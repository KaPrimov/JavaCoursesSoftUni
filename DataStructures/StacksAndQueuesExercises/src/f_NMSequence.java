import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class f_NMSequence {

    private static class Node {
        private int value;
        private Node prevNode;

        public Node(int value, Node prevNode) {
            this.value = value;
            this.prevNode = prevNode;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Deque<Node> nodes = new ArrayDeque<>();
        String input[] = reader.readLine().split(" ");

        Integer startInt = Integer.parseInt(input[0]);
        Integer endInt = Integer.parseInt(input[1]);
        nodes.add(new Node(startInt, null));

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            if(currentNode.value < endInt) {
                int value = currentNode.value;

                nodes.add(new Node(value + 1, currentNode));
                nodes.add(new Node(value + 2, currentNode));
                nodes.add(new Node(value * 2, currentNode));
            } else if(currentNode.value == endInt) {
                System.out.println(collectResult(currentNode));
                return;
            }
        }
    }

    private static String collectResult(Node currentNode) {
        StringBuilder result = new StringBuilder();

        Node tempNode = currentNode;

        while (tempNode != null) {
            result.insert(0, tempNode.value + " -> ");
            tempNode = tempNode.prevNode;
        }
        return result.delete(result.length() - 4, result.length()).toString();
    }
}
