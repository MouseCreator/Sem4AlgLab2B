package Classic;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Double> doubles = InputReader.readFile();
            System.out.println(doubles);
            BTree tree = new BTree(InputReader.readDegree());
            for (double d : doubles) {
                tree.add(d);
            }
            OutputWriter.write(tree.asString());
        } catch (IOException e) {
            System.err.println("Cannot open a file");
        }
    }

}
