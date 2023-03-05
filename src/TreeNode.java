import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {
    private LinkedList<Double> values;
    private LinkedList<TreeNode> children;
    private TreeNode parent;
    private boolean isLeaf;
    private final int minKeys;
    private final int maxKeys;

    public TreeNode(int d) {
        minKeys = d - 1;
        maxKeys = 2 * d - 1;
        this.children = new LinkedList<>();
        this.values = new LinkedList<>();
    }


    public TreeNode(int degree, double val) {
        this(degree);
        this.values.add(val);
    }

    private boolean compare(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

    public void add(double val) {
        if (isLeaf) {
            ;
        }
    }
    public void pop(double val) {

    }
    public void find(double val) {

    }
}
