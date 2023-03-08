package Std;

public class Doubles {

    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }
    public static String asString(double d) {
        return String.format("%.2f", d);
    }
}
