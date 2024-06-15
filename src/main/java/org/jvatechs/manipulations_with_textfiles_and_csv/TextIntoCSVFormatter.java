package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.jvatechs.loggers.Loggable;
import org.jvatechs.manipupulations_with_files.TextIntoFileSaver;
import org.jvatechs.word_docx_to_txt_parser.TextFromFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.Stream;

/*
The class has written for input lines like:
"text1 – text2 – text3 – text4"
for converting that into .csv where's line model is:
"text3(text1);text4"
If you would change please refer into the methods in the class
 */

public class TextIntoCSVFormatter implements Loggable {
    private String CSVFormattedText = "";
    private static final String SOURCE_FILE = "word-to-txt.txt";
    private static final String DESTINATION_FILE = "finalCSV.csv";

    public void init() {
        TextFromFileReader textReader = new TextFromFileReader(SOURCE_FILE);
        TextIntoFileSaver textIntoFileSaver = new TextIntoFileSaver(DESTINATION_FILE);
        createCSVFormattedText(textReader.readFromTxt());
        textIntoFileSaver.saveIntoTxt(CSVFormattedText);

    }
    public void createCSVFormattedText(String text) {
        text = text.trim();
        Stream<String> lines = returnLine(text);
        lines.map(this::lineSplit).
                map(this::formatLine).
                map(this::convertArrayListIntoCSVline).
                forEach(this::createOneStringWithLines);
        getLogger().info("CSVFormattedText successfully created...");
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
