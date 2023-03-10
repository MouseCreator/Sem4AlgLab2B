package Classic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicBTreeTest {
    @Test
    void add() {
        ClassicBTree tree = new ClassicBTree(3);
        for (int i = 1; i < 22; i++) {
            tree.add(i);
        }
        System.out.println(tree.asString());
    }
}