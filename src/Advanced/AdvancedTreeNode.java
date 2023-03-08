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
        } else {
            AdvancedTreeNode toAdd = this.array.moveTo(value);
            if (toAdd.isFull()) {
                toAdd.split(this);
            }
        }
    }

    private void split(AdvancedTreeNode parent) {
        AdvancedNode median = this.array.median();
        median.removeChildren();
        AdvancedTreeNode neighbor = new AdvancedTreeNode();

        neighbor.array = array.right();
        this.array = array.left();

    }
}
