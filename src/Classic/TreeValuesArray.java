package Classic;

import java.util.Iterator;
import java.util.NoSuchElementException;

class TreeValuesArray implements Iterable<Double> {
    private final double[] array; //значення масиву
    private final int minSize; //мінімальний розмір
    private final int maxSize; //максимальний розмір

    private final int degree; //ступінь
    private int size; //поточний розмір масиву

    /**
     * Утворення порожнього масиву
     * @param degree - степінь дерева
     */
    public TreeValuesArray(int degree) {
        minSize = degree - 1;
        maxSize = 2 * degree - 1;
        this.degree = degree;
        size = 0;
        array = new double[maxSize];
    }

    /**
     * Додавання елемента до масиву (зберігаючи відсортованість)
     * @param value - значення, що потрібно додати
     */
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

    /**
     * Вилучення значення за індексом
     * @param position - індекс
     * @return - вилучене значення
     */
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

    /**
     *
     * @param value - значення
     * @return - номер відповідного цьому значенню піддерева
     */
    public int position(double value) {
        for (int i = 0; i < size; i++) {
            if (value < array[i]) {
               return i;
            }
        }
        return size;
    }

    /**
     *
     * @param value - значення, позиція якого знаходиться
     * @return номер цього значення в масиві
     */
    public int positionExact(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(value, array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't find value " + value + " in the array");
    }

    /**
     *
     * @return true, якщо масив порожній
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @param j - індекс елемента
     * @return елемент за заданим індексом
     */
    public double get(int j) {
        if (j >= size)
            throw new IndexOutOfBoundsException("Trying to get " + j + " in array of size " + size);
        return array[j];
    }

    /**
     *
     * @param value - значення, для якого здійснюється перевірка
     * @return true, якщо значення наявне в масиві
     */
    public boolean contains(double value) {
        for (int i = 0; i < size; i++) {
            if (Doubles.isEqual(array[i], value)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return рядкове представлення масиву
     */
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

    /**
     *
     * @return true, якщо масив повний
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     *
     * @return медіана масиву (за умови, що масив має непарну довжину)
     */
    public double median() {
        if (size % 2 == 0) {
            throw new IllegalStateException("Trying to get median in array of even size");
        }
        return array[size/2];
    }

    /**
     *
     * @return лівий підмасив (значення більші медіани)
     */

    public TreeValuesArray leftToMedian() {
        TreeValuesArray result = new TreeValuesArray(degree);
        int s = size/2;
        System.arraycopy(array, 0, result.array, 0, s);
        result.size = s;
        return result;
    }

    /**
     *
     * @return правий підмасив (значення більші медіани)
     */
    public TreeValuesArray rightToMedian() {
        TreeValuesArray result = new TreeValuesArray(degree);
        int s = size/2;
        System.arraycopy(array, s+1, result.array, 0, s);
        result.size = s;
        return result;
    }

    /**
     *
     * @return розмір масиву
     */
    public int size() {
        return size;
    }

    /**
     *
     * @param v значення, що потрібно вилучити з масиву
     */
    public void popValue(double v) {
        pop(positionExact(v));
    }

    /**
     *
     * @return найбільше значення масиву
     */
    public double last() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Can't get last in empty array");
        return array[size-1];
    }

    /**
     *
     * @return найменше значення масиву
     */
    public double first() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Can't get first in empty array");
        return array[0];
    }

    /**
     * Вставка елемента за індексом
     * @param position - позицій нового елемента
     * @param d - значення, що потрібно додати
     */
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

    /**
     *
     * @return вилучене останнє значення
     */
    public double popLast() {
        size--;
        return array[size];
    }

    /**
     *
     * @return вилечене перше значення
     */
    public double popFirst() {
        double d = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i+1];
        }
        size--;
        return d;
    }

    /**
     *
     * @return true, якщо кількість елементів масиву не мінімальна
     */
    public boolean isNotMinimum() {
        return size > minSize;
    }

    /**
     * Вилучає всі значення з масиву
     */
    public void clear() {
        this.size = 0;
    }

    /**
     *
     * @return ітератор, що проходить масив
     */
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
