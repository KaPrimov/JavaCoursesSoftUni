import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Integer, Tree<Integer>> nodeByValue = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        //readTree();
        //2nd Task
//        System.out.println(
//                getRoodNode() == null ?
//                        "" :
//                        getRootNode().print(0, new StringBuilder()));

        //3rd Task
//        Map<Integer, Tree<Integer>> leafs = findLeafs();
//        StringBuilder sb = new StringBuilder();
//        sb.append("Leaf nodes: ");
//        for (Tree<Integer> integerTree : leafs.values()) {
//            sb.append(integerTree.getValue());
//            sb.append(" ");
//        }
//        System.out.println(sb.toString());

        //4th Task
//        Map<Integer, Tree<Integer>> middleNodes = findMiddleNodes();
//        StringBuilder sb = new StringBuilder();
//        sb.append("Middle nodes: ");
//        for (Tree<Integer> integerTree : middleNodes.values()) {
//            sb.append(integerTree.getValue());
//            sb.append(" ");
//        }
//        System.out.println(sb.toString());

        //5th Task;
//        Tree<Integer> root = null;
//        for (Tree<Integer> integerTree : nodeByValue.values()) {
//            if(integerTree.getParent() == null) {
//                root = integerTree;
//            }
//        }
//        Tree<Integer> deepestNode = findDeepestNode(root);
//        System.out.println("Deepest node: " + deepestNode.getValue());

        //6th Task
//        Tree<Integer> root = null;
//        for (Tree<Integer> integerTree : nodeByValue.values()) {
//            if(integerTree.getParent() == null) {
//                root = integerTree;
//            }
//        }
//        Tree<Integer> tree = findDeepestNode(root);
//        StringBuilder sb = new StringBuilder();
//        while (tree != null) {
//            sb.insert(0, tree.getValue() + " ");
//            tree = tree.getParent();
//        }
//        System.out.println("Longest path: " + sb);

        //7th Task
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int nodeCount = Integer.parseInt(reader.readLine());
//        for (int i = 1; i < nodeCount; i++) {
//            String[] edge = reader.readLine().split(" ");
//            addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
//        }
//        long sum = Long.parseLong(reader.readLine());
//        List<Tree<Integer>> endNodes = new ArrayList<>();
//        Tree<Integer> root = null;
//        for (Tree<Integer> integerTree : nodeByValue.values()) {
//            if(integerTree.getParent() == null) {
//                root = integerTree;
//            }
//        }
//        findSums(endNodes, sum, root, root.getValue());
//        System.out.printf("Paths of sum %d:%n", sum);
//
//        for (Tree<Integer> endNode : endNodes) {
//            System.out.println(printNode(endNode));
//        }

        //8th Task
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());
        for (int i = 1; i < nodeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
        long sum = Long.parseLong(reader.readLine());
        List<Tree<Integer>> roots = new ArrayList<>();
        Tree<Integer> root = null;
        for (Tree<Integer> integerTree : nodeByValue.values()) {
            if(integerTree.getParent() == null) {
                root = integerTree;
            }
        }
        findSubtrees(roots, root, sum, 0);
        System.out.println("Subtrees of sum " + sum + ":");
        for (Tree<Integer> node : roots) {
            StringBuilder output = new StringBuilder();
            System.out.println(printPreOrder(node, output));
        }
    }

    private static int findSubtrees(List<Tree<Integer>> roots, Tree<Integer> root,
                                     long sumToFind, int currentSum) {
        currentSum = root.getValue();

        for (Tree<Integer> child : root.getChildren()) {
            currentSum += findSubtrees(roots, child, sumToFind, currentSum);
        }

        if(currentSum == sumToFind) {
            roots.add(root);
        }

        return currentSum;
    }

    public static String printPreOrder(Tree<Integer> parentNode, StringBuilder builder) {
        if (parentNode != null) {
            builder.append(parentNode.getValue() + " ");
            for (Tree<Integer> child : parentNode.getChildren()) {
                printPreOrder(child, builder);
            }
        }
        return builder.toString();
    }

    private static String printNode(Tree<Integer> endNode) {
        StringBuilder sb = new StringBuilder();

        while (endNode != null) {
            sb.insert(0, endNode.getValue() + " ");
            endNode = endNode.getParent();
        }

        return sb.toString();
    }

    private static void findSums(List<Tree<Integer>> endNodes, long sumToFind, Tree<Integer> node, int currentSum) {
        for (Tree<Integer> child : node.getChildren()) {
            findSums(endNodes, sumToFind, child, currentSum + child.getValue());
        }
        if(currentSum == sumToFind) {
            endNodes.add(node);
        }
    }

    private static Tree<Integer> findDeepestNode(Tree<Integer> root) {
        Map<Integer, Tree<Integer>> depths = new TreeMap<>((a, b) -> Integer.compare(b, a));
        DepthFirstSearch(depths, root, 0);
        return depths.entrySet().stream().findFirst().get().getValue();
    }

    private static void DepthFirstSearch(Map<Integer, Tree<Integer>> depths, Tree<Integer> node, int depth) {
        for (Tree<Integer> child : node.getChildren()) {
            DepthFirstSearch(depths, child, depth + 1);
        }
        depths.putIfAbsent(depth, node);
    }

    private static Map<Integer, Tree<Integer>> findMiddleNodes() {
        Map<Integer, Tree<Integer>> leafs = new TreeMap<>();
        for (Tree<Integer> integerTree : nodeByValue.values()) {
            if (integerTree.getChildren().size() != 0 && integerTree.getParent() != null) {
                leafs.putIfAbsent(integerTree.getValue(), integerTree);
            }
        }
        return leafs;
    }

    private static Map<Integer, Tree<Integer>> findLeafs() {
        Map<Integer, Tree<Integer>> leafs = new TreeMap<>();
        for (Tree<Integer> integerTree : nodeByValue.values()) {
            if (integerTree.getChildren().size() == 0) {
                leafs.putIfAbsent(integerTree.getValue(), integerTree);
            }
        }
        return leafs;
    }

    private static Tree<Integer> gerTreeNodeByValue(int value) {
        nodeByValue.putIfAbsent(value, new Tree<Integer>(value));
        return nodeByValue.get(value);
    }

    private static void addEdge(int parent, int child) {
        Tree<Integer> parentNode = gerTreeNodeByValue(parent);
        Tree<Integer> childNode = gerTreeNodeByValue(child);
        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
    }

    private static void readTree() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeCount = Integer.parseInt(reader.readLine());
        for (int i = 1; i < nodeCount; i++) {
            String[] edge = reader.readLine().split(" ");
            addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
        }
    }

    private static Tree<Integer> getRootNode() {
        for (Tree<Integer> integerTree : nodeByValue.values()) {
            if (integerTree.getParent() == null) {
                return integerTree;
            }
        }
        return null;
    }
}
