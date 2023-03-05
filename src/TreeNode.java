public class TreeNode {
    private double[] val;
    private TreeNode[] children;
    private TreeNode parent;
    private boolean isLeaf;

    private int degree;

    private final int minDegree;
    private final int maxDegree;

    public TreeNode(int d) {
        minDegree = d -1;
        maxDegree = 2 * d - 1;
    }

    private boolean compare(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

}
