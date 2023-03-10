package Classic;

public class ClassicBTreeNode {
    private ClassicTreeArray values;
    private ClassicChildrenArray children;
    private int degree;

    private boolean isLeaf;
    public ClassicBTreeNode(int degree) {
        this.degree = 0;
        values = new ClassicTreeArray(degree);
        children = new ClassicChildrenArray(degree);
    }
    private ClassicBTreeNode(ClassicTreeArray array, ClassicChildrenArray children, int degree) {
        this.values = array;
        this.children = children;
        this.degree = degree;
    }
    public void add(double value) {
        if (isLeaf) {
            this.values.add(value);
        } else {
            this.addNotLeaf(value);
        }
    }

    private void addNotLeaf(double value) {
        int addTo = values.position(value);
        if (children.get(addTo).isFull()) {
            children.get(addTo).split(this);
        }
    }

    private void split(ClassicBTreeNode parent) {
        double median = this.values.median();
        ClassicBTreeNode left = new ClassicBTreeNode(values.leftToMedian(), children.left(), degree);
        ClassicBTreeNode right = new ClassicBTreeNode(values.rightToMedian(), children.right(), degree);
        parent.add(median);
    }

    private boolean isFull() {
        return values.isFull();
    }
}
