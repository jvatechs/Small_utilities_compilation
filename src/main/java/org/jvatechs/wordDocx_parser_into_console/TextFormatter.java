package org.jvatechs.wordDocx_parser_into_console;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/*
The class has written for input lines like:
"text1 – text2 – text3 – text4"
for converting that into .csv where's line model is:
"text3(text1);text4"
If you would change please refer into the methods in the class
 */

public class TextFormatter {
    private String finalText = "";
    void CSVCreator(String text) {
        text = text.trim();
        Stream<String> lines = returnLine(text);
        lines.map(this::lineSplit).
                map(this::formateLine).
                map(this::convertArrayListIntoCSVline).
                forEach(this::createOneStringWithLines);
    }
    void saveResultIntoFile() {
        try (PrintWriter printWriter = new PrintWriter("finalCSV.csv")) {
            printWriter.print(finalText);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private Stream<String> returnLine(String text) {
        return text.lines();
    }
    private String[] lineSplit(String line) {
        return line.split("\\s*–\\s*");
    }

    private ArrayList<String> formateLine(String[] line) {
        ArrayList<String> formatted = new ArrayList<>();
        formatted.add(line[2] + "(" + line[0] +")");
        formatted.add(line[3]);
        return formatted;
    }

    private String convertArrayListIntoCSVline(ArrayList<String> line) {
        return line.get(0) + ";" + line.get(1);
    }
    private void createOneStringWithLines(String line) {
        finalText = finalText + '\n' + line;
    }
}
