package Classic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас для виводу у текстовий файл
 */
public class OutputWriter {
    public static void write(String message) {
        try {
            String outputFile = "data/output.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
            writer.write(message); //записати повідомлення
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
