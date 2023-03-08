package Advanced;

public class AdvancedTreeNode {

    private AdvancedNodeArray array;
    private boolean isLeaf;
    public boolean isFull() {
        return array.isFull();
    }

    public boolean isMinimum() {
        return array.isOnMinimumCapacity();
    }
    public void add(double value) {
        if (this.isLeaf) {
            this.array.add(value);
        }
    }
}
