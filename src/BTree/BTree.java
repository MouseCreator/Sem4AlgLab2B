package BTree;

import java.util.ArrayList;

/**
 * Клас B-дерева
 */
public class BTree {

    protected BTreeNode root; //корінь дерева

    /**
     * Створює порожнє B-дерево
     * @param degree - степінь дерева
     */
    public BTree(int degree) {
        this.root = new BTreeNode(degree);
    }

    /**
     * Додає значення до дерева
     * @param value - значення, що потрібно додати
     */
    public void add(double value) {
        root.add(value);
    }

    /**
     * Вилучає значення, якщо воно наявне у дереві
     * @param value - значення, що потрібно вилучити
     */
    public void pop(double value) {
        root.pop(value);
        if (root.isEmpty()) {
            root = root.toChild();
        }
    }

    /**
     *
     * @param value - значення, яке потрібно знайти
     * @return true, якщо це значення наявне в дереві, false - інакше
     */
    public boolean get(double value) {
        return root.get(value);
    }

    /**
     *
     * @return представлення дерева у вигляді відсотрованого масиву
     */
    public ArrayList<Double> toArray() {
        ArrayList<Double> doubles = new ArrayList<>();
        root.toArray(doubles);
        return doubles;
    }

    /**
     *
     * @return рядкове представлення дерева у форматі табуляцій
     */
    public String asString() {
        return root.isEmpty() ? "Empty tree" : root.asString(0);
    }

    /**
     *
     * @return рядкове предствавлення дерева як відсортованого масиву значень
     */
    @Override
    public String toString() {
        return this.toArray().toString();
    }

    /**
     *
     * @return кількість ключів у дереві
     */
    public int size() {
        return root.fullSize();
    }

    /**
     *
     * @return висота дерева;
     * висотою вважається довжина шляху до листа, тому дерево, що містить лише корінь має висоту 0.
     */
    public int height() {
        return root.height();
    }
}
