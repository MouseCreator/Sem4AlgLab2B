package Classic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertTrue(isSorted(tree.toArray()));
    }
    private boolean isSorted(List<Double> list) {
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i) > list.get(i+1))
                return false;
        }
        return true;
    }
}