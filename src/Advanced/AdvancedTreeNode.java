package Advanced;

import Std.Doubles;

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
            toAdd.add(value);
        }
    }

    public void split(AdvancedTreeNode parent) {
        AdvancedNode median = this.array.median();
        median.removeChildren();
        AdvancedTreeNode neighbor = new AdvancedTreeNode(this.degree);

        neighbor.array = array.right();
        neighbor.isLeaf = this.isLeaf;

        median.setRight(new AdvancedTreeNodeContainer(neighbor));
        median.getRight().setGreaterParent(median);

        parent.array.add(median);
        parent.isLeaf = false;
        this.array = array.left();

    }
    public AdvancedTreeNode splitRoot() {
        AdvancedTreeNode parent = new AdvancedTreeNode(degree);

        AdvancedNode median = this.array.median();
        median.removeChildren();
        AdvancedTreeNode neighbor = new AdvancedTreeNode(this.degree);

        neighbor.array = array.right();
        neighbor.isLeaf = this.isLeaf;

        median.setRight(new AdvancedTreeNodeContainer(neighbor));
        median.setLeft(new AdvancedTreeNodeContainer(this));

        median.getRight().setSmallerParent(median);
        median.getLeft().setGreaterParent(median);

        this.array = array.left();
        parent.array.add(median);
        parent.isLeaf = false;
        return parent;
    }

    public String asString(int tabulation) {
        StringBuilder builder = new StringBuilder();
        for (AdvancedNode node : array) {
            if (node.hasLeft())
                builder.append("\t".repeat(tabulation)).append(node.getLeft().getNode().asString(tabulation+1));
            builder.append(Doubles.asString(node.getValue())).append("\n");
        }
        if (array.last().hasRight())
            builder.append("_\n").
                    append("\t".repeat(tabulation)).append(array.last().getRight().getNode().asString(tabulation+1));
        return builder.toString();
    }

    @Override
    public String toString() {
        return "Size: " + array.size();
    }
}
