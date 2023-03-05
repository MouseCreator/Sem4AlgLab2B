import java.util.ArrayList;

public class TreeNode {
    private ArrayList<Double> values;
    private ArrayList<TreeNode> children;
    private TreeNode parent;
    private boolean isLeaf;

    private int degree;

    private final int minKeys;
    private final int maxKeys;

    public TreeNode(int d) {
        minKeys = d - 1;
        maxKeys = 2 * d - 1;
        this.children = new ArrayList<>();
        this.values = new ArrayList<>();
    }


    public TreeNode(int degree, double val) {
        this(degree);
        this.values.add(val);
    }

    private boolean compare(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

    public void add(double val) {
    }
    public void pop(double val) {

    }
    public void find(double val) {

    }
}
