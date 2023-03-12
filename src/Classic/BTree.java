package Classic;

import java.util.ArrayList;

public class BTree {

    protected BTreeNode root;

    public BTree(int degree) {
        this.root = new BTreeNode(degree);
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
    public int height() {
        return root.height();
    }
}
