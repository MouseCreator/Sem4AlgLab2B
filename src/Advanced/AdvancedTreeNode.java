package Advanced;

public class AdvancedTreeNode {

    AdvancedNodeArray array;
    public boolean isFull() {
        return array.isFull();
    }

    public boolean isMinimum() {
        return array.isOnMinimumCapacity();
    }
}
