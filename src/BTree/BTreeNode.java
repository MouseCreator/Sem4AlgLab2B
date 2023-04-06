package BTree;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Вузол у B-дереві
 */
class BTreeNode {
    private final TreeValuesArray values; //масив значень
    private final TreeChildrenArray children; //масив піддерев
    private final int degree; //степінь дерева

    private boolean isLeaf; //чи є даний вузол листовим

    /**
     * Створення порожнього вузла
     * @param degree - степінь вузла
     */
    public BTreeNode(int degree) {
        this.degree = degree;
        values = new TreeValuesArray(degree);
        children = new TreeChildrenArray(degree);
        this.isLeaf = true;
    }

    /**
     * Створення вузла на основі відомих параметрів
     * @param array - значення вузла
     * @param children - піддерева вузла
     * @param degree - степінь вузла
     * @param isLeaf - чи є вузол листовим
     */
    private BTreeNode(TreeValuesArray array, TreeChildrenArray children, int degree, boolean isLeaf) {
        this.values = array;
        this.children = children;
        this.degree = degree;
        this.isLeaf = isLeaf;
    }

    /**
     * Створення вузла на основі медіани та двох піддерев
     * @param degree - степінь дерева
     * @param isLeaf - чи є листом
     * @param smallNode - структура, з якої беруться медіана і піддерева
     */
    public BTreeNode(int degree, boolean isLeaf, SmallNode smallNode) {
        this.degree = degree;
        this.isLeaf =isLeaf;
        this.values = new TreeValuesArray(degree);
        this.children = new TreeChildrenArray(degree);
        fromSmallNode(smallNode);
    }

    /**
     * Злиття медіани й двох вузлів в один
     * @param smallNode - структура, на основі якої будується новий вузол
     */
    private void fromSmallNode(SmallNode smallNode) {
        for (double d : smallNode.left().values) {
            this.values.add(d);
        }
        this.values.add(smallNode.median());
        for (double d : smallNode.right().values) {
            this.values.add(d);
        }
        for (BTreeNode child : smallNode.left().children) {
            this.children.addLast(child);
        }
        for (BTreeNode child: smallNode.right().children) {
            this.children.addLast(child);
        }
    }

    /**
     * Додавання нового значення у деревл
     * @param value - значення, що необхідно додати
     */
    public void add(double value) {
        if (isFull()) {
            addToFullRoot(value); //розбиття кореневого вузла
            return;
        }
        if (isLeaf) {
            this.values.add(value); //додавання у листовий вузол
        } else {
            this.addNotLeaf(value); //перехід до наступного вузла
        }
    }

    /**
     * Додавання до повного кореня
     * @param value - значення, яке потрібно додати
     */
    private void addToFullRoot(double value) {
        SmallNode small = this.split();

        //поділ значень між утвореними листами
        this.isLeaf = false;
        this.values.clear();
        this.values.add(small.median());

        //утворення зв'язів з новоутвореними листами
        this.children.clear();
        this.children.addLast(small.left());
        this.children.addLast(small.right());
        this.add(value);
    }

    /**
     * Перехід до наступного вузла
     * @param value - значення, що вставляється
     */
    private void addNotLeaf(double value) {
        int addTo = values.position(value);
        if (children.get(addTo).isFull()) { //якщо наступний вузол повний
            splitChild(addTo); //розбиваємо вузол
            addTo = values.position(value);
        }
        children.get(addTo).add(value); //додаємо значення value до одного з утворених вузлів
    }

    /**
     * Розбиття вузла
     * @param addTo - місце вузла в масиві піддерев, до якого потрібно здійснити перехід
     */
    private void splitChild(int addTo) {
        SmallNode splitResult = children.get(addTo).split();
        values.add(splitResult.median());
        children.replace(addTo, splitResult.left());
        children.insert(addTo+1,splitResult.right());
        this.isLeaf = false;
    }

    /**
     * Розбиття вузла на медіану й два піддерева
     * @return відповідна структура даних
     */
    private SmallNode split() {
        double median = this.values.median();
        BTreeNode left = new BTreeNode(values.leftToMedian(), children.left(), degree, this.isLeaf);
        BTreeNode right = new BTreeNode(values.rightToMedian(), children.right(), degree, this.isLeaf);
        return new SmallNode(median, left, right);
    }

