package Classic;

public class ClassicBTreeNode {
    private final ClassicTreeArray values;
    private final ClassicChildrenArray children;
    private final int degree;

    private boolean isLeaf;
    public ClassicBTreeNode(int degree) {
        this.degree = 0;
        values = new ClassicTreeArray(degree);
        children = new ClassicChildrenArray(degree);
        this.isLeaf = true;
    }
    private ClassicBTreeNode(ClassicTreeArray array, ClassicChildrenArray children, int degree, boolean isLeaf) {
        this.values = array;
        this.children = children;
        this.degree = degree;
        this.isLeaf = isLeaf;
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
            splitChild(value, addTo);
            addTo = values.position(value);
        }
        children.get(addTo).add(value);
    }

    private void splitChild(double value, int addTo) {
        SmallNode splitResult = children.get(addTo).split();
        values.add(splitResult.median());
        addNotLeaf(value);
        children.replace(addTo, splitResult.left());
        children.insert(addTo+1,splitResult.right());
        this.isLeaf = false;
    }

    private SmallNode split() {
        double median = this.values.median();
        ClassicBTreeNode left = new ClassicBTreeNode(values.leftToMedian(), children.left(), degree, this.isLeaf);
        ClassicBTreeNode right = new ClassicBTreeNode(values.rightToMedian(), children.right(), degree, this.isLeaf);
        return new SmallNode(median, left, right);
    }

    private boolean isFull() {
        return values.isFull();
    }
}
