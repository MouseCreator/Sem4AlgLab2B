package Classic;

public class ClassicTreeArray {
    private double[] array;
    private final int minSize;
    private final int maxSize;

    public ClassicTreeArray(int degree) {
        minSize = degree - 1;
        maxSize = 2 * degree - 1;
    }
}
