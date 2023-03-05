
public class ListNode {
    public double value;
    private ListNode prev;
    private double d;
    private ListNode next;

    public ListNode(double d) {
        this.next = null;
        this.prev = null;
        this.d = d;
    }

    public ListNode(double d, ListNode prev, ListNode next) {
        this.d = d;
        this.prev = prev;
        this.next = next;
    }

    public ListNode getNext() {
        return next;
    }
    public double value() {
        return d;
    }

    public void setNext(ListNode listNode) {
        this.next = listNode;
    }
}
