package org.jvatechs.word_docx_to_txt_parser;

import org.jvatechs.manipulations_with_textfiles_and_csv.TextFileLineByLineReader;
import org.jvatechs.manipulations_with_textfiles_and_csv.TextIntoCSVFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class DocxToTxtParser {
    private static final Logger LOGGER = LoggerFactory.getLogger("DocxToTxtParser.class");
    private static final String SOURCE_FILE = "word-to-txt.txt";
    private static final String DESTINATION_FILE = "finalCSV.csv";

    public void init() {
        TextIntoCSVFormatter textIntoCSVFormatter = new TextIntoCSVFormatter();
        String text = readFromTxt();
        textIntoCSVFormatter.createCSVFormattedText(text);
        textIntoCSVFormatter.saveResultIntoFile(DESTINATION_FILE);
    }
    private String readFromTxt() {
        StringBuilder text = new StringBuilder();
        TextFileLineByLineReader lineReader = new TextFileLineByLineReader();
        lineReader.setFilename(SOURCE_FILE);
        lineReader.processFileWithFunction(line -> {
            text.append(line);
            text.append('\n');
            return null;
        });
        LOGGER.info("Successfully read from file...");
        return text.toString();
    }


}
