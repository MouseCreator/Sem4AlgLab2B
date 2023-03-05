import java.util.ArrayList;
import java.util.LinkedList;

public class TreeNode {
    private LList values;
    private LinkedList<TreeNode> children;
    private TreeNode parent;
    private boolean isLeaf;
    private final int minKeys;
    private final int maxKeys;

    public TreeNode(int d) {
        minKeys = d - 1;
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
                this.children.get(toChild).split();
            }
            children.get(toChild).add(val);
        }
    }

    private TreeNode[] split() {
        LList[] lists = this.values.split();
        TreeNode[] nodes = new TreeNode[3];
        return nodes; //TODO: implement splitting
    }

    private boolean isFull() {
        return values.isFull();
    }

    public void pop(double val) {

    }
    public void find(double val) {

    }
}
