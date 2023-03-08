package Advanced;

import Std.Doubles;
import Std.TreeNode;

import java.util.NoSuchElementException;

public class AdvancedNodeArray {
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
        if (node.hasLeft() && node.hasRight()) {
            throw new IllegalStateException("Cannot add node with two children to node array");
        }
        if(node.hasRight())
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
                if (i != 0)
                    node.setLeft(nodes[i-1].getRight());
            }
            nodes[i+isAdded]=nodes[i];
        }
    }

    public AdvancedNode pop(double value) {
        if (size == 0) {
            throw new NoSuchElementException("Trying to get element from empty array.");
        }
        AdvancedNode result = null;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(value, nodes[i].getValue())) {
                j = -1;
                result = nodes[i];
            }
            nodes[i+j]=nodes[i];
        }
        if (j==0)
            throw new NoSuchElementException("No value " + value + " in the array node");
        nodes[size-1]=null;
        size--;
        return result;
    }

    public AdvancedNode replace(AdvancedNode from) {
        for (int i = 0; i < size; i++) {
            if (from.getValue() > nodes[i].getValue()) {
                AdvancedNode result = nodes[i];
                nodes[i] = from;
                return result;
            }
        }
        return nodes[size-1];
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
        for (AdvancedNode node : nodes) {
            if (value > node.getValue()) {
                return node.getLeft().getNode();
            }
        }
        return nodes[size-1].getRight().getNode();
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
        if (s >= 0) System.arraycopy(nodes, 0, array.nodes, 0, s);
        return array;
    }
    public AdvancedNodeArray right() {
        AdvancedNodeArray array = new AdvancedNodeArray(minSize+1);
        int s = size/2;
        if (size - s >= 0) System.arraycopy(nodes, s + s, array.nodes, s, size - s);
        return array;
    }
}
