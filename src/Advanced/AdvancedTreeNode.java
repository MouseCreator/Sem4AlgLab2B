package Advanced;

public class AdvancedTreeNode {

    private AdvancedNodeArray array;
    private boolean isLeaf;
    private final int degree;

    public AdvancedTreeNode(int degree, double val) {
        this.isLeaf = true;
        this.degree = degree;
        this.array = new AdvancedNodeArray(degree);
        array.add(val);
    }
    public AdvancedTreeNode(int degree) {
        this.array = new AdvancedNodeArray(degree);
        this.degree = degree;

    }

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
        AdvancedTreeNode neighbor = new AdvancedTreeNode(this.degree);

        neighbor.array = array.right();
        neighbor.isLeaf = this.isLeaf;

        median.setRight(new AdvancedTreeNodeContainer(neighbor));

        parent.array.add(median);

        this.array = array.left();

    }
}
