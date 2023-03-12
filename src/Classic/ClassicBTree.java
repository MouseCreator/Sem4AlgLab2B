package Classic;

import java.util.ArrayList;

public class ClassicBTree {

    protected ClassicBTreeNode root;

    public ClassicBTree(int degree) {
        this.root = new ClassicBTreeNode(degree);
    }

    public void add(double value) {
        root.add(value);
    }
    public void pop(double value) {
        root.pop(value);
        if (root.isEmpty()) {
            root = root.toChild();
        }
    }
    public boolean get(double value) {
        return root.get(value);
    }

    public ArrayList<Double> toArray() {
        ArrayList<Double> doubles = new ArrayList<>();
        root.toArray(doubles);
        return doubles;
    }

    public String asString() {
        return root.asString(0);
    }
    @Override
    public String toString() {
        return this.toArray().toString();
    }

    public int size() {
        return root.fullSize();
    }
}
