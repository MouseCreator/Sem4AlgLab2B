package Classic;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Основний клас (запуск програми)
 */
public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Double> doubles = InputReader.readFile(); // зчитування значень
            BTree tree = new BTree(InputReader.readDegree()); //створення порожнього дерева
            for (double d : doubles) {
                tree.add(d); //додавання значень до дерева
            }
            OutputWriter.write(tree.asString()); //запис дерева у файл
        } catch (IOException e) {
            System.err.println("Cannot open a file");
        }
    }

}
