import java.util.LinkedList;

public class TreeNode {
    private LList values;
    private LinkedList<TreeNode> children;
    private TreeNode parent;

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    private boolean isLeaf = true;

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
                this.children.get(toChild).split(this, toChild);
            }
            children.get(toChild).add(val);
        }
    }

    protected void split(TreeNode parent, int pos) {
        LList[] lists = this.values.split();
        this.values = lists[0];
        parent.values.merge(lists[1]);
        TreeNode neighbor = new TreeNode(isLeaf, lists[2], degree);
        if (!isLeaf) {
            for (int i = 0; i < degree; i++) {
                neighbor.children.add(this.children.remove(degree));
            }
        }
        parent.children.add(pos+1, neighbor);
    }

    private TreeNode(boolean isLeaf, LList list, int d) {
        this.isLeaf = isLeaf;
        this.values = list;
        minKeys = d - 1;
        this.degree = d;
        maxKeys = 2 * d - 1;
        this.children = new LinkedList<>();
    }

    private boolean isFull() {
        return values.isFull();
    }

    public void pop(double val) {

    }
    public void find(double val) {

    }

    protected void setChildren(LinkedList<TreeNode> t) {
        this.children = t;
    }
    protected void setList(LList list) {
        this.values = list;
    }
    public String asString() { return asString(0); }

    public String asString(int tabulation) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("\t".repeat(tabulation)).append("[").append(values.get(i)).append("]\n");
            if (!isLeaf)
                builder.append(children.get(i).asString(tabulation+1));
        }
        if (!isLeaf)
            builder.append(children.get(values.size()).asString(tabulation+1));
        return builder.toString();
    }
}
