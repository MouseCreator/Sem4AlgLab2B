package Classic;

import Advanced.AdvancedNode;

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

    public void add(double value) {
        if (size == maxSize) {
            throw new IndexOutOfBoundsException("Adding value to a full array of size " + size);
        }
        size++;
        for (int i = size; i >= 0; i--) {
            if (value > array[i]) {
                array[i+1] = value;

                return;
            }
            array[i+1] = array[i];
        }
        array[0] = value;
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
}
