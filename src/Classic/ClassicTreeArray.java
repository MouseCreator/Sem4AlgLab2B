package Classic;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassicTreeArray implements Iterable<Double> {
    private double[] array;
    private final int minSize;
    private final int maxSize;

    private int size;

    public ClassicTreeArray(int degree) {
        minSize = degree - 1;
        maxSize = 2 * degree - 1;
        size = 0;
        array = new double[maxSize];
    }
    protected ArrayList<Double> toArrayList() {
        ArrayList<Double> doubles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            doubles.add(array[i]);
        }
        return doubles;
    }
    public void add(double value) {
        if (size == maxSize) {
            throw new IndexOutOfBoundsException("Adding value to a full array of size " + size);
        }

        for (int i = size - 1; i >= 0; i--) {
            if (value > array[i]) {
                array[i+1] = value;
                size++;
                return;
            }
            array[i+1] = array[i];
        }
        array[0] = value;
        size++;
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[Empty array]";
        }
            StringBuilder builder = new StringBuilder("[" + array[0]);
        for (int i = 1; i < size; i++) {
            builder.append(", ").append(array[i]);
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Double> iterator() {
        return new Iterator<>() {

            private int num = -1;

            @Override
            public boolean hasNext() {
                return num < size - 1;
            }

            @Override
            public Double next() {
                return array[++num];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public double get(int j) {
        if (j >= size)
            throw new IndexOutOfBoundsException("Trying to get " + j + " in array of size " + size);
        return array[j];
    }
}
