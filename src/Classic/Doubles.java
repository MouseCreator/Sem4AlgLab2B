package Classic;

/**
 * Клас для роботи з дійсними числами
 */
public class Doubles {
    /**
     * порівняння двох чисел на рівність
     * @param a - число 1
     * @param b - число 2
     * @return true, якщо числа рівні з точність до четвертого знака
     */
    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < 0.0001;
    }

    /**
     *
     * @param d - дійсне число
     * @return рядкове представлення числа {@param d}
     */
    public static String asString(double d) {
        return String.format("%.2f", d);
    }

    /**
     * Порівння двох дійсних чисел
     * @param o1 - скінеченне дійсне число
     * @param o2 - скінеченне дійсне число
     * @return -1, якщо o1 < o2,
     *          0, якщо o1 == o2,
     *          1, якщо o1 > o2.
     */
    public static int compare(Double o1, Double o2) {
        if (isEqual(o1, o2))
            return 0;
        if (o1 > o2)
            return 1;
        return -1;
    }
}
