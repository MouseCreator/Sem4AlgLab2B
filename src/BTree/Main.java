package BTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Основний клас (запуск програми)
 */
public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<String> inputs = InputReader.readFile(); // зчитування значень
            BTree tree = new BTree(InputReader.readDegree()); //створення порожнього дерева
            OutputWriter.refresh();
            OutputWriter.log("Created tree");
            Mode mode = Mode.ADD;
            for (String s : inputs) {
                if (isCommand(s)) {
                    if (s.equalsIgnoreCase("add")) {
                        mode = Mode.ADD;
                    } else if (s.equalsIgnoreCase("pop")) {
                        mode = Mode.POP;
                    } else if (s.equalsIgnoreCase("get")) {
                        mode = Mode.GET;
                    } else if (s.equalsIgnoreCase("write")) {
                        OutputWriter.log( ("B-Tree:\n") + tree.asString());
                    }
                    continue;
                }
                try {
                    switch (mode) {
                        case ADD -> addToTree(tree, s);
                        case GET -> getFromTree(tree, s);
                        case POP -> removeFromTree(tree, s);
                    }
                } catch (Exception e) {
                    System.err.println("Cannot parse to double value of " + s);
                }
            }
            OutputWriter.write(tree.asString()); //запис дерева у файл
        } catch (IOException e) {
            System.err.println("Cannot open a file");
        }
    }

    private static void getFromTree(BTree tree, String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Cannot parse value of " + s);
            return;
        }
        OutputWriter.log( tree.get(d) ? "Found " + d : "Didn't find " + d);
    }

    private static void addToTree(BTree tree, String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Cannot parse value of " + s);
            return;
        }
        tree.add(d);
        OutputWriter.log("Added " + d);
    }
    private static void removeFromTree(BTree tree, String s) {
        double d;
        try {
            d = Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Cannot parse value of " + s);
            return;
        }
        try {
            tree.pop(d);
            OutputWriter.log("Popped " + d);
        } catch (NoSuchElementException e) {
            OutputWriter.log("No element " + d + " in the tree");
        }
    }
    private static boolean isCommand(String s) {
        return s.equalsIgnoreCase("add") ||
                s.equalsIgnoreCase("pop") || s.equalsIgnoreCase("get") ||
                s.equalsIgnoreCase("write");
    }

}
