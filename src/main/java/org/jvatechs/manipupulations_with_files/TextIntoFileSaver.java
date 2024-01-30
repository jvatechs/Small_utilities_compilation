package org.jvatechs.manipupulations_with_files;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextIntoFileSaver {

    private final String filename;


    public TextIntoFileSaver(String filename) {
        this.filename = filename;
    }

    public void saveIntoTxt(String text) {
        try (PrintWriter printWriter = new PrintWriter(filename)){
            printWriter.println(text);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        }
    }
}
