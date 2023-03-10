package Classic;

import Std.Doubles;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassicTreeArray implements Iterable<Double> {
    private final double[] array;
    private final int minSize;
    private final int maxSize;

    private final int degree;
    private int size;

    public ClassicTreeArray(int degree) {
        minSize = degree - 1;
        maxSize = 2 * degree - 1;
        this.degree = degree;
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
        if (isFull()) {
            throw new IndexOutOfBoundsException("Adding value to a full array of size " + size);
        }

        for (int i = size - 1; i >= 0; i--) {
            if (value >= array[i]) {
                array[i+1] = value;
                size++;
                return;
            }
            array[i+1] = array[i];
        }
        array[0] = value;
        size++;
    }

    public double pop(int position) {
        if (position >= size) {
            throw new IndexOutOfBoundsException("Can't get " + position + " in array of size " + size);
        }
        double toPop = array[position];
        for (int i = position; i < size; i++) {
            array[i] = array[i+1];
        }
        size--;
        return toPop;
    }
    public int position(double value) {
        for (int i = 0; i < size; i++) {
            if (value < array[i]) {
               return i;
            }
        }
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public double get(int j) {
        if (j >= size)
            throw new IndexOutOfBoundsException("Trying to get " + j + " in array of size " + size);
        return array[j];
    }

    public boolean contains(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(array[i], value)) {
                return true;
            }
        }
        return false;
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

    public boolean isFull() {
        return size == maxSize;
    }

    public double median() {
        if (size % 2 == 0) {
            throw new IllegalStateException("Trying to get median in array of even size");
        }
        return array[size-1];
    }

    public ClassicTreeArray leftToMedian() {
        ClassicTreeArray result = new ClassicTreeArray(degree);
        int s = size/2;
        System.arraycopy(array, 0, result.array, 0, s);
        result.size = s;
        return result;
    }
    public ClassicTreeArray rightToMedian() {
        ClassicTreeArray result = new ClassicTreeArray(degree);
        int s = size/2;
        System.arraycopy(array, s+1, result.array, 0, s);
        result.size = s;
        return result;
    }
}
