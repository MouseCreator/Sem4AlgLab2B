package BTree;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас для виводу у текстовий файл
 */
public class OutputWriter {
    private static final String outputFile = "data/output.txt"; //вивідний файл
    private static final String loggerFile = "data/logger.txt"; //логуючий файл

    /**
     * Запис у файл для виведення
     * @param message - повідомлення
     */
    public static void write(String message) {
        writeTo(message, outputFile);
    }

    /**
     * Запис у файл для логування
     * @param message - повідомлення
     */
    public static void log(String message) {
        writeTo(message, loggerFile);
    }

    private static void writeTo(String message, String file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(message); //записати повідомлення
            writer.newLine(); //додати новий рядок
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Оновлення файлів
     */
    public static void refresh() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(loggerFile, false));
            writer.close();
            writer2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
