package Classic;

public class ClassicBTreeNode {
    private ClassicTreeArray values;
    private ClassicChildrenArray children;
    private int degree;
    public ClassicBTreeNode(int degree) {
        this.degree = 0;
        values = new ClassicTreeArray(degree);
        children = new ClassicChildrenArray(degree);
    }
}
