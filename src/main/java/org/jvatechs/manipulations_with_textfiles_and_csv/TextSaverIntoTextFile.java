package org.jvatechs.manipulations_with_textfiles_and_csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextSaverIntoTextFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextIntoCSVFormatter.class.getName());
    public void saveTextToFile(String text, String filename) {
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.print(text);
            LOGGER.info(filename + " successfully created...");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
