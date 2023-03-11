package Classic;

import java.util.Iterator;

public class ClassicChildrenArray implements Iterable<ClassicBTreeNode>{
    private ClassicBTreeNode[] nodes;

    private final int minSize;
    private final int maxSize;


    private int size;

    public ClassicChildrenArray(int degree) {
        this.minSize = degree;
        this.maxSize = 2 * degree;
        nodes = new ClassicBTreeNode[maxSize];
    }

    public ClassicBTreeNode get(int i) {
        return nodes[i];
    }


    public ClassicChildrenArray left() {
        ClassicChildrenArray result = new ClassicChildrenArray(minSize);
        if (size > 0) {
            int s = size / 2;
            System.arraycopy(nodes, 0, result.nodes, 0, s);
            result.size = s;
        }
        return result;
    }
    public ClassicChildrenArray right() {
        ClassicChildrenArray result = new ClassicChildrenArray(minSize);
        if (size > 0) {
            int s = size / 2;
            System.arraycopy(nodes, s, result.nodes, 0, s);
            result.size = s;
        }
        return result;
    }
    public void replace(int index, ClassicBTreeNode newValue) {
        if (size > maxSize || index > size)
            throw new IndexOutOfBoundsException("Cannot replace at index " + index + " in array of size " + size);
        nodes[index] = newValue;
    }

    public void addLast(ClassicBTreeNode newValue) {
        if (size >= maxSize)
            throw new IndexOutOfBoundsException("Array is over its size of " + maxSize);
        nodes[size] = newValue;
        size++;

    }
    public void insert(int index, ClassicBTreeNode value) {
        if (size >= maxSize || index > size)
            throw new IndexOutOfBoundsException("Cannot insert at index " + index + " in array of size " + size);
        for (int i = size-1; i >= index; i--) {
            nodes[i+1]=nodes[i];
        }
        nodes[index]=value;
        size++;
    }

    public ClassicBTreeNode last() {
        return this.nodes[size-1];
    }

    public void clear() {
        this.nodes = new ClassicBTreeNode[maxSize];
        size = 0;
    }

    public boolean isNotMinimum() {
        return size != minSize;
    }

    public ClassicBTreeNode first() {
        return nodes[0];
    }

    @Override
    public Iterator<ClassicBTreeNode> iterator() {
        return new Iterator<>() {

            private int num = -1;

            @Override
            public boolean hasNext() {
                return num < size - 1;
            }

            @Override
            public ClassicBTreeNode next() {
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
        if (size != maxSize)
            nodes[size-1] = null;
        size--;
    }

    public ClassicBTreeNode popFirst() {
        ClassicBTreeNode d = nodes[0];
        for (int i = 0; i < size; i++) {
            nodes[i] = nodes[i+1];
        }
        size--;
        return d;
    }

    public ClassicBTreeNode popLast() {
        size--;
        ClassicBTreeNode v = nodes[size];
        nodes[size] = null;
        return v;
    }

    public void addFirst(ClassicBTreeNode value) {
        nodes[size] = value;
        size++;
    }
}
