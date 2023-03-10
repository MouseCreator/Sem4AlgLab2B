package Classic;

public class ClassicChildrenArray {
    private ClassicBTreeNode[] nodes;

    private final int minSize;
    private final int maxSize;

    public ClassicChildrenArray(int degree) {
        this.minSize = degree;
        this.maxSize = 2 * degree;
        nodes = new ClassicBTreeNode[maxSize];
    }

    public ClassicBTreeNode get(int addTo) {
        return nodes[addTo];
    }
}
