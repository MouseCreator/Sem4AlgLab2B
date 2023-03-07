package Std;

import java.util.NoSuchElementException;

public class BTree {
    private TreeNode root;

    private int degree;
    public TreeNode getRoot() {
        return root;
    }
    public BTree() {
        this.degree = 3;
    }
    public BTree(int degree) {
        this.degree = degree;
    }
    public BTree(int degree, double[] values) {
        this.degree = degree;
        for (double d : values) {
            this.add(d);
        }
    }
    public void add(double val) {
        if (root == null) {
            root = new TreeNode(degree, val);
        } else {
            if (root.isFull()) {
                root = root.split(0);
            }
            root.add(val);
        }
    }
    public void pop(double val) throws NoSuchElementException {
        root.pop(val);
    }

    public void find(double val) throws NoSuchElementException {

    }
    public String asString() {
        if (root == null) {
            return "Empty tree";
        } else {
            return root.asString();
        }
    }
}
