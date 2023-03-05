import java.util.Iterator;

public class LList implements Iterable<ListNode> {
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
            if (d < c.value()) {
                head = new ListNode(d, null, head);
                if (head.hasNext())
                    head.getNext().setPrev(head);
                size++;
                return result;
            }
            for (; c != tail; c = c.getNext()) {
                if (d < c.value()) {
                    ListNode p = c.getPrev();
                    c.setPrev(new ListNode(d, c.getPrev(), c));
                    if (p != null)
                        p.setNext(c.getPrev());
                    size++;
                    return result;
                }
                result++;
            }
            if (d < c.value()) {
                ListNode p = c.getPrev();
                c.setPrev(new ListNode(d, c.getPrev(), c));
                if (p != null)
                    p.setNext(c.getPrev());
            } else {
                result++;
                c.setNext(new ListNode(d, c, null));
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
        for (ListNode c = head; c != null; c = c.getNext()) {
            result++;
            if (Doubles.compare(d, c.value())) {
                size--;
                if (c == head) {
                    if (head.hasNext()) {
                        head.getNext().setPrev(null);
                    } else {
                        head = tail = null;
                        return 0;
                    }
                    head = head.getNext();
                } else if (c == tail) {
                    tail.getPrev().setNext(null);
                    tail = tail.getPrev();
                } else {
                    c.getNext().setPrev(c.getPrev());
                    c.getPrev().setNext(c.getNext());
                }
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
    public LList[] split() {
        if (size % 2 == 0 || size < 3) {
            throw new IllegalStateException("Size cannot be equal to " + size + ". Unable to find median");
        }
        ListNode median = head;
        int m = size / 2;
        for (int i = 0; i < m; i++) {
            median = median.getNext();
        }
        ListNode medianNext = median.getNext();
        LList[] arr = new LList[3];
        arr[0] = new LList(maxSize,m, head, median.getPrev());
        arr[0].tail.setNext(null);

        arr[1] = new LList(1,1, median, median);
        arr[1].head.setPrev(null);
        arr[1].tail.setNext(null);

        arr[2] = new LList(maxSize,m ,medianNext, tail);
        arr[2].head.setPrev(null);
        return arr;
    }
    private LList(int maxSize, int size, ListNode head, ListNode tail) {
        this.maxSize = maxSize;
        this.size = size;
        this.head = head;
        this.tail = tail;
    }

    public int position(double d) {
        int result = 0;
        for (ListNode current = head; current != null; current = current.getNext()) {
            if (d < current.value())
                return result;
            result++;
        }
        return result;
    }


    @Override
    public Iterator<ListNode> iterator() {
        return new Iterator<>() {

            private ListNode currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public ListNode next() {
                return currentNode.getNext();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    @Override
    public String toString() {
        if (head == null)
            return "Empty list";
        StringBuilder builder = new StringBuilder();
        builder.append(head);
        for (ListNode c = head.getNext(); c != null; c = c.getNext()) {
           builder.append("->").append(c);
        }
        return builder.toString();
    }
    protected String printBothSides() {
        if (head == null)
            return "Empty list";
        StringBuilder builder = new StringBuilder();
        builder.append(head);
        for (ListNode c = head.getNext(); c != null; c = c.getNext()) {
            builder.append("->").append(c);
        }
        builder.append('\n');
        builder.append(tail);
        for (ListNode c = tail.getPrev(); c != null; c = c.getPrev()) {
            builder.append("<-").append(c);
        }
        return builder.toString();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
    public void merge(LList other) {
        for (ListNode current = other.head; current != null; current = current.getNext()) {
            this.add(current.value());
        }
    }

    public double get(int index) {
        ListNode current = head;
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is larger than list size.");
        }
        for (int j = 0; j < index; j++) {
            current = current.getNext();
        }
        return current.value();
    }
}
