package ru.academits.kharitonov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("fileCsv.csv"));
             PrintWriter writer = new PrintWriter("outputFileCsv.html")) {
            writer.println("<table>");

            String semicolon = ";";
            String quotes = "\"";
            String duoQuotes = "\"\"";

            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();

                writer.print("<td>");
                if (fileLine.contains(semicolon)) {
                    fileLine = fileLine.replace(semicolon, "</td><td>");
                    writer.print(fileLine);
                }
                writer.println("</td>");
            }

            writer.print("</table>");
        }
    }
}
