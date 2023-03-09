package Std;

import org.junit.jupiter.api.Test;

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
        for (int i = 1; i <= 21; i++) {
            tree.add(i);
        }
        System.out.println(tree.asString());
        tree.pop(8);
        tree.pop(9);
        tree.pop(10);
        tree.pop(40);
        System.out.println(tree.asString());

    }
}