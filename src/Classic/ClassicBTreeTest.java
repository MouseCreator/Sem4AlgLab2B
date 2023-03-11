package Classic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassicBTreeTest {
    @Test
    void add() {
        ClassicBTree tree = formTree();
        System.out.println(tree.asString());
        assertTrue(isSorted(tree.toArray()));
    }

    private ClassicBTree formTree() {
        ClassicBTree tree = new ClassicBTree(3);
         for (int i = 1; i <= 22; i++) {
            try {
                tree.add(i);
            } catch (Exception e) {
                System.err.println("Exception at " + i);
                throw e;
            }
        }
        return tree;
    }
    private boolean isSorted(List<Double> list) {
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i) > list.get(i+1))
                return false;
        }
        return true;
    }

    @Test
    void pop() {
        ClassicBTree tree = formTree();
        System.out.println(tree.asString());
        tree.pop(6);
        tree.pop(12);
        tree.pop(18);
        System.out.println(tree.asString());
        assertTrue(isSorted(tree.toArray()));
    }
}