    /**
     *
     * @return true, якщо вузол повний
     */
    private boolean isFull() {
        return values.isFull();
    }

    /**
     *
     * @param tab - рівень табуляцій
     * @return рядкове представлення вузла
     */
    public String asString(int tab) {
        return isLeaf ? leafAsString(tab) : innerAsString(tab);
    }

    /**
     *
     * @param tab - рівень табуляцій
     * @return рядкове представлення листового вузла
     */
    private String leafAsString(int tab) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("\t".repeat(tab)).append(values.get(i)).append("\n");
        }
        builder.append("\t".repeat(tab)).append("_\n");
        return builder.toString();
    }

    /**
     *
     * @param tab - рівень табуляцій
     * @return рядкове представлення внутрішнього вузла
     */
    private String innerAsString(int tab) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            builder.append("\t".repeat(tab)).append(values.get(i)).append("\n");
            builder.append(children.get(i).asString(tab+1));
        }
        builder.append(children.last().asString(tab+1));
        builder.append("\t".repeat(tab)).append("_\n");
        return builder.toString();
    }

    /**
     * Представлення вузла у вигляді масиву
     * @param doubles - масив для запису значень
     */
    public void toArray(List<Double> doubles) {
        if (isLeaf) { //якщо листовий вузол - просто записати значення
            for (double d : values) {
                doubles.add(d);
            }
        } else { //якщо внутрішній - записувати також значення з піддерев
            for (int i = 0; i < values.size(); i++) {
                children.get(i).toArray(doubles);
                doubles.add(values.get(i));
            }
            children.last().toArray(doubles);
        }
    }

    /**
     * Вилучення значення
     * @param v - значення, що необхідно вилучити
     */
    public void pop(double v) {

        if (values.contains(v)) { //якщо значення наявне в даному вузлі
            if (isLeaf) {
                this.values.popValue(v); //якщо лист - просто вилучаємо
            } else {
                popNotLeaf(v); //шукаємо у піддеревах значення, на яке можна його замінити
            }
        } else { //перехід до наступного вузла
            if (isLeaf) { //якщо лист - значення не знайдено
                throw new NoSuchElementException("Cannot find element " + v + " in the tree");
            }
            int removeFrom = values.position(v);
            if (children.get(removeFrom).isNotMinimum()) {
                children.get(removeFrom).pop(v); //якщо наступний не мінімальний - переходимо до нього
            } else {
                appendNode(removeFrom, v); //зливаємо з сусідом або позичаємо значення
            }
        }
    }

    /**
     * Пошук і вилучення мінімального значення у піддереві
     * @return мінімальне значення
     */
    public double popLeft() {
        if (isLeaf) {
            return values.popFirst(); //якщо лист - потрібне значення знайдено
        }
        if (!children.first().isNotMinimum()) {
            appendLeft(); //якщо наступний вузол мінімальний - доповнюємо або зливаємо
        }
        return children.first().popLeft(); //переходимо до наступного вузла
    }
    /**
     * Пошук і вилучення максимального значення у піддереві
     * @return максимальне значення
     */
    public double popRight() {
        if (isLeaf) {
            return values.popLast();
        }
        if (!children.last().isNotMinimum()) {
            appendRight();
        }
        return children.last().popRight();
    }

    /**
     * Доповнення вузла при пошуку мінімального значення
     */
    private void appendLeft() {
        BTreeNode node = this.children.first();
        if (this.children.get(1).isNotMinimum()) { //сусідній вузол не мінімальний - позичаємо значення
            node.values.add(this.values.first());
            this.values.insert(0, this.children.get(1).values.popFirst());
            node.children.addLast(this.children.get(1).children.popFirst());
        } else { //зливаємо з сусіднім вузлом
            mergeInsert(0);
            this.children.first().popLeft();
        }
    }

    /**
     * Доповнення вузла при пошуку максимуму
     */
    private void appendRight() {
        BTreeNode node = this.children.last();
        int lastIndex = node.values.size()-1;
        if (this.children.get(lastIndex).isNotMinimum()) { //сусідній вузол не мінімальний - позичаємо значення
            node.values.add(this.values.last());
            this.values.insert(lastIndex, this.children.last().values.popLast());
            node.children.addFirst(this.children.get(lastIndex-1).children.popLast());
        } else { //зливаємо з сусідом
            mergeInsert(lastIndex);
            this.children.last().popRight();
        }
    }

    /**
     * Доповнення або злиття вузла для переходу
     * @param removeFrom - номер піддерева для переходу
     * @param v - значення, яке потрібно вилучити
     */
    private void appendNode(int removeFrom, double v) {
        BTreeNode node = this.children.get(removeFrom);
        if (removeFrom != 0 && this.children.get(removeFrom-1).isNotMinimum()) {
            //сусід зліва не мінімальний - позичаємо значення
            node.values.add(this.values.get(removeFrom-1));
            this.values.insert(removeFrom-1, this.children.get(removeFrom-1).values.popLast());
            node.children.addFirst(this.children.get(removeFrom-1).children.popLast());
            this.children.get(removeFrom).pop(v);
        } else if (removeFrom != node.values.size() && this.children.get(removeFrom+1).isNotMinimum()) {
            //сусід справа не мінімальний - позичаємо значення
            node.values.add(this.values.get(removeFrom));
            this.values.insert(removeFrom, this.children.get(removeFrom+1).values.popFirst());
            node.children.addLast(this.children.get(removeFrom+1).children.popFirst());
            this.children.get(removeFrom).pop(v);
        } else if (removeFrom != 0) { //наявний сусід зліва - зливаємо
            mergeInsert(removeFrom-1);
            this.children.get(removeFrom).pop(v);
        } else if (removeFrom != node.values.size()) { //наявний сусід справа - зливаємо
            mergeInsert(removeFrom);
            this.children.get(removeFrom).pop(v);
        } else {
            throw new IllegalStateException("Cannot merge children nodes!");
        }
    }

    /**
     * Перехід до наступного вузла при видаленні
     * @param v - значення, що потрібно вилучити
     */
    private void popNotLeaf(double v) {
        int removeFrom = values.positionExact(v);
        if (simpleInsert(removeFrom)) { //можемо позичити значення
            mergeInsert(removeFrom); //інакше зливаємо з сусіднім вузлом
            this.children.get(removeFrom).pop(v);
        }
    }

    private void mergeInsert(int removeFrom) {
        double median = values.pop(removeFrom);
        BTreeNode node = new BTreeNode(degree, this.children.get(removeFrom).isLeaf,
                new SmallNode(median, children.get(removeFrom), children.get(removeFrom+1)));
        this.children.replace(removeFrom, node);
        this.children.remove(removeFrom+1);
    }

    /**
     *
     * @param removeFrom - місце наступного вузла у масиві піддерев
     * @return false, якщо вдалося вилучити значення; true - інакше
     */
    private boolean simpleInsert(int removeFrom) {
        if (children.get(removeFrom).isNotMinimum()) {
            this.values.insert(removeFrom, children.get(removeFrom).popRight());
        } else if (children.get(removeFrom +1).isNotMinimum()) {
            this.values.insert(removeFrom, children.get(removeFrom+1).popLeft());
        } else {
            return true;
        }
        return false;
    }

    /**
     *
     * @return true, якщо вузол не є мінімальним
     */
    private boolean isNotMinimum() {
        return values.isNotMinimum();
    }

    /**
     *
     * @return true, якщо вузол порожній
     */
    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    /**
     *
     * @return значення, у яке переходить корінь, якщо з нього видалено останнє значення
     */
    public BTreeNode toChild() {
        if (isLeaf)
            return new BTreeNode(degree);
        return this.children.first();
    }

    /**
     *
     * @param value - значення, яке потрібно знайти
     * @return true, якщо значення наявне у піддереві
     */

    public boolean get(double value) {
        if (values.contains(value))
            return true;
        if (isLeaf)
            return false;
        int moveTo = values.position(value);
        return children.get(moveTo).get(value);
    }

    /**
     *
     * @return кількість значень у піддереві з коренем у даному вузлі
     */
    public int fullSize() {
        int size = values.size();
        if (!isLeaf) {
            for (BTreeNode node : children) {
                size += node.fullSize();
            }
        }
        return size;
    }

    /**
     *
     * @return висота піддерева з коренем у даному вузлі
     */
    public int height() {
        BTreeNode node = this;
        int height = 0;
        while (!node.isLeaf) {
            node = node.children.first();
            height++;
        }
        return height;
    }
}
