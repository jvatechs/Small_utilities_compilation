package org.jvatechs.manipulations_with_textfiles_and_csv.text_savers;

import org.jvatechs.csv_custom_utils.exceptions.FilenameNotSettedException;
import org.jvatechs.loggers.Loggable;

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
        try (PrintWriter printWriter = new PrintWriter(filename)){
            if (filename == null) {
                throw new FilenameNotSettedException();
            }
            printWriter.println(text);
            getLogger().info("Text from String successfully saved into " + filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        } catch (FilenameNotSettedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveArrayStringIntoFile(String[] words) {
        try (PrintWriter printWriter = new PrintWriter(filename)) {
            for (String word : words) {
                printWriter.println(word);
                getLogger().info("Text from Array successfully saved into " + filename);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTwoListsPairedIntoFile(ArrayList<String> original, ArrayList<String> translated) {
        try (PrintWriter printWriter = new PrintWriter(filename)){
            String delimiter = ";";
            for (int i = 0; i < original.size(); i++) {
                printWriter.println(original.get(i) + delimiter + translated.get(i) );
            }
            getLogger().info("Text from arrays successfully saved into " + filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File is not found!");
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
