import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    void add() {
        BTree tree = new BTree(3);
        tree.add(1);
        tree.add(3);
        tree.add(2);
        tree.add(4);
        tree.add(5);
        tree.add(8);
        tree.add(7);
        tree.add(6);
        System.out.println(tree.asString());
    }
    @Test
    void pop() {
        BTree tree = new BTree(3);
        for (int i = 1; i <= 20; i++) {
            if (i == 6) {
                System.out.println("a");
            }
            tree.add(i);
        }
        System.out.println(tree.asString());
        tree.pop(10);
        tree.pop(12);
        tree.pop(2);
        System.out.println(tree.asString());
    }
}