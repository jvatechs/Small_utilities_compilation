package org.jvatechs.manipulations_with_textfiles_and_csv.text_savers;

import org.jvatechs.loggers.Loggable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TextIntoFileSaver implements Loggable {
    private String filename;

    public TextIntoFileSaver() {
    }

    public TextIntoFileSaver(String destination) {
        this.filename = destination;
    }

    public void saveIntoFile(String text) {
        if (filename == null) {
            getLogger().info("Filename didn't set. Please set filename or use constructor with parameter.");
        }
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            printWriter.println(text);
            getLogger().info("Text from String successfully saved into " + filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        }
    }

    public void saveArrayStringIntoFile(String[] words) {
        if (filename == null) {
            getLogger().info("Filename didn't set. Please set filename or use constructor with parameter.");
        } else {
            File file = new File(filename);
            file.getParentFile().mkdirs();

            try (PrintWriter printWriter = new PrintWriter(file)) {
                for (String word : words) {
                    printWriter.println(word);
                    getLogger().info("Text from Array successfully saved into " + filename);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveTwoListsPairedIntoFile(ArrayList<String> original, ArrayList<String> translated) {
        if (filename == null) {
            getLogger().info("Filename didn't set. Please set filename or use constructor with parameter.");
        } else {
            File file = new File(filename);
            file.getParentFile().mkdirs();

            try (PrintWriter printWriter = new PrintWriter(file)) {
                String delimiter = ";";
                for (int i = 0; i < original.size(); i++) {
                    printWriter.println(original.get(i) + delimiter + translated.get(i));
                }
                getLogger().info("Text from arrays successfully saved into " + filename);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File is not found!");
            }
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
