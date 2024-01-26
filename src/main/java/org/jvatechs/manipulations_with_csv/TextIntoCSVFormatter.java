package org.jvatechs.manipulations_with_csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
The class has written for input lines like:
"text1 – text2 – text3 – text4"
for converting that into .csv where's line model is:
"text3(text1);text4"
If you would change please refer into the methods in the class
 */

public class TextIntoCSVFormatter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextIntoCSVFormatter.class.getName());
    private String CSVFormattedText = "";
    public void createCSVFormattedText(String text) {
        text = text.trim();
        Stream<String> lines = returnLine(text);
        lines.map(this::lineSplit).
                map(this::formatLine).
                map(this::convertArrayListIntoCSVline).
                forEach(this::createOneStringWithLines);
        LOGGER.info("CSVFormattedText successfully created...");
    }
    public void saveResultIntoFile() {
        String filename = "finalCSV.csv";
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.print(CSVFormattedText);
            LOGGER.info("CSVFormattedText successfully created...");
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

    private ArrayList<String> formatLine(String[] line) {
        ArrayList<String> formatted = new ArrayList<>();
        formatted.add(line[2] + "(" + line[0] +")");
        formatted.add(line[3]);
        return formatted;
    }

    private String convertArrayListIntoCSVline(ArrayList<String> line) {
        return line.get(0) + ";" + line.get(1);
    }
    private void createOneStringWithLines(String line) {
        CSVFormattedText = CSVFormattedText + '\n' + line;
    }
}
