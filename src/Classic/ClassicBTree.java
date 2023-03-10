package Classic;

public class ClassicBTree {

    private ClassicBTreeNode root;

    public ClassicBTree(int degree) {
        this.root = new ClassicBTreeNode(degree);
    }

    public void add(double value) {
        root.add(value);
    }
    public void pop(double value) {

    }
    public void get(double value) {

    }

    public String asString() {
        return root.asString(0);
    }
}
