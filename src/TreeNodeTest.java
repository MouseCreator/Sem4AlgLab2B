import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {
    @Test
    void split() {
        TreeNode parent = new TreeNode(3, 8);
        LinkedList<TreeNode> children = new LinkedList<>();
        TreeNode node = new TreeNode(3);
        children.add(node);
        children.add(new TreeNode(10));
        parent.setChildren(children);
        parent.setLeaf(false);
        node.setLeaf(false);
        LList list = new LList(5);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        node.setList(list);
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(new TreeNode(3, 0.5));
        nodes.add(new TreeNode(3, 1.5));
        nodes.add(new TreeNode(3, 2.5));
        nodes.add(new TreeNode(3, 3.5));
        nodes.add(new TreeNode(3, 4.5));
        nodes.add(new TreeNode(3, 5.5));
        node.setChildren(nodes);
        node.split(parent, 0);
        System.out.println(parent.asString());
    }
}