package Classic;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BTeeTest {
    @Test
    void add() {
        BTree tree = formTree();
        System.out.println(tree.asString());
        assertTrue(isSorted(tree.toArray()));
        assertEquals(22, tree.size());
        assertEquals(2, tree.height());
    }

    @Test
    void addEqualValues() {
        BTree tree = new BTree(3);
        for (int i = 1; i <= 22; i++) {
            try {
                tree.add(2);
            } catch (Exception e) {
                System.err.println("Exception at " + i);
                throw e;
            }
        }
        System.out.println(tree.asString());
        assertTrue(isSorted(tree.toArray()));
    }

    private BTree formTree() {
        BTree tree = new BTree(3);
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
        BTree tree = formTree();
        System.out.println(tree.asString());
        tree.pop(6);
        tree.pop(12);
        System.out.println(tree.asString());
        tree.pop(18);
        assertThrows(NoSuchElementException.class, ()->tree.pop(40));
        System.out.println(tree.asString());
        assertTrue(isSorted(tree.toArray()));
        assertEquals( 19, tree.size());
    }

    @Test
    void get() {
        BTree tree = formTree();
        assertTrue(tree.get(2));
        assertTrue(tree.get(5));
        assertTrue(tree.get(8));
        assertFalse(tree.get(5.5));
        assertFalse(tree.get(-4));
        assertFalse(tree.get(29));
    }

    @Test
    void small() {
        BTree tree = new BTree(2);
        tree.add(1);
        tree.pop(1);
        tree.add(2);
        assertEquals(1, tree.size());
        assertEquals(0, tree.height());
    }
}