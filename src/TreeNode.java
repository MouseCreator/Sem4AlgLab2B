import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {
    private LList values;
    private LinkedList<TreeNode> children;
    private TreeNode parent;
    private boolean isLeaf;

    private int degree;
    private final int minKeys;
    private final int maxKeys;

    public TreeNode(int d) {
        minKeys = d - 1;
        this.degree = d;
        maxKeys = 2 * d - 1;
        this.children = new LinkedList<>();
        this.values = new LList(maxKeys);
    }


    public TreeNode(int degree, double val) {
        this(degree);
        this.values.add(val);
    }


    public void add(double val) {
        if (isLeaf) {
            this.values.add(val);
        } else {
            int toChild = values.position(val);
            if (this.children.get(toChild).isFull()) {
                this.children.get(toChild).split(this);
            }
            children.get(toChild).add(val);
        }
    }

    private void split(TreeNode parent) {
        LList[] lists = this.values.split();
        this.values = lists[0];
        parent.values.merge(lists[1]);
        TreeNode neighbor = new TreeNode(isLeaf, lists[2], degree);
        if (!isLeaf) {
            for (int i = 0; i < degree; i++) {
                neighbor.children.add(this.children.remove(0));
            }
        }
    }

    private TreeNode(boolean isLeaf, LList list, int d) {
        this.isLeaf = isLeaf;
        this.values = list;
        minKeys = d - 1;
        this.degree = d;
        maxKeys = 2 * d - 1;
    }

    private boolean isFull() {
        return values.isFull();
    }

    public void pop(double val) {

    }
    public void find(double val) {

    }
}
