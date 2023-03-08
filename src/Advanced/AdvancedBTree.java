package Advanced;

public class AdvancedBTree {

    private AdvancedTreeNode root;

    private final int degree;

    public AdvancedBTree(int degree) {
        this.degree = degree;
    }


    public void add(double val) {
        if (root == null) {
            root = new AdvancedTreeNode(degree, val);
        }
        else {
            root.add(val);
        }
    }
}
