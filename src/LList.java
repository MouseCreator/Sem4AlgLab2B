import java.util.LinkedList;

public class LList {
    private ListNode head;

    private ListNode tail;
    private int size;
    private final int maxSize;

    public LList(int maxSize) {
        this.maxSize = maxSize;
    }
    public int add(double d) {
        int result = 0;
        if (head == null) {
            head = tail = new ListNode(d);
        } else {
            for (ListNode c = head; c != tail; c = c.getNext()) {
                result++;
                if (d > c.value()) {
                    c.setNext(new ListNode(d, c, c.getNext()));
                    break;
                }
            }
        }
        size++;
        return result;
    }
    public boolean isFull() {
        return size == maxSize;
    }

}
