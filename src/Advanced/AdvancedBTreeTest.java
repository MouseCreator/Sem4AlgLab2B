package Advanced;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedBTreeTest {

    @Test
    void add() {
        AdvancedBTree tree = new AdvancedBTree(3);
        for (int i = 1; i < 22; i++) {
            tree.add(i);
        }
        System.out.println(tree.asString());
    }

}