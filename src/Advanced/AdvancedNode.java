package Advanced;

import BTree.Doubles;

public class AdvancedNode {

    private double value;

    private AdvancedTreeNodeContainer left;

    private AdvancedTreeNodeContainer right;

    public AdvancedNode(double value) {
        this.value =value;
        this.left = null;
        this.right = null;
    }
    public AdvancedNode(double value, AdvancedTreeNodeContainer left, AdvancedTreeNodeContainer right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public AdvancedTreeNodeContainer getLeft() {
        return left;
    }

    public void setLeft(AdvancedTreeNodeContainer left) {
        this.left = left;
    }

    public AdvancedTreeNodeContainer getRight() {
        return right;
    }

    public void setRight(AdvancedTreeNodeContainer right) {
        this.right = right;
    }


    public boolean hasRight() {
        return this.right != null;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public void removeChildren() {
        if (hasLeft()) {
            this.left.setGreaterParent(null);
            this.setLeft(null);
        }
        if (hasRight()) {
            this.right.setSmallerParent(null);
            this.setRight(null);
        }
    }
    @Override
    public String toString() {
        return "(" + Doubles.asString(value) + ")";
    }
}
