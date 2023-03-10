package Classic;

public class ClassicChildrenArray {
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
        if (size >= maxSize || index > size)
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
}
