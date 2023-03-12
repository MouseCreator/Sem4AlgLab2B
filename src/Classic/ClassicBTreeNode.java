package Classic;

import java.util.List;
import java.util.NoSuchElementException;

class ClassicBTreeNode {
    private ClassicTreeArray values;
    private final ClassicChildrenArray children;
    private final int degree;

    private boolean isLeaf;
    public ClassicBTreeNode(int degree) {
        this.degree = degree;
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

    public ClassicBTreeNode(int degree, boolean isLeaf, SmallNode smallNode) {
        this.degree = degree;
        this.isLeaf =isLeaf;
        this.values = new ClassicTreeArray(degree);
        this.children = new ClassicChildrenArray(degree);
        fromSmallNode(smallNode);
    }

    private void fromSmallNode(SmallNode smallNode) {
        for (double d : smallNode.left().values) {
            this.values.add(d);
        }
        this.values.add(smallNode.median());
        for (double d : smallNode.right().values) {
            this.values.add(d);
        }
        for (ClassicBTreeNode child : smallNode.left().children) {
            this.children.addLast(child);
        }
        for (ClassicBTreeNode child: smallNode.right().children) {
            this.children.addLast(child);
        }
    }

    public void add(double value) {
        if (isFull()) {
            addToFullRoot(value);
            return;
        }
        if (isLeaf) {
            this.values.add(value);
        } else {
            this.addNotLeaf(value);
        }
    }

    private void addToFullRoot(double value) {
        SmallNode small = this.split();
        this.isLeaf = false;
        this.values = new ClassicTreeArray(degree);
        this.values.add(small.median());
        this.children.clear();
        this.children.addLast(small.left());
        this.children.addLast(small.right());
        this.add(value);
    }

    private void addNotLeaf(double value) {
        int addTo = values.position(value);
        if (children.get(addTo).isFull()) {
            splitChild(addTo);
            addTo = values.position(value);
        }
        children.get(addTo).add(value);
    }

    private void splitChild(int addTo) {
        SmallNode splitResult = children.get(addTo).split();
        values.add(splitResult.median());
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

    public String asString(int tab) {
        return isLeaf ? leafAsString(tab) : innerAsString(tab);
    }
    private String leafAsString(int tab) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("\t".repeat(tab)).append(values.get(i)).append("\n");
        }
        builder.append("\t".repeat(tab)).append("_\n");
        return builder.toString();
    }

    private String innerAsString(int tab) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("\t".repeat(tab)).append(values.get(i)).append("\n");
            builder.append(children.get(i).asString(tab+1));
        }
        builder.append(children.last().asString(tab+1));
        builder.append("\t".repeat(tab)).append("_\n");
        return builder.toString();
    }

    public void toArray(List<Double> doubles) {
        if (isLeaf) {
            for (double d : values) {
                doubles.add(d);
            }
        } else {
            for (int i = 0; i < values.size(); i++) {
                children.get(i).toArray(doubles);
                doubles.add(values.get(i));
            }
            children.last().toArray(doubles);
        }
    }

    public void pop(double v) {

        if (values.contains(v)) {
            if (isLeaf) {
                this.values.popValue(v);
            } else {
                popNotLeaf(v);
            }
        } else {
            if (isLeaf) {
                throw new NoSuchElementException("Cannot find element " + v + " in the tree");
            }
            int removeFrom = values.position(v);
            if (children.get(removeFrom).isNotMinimum()) {
                children.get(removeFrom).pop(v);
            } else {
                appendNode(removeFrom, v);
                children.get(removeFrom).pop(v);
            }
        }
    }

    public double popLeft() {
        if (isLeaf) {
            return values.popFirst();
        }
        if (!children.first().isNotMinimum()) {
            appendLeft();
        }
        return children.first().popLeft();
    }

    public double popRight() {
        if (isLeaf) {
            return values.popLast();
        }
        if (!children.last().isNotMinimum()) {
            appendRight();
        }
        return children.last().popRight();
    }

    private void appendLeft() {
        ClassicBTreeNode node = this.children.first();
        if (this.children.get(1).isNotMinimum()) {
            node.values.add(this.values.first());
            this.values.insert(0, this.children.get(1).values.popFirst());
            node.children.addLast(this.children.get(1).children.popFirst());
        } else {
            mergeInsert(0);
            this.children.first().popLeft();
        }
    }
    private void appendRight() {
        ClassicBTreeNode node = this.children.last();
        int lastIndex = node.values.size()-1;
        if (this.children.get(lastIndex).isNotMinimum()) {
            node.values.add(this.values.last());
            this.values.insert(lastIndex, this.children.last().values.popLast());
            node.children.addFirst(this.children.get(lastIndex-1).children.popLast());
        } else {
            mergeInsert(lastIndex);
            this.children.last().popRight();
        }
    }
    private void appendNode(int removeFrom, double v) {
        ClassicBTreeNode node = this.children.get(removeFrom);
        if (removeFrom != 0 && this.children.get(removeFrom-1).isNotMinimum()) {
            node.values.add(this.values.get(removeFrom));
            this.values.insert(removeFrom, this.children.get(removeFrom-1).values.popLast());
            node.children.addFirst(this.children.get(removeFrom+1).children.popLast());
        } else if (removeFrom != node.values.size() && this.children.get(removeFrom+1).isNotMinimum()) {
            node.values.add(this.values.get(removeFrom));
            this.values.insert(removeFrom, this.children.get(removeFrom+1).values.popFirst());
            node.children.addLast(this.children.get(removeFrom+1).children.popFirst());
        } else if (removeFrom != 0) {
            mergeInsert(removeFrom-1);
            this.children.get(removeFrom).pop(v);
        } else if (removeFrom != node.values.size()) {
            mergeInsert(removeFrom);
            this.children.get(removeFrom).pop(v);
        } else {
            throw new IllegalStateException("Cannot merge children nodes!");
        }
    }

    private void popNotLeaf(double v) {
        int removeFrom = values.positionExact(v);
        if (simpleInsert(removeFrom)) {
            mergeInsert(removeFrom);
            this.children.get(removeFrom).pop(v);
        }
    }

    private void mergeInsert(int removeFrom) {
        double median = values.pop(removeFrom);
        ClassicBTreeNode node = new ClassicBTreeNode(degree, this.children.get(removeFrom).isLeaf,
                new SmallNode(median, children.get(removeFrom), children.get(removeFrom+1)));
        this.children.replace(removeFrom, node);
        this.children.remove(removeFrom+1);
    }

    private boolean simpleInsert(int removeFrom) {
        if (children.get(removeFrom).isNotMinimum()) {
            this.values.insert(removeFrom, children.get(removeFrom).popRight());
        } else if (children.get(removeFrom +1).isNotMinimum()) {
            this.values.insert(removeFrom, children.get(removeFrom+1).popLeft());
        } else {
            return true;
        }
        return false;
    }

    private boolean isNotMinimum() {
        return values.isNotMinimum();
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    public ClassicBTreeNode toChild() {
        return this.children.first();
    }

    public boolean get(double value) {
        if (values.contains(value))
            return true;
        if (isLeaf)
            return false;
        int moveTo = values.position(value);
        return children.get(moveTo).get(value);
    }

    public int fullSize() {
        int size = values.size();
        if (!isLeaf) {
            for (ClassicBTreeNode node : children) {
                size += node.fullSize();
            }
        }
        return size;
    }
}
