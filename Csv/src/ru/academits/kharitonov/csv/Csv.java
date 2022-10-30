package ru.academits.kharitonov.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Csv {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("fileCsv.csv"));
             PrintWriter writer = new PrintWriter("outputFileCsv.html")) {
            writer.println("<table>");

            String semicolon = ";";
            String quotes = "\"";
            String duoQuotes = "\"\"";

            while (scanner.hasNextLine()) {
                String fileLine = scanner.nextLine();

                fileLine = fileLine.replace("&", "&amp");
                fileLine = fileLine.replace(">", "&gt");
                fileLine = fileLine.replace("<", "&lt");

                String[] split = fileLine.split(semicolon);

                if (!fileLine.contains(quotes) || !split[0].contains(quotes)) {
                    writer.println("<tr>");
                }

                for (int i = 0; i < split.length; i++) {
                    if (split[i].startsWith(quotes) && split[i].endsWith(quotes)) {
                        if (i == 0) {
                            writer.println("<tr>");
                        }

                        split[i] = split[i].substring(quotes.length(), split[i].length() - quotes.length());
                        split[i] = split[i].replace(duoQuotes, quotes);

                        writer.print("<td>");
                        writer.print(split[i]);
                        writer.print("</td>");
                    } else if (split[i].startsWith(quotes)) {
                        if (i == 0) {
                            writer.println("<tr>");
                        }

                        writer.print("<td>");
                        writer.print(split[i].substring(quotes.length()));
                        writer.println("<br/>");
                    } else if (split[i].endsWith(quotes)) {
                        writer.print(split[i].substring(0, split[i].length() - quotes.length()));
                        writer.print("</td>");
                    } else {
                        writer.print("<td>");
                        writer.print(split[i]);
                        writer.print("</td>");
                    }
                }

                if (!fileLine.contains(quotes) || !split[0].contains(quotes)) {
                    writer.println("</tr>");
                } else if (fileLine.endsWith(quotes)) {
                    writer.println("</tr>");
                }
            }

            writer.println("</tr>");
            writer.print("</table>");
        }
    }
}