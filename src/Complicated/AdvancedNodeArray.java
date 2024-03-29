package Complicated;

import BTree.Doubles;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdvancedNodeArray implements Iterable<AdvancedNode> {
    private final AdvancedNode[] nodes;
    private final int maxSize;
    private final int minSize;

    private int size;

    public AdvancedNodeArray(int degree) {
        if (degree < 3) {
            throw new IllegalStateException("Cannot create array with less than 2 fields! Caused by degree = " + degree);
        }
        this.maxSize = 2 * degree - 1;
        this.minSize = degree - 1;
        this.size = 0;
        this.nodes = new AdvancedNode[maxSize];
    }
    public void add(AdvancedNode node) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Too much elements! Limit: "  + maxSize);
        }
        if (size == 0) {
            this.nodes[0] = node;
            size++;
            return;
        }
        addRight(node);
        size++;
    }


    private void addRight(AdvancedNode node) {
        int isAdded = 0;
        for (int i = 0; i < size; i++) {
            if (isAdded == 0 && node.getValue() < nodes[i].getValue()) {
                nodes[i] = node;
                isAdded = 1;
                nodes[i].setLeft(node.getRight());
                nodes[i].getLeft().setGreaterParent(nodes[i]);
            }
            nodes[i+isAdded]=nodes[i];
        }
        if (isAdded == 0) {
            nodes[size] = node;
            if (size != 0 && nodes[size - 1].hasRight()) {
                node.setLeft(nodes[size - 1].getRight());
                node.getLeft().setGreaterParent(node);
            }
        }
    }

    public AdvancedNode pop(double value) {
        if (size == 0) {
            throw new NoSuchElementException("Trying to get element from empty array.");
        }
        AdvancedNode result = null;
        int j = 0;
        for (int i = 0; i < size; i++) {
            nodes[i+j]=nodes[i];
            if (Doubles.isEqual(value, nodes[i].getValue())) {
                result = nodes[i];
                j = -1;
            }

        }
        if (j==0)
            throw new NoSuchElementException("No value " + value + " in the array node");
        nodes[size-1]=null;
        size--;
        return result;
    }

    public boolean isFull() {
        return size == maxSize;
    }
    public boolean isOnMinimumCapacity() {
        return size == minSize;
    }

    public void add(double value) {
        this.add(new AdvancedNode(value));
    }

    public AdvancedTreeNode moveTo(double value) {
        for (int i = 0; i < size; i++) {
            if (value < nodes[i].getValue()) {
                return nodes[i].getLeft().getNode();
            }
        }
        return nodes[size-1].getRight().getNode();
    }
    public AdvancedTreeNodeContainer getByValue(double value) {
        for (int i = 0; i < size; i++) {
            if (value < nodes[i].getValue()) {
                return nodes[i].getLeft();
            }
        }
        return nodes[size-1].getRight();
    }

    public AdvancedNode median() {
        if (size % 2 == 0) {
            throw new IllegalStateException("Cannot find median if size is even");
        }
        return nodes[size/2];
    }

    public AdvancedNodeArray left() {
        AdvancedNodeArray array = new AdvancedNodeArray(minSize+1);

        int s = size/2;
        array.size = s;

        if (s >= 0) System.arraycopy(nodes, 0, array.nodes, 0, s);
        return array;
    }
    public AdvancedNodeArray right() {
        AdvancedNodeArray array = new AdvancedNodeArray(minSize+1);

        int s = size/2;
        array.size = s;

        if (size - s >= 0) System.arraycopy(this.nodes, s + 1, array.nodes, 0, size - s - 1);
        return array;
    }

    @Override
    public Iterator<AdvancedNode> iterator() {
        return new Iterator<>() {

            private int num = -1;

            @Override
            public boolean hasNext() {
                return num < size - 1;
            }

            @Override
            public AdvancedNode next() {
                return nodes[++num];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public AdvancedNode last() {
        return nodes[size-1];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        builder.append(nodes[0]);
        for (int i = 1; i < size; i++) {
            builder.append(", ").append(nodes[i]);
        }
        builder.append("]");
        return builder.toString();
    }

    public int size() {
        return size;
    }



    public AdvancedNode getExact(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(value, nodes[i].getValue())) {
                return nodes[i];
            }
        }
        throw new NoSuchElementException("Cannot find node with value of " + value);
    }

    public double popLast() {
        return this.pop(last().getValue()).getValue();
    }

    public AdvancedNode first() {
        return nodes[0];
    }
    public double popFirst() {
        return this.pop(first().getValue()).getValue();
    }

    public boolean contains(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(nodes[i].getValue(), value))
                return true;
        }
        return false;
    }

    public void replace(double initial, double changed) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(nodes[i].getValue(), initial)) {
                nodes[i].setValue(changed);
                return;
            }
        }
        throw new NoSuchElementException("Can't replace element to " + changed + " because " + initial +
                "is not included in the array");
    }

    public void merge(double value, AdvancedNodeArray array) {
        if (this.last().getValue() > value || this.last().getValue() > array.first().getValue()) {
            throw new RuntimeException("Improper parameters for merge function. The output array won't be sorted");
        }
        if (this.size != minSize) {
            throw new IllegalStateException("Called merge, when array is not minimal");
        }
        AdvancedNode toAdd = nodes[size] = new AdvancedNode(value, last().getRight(), array.first().getLeft());
        if (toAdd.hasRight())
            toAdd.getRight().setSmallerParent(nodes[size]);
        if (toAdd.hasLeft())
            toAdd.getLeft().setGreaterParent(nodes[size]);
        last().setRight(nodes[size].getLeft());
        for (int i = 0; i < size; i++) {
            nodes[size + 1 + i] = array.nodes[i];
        }
        this.size = 2 * size + 1;
    }

    public AdvancedNode borrowFirst() {
        AdvancedNode toReturn = new AdvancedNode(minSize+1);

        toReturn.setRight(first().getLeft());
        toReturn.setValue(this.popFirst());

        return toReturn;
    }
    public AdvancedNode borrowLast() {
        AdvancedNode toReturn = new AdvancedNode(minSize+1);

        toReturn.setLeft(last().getRight());
        toReturn.setValue(this.popLast());

        return toReturn;
    }


}
