package Std;

public class ListNode {
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
    @Override
    public String toString() {
        return "[" + Doubles.asString(d) + "]";
    }

    public ListNode getPrev() {
        return prev;
    }

    public void setPrev(ListNode listNode) {
        this.prev = listNode;
    }

    public boolean hasNext() {
        return next != null;
    }
    public boolean hasPrev() {
        return prev != null;
    }

    public void setValue(double value) {
        d = value;
    }

    private ListNode next() {
        return this.next;
    }
    private ListNode prev() {
        return this.prev;
    }
}
