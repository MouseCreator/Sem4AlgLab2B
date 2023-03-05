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
}