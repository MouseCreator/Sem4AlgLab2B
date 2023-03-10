package Classic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicBTreeTest {
    @Test
    void add() {
        ClassicBTree tree = new ClassicBTree(3);
        for (int i = 1; i < 24; i++) {
            try {
                tree.add(i);
            } catch (Exception e) {
                System.err.println("Exception at " + i);
                throw e;
            }
        }
        System.out.println(tree.asString());
    }
}