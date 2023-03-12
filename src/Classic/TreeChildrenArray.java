package Classic;

import java.util.Iterator;

class TreeChildrenArray implements Iterable<BTreeNode>{
    private BTreeNode[] nodes;

    private final int minSize;
    private final int maxSize;
    private int size;

    public TreeChildrenArray(int degree) {
        this.minSize = degree;
        this.maxSize = 2 * degree;
        nodes = new BTreeNode[maxSize];
    }

    public BTreeNode get(int i) {
        return nodes[i];
    }


    public TreeChildrenArray left() {
        TreeChildrenArray result = new TreeChildrenArray(minSize);
        if (size > 0) {
            int s = size / 2;
            System.arraycopy(nodes, 0, result.nodes, 0, s);
            result.size = s;
        }
        return result;
    }
    public TreeChildrenArray right() {
        TreeChildrenArray result = new TreeChildrenArray(minSize);
        if (size > 0) {
            int s = size / 2;
            System.arraycopy(nodes, s, result.nodes, 0, s);
            result.size = s;
        }
        return result;
    }
    public void replace(int index, BTreeNode newValue) {
        if (size > maxSize || index > size)
            throw new IndexOutOfBoundsException("Cannot replace at index " + index + " in array of size " + size);
        nodes[index] = newValue;
    }

    public void addLast(BTreeNode newValue) {
        if (size >= maxSize)
            throw new IndexOutOfBoundsException("Array is over its size of " + maxSize);
        nodes[size] = newValue;
        size++;

    }
    public void insert(int index, BTreeNode value) {
        if (size >= maxSize || index > size)
            throw new IndexOutOfBoundsException("Cannot insert at index " + index + " in array of size " + size);
        for (int i = size-1; i >= index; i--) {
            nodes[i+1]=nodes[i];
        }
        nodes[index]=value;
        size++;
    }

    public BTreeNode last() {
        return this.nodes[size-1];
    }

    public void clear() {
        this.nodes = new BTreeNode[maxSize];
        size = 0;
    }


    public BTreeNode first() {
        return nodes[0];
    }

    @Override
    public Iterator<BTreeNode> iterator() {
        return new Iterator<>() {

            private int num = -1;

            @Override
            public boolean hasNext() {
                return num < size - 1;
            }

            @Override
            public BTreeNode next() {
                return nodes[++num];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            nodes[i] = nodes[i+1];
        }
        nodes[size-1] = null;
        size--;
    }

    public BTreeNode popFirst() {
        BTreeNode d = nodes[0];
        for (int i = 0; i < size; i++) {
            nodes[i] = nodes[i+1];
        }
        size--;
        return d;
    }

    public BTreeNode popLast() {
        size--;
        BTreeNode v = nodes[size];
        nodes[size] = null;
        return v;
    }

    public void addFirst(BTreeNode value) {
        nodes[size] = value;
        size++;
    }
}
