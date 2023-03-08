package Advanced;

public class AdvancedNode {
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

    private double value;

    private AdvancedTreeNodeContainer left;

    private AdvancedTreeNodeContainer right;

}
