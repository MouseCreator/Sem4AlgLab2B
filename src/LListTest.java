import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LListTest {

    private boolean isSorted(LList list) {
        if (list.isEmpty())
            return false;
        for (ListNode a : list) {
            if (a.getNext() != null && a.value() > a.getNext().value())
                return false;
        }
        return true;
    }
    @Test
    void testAdd() {
        LList list = new LList(100);
        assertEquals(0, list.add(2.0));
        assertEquals(1, list.add(3.0));
        assertEquals(0, list.add(1.5));
        assertEquals(2,list.add(2.5));
        System.out.println(list.printBothSides());
        assertTrue(isSorted(list));
    }

    @Test
    void pop() {
        LList list = new LList(100);
        list.add(2.0);
        list.add(3.0);
        list.add(1.5);
        list.add(2.5);
        list.pop(2.0);
        list.pop(3.0);
        list.pop(2.5);
        System.out.println(list.printBothSides());
        assertTrue(isSorted(list));
    }
}