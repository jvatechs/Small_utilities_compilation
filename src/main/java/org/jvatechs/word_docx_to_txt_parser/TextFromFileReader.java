package org.jvatechs.word_docx_to_txt_parser;

import org.jvatechs.loggers.Loggable;
import org.jvatechs.manipulations_with_textfiles_and_csv.TextFileLineByLineReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.TextIntoCSVFormatter;
import org.jvatechs.manipulations_with_textfiles_and_csv.TextSaverIntoTextFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextFromFileReader implements Loggable {
//    private static final Logger LOGGER = LoggerFactory.getLogger("StringFromFileReader.class");
    private static final String SOURCE_FILE = "word-to-txt.txt";
    private static final String DESTINATION_FILE = "finalCSV.csv";
    private String sourceFile;

    public TextFromFileReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void init() {
//        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
//        String text = readFromTxt();
//        String csvFormattedText = textIntoCSVFormatter.createCSVFormattedText(text);
//        new TextSaverIntoTextFile().saveTextToFile(csvFormattedText, DESTINATION_FILE);

//        textIntoCSVFormatter.saveResultIntoFile(DESTINATION_FILE);
    }
    public String readFromTxt() {
        StringBuilder text = new StringBuilder();
        TextFileLineByLineReader lineReader = new TextFileLineByLineReader();
        lineReader.setFilename(SOURCE_FILE);
        lineReader.processFileWithFunction(line -> {
            text.append(line);
            text.append('\n');
            return null;
        });
        getLogger().info("Successfully read from file...");
        return text.toString();
    }
}
