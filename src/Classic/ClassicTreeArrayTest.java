package Classic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ClassicTreeArrayTest {

    @Test
    void add() {
        ArrayList<Double> values = new ArrayList<>();
        values.add(2.0);
        values.add(1.0);
        values.add(4.0);
        values.add(2.5);
        values.add(1.5);
        int degree = 3;
        int attempts = 5;
        ArrayList<Double> sorted = new ArrayList<>(values);
        sorted.sort(Doubles::compare);
        for (int i = 0; i < attempts; i++) {
            Collections.shuffle(values);
            TreeValuesArray arr = new TreeValuesArray(degree);
            for (int j = 0; j < values.size(); j++) {
                if (j < degree * 2)
                    arr.add(values.get(j));
                else {
                    double toAdd = values.get(j);
                    assertThrows(IndexOutOfBoundsException.class, () -> arr.add(toAdd));
                }
            }
            for (int j = 0; j < values.size(); j++) {
                assertTrue(Doubles.isEqual(sorted.get(j), arr.get(j)));
            }
        }

    }

}