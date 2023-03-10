package Classic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicTreeArrayTest {

    @Test
    void add() {
        ClassicTreeArray array = new ClassicTreeArray(3);
        array.add(2);
        array.add(1);
        array.add(4);
        array.add(2.5);
        array.add(1.5);
        System.out.println(array);
        assertThrows(IndexOutOfBoundsException.class, ()->array.add(5));
    }

}