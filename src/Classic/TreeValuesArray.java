package Classic;

import java.util.Iterator;
import java.util.NoSuchElementException;

class TreeValuesArray implements Iterable<Double> {
    private final double[] array;
    private final int minSize;
    private final int maxSize;

    private final int degree;
    private int size;

    public TreeValuesArray(int degree) {
        minSize = degree - 1;
        maxSize = 2 * degree - 1;
        this.degree = degree;
        size = 0;
        array = new double[maxSize];
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
        for (int i = position; i < size - 1; i++) {
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
    public int positionExact(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(value, array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't find value " + value + " in the array");
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
        return array[size/2];
    }

    public TreeValuesArray leftToMedian() {
        TreeValuesArray result = new TreeValuesArray(degree);
        int s = size/2;
        System.arraycopy(array, 0, result.array, 0, s);
        result.size = s;
        return result;
    }
    public TreeValuesArray rightToMedian() {
        TreeValuesArray result = new TreeValuesArray(degree);
        int s = size/2;
        System.arraycopy(array, s+1, result.array, 0, s);
        result.size = s;
        return result;
    }

    public int size() {
        return size;
    }

    public void popValue(double v) {
        pop(positionExact(v));
    }

    public double last() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Can't get last in empty array");
        return array[size-1];
    }

    public double first() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Can't get first in empty array");
        return array[0];
    }

    public void insert(int position, double d) {
        if (position < size) {
            array[position] = d;
        } else if (position == size) {
            array[size] = d;
            size++;
        } else {
            throw new IndexOutOfBoundsException("Position cannot be greater than size: " + size);
        }
    }

    public double popLast() {
        size--;
        return array[size];
    }

    public double popFirst() {
        double d = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i+1];
        }
        size--;
        return d;
    }

    public boolean isNotMinimum() {
        return size > minSize;
    }
}
