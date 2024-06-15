package org.jvatechs.manipupulations_with_files;

import org.jvatechs.loggers.Loggable;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextIntoFileSaver implements Loggable {
    private final String filename;
    public TextIntoFileSaver(String filename) {
        this.filename = filename;
    }

    public void saveIntoTxt(String text) {
        try (PrintWriter printWriter = new PrintWriter(filename)){
            printWriter.println(text);
            getLogger().info("Text successfully saved into " + filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        }
    }
}
