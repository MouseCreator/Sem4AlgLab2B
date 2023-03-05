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
            ListNode c = head;
            if (d < c.value) {
                head = new ListNode(d, null, head);
                size++;
                return result;
            }
            for (; c != tail; c = c.getNext()) {
                result++;
                if (d > c.value()) {
                    c.setNext(new ListNode(d, c, c.getNext()));
                    size++;
                    return result;
                }
            }
            if (d > c.value()) {
                c.setNext(new ListNode(d, c, c.getNext()));
                tail = c.getNext();
            }
        }
        size++;
        return result;
    }
    public int pop(double d) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        int result = 0;
        for (ListNode c = head; c != tail; c = c.getNext()) {
            result++;
            if (Doubles.compare(d, c.value())) {
                size--;
                return result;
            }
        }
        return -1;
    }
    public boolean contains(double d) {
        for (ListNode c = head; c != null; c = c.getNext()) {
            if (Doubles.compare(d, c.value())) {
                return true;
            }
        }
        return false;
    }
    public boolean isFull() {
        return size == maxSize;
    }

}
