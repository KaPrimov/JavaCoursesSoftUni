import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BinaryTreeTests {

    @Test
    public void buildBinaryTree_forEachTraversal_inOrder_shouldWorkCorrectly() {
        // Arrange
        BinaryTree<String> binaryTree = new BinaryTree.Builder<>("*")
                .leftChild(new BinaryTree.Builder<>("+")
                        .leftChild(new BinaryTree.Builder<>("3").build())
                        .rightChild(new BinaryTree.Builder<>("2").build())
                        .build())
                .rightChild(new BinaryTree.Builder<>("-")
                        .leftChild(new BinaryTree.Builder<>("9").build())
                        .rightChild(new BinaryTree.Builder<>("6").build())
                        .build())
                .build();


        // Act
        List<String> nodes = new ArrayList<>();
        binaryTree.eachInOrder(nodes::add);
        String[] actualResult = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            actualResult[i] = nodes.get(i);
        }

        // Assert
        String[] expectedNodes = new String[]{"3", "+", "2", "*", "9", "-", "6"};
        Assert.assertArrayEquals(expectedNodes, actualResult);
    }

    @Test
    public void buildBinaryTree_forEachTraversal_postOrder_shouldWorkCorrectly() {
        // Arrange
        BinaryTree<String> binaryTree = new BinaryTree.Builder<>("*")
                .leftChild(new BinaryTree.Builder<>("+")
                        .leftChild(new BinaryTree.Builder<>("3").build())
                        .rightChild(new BinaryTree.Builder<>("2").build())
                        .build())
                .rightChild(new BinaryTree.Builder<>("-")
                        .leftChild(new BinaryTree.Builder<>("9").build())
                        .rightChild(new BinaryTree.Builder<>("6").build())
                        .build())
                .build();

        // Act
        List<String> nodes = new ArrayList<>();
        binaryTree.eachPostOrder(nodes::add);
        String[] actualResult = new String[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            actualResult[i] = nodes.get(i);
        }

        // Assert
        String[] expectedNodes = new String[]{"3", "2", "+", "9", "6", "-", "*"};
        Assert.assertArrayEquals(expectedNodes, actualResult);
    }

    @Test
    public void buildBinaryTree_printIndentedPreOrder_shouldWorkCorrectly() {
        // Arrange
        BinaryTree<String> binaryTree =
                new BinaryTree.Builder<>("*")
                    .leftChild(new BinaryTree.Builder<>("-")
                            .leftChild(new BinaryTree.Builder<>("+")
                                .leftChild(new BinaryTree.Builder<>("3").build())
                                .rightChild(new BinaryTree.Builder<>("2").build()).build())
                            .rightChild(new BinaryTree.Builder<>("*")
                                    .leftChild(new BinaryTree.Builder<>("9").build())
                                    .rightChild(new BinaryTree.Builder<>("6").build()).build())
                            .build())
                    .rightChild(new BinaryTree.Builder<>("8").build()).build();

        // Act
        String output = binaryTree.printIndentedPreOrder(0, new StringBuilder());

        // Assert
        String expectedOutput = "*\n  -\n    +\n      3\n      2\n    *\n      9\n      6\n  8\n";
        Assert.assertEquals(expectedOutput, output);
    }
}
