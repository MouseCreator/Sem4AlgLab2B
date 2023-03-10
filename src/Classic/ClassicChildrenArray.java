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

    public ClassicBTreeNode get(int addTo) {
        return nodes[addTo];
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
            System.arraycopy(nodes, s + 1, result.nodes, 0, s);
            result.size = s;
        }
        return result;
    }
}
