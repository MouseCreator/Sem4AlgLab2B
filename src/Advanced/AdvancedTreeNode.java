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
            addNotLeaf(value);
        }
    }

    private void addNotLeaf(double value) {
        AdvancedTreeNode toAdd = this.array.moveTo(value);
        if (toAdd.isFull()) {
            toAdd.split(this);
            addNotLeaf(value);
        } else {
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

        median.setLeft(new AdvancedTreeNodeContainer(this));
        median.getLeft().setSmallerParent(median);

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
            builder.append("\t".repeat(tabulation)).append(node.getValue()).append("\n");
            if (node.hasLeft())
                builder.append(node.getLeft().getNode().asString(tabulation+1));
        }
        if (array.last().hasRight())
            builder.append(array.last().getRight().getNode().asString(tabulation+1));
        builder.append("\t".repeat(tabulation)).append("_").append("\n");
        return builder.toString();
    }

    public void pop(double value) {
        if (contains(value)) {
            if (this.isLeaf) {
                this.array.pop(value);
            } else {
                popNotLeaf(value);
            }
        } else {
            AdvancedTreeNodeContainer next = array.getByValue(value);
            if (next.hasSmallerParent() && next.getSmallerParent().hasLeft() &&
                    !next.getSmallerParent().getLeft().getNode().isMinimum()) {
                AdvancedNode borrowed = array.borrowFirst();
                this.array.add(borrowed);

                double temp = this.array.last().getValue();
                this.array.last().setValue(next.getSmallerParent().getValue());
                next.getSmallerParent().setValue(temp);

            } else if (next.hasGreaterParent() && next.getGreaterParent().hasRight() &&
                    !next.getGreaterParent().getRight().getNode().isMinimum()) {

            }
        }
    }

    private boolean contains(double value) {
        return this.array.contains(value);
    }

    private void popNotLeaf(double value) {
        AdvancedNode node = this.array.getExact(value);
        if (node.hasLeft() && !node.getLeft().getNode().isMinimum()) {
            this.array.replace(value, findLeft(node.getLeft().getNode()));
        } else if (node.hasRight() && !node.getRight().getNode().isMinimum()) {
            this.array.replace(value, findRight(node.getRight().getNode()));
        } else {
            popFromMin(value, node);
        }
    }

    private void popFromMin(double value, AdvancedNode node) {
        AdvancedTreeNodeContainer left = node.getLeft();
        AdvancedTreeNodeContainer right = node.getRight();
        left.getNode().array.merge(node.getValue(), right.getNode().array);
        left.setGreaterParent(right.getGreaterParent());
        right.setSmallerParent(null);
        this.array.pop(value);
        left.getNode().pop(value);
    }

    private double findLeft(AdvancedTreeNode left) {
        AdvancedTreeNode current = left;
        while (!current.isLeaf) {
            current = current.array.last().getRight().getNode();
        }
        return array.popLast();
    }
    private double findRight(AdvancedTreeNode right) {
        AdvancedTreeNode current = right;
        while (!current.isLeaf) {
            current = current.array.last().getLeft().getNode();
        }
        return array.popFirst();
    }

    @Override
    public String toString() {
        return "Size: " + array.size();
    }
}
