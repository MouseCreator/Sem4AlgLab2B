import java.util.LinkedList;

public class TreeNode {
    private LList values;
    private LinkedList<TreeNode> children;
    private TreeNode parent;

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    private boolean isLeaf = true;

    private final int degree;
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
                this.children.get(toChild).split(toChild);
            }
            children.get(toChild).add(val);
        }
    }

    protected TreeNode split(int pos) {
        LList[] lists = this.values.split();
        this.values = lists[0];

        TreeNode neighbor = new TreeNode(this.parent, isLeaf, lists[2], degree);
        if (!isLeaf) {
            for (int i = 0; i < degree; i++) {
                neighbor.children.add(this.children.remove(degree));
            }
        }
        if (parent != null) {
            parent.children.add(pos + 1, neighbor);
            parent.values.merge(lists[1]);
        } else {
            parent = new TreeNode(null, false, lists[1], degree);
            parent.children.add(this);
            parent.children.add(neighbor);
        }
        return parent;
    }

    private TreeNode(TreeNode parent, boolean isLeaf, LList list, int d) {
        this.isLeaf = isLeaf;
        this.parent = parent;
        this.values = list;
        minKeys = d - 1;
        this.degree = d;
        maxKeys = 2 * d - 1;
        this.children = new LinkedList<>();
    }

    public boolean isFull() {
        return values.isFull();
    }

    public void pop(double val) {
        int i = 0;
        for (ListNode a : values) {
            if (Doubles.compare(a.value(), val)) {
                if (isLeaf) {
                    values.pop(val);
                    return;
                } else {

                }
            } else if (val > a.value()) {
                this.children.get(i).pop(val);
            }
        }
    }
    public int size() {
        return values.size();
    }
    public TreeNode childrenBalance(int myNumber) {
        if (this.parent != null) {
            TreeNode left = parent.leftOf(myNumber);
            TreeNode right = parent.rightOf(myNumber);
            if (left != null && left.size() > minKeys) {
                this.children.addFirst(left.children.pollFirst());
                this.values.add(left.values.popLast());
            }
            else if (right != null && right.size() > minKeys) {
                this.children.addLast(right.children.pollFirst());
                this.values.add(right.values.popFirst());
            } else {
                if (right != null) {
                    this.values.merge(right.values);
                    this.children.addAll(right.children);
                    this.parent.children.remove(right);
                } else if (left != null) {
                    this.children.addAll(minKeys, left.children);
                    this.values.merge(left.values);
                    this.parent.children.remove(left);
                    this.parent.values.popAt(myNumber+1);
                } else {
                    throw new RuntimeException("Cannot merge nodes");
                }
            }
        }
        return this.parent;
    }

    private TreeNode rightOf(int index) {
        try {
            return children.get(index + 1);
        } catch (Exception e) {
            return null;
        }
    }

    private TreeNode leftOf(int index) {
        try {
            return children.get(index - 1);
        } catch (Exception e) {
            return null;
        }
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
        if (!isLeaf) {
            builder.append("\t".repeat(tabulation))
                    .append("_\n").append(children.get(values.size()).asString(tabulation + 1));
        }
        return builder.toString();
    }

}
