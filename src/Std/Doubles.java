package Std;

public class Doubles {

    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }
    public static String asString(double d) {
        return String.format("%.2f", d);
    }

    public static int compare(Double o1, Double o2) {
        if (isEqual(o1, o2))
            return 0;
        if (o1 > o2)
            return 1;
        return -1;
    }
}
