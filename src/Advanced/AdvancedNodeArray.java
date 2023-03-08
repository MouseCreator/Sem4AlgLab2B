package Advanced;

import Std.Doubles;

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
        int isAdded = 0;
        for (int i = 0; i < size; i++) {
            if (isAdded == 0 && node.getValue() > nodes[i].getValue()) {
                nodes[i] = node;
                isAdded = 1;
            }
            nodes[i+isAdded]=nodes[i];
        }
        if (isAdded == 0) {
            nodes[size] = node;
        }
        size++;
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
}
