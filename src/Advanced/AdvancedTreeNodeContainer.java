package Advanced;

public class AdvancedTreeNodeContainer {

    private AdvancedTreeNode node;
    private AdvancedNode greaterParent;
    private AdvancedNode smallerParent;

    public AdvancedTreeNodeContainer(AdvancedTreeNode neighbor) {
        this.node = neighbor;
    }

    public AdvancedTreeNode getNode() {
        return node;
    }

    public void setNode(AdvancedTreeNode node) {
        this.node = node;
    }



    public AdvancedNode getSmallerParent() {
        return smallerParent;
    }

    public void setSmallerParent(AdvancedNode smallerParent) {
        this.smallerParent = smallerParent;
    }

    public AdvancedNode getGreaterParent() {
        return greaterParent;
    }

    public void setGreaterParent(AdvancedNode greaterParent) {
        this.greaterParent = greaterParent;
    }
    @Override
    public String toString() {
        return this.node.toString();
    }
}